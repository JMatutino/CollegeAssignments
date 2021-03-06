public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() {
        while(value <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        value--;
    }

    public synchronized void V() {
        value++;
        this.notify();
    }


}
