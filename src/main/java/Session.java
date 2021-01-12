public abstract class Session extends Thread {

    protected boolean doStop = false;

    public synchronized void doStop(){
        this.doStop = true;
    }

    protected synchronized boolean keepRunning(){
        return this.doStop == false;
    }

    public abstract void run ();

}
