package org.light.demo.task.common.event;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CommonEventStarter {

    public static int DEFAULT_BUFFER_SIZE = 1 << 17;

    private static Disruptor<CommonEvent> disruptor;

    private static CommonEventProducer producer;

    /**
     * 初始化Buffer与Consumer
     *
     * @param bufferSize
     */
    public static void init(int bufferSize, EventHandler consumer) {
        if (disruptor == null) {
            synchronized (CommonEventStarter.class) {
                if (disruptor == null) {
                    initDisruptor(bufferSize, consumer);
                }
            }
        }
    }

    private static void initDisruptor(int bufferSize, EventHandler consumer) {
        CommonEvent.Factory factory = new CommonEvent.Factory();  // 指定事件工厂
        Executor executor = Executors.newFixedThreadPool(3); // 执行器，用于构造消费者线程
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };

        bufferSize = (bufferSize % 2 == 0 && bufferSize > 0) ? bufferSize : DEFAULT_BUFFER_SIZE; // 必须为2的N次方.

        //disruptor = new Disruptor(factory, bufferSize, executor, ProducerType.SINGLE, new SleepingWaitStrategy());
        disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, new SleepingWaitStrategy());

        disruptor.handleEventsWith(consumer); //设置事件业务处理器---消费者
        disruptor.start();  //启动disruptor线程
        producer = new CommonEventProducer(disruptor.getRingBuffer());
    }

    public static Disruptor<CommonEvent> getDisrutor() {
        return disruptor;
    }

    public static CommonEventProducer getProducer() {
        return producer;
    }
}