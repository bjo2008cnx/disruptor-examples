package org.light.demo.task;

import com.lmax.disruptor.EventHandler;
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
public class CustomerMain {
    public static void main(String[] args) {
        EventHandler consumer = new CustomerConsumer();
        CommonEventStarter.init(1 << 15, consumer);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new DataLoaderThread()); //启动生产线程
        executor.shutdown();
    }
}