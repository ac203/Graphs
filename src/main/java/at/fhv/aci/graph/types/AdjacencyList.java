package at.fhv.aci.graph.types;

import at.fhv.aci.graph.GraphStructure;
import at.fhv.aci.graph.elements.Node;

import java.util.*;

public class AdjacencyList implements GraphStructure {
    private List<Node> _nodes;
    private List<Map<Node, Integer>> _adjacencyList;

    public AdjacencyList() {
        _nodes = new ArrayList<>();
        _adjacencyList = new LinkedList<>();
    }

    @Override
    public void addNode(Node node) {
        // add Node to list of Nodes
        _nodes.add(node);
        // create AdjacencyList for the Node
        _adjacencyList.add(new HashMap<>());
    }

    @Override
    public void addEdge(Node firstNode, Node secondNode, int weight) {
        // iterate through list of Nodes
        for (int i = 0; i < _nodes.size(); i++) {
            // add weight and second Node to AdjacencyList simulating an Edge
            if (_nodes.get(i).equals(firstNode)) {
                _adjacencyList.get(i).put(secondNode, weight);
            }
        }
    }

    @Override
    public List<Node> getNeighbours(Node node) {
        // create list for neighbours
        List<Node> neighbours = new ArrayList<>();

        // iterate through list of Nodes
        for (int i = 0; i < _nodes.size(); i++) {
            // get matching Node from list of Nodes
            if (_nodes.get(i).equals(node)) {
                // add all neighbours from AdjacencyList to list of neighbours
                _adjacencyList.get(i).forEach((neighbour, weight) -> neighbours.add(neighbour));
            }
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
}
