package com.yarish.awsmonitor;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.yarish.awsmonitor.model.PrettyPrinter;



public class JobScheduler {

  public static void main(String[] args) {
    JobDetail job = JobBuilder.newJob(AWS_Job.class).withIdentity("aws_job").build();
    int intervalInHours = new PrettyPrinter().getIntervalInHours();

    Trigger trigger =
        TriggerBuilder.newTrigger()
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(intervalInHours).repeatForever())
            .build();

    /*
     * Trigger trigger = TriggerBuilder.newTrigger()
     * .withSchedule(SimpleScheduleBuilder.simpleSchedule
     * ().withIntervalInMinutes(1).repeatForever()).build();
     */
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    try {
      Scheduler scheduler = schedulerFactory.getScheduler();
      scheduler.start();
      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }


  }

}
