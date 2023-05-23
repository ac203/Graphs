package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchRecursive {
    private final Graph _graph;
    public DepthFirstSearchRecursive(Graph graph) {
        _graph = graph;
    }
    public void traverse(Node node, List<Node> closeList) {
        if (closeList.contains(node)) {
            return;
        }
        closeList.add(node);

        _graph.getNeighbours(node).forEach(neighbour -> traverse(neighbour, closeList));

        System.out.println(node);
    }
}
