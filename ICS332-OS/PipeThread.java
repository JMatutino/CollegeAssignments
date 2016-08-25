import java.io.*;

public class PipeThread implements Runnable{
    private InputStream stdout;
    private OutputStream stdin;
    private BufferedReader br;
    private BufferedWriter bw;
    private PrintStream ps;

    public PipeThread (InputStream stdout, OutputStream stdin) {
        this.stdout = stdout;
        this.stdin = stdin;
        this.br = new BufferedReader(new InputStreamReader(stdout));
        this.bw = new BufferedWriter(new OutputStreamWriter(stdin));
        this.ps = new PrintStream(stdin);
    }

    @Override
    public void run() {
        try {
            String line = br.readLine();
            while (line != null) {
                ps.println(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stdout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
