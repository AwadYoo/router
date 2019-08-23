package com.example;

import com.example.entity.Node;
import com.example.entity.Way;
import com.example.service.Service;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class DemoApplicationTests {
    @Autowired
    private Service service;

    /**
     * 上海到南京的路线个数
     */
    @Test
    public void question1() {
        Map<String, Node> initData = service.init();
        Node sh = initData.get("上海");
        Node nj = initData.get("南京");
        ArrayList<Way> list = service.recursive(initData, sh, new Way(sh, nj), nj.getName(), null);
        log.info("上海到南京的路线个数:{}个。", list.size());
        Assert.assertEquals(3, list.size());
    }

    /**
     * 北京到厦门之间最少站点的线路
     */
    @Test
    public void question2() {
        Map<String, Node> initData = service.init();
        Node bj = initData.get("北京");
        Node xm = initData.get("厦门");
        ArrayList<Way> list = service.recursive(initData, bj, new Way(bj, xm), xm.getName(), null);
        list.sort(Comparator.comparingInt(Way::namesSize));
        log.info("北京到厦门之间最少站点的线路:{}。", list.get(0).getNames());
        Assert.assertArrayEquals(new String[]{"北京", "厦门"}, list.get(0).getNames().toArray(new String[list.get(0).getNames().size()]));
    }

    /**
     * 南京到上海之间停靠2站的个数
     */
    @Test
    public void question3() {
        Map<String, Node> initData = service.init();
        Node sh = initData.get("上海");
        Node nj = initData.get("南京");
        ArrayList<Way> list = service.recursive(initData, sh, new Way(sh, nj), nj.getName(), null);

        int count = 0;
        for (Way way : list) {
            if (way.getNames().size() == 2 + 2) {
                count++;
            }
        }
        log.info("南京到上海之间停靠2站的个数:{}个。", count);
        Assert.assertEquals(2, count);
    }

    /**
     * 所有路程为5的线路
     */
    @Test
    public void question4() {
        Map<String, Node> initData = service.init();
        ArrayList<Way> list = new ArrayList<>();
        String[] keySet = initData.keySet().toArray(new String[initData.keySet().size()]);
        for (int i = 0; i < keySet.length; i++) {
            for (int j = i; j < keySet.length; j++) {
                Node start = initData.get(keySet[i]);
                Node end = initData.get(keySet[j]);
                list.addAll(service.recursive(initData, start, new Way(start, end), end.getName(), null));
            }
        }
        int count = 0;
        for (Way way : list) {
            if (way.getDistance() == 5) {
                count++;
            }
        }
        log.info("所有路程为5的线路的个数:{}个。", count);
        Assert.assertEquals(3, count);
    }

}
