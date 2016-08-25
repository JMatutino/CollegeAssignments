import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Jsh {
  private String command;
  private String[] tokens;

  private ProcessBuilder pb;
  private Process p;

  private InputStream sstderr;
  private InputStream sstdout;
  private OutputStream sstdin;

  private BufferedReader stderr;
  private BufferedReader stdout;
  private BufferedWriter stdin;

  private String startWorkingDir;
  private String currentDir;

  private ArrayList<String> cmdList;
  private int curCmdIndex;
  private int historySize;


  public Jsh(int historySize) {
    pb = null;
    p = null;

    sstderr = null;
    sstdout = null;
    sstdin = null;

    stderr = null;
    stdout = null;
    stdin = null;

    cmdList = new ArrayList<String>();
    curCmdIndex = 0;
    this.historySize = historySize;

    currentDir = System.getProperty("user.dir");
    startWorkingDir = currentDir;
  }

  public static void main(String[] args) {
    System.out.print("Jsh> ");
    Jsh jsh = new Jsh(10);
    Scanner scan = new Scanner(System.in);
    while (scan.hasNextLine()) {
      if (jsh.getCommand(scan)) {
        jsh.runProcess(jsh);
      }
      System.out.print("Jsh> ");
    }
  }

  public void addHistory() {
    if (cmdList.size() >= historySize) {
      cmdList.add(command);
      cmdList.remove(0);
    } else {
      cmdList.add(command);
      //curCmdIndex++;
    }
  }

  public void runProcess(Jsh jsh) {
    if (jsh.makeProcess()) {
      addHistory();
      jsh.workProcess();
    }
    return;
  }

  //Returns true when NOT ctrl+d
  public boolean getCommand(Scanner scan) {
    command = scan.nextLine();

    command = command.trim();
    tokens = command.split("[ \t\n]+");

    return !command.equals("");
  }

  public boolean makeProcess() {
    if (tokens.length <= 0) {
      System.err.println("ERROR:Zero Tokens");
      return false;
    }
    pb = new ProcessBuilder(tokens);
    pb.directory(new File(currentDir));

    if (tokens[0].equals("history")) {
      addHistory();
      int i = 1;
      for (String command : cmdList) {
        System.out.println(i + "    " + command);
        i++;
      }
      return false;
    }

    if(tokens[0].startsWith("!")) {
      String bangNum = tokens[0].substring(1);
      int num = Integer.parseInt(bangNum);
      String wantCommand;

      if(num <= 0 || num > cmdList.size()) {
        System.err.println("History Error");
        return false;
      }
      command = cmdList.get(num - 1);
      System.out.println("Command executed: " + command);

      //NEED TO CHECK FOR HISTORY

      //Need to input new commands
      pb = new ProcessBuilder(command.split("[ \t\n]+"));
      pb.directory(new File(currentDir));
    }

    if (tokens[0].equals("cd")) {
      if (tokens.length == 1) {
        //Just cd, go back to start directory
        currentDir = startWorkingDir;
      } else if (tokens.length == 2) {
        try {
          String attemptDir = currentDir + "/" + tokens[1];
          File newFile = new File(attemptDir);
          File newPath = new File(tokens[1]);
          if (!tokens[1].startsWith("/") && newFile.isDirectory()) {
            currentDir = newFile.getCanonicalPath();
            addHistory();
          } else if (tokens[1].startsWith("/") && newPath.isDirectory()) {
            //If type in full path go to full path instead
            currentDir = newPath.getCanonicalPath();
            addHistory();
          } else {
            if (tokens[1].startsWith("/")) {
              //Invalid full path to directory
              System.err.println("Invalid Directory: " + tokens[1]);
            } else {
              System.err.println("Invalid Directory: " + attemptDir);
            }
          }
        } catch (IOException ioe) {
          System.err.println("IO Exception with cd command");
        }
      } else {
        //If cd getting wrong number of arguments
        System.err.println("Invalid syntax");
      }
      //Do not execute command -> get new command
      return false;
    }

    try {
      //System.out.println("IN TRY BLOCK!");
      p = pb.start();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println("Cannot start process");
      return false;
    }
    return true;
  }

  public boolean workProcess() {
    //System.err.println("Establishing streams to communicate with process...");
    sstderr = p.getErrorStream();
    sstdout = p.getInputStream();
    sstdin = p.getOutputStream();

    stderr = new BufferedReader(new InputStreamReader(sstderr));
    stdout = new BufferedReader(new InputStreamReader(sstdout));
    stdin = new BufferedWriter(new OutputStreamWriter(sstdin));

    Thread pipeErr = new Thread(new PipeThread(p.getErrorStream(), System.err));
    Thread pipeOut = new Thread(new PipeThread(p.getInputStream(), System.out));
    pipeErr.start();
    pipeOut.start();

    try {
      int returnValue = p.waitFor();
      //System.err.println("Process completed with return value" + returnValue);
    } catch (InterruptedException e) {
      // Do nothing for now...this is about threads and will
      // be explained later.
    }

    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      // Do nothing for now...this is about threads and will
      // be explained later
    }

    p.destroy();

    return true;
  }
}
