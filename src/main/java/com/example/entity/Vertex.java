package com.example.entity;

import lombok.Data;

/**
 * @author awad_yoo
 * @create 2019-08-23 15:28
 */
@Data
public class Vertex {
    private final static int infinite_dis = Integer.MAX_VALUE;

    private String name;  //节点名字
    private int distance; //此节点距离

    public Vertex(String name) {
        this.name = name;
    }
}
