public class LearningSession extends Session {

    @Override
    public void run() {
        for (int i = 0; i < 20;i ++){
            System.out.println("LeariningSession Running " + i);
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
