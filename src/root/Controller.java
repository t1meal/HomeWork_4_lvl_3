package root;

public class Controller implements Runnable {

    private int status = 0;
    private Object monitor = new Object();

    @Override
    public void run() {
        new Thread(() -> {
            synchronized (monitor) {
                int iterator = 0;
                while (iterator != 5) {
                    if (status == 0) {
                        System.out.print("А");
                        iterator++;
                        status = 1;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                int iterator = 0;
                while (iterator != 5) {
                    if (status == 1) {
                        System.out.print("B");
                        iterator++;
                        status = 2;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (monitor) {
                int iterator = 0;
                while (iterator != 5) {
                    if (status == 2) {
                        System.out.println("C");
                        iterator++;
                        status = 0;
                        monitor.notifyAll();
                    } else {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

}
