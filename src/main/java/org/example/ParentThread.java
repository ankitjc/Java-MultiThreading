package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParentThread implements Runnable {

    @Override
    public void run() {
        System.out.println("--- Inside Parent Thread ---- ");

        // Create a thread pool for sub-tasks
        int subTaskThreads = 3;
        ThreadPoolExecutor subTaskExecutor = new ThreadPoolExecutor(
                subTaskThreads,
                subTaskThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );
        List<String> processes = List.of("A", "B", "C", "D", "E");

        for(int i=0; i<5; i++) {
            ChildThread ct = new ChildThread(processes.get(i));
            subTaskExecutor.execute(ct);
        }

        System.out.println("--- Exiting Parent Thread ---- ");

    }
}
