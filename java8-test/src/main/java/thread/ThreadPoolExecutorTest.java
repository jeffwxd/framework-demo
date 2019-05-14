package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
 public static void main(String[] args) {  
  ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
  scheduledThreadPool.scheduleAtFixedRate(new Runnable() {  
   public void run() {  
    System.out.println("delay 1 seconds, and excute every 3 seconds");  
   }  
  }, 1, 3, TimeUnit.SECONDS);
 }  
}

