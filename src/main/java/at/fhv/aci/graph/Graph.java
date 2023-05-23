package at.fhv.aci.graph;

import at.fhv.aci.graph.elements.Node;
import at.fhv.aci.graph.types.AdjacencyList;
import at.fhv.aci.graph.types.AdjacencyMatrix;
import at.fhv.aci.graph.types.ObjectGraph;

import java.util.List;

public class Graph {
    private GraphStructure _graphStructure;

    public Graph(GraphType graphType) {
        createGraph(graphType);
    }

    private void createGraph(GraphType graphType) {
        switch (graphType) {
            case OBJECT_GRAPH: _graphStructure = new ObjectGraph(); break;
            case ADJACENCY_LIST: _graphStructure = new AdjacencyList(); break;
            case ADJACENCY_MATRIX: _graphStructure = new AdjacencyMatrix(); break;
        }
    }

    public void addNode(Node node) {
        _graphStructure.addNode(node);
    }

    public void addEdge(Node firstNode, Node secondNode, int weight) {
        _graphStructure.addEdge(firstNode, secondNode, weight);
    }

    public List<Node> getNeighbours(Node node) {
        return _graphStructure.getNeighbours(node);
    }

    public Node getFirstNode() {
        return _graphStructure.getFirstNode();
    }

    public List<Node> getNodes() {
        return _graphStructure.getNodes();
    }

}
