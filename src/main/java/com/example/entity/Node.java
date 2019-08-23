package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Node implements Serializable {
    private String name;

    private List<String> names;

    private List<Integer> weights;

    public Node(String name, List<String> names, List<Integer> weights) {
        this.name = name;
        this.names = names;
        this.weights = weights;
    }

    public int getDistance(String name) {
        return weights.get(names.indexOf(name));
    }
}

