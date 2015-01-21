package org.tf.monitor.worker.service;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author hezhiyu on 15/1/18.
 */
public class ConveyorQueue {

    private Deque<Long> bucketQue;
    private long bucketSize;
    private long totalSum;

    public ConveyorQueue(int bucketSize) {
        this.bucketQue = new ArrayDeque<>(bucketSize);
        this.bucketSize = bucketSize;
    }

    public void shifted(int shifted) {
        if (shifted >= bucketSize) {
            bucketQue.clear();
            totalSum = 0;
            return;
        }

        // 当shifted < bucket的大小时, 进行push操作, 用0进行占位
        while (shifted < bucketSize && shifted > 0) {
            bucketQue.add(0L);
            shifted--;
        }

        // 当bucketQueue.size > bucketSize时,
        while (bucketQue.size() > bucketSize) {
            totalSum -= bucketQue.poll();
        }
    }

    public void add(long count) {
        // 保证至少有一个元素
        if (bucketQue.size() == 0) bucketQue.add(0L);
        Long value = bucketQue.pollLast();
        value += count;
        bucketQue.addLast(value);
        totalSum += count;
    }

    public long getTotalSum() {
        return totalSum;
    }
}