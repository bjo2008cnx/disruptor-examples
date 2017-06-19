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
public class FakeDataLoader implements DataLoader {
    private AtomicInteger index = new AtomicInteger(0);

    public List<CustomerData> load() {
        LinkedList list = new LinkedList();
        // TODO load from db
        for (int i = 0; i < 100; i++) {
            CustomerData data = new CustomerData();
            data.setId(index.incrementAndGet());
            data.setName("TestData");
            list.add(data);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}