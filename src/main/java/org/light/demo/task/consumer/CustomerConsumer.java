package org.light.demo.task.consumer;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.light.demo.task.common.event.CommonEvent;
import org.light.demo.task.common.event.CommonEventConsumer;
import org.light.demo.task.common.event.CommonEventStarter;
import org.light.demo.task.domain.CustomerData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CustomerConsumer
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
@Slf4j
public class CustomerConsumer extends CommonEventConsumer {
    ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void onEvent(CommonEvent event, long sequence, boolean endOfBatch) {
        log.debug("consume log[start]");
        RingBuffer<CommonEvent> ringBuffer = CommonEventStarter.getDisrutor().getRingBuffer();
        CommonEvent<CustomerData> CommonEvent = ringBuffer.get(sequence);
        final CustomerData entity = CommonEvent.getValue();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                DataConsumer.consume(entity);
            }
        });
        log.debug("consume log[end]");
    }
}