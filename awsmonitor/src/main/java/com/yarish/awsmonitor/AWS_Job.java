package com.yarish.awsmonitor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AWS_Job implements Job {
  private static final Logger LOG = LoggerFactory.getLogger(AWS_Job.class);

  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    LOG.debug("AWS_Job begins ...");
    new App().main(null);
    LOG.debug("AWS_Job begins ...");
  }

}
