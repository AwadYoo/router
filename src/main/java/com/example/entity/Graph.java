package com.example.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author awad_yoo
 * @create 2019-08-23 15:24
 */
@Data
public class Graph {
    private List<String> topPointList;   //图的顶点集
    private Map<String, List<Edge>> verEdgeListMap;  //图的每个顶点对应的有向边

    public Graph(List<String> topPointList, Map<String, List<Edge>> verEdgeListMap) {
        this.topPointList = topPointList;
        this.verEdgeListMap = verEdgeListMap;
    }
}
