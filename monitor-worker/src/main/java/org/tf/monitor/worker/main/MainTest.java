package org.tf.monitor.worker.main;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhiyu on 15/1/18.
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        MinuteHourCounter minuteHourCounter = MinuteHourCounter.getMinuteHourCounter();
        Map<Long, Integer> counterMap = new HashMap<>(100);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            long currentSec = System.currentTimeMillis() / 1000;
            int mockCount = random.nextInt(1000);
            counterMap.put((long) i, mockCount);
            TimeUnit.SECONDS.sleep(1);
            minuteHourCounter.add(mockCount, currentSec);
        }
        System.out.println(counterMap);
        System.out.println("mc: " + minuteHourCounter.minuteCount());
        System.out.println("hc: " + minuteHourCounter.hourCount());
    }
}