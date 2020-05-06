package thread;

class MyTask implements Runnable {
        private int taskNum;

    public MyTask() {
    }

    public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        myTask.run();
        Thread th = new Thread();
        th.start();
    }
}