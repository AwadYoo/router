package com.example.service;

import com.example.entity.Edge;
import com.example.entity.Graph;
import com.example.entity.Vertex;

import java.util.*;

/**
 * @author awad_yoo
 * @create 2019-08-23 15:26
 */
public class TestGraph {
    Graph init() {
        String sh = "上海";
        String sz = "深圳";
        String xm = "厦门";
        String nj = "南京";
        String bj = "北京";


        List<String> verList = new LinkedList<>();
        verList.add(sh);
        verList.add(sz);
        verList.add(xm);
        verList.add(nj);
        verList.add(bj);


        Map<String, List<Edge>> vertexEdgeListMap = new HashMap<>();

        List<Edge> shList = new LinkedList<>();
        shList.add(new Edge(sh, sz, 3));

        List<Edge> szList = new LinkedList<>();
        szList.add(new Edge(sz, sh, 3));
        szList.add(new Edge(sz, xm, 2));
        szList.add(new Edge(sz, bj, 1));

        List<Edge> xmList = new LinkedList<>();
        xmList.add(new Edge(xm, nj, 1));

        List<Edge> njList = new LinkedList<>();
        njList.add(new Edge(nj, sz, 5));
        njList.add(new Edge(nj, xm, 1));
        njList.add(new Edge(nj, bj, 1));

        List<Edge> bjList = new LinkedList<>();
        bjList.add(new Edge(bj, sh, 3));
        bjList.add(new Edge(bj, sz, 1));
        bjList.add(new Edge(bj, xm, 3));
        bjList.add(new Edge(bj, nj, 1));


        vertexEdgeListMap.put(sh, shList);
        vertexEdgeListMap.put(sz, szList);
        vertexEdgeListMap.put(xm, xmList);
        vertexEdgeListMap.put(nj, njList);
        vertexEdgeListMap.put(bj, bjList);
        return new Graph(verList, vertexEdgeListMap);
    }

    public static void main(String[] args) {
        TestGraph graph = new TestGraph();
        Graph g = graph.init();


        System.out.println(123);
        int search = graph.search(g, "上海", "南京");
        System.out.println(search);


    }

    private int search(Graph g, String begin, String end) {
        int res = 0;

        Map<String, List<Edge>> map = g.getVerEdgeListMap();
        if (!map.keySet().contains(begin) || !map.keySet().contains(end)) {
            return 0;
        }

        //直接到达的
        for (Map.Entry<String, List<Edge>> entry : map.entrySet()) {
            if (Objects.equals(entry.getKey(), begin)) {
                for (Edge edge : map.get(begin)) {
                    String endVertex = edge.getEndVertex();
                    if (Objects.equals(end, endVertex)) {
                        res = res + 1;
                        break;
                    }
                }
            }
        }

        int count = getCount(map, begin, end, res);

        return res;
    }

    private int getCount(Map<String, List<Edge>> map, String begin, String end, int count) {
        //需要中转的

        for (Map.Entry<String, List<Edge>> entry : map.entrySet()) {

            int i = 0;
            for (Edge edge : entry.getValue()) {

                if (Objects.equals(edge.getEndVertex(), end)) {
                    count++;
                    i++;
                }
            }
        }

        return count;
    }
}



