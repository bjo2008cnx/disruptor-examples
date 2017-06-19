package org.light.demo.task.common.event;

import com.lmax.disruptor.EventFactory;
import lombok.Data;

/**
 * Log 事件
 *
 * @author Michael.Wang
 */
@Data
public class CommonEvent<T> {
    private T value;

    /**
     * 事件生产工厂
     *
     * @author Michael.Wang
     */
    public static class Factory implements EventFactory<CommonEvent> {

        @Override
        public CommonEvent newInstance() {
            return new CommonEvent();
        }
    }
}

