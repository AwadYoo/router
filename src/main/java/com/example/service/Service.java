package com.example.service;

import com.example.entity.Node;
import com.example.entity.Way;
import com.example.util.CommonUtils;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * 答案在测试类
 */
@org.springframework.stereotype.Service
public class Service {

    public Map<String, Node> init() {
        Node sh = new Node("上海", Lists.newArrayList("深圳"), Lists.newArrayList(3));
        Node sz = new Node("深圳", Lists.newArrayList("上海", "北京", "厦门"), Lists.newArrayList(3, 1, 2));
        Node bj = new Node("北京", Lists.newArrayList("上海", "深圳", "南京", "厦门"), Lists.newArrayList(3, 1, 1, 3));
        Node nj = new Node("南京", Lists.newArrayList("深圳", "北京", "厦门"), Lists.newArrayList(5, 1, 1));
        Node xm = new Node("厦门", Lists.newArrayList("南京"), Lists.newArrayList(1));

        Map<String, Node> cities = new HashMap<>();
        cities.put("上海", sh);
        cities.put("深圳", sz);
        cities.put("北京", bj);
        cities.put("南京", nj);
        cities.put("厦门", xm);
        return cities;
    }

    public ArrayList<Way> recursive(Map<String, Node> originalData, Node start, Way way, String name, ArrayList<Way> res) {
        if (res == null) res = new ArrayList<>();
        List<String> names = start.getNames();
        List<String> oldNames = way.getNames();
        for (String startName : names) {
            if (oldNames.contains(startName))
                continue;
            Way newWay = CommonUtils.clone(way);
            if (startName.equals(name)) {
                newWay.add(name, start.getDistance(name));
                res.add(newWay);
            } else {
                Node nextNode = originalData.get(startName);
                newWay.add(startName, start.getDistance(startName));
                recursive(originalData, nextNode, newWay, name, res);
            }
        }
        return res;
    }
}
