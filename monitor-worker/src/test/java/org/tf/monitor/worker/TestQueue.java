package org.tf.monitor.worker;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author hezhiyu on 15/1/21.
 */
public class TestQueue {

    @Test
    public void testDeque() {
        // 为了测试queue的tail insert
        Deque<Long> queue = new ArrayDeque<>();
        queue.add(10L);
        Long value = queue.peekLast();
        value = value + 10L;
        queue.addLast(value);
        System.out.println(queue.size());
        System.out.println(queue.getLast());
    }

}