package quartz;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest04 {
    private Timer timer;
    public long start;   
    
    public TimerTest04(){
        this.timer = new Timer();
        start = System.currentTimeMillis();
    }
    
    public void timerOne(){
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("timerOne invoked01 ,the time:" + (System.currentTimeMillis() - start));
                try {
                    //线程休眠3000
                    System.out.println(1/0);
                    Thread.sleep(4000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
    
    public void timerTwo(){
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("timerOne invoked ,the time:" + (System.currentTimeMillis() - start));
            }
        }, 3000);
    }
    
    public static void main(String[] args) throws Exception {
        TimerTest04 test = new TimerTest04();
        
        test.timerOne();
        test.timerTwo();
    }
}