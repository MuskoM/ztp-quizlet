public class TestSession extends Session {


    @Override
    public void run() {

        while (keepRunning()){

            for (int i = 0; i < 20;i ++){
                System.out.println("EditSession Running " + i);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
