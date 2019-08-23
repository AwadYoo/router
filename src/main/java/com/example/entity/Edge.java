package com.example.entity;

import lombok.Data;

/**
 * @author awad_yoo
 * @create 2019-08-23 15:27
 */
@Data
public class Edge {
    private String startVertex;  //此有向边的起始点
    private String endVertex;  //此有向边的终点
    private int weight;  //此有向边的权值

    public Edge(String startVertex, String endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }
}
