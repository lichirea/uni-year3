package com.company;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
public class Graph {
    private int nodesNumber;
    private Map<Integer, Set<Integer>> vertices;

    public Graph(int nodesNumber){
        this.nodesNumber = nodesNumber;

        vertices = new HashMap<>();
        for(int node=0; node<nodesNumber;node++)
        {
            vertices.put(node, new HashSet<>());
        }
    }

    public boolean addVertex(int fromVertex, int toVertex)
    {
        return vertices.get(fromVertex).add(toVertex);
    }

    public boolean isVertex(int fromVertex, int toVertex)
    {
        return vertices.get(fromVertex).contains(toVertex);
    }
}
