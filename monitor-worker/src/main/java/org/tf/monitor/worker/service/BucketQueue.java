package org.tf.monitor.worker.service;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author hezhiyu on 15/1/18.
 */
public class BucketQueue {

    private Deque<Long> bucketQue;
    private long bucketSize;
    private long sum;

    public BucketQueue(int bucketSize) {
        this.bucketQue = new ArrayDeque<>(bucketSize);
        this.bucketSize = bucketSize;
    }

    public void shifted(int num) {
        if (num >= bucketSize) {
            bucketQue.clear();
            return;
        }
        while (num-- > 0 && bucketQue.size() > bucketSize) {
            sum -= bucketQue.pollFirst();
        }
    }

    public void update(long count) {
        Long value = bucketQue.pollLast();
        value += count;
        bucketQue.add(value);
        sum += count;
    }

    public void add(long count) {
        bucketQue.add(count);
        sum += count;
    }

    public long getSum() {
        return sum;
    }
}