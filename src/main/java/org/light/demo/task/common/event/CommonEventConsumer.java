package org.light.demo.task.common.event;

import com.lmax.disruptor.EventHandler;

/**
 * 消息者事件处理器，发送给远程服务
 *
 * @author Michael.Wang
 */
public abstract class CommonEventConsumer implements EventHandler<CommonEvent> {

}