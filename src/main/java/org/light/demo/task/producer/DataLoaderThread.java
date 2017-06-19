package org.light.demo.task.producer;

import lombok.extern.slf4j.Slf4j;
import org.light.demo.task.common.event.CommonEventProducer;
import org.light.demo.task.common.event.CommonEventStarter;

import java.util.List;

/**
 * 从DB中加载数据
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
@Slf4j
public class DataLoaderThread implements Runnable {

    private static Thread thread = new Thread(new DataLoaderThread());

    private static volatile boolean stopFlag = false;

    static {
        thread.setName("Customer-DataLoaderThread");
    }

    public static Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        CommonEventProducer producer = CommonEventStarter.getProducer();

        while (!stopFlag) {
            List list = DataLoader.load();
            for (int i = 0; i < list.size(); i++) {
                producer.produce(list.get(i));//生产者生产数据
            }
        }
    }

    public static void stop() {
        stopFlag = true;
        log.info(" update thread  [stopped]");
    }
}