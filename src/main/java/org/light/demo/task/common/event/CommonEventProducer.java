package org.light.demo.task.common.event;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

/**
 *  生产者
 *
 * @author Michael.Wang
 */
@Slf4j
public class CommonEventProducer<T> {
    private final RingBuffer<CommonEvent> ringBuffer;

    public CommonEventProducer(RingBuffer<CommonEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void produce(T entity) {
        long sequence = ringBuffer.next();
        try {
            CommonEvent event = ringBuffer.get(sequence);
            event.setValue(entity);  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}