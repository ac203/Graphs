package at.fhv.aci.graph;

import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.List;

public interface GraphStructure {
    void addNode(Node node);

    void addEdge(Node firstNode, Node secondNode, int weight);

    List<Node> getNeighbours(Node node);

    Node getFirstNode();

    List<Node> getNodes();
}
