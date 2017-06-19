package org.light.demo.task.producer;

import org.light.demo.task.domain.CustomerData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DataLoader
 *
 * @author Michael.Wang
 * @date 2017/6/19
 */
public interface DataLoader {
    List<CustomerData> load();
}