package com.hw.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author  
 * @date 2020/11/21 11:28 下午
 */
public abstract class CusDelay {
    private final Logger logger = LoggerFactory.getLogger(CusDelay.class);

    protected DelayQueue<DelayTask> delayQueue ;

    protected ThreadPoolExecutor executor;


    protected void runTask() {
        while (true) {
            try {

                DelayTask task = delayQueue.take();
                processTask(task);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    protected abstract void processTask(DelayTask delayTask);

    public void addTask(DelayTask task) {
        logger.info("加入延时任务：{}", task);
        delayQueue.put(task);
        System.out.println(delayQueue.size());
    }
    public void addTask(String taskId,String taskName,long expire) {
        DelayTask delayTask = new DelayTask(taskId, taskName, expire);
        logger.info("加入延时任务：{}", delayTask);
        System.out.println("加入延迟任务"+delayTask);
        delayQueue.put(delayTask);
    }

    public boolean removeTask(DelayTask task) {
        logger.info("取消延时任务：{}", task);
        return delayQueue.remove(task);
    }
    public boolean remove(String taskid) {
        return removeTask(new DelayTask(taskid, 0));
    }
}
