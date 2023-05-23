package at.fhv.aci.graph.types;

import at.fhv.aci.graph.GraphStructure;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.ArrayList;
import java.util.List;


public class ObjectGraph implements GraphStructure {

    private List<Node> _nodes;

    public ObjectGraph() {
        _nodes = new ArrayList<>();
    }

    @Override
    public void addNode(Node node) {
        _nodes.add(node);
    }

    @Override
    public void addEdge(Node firstNode, Node secondNode, int weight) {
        if (_nodes.contains(firstNode) && _nodes.contains(secondNode)) {
            // create Edge with weight and Nodes
            Edge edge = new Edge(getNode(firstNode), getNode(secondNode), weight);
            getNode(firstNode).addEdge(edge);
            getNode(secondNode).addEdge(edge);

        } else {
            System.out.println("Graph does not contain Nodes [" + firstNode.getName() + "] and [" + secondNode.getName() + "]");
        }
    }

    @Override
    public List<Node> getNeighbours(Node node) {
        // get Node from the graph
        Node nodeInList = getNode(node);
        // get the Edges of the Node
        List<Edge> edges = nodeInList.getEdges();
        // create List for the neighbours
        List<Node> neighbours = new ArrayList<>();

        // the Nodes on the other side of the Edges are the Nodes neighbours
        for (Edge edge : edges) {
            neighbours.add(edge.getNodes()[1]);
        }

        return neighbours;
    }

    @Override
    public Node getFirstNode() {
        return _nodes.get(0);
    }

    @Override
    public List<Node> getNodes() {
        return _nodes;
    }

    // helper method to get the Node from the Graph
    private Node getNode(Node node) {
        Node n = null;

        for (Node nodeInList : _nodes) {
            if (nodeInList.equals(node)) {
                n = nodeInList;
            }
        }

        return n;
    }
}
