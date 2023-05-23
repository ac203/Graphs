package at.fhv.aci.graph.elements;

import java.util.LinkedList;
import java.util.Objects;

public class Node {
    private String _name;
    private LinkedList<Edge> _edges;

    public Node(String name) {
        _name = name;
        _edges = new LinkedList<>();
    }

    public String getName() {
        return _name;
    }

    public LinkedList<Edge> getEdges() {
        return _edges;
    }

    public void addEdge(Edge edge) {
        _edges.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(_name, node._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name);
    }

    @Override
    public String toString() {
        return _name;
    }
}
