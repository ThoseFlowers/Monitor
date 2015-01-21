package org.tf.monitor.worker.service;

/**
 * @author hezhiyu on 15/1/18.
 */
public class InvokeHandler {

    private ConveyorQueue conveyorQueue;
    private long lastInvokeTime;
    private int timeWidth;

    public InvokeHandler(int bucketSize, int timeWidth) {
        this.conveyorQueue = new ConveyorQueue(bucketSize);
        this.timeWidth = timeWidth;
    }

    public void updateInvoke(int count, long currentTimeSec) {
        update(currentTimeSec);
        conveyorQueue.add(count);
    }

    public long invokeCount(long currentTimeSec) {
        update(currentTimeSec);
        return conveyorQueue.getTotalSum();
    }

    private void update(long currentTimeSec) {
        int shifted = (int)((currentTimeSec - lastInvokeTime) / timeWidth);
        conveyorQueue.shifted(shifted);
        lastInvokeTime = currentTimeSec;
    }
}