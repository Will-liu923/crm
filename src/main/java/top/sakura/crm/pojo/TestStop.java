package top.sakura.crm.pojo;

/**
 * @author leoLW
 * @Description
 * @create 2021-08-10 10:40
 */
public class TestStop implements Runnable {
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println(i++);
        }
    }

    public void stop() {
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop ts = new TestStop();
        new Thread(ts).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 900) {
                ts.stop();
                System.out.println("it is time to stop!");
            }
        }
    }
}
