package com.example.Flying_Tiger.Class;
import com.example.Flying_Tiger.Mapper.ExclusiveWriteLockManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class ReleaseTask {
    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    long id;
    long hcpid;
    Runnable release;
    ScheduledFuture<?> releaseHandle;
    public ReleaseTask(long id, long hcpid) {
        this.id = id;
        this.hcpid = hcpid;
        System.out.println("beep");
        release = new Runnable() {
            public void run() {
                System.out.println("time out!");
                ExclusiveWriteLockManager.getInstance().releaseLock(id, hcpid);
                scheduler.shutdown();
            }
        };
        releaseHandle =
                scheduler.schedule(release, 1800, SECONDS);

    }


    public void cancelTask(){
        releaseHandle.cancel(true);
        System.out.println("cancel timer!");
        scheduler.shutdown();
    }

   /* public static void main(String[] args){
        ExclusiveWriteLockManager.getInstance().acquireLock(1,1);
        ReleaseTask releaseTask = new ReleaseTask(1,1);
        releaseTask.cancelTask();
    }*/
}



