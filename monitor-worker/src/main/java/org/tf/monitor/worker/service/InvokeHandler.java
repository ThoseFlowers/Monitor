package org.tf.monitor.worker.service;

/**
 * @author hezhiyu on 15/1/18.
 */
public class InvokeHandler {

    private BucketQueue bucketQueue;
    private long lastInvokeTime;
    private int timeWidth;

    public InvokeHandler(int bucketSize, int timeWidth) {
        this.bucketQueue = new BucketQueue(bucketSize);
        this.timeWidth = timeWidth;
    }

    public void updateInvoke(int count, long currentTimeSec) {
        int shifted = (int)((currentTimeSec - lastInvokeTime) / timeWidth);
        System.out.println(String.format("currentTimeSec: %s lastInvokeTime: %s shifted: %s", currentTimeSec, lastInvokeTime, shifted));
        bucketQueue.shifted(shifted);
        if (shifted == 0) {
            bucketQueue.update(count);
        } else {
            bucketQueue.add(count);
        }
        lastInvokeTime = currentTimeSec;
    }

    public long count() {
        return bucketQueue.getSum();
    }
}