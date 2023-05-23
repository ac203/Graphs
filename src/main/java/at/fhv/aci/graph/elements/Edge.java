package at.fhv.aci.graph.elements;

import java.util.Arrays;
import java.util.Objects;

public class Edge {
    private int _weight;
    private Node[] _nodes;

    public Edge(Node firstNode, Node secondNode, int weight) {
        _weight = weight;
        _nodes = new Node[2];
        _nodes[0] = firstNode;
        _nodes[1] = secondNode;
    }

    public int getWeight() {
        return _weight;
    }

    public Node[] getNodes() {
        return _nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        Node firstNode = edge.getNodes()[0];
        Node secondNode = edge.getNodes()[0];

        return _weight == edge._weight && (_nodes[0].equals(firstNode) && _nodes[1].equals(secondNode) ||
                (_nodes[0].equals(secondNode) && _nodes[1].equals(firstNode)));
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(_weight);
        result = 31 * result + Arrays.hashCode(_nodes);
        return result;
    }

    @Override
    public String toString() {
        return _nodes[0] + "-" + _weight + "-" + _nodes[1];
    }
}
