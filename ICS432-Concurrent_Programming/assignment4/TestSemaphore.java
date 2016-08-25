public class TestSemaphore {

  MyThread producer, consumer;
  public Semaphore semaphore;

  public static void main(String args[]) {
    TestSemaphore t = new TestSemaphore();
  }

  public TestSemaphore() {
    semaphore = new Semaphore(0);
    producer = new MyThread(semaphore,true); 
    consumer = new MyThread(semaphore,false); 
    producer.start();
    consumer.start();
    try {
      producer.join();
      consumer.join();
    } catch (InterruptedException e) {
      // do nothing
    }
    System.exit(0);
  }

  private class MyThread extends Thread {
    private Semaphore semaphore;
    private boolean producer;

    public MyThread(Semaphore s, boolean p) {
      semaphore = s; 
      producer = p;
    }
  
    public void run() {

      for (int counter=0; counter < 5; counter++) {
        if (producer) {
          System.out.println("Producer: Producing a resource!");
          semaphore.V();
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // do nothing
          }
        } else {
          System.out.println("Consumer: Waiting for a resource...");
          semaphore.P();
          System.out.println("Consumer: Got it!");
        }
      }
    }
  }
}

