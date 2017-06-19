package org.light.demo.task;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.light.demo.task.common.event.CommonEventStarter;
import org.light.demo.task.consumer.CustomerConsumer;
import org.light.demo.task.producer.DataLoaderThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CustomerMain
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
@Slf4j
public class CustomerMain {
    public static void main(String[] args) {
        log.info("task start");
        EventHandler consumer = new CustomerConsumer();
        CommonEventStarter.init(1 << 15, consumer);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(DataLoaderThread.getThread()); //启动生产线程
        executor.shutdown();
        log.info("task end");
    }
}