package org.tf.monitor.worker.main;

import org.tf.monitor.worker.service.InvokeHandler;

/**
 * @author hezhiyu on 15/1/18.
 */
public class MinuteHourCounter {

    private static final MinuteHourCounter counter = new MinuteHourCounter();

    private InvokeHandler minuteHandler;
    private InvokeHandler hourHandler;

    private  MinuteHourCounter() {
        this.minuteHandler = new InvokeHandler(60, 1);
        this.hourHandler = new InvokeHandler(60, 60);
    }

    public static MinuteHourCounter getMinuteHourCounter() {
        return counter;
    }

    public void add(int count, long timeSec) {
        minuteHandler.updateInvoke(count, timeSec);
        hourHandler.updateInvoke(count, timeSec);
    }

    public long minuteCount(long timeSec) {
        return minuteHandler.invokeCount(timeSec);
    }

    public long hourCount(long timeSec) {
        return hourHandler.invokeCount(timeSec);
    }
}