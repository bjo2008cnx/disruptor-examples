package org.light.demo.task.consumer;

import lombok.extern.slf4j.Slf4j;
import org.light.demo.task.domain.CustomerData;

/**
 * DataConsumer
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
@Slf4j
public class DataConsumer {
    public static void consume(CustomerData data) {
        sendMessage(data);
        persist(data);
        //TODO Handle exception
    }

    private static void sendMessage(CustomerData data) {
        //TODO
        log.debug("send message .id :" + data.getId());
    }

    private static void persist(CustomerData data) {
        //TODO
        log.debug("persist message .id :" + data.getId());
    }
}