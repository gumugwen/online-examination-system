package com.hw.task;

import com.hw.mapper.ExamMapper;
import com.hw.model.vo.ExamSearchVo;
import com.hw.service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * @author  
 * @date 2020/11/21 11:17 下午
 */
@Component
public class ExamDelay extends CusDelay {

    private final Logger log = LoggerFactory.getLogger(ExamDelay.class);

    private static final String BEGIN="begin";
    private static final String END="end";
    @Autowired
    private ExamMapper examMapper;

    @PostConstruct
    public void run() {
        delayQueue = new DelayQueue<>();
        executor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        executor.execute(() -> {
            runTask();
        });
    }

    @Override
    protected void processTask(DelayTask delayTask) {
        String taskId = delayTask.getTaskId();
        log.info("执行任务:{}",taskId);
        System.out.println("执行任务："+delayTask.getName()+"，任务ID："+taskId);
        String[] split = taskId.split("-");
        if (split[1].equals(BEGIN)){
           examMapper.updateStatusById(Integer.parseInt(split[0]),3);
        }else if (split[1].equals(END)){
            examMapper.updateStatusById(Integer.parseInt(split[0]),4);
        }
    }
}
