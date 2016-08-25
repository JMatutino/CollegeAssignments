public class FlipFlop {
  Long flipSecs;
  Long flopSecs;

  public FlipFlop(long flipSecs,Long flopSecs) {
    this.flipSecs = flipSecs;
    this.flopSecs = flopSecs;
  }

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
                System.err.print("flop\n");
                try {
                    Thread.sleep(secs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

  public static void main(String[] args) {
    FlipFlop flipFlop = new FlipFlop(Long.parseLong(args[0]),Long.parseLong(args[1]));

    Thread flipThread = new Thread(new Flip(flipFlop.flipSecs));
    Thread flopThread = new Thread(new Flop(flipFlop.flopSecs));

    flipThread.start();
    flopThread.start();
  }
}
