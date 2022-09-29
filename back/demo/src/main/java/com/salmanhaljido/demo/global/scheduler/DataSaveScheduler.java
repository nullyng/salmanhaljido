package com.salmanhaljido.demo.global.scheduler;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DataSaveScheduler implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("스케줄러 실행");
    }
}
