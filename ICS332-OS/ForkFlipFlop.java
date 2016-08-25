import java.io.IOException;
import java.io.InputStream;

public class ForkFlipFlop {
  private static class Flip implements Runnable {
    Long secs;
    public Flip(Long secs) {
      this.secs = secs;
    }

    @Override
    public void run() {
      for(int i = 0;i<20;i++) {
        System.out.print("flip\n");
        try {
          Thread.sleep(secs);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static class Flop implements Runnable {
    Long secs;
    public Flop(Long secs) {
      this.secs = secs;
    }

    @Override
    public void run() {
      for(int i = 0;i<20;i++) {
        System.out.print("flop\n");
        try {
          Thread.sleep(secs);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String command = String.format("java FlipFlop %s %s", args[0], args[1]);
    String[] tokens = command.split("[ \t\n]+");

    ProcessBuilder pb = new ProcessBuilder(tokens);
    Process p = pb.start();

    Thread pipeErr = new Thread(new PipeThread(p.getErrorStream(),System.err));
    Thread pipeOut = new Thread(new PipeThread(p.getInputStream(),System.out));
    pipeErr.start();
    pipeOut.start();
  }
}
