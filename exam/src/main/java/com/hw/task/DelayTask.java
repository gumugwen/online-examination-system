package com.hw.task;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务
 *
 * @author  
 * @date 2020/11/21 10:48 下午
 */
public class DelayTask implements Delayed {
    private final String taskId;
    private String name;
    private final long expire;

    public DelayTask(String taskId, long expire) {
        this(taskId, "task->" + taskId, expire);
    }

    public DelayTask(String taskId, String taskName, long expire) {
        this.taskId = taskId;
        this.name = taskName;
        this.expire = expire + System.currentTimeMillis();
    }


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long delta = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (int) delta;
    }

    public String getTaskId() {
        return taskId;
    }

    public long getExpire() {
        return expire;
    }

    public String getName() {
        return name == null ? "task -> " + taskId : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
