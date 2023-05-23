package at.fhv.aci.graph.types;

import at.fhv.aci.graph.GraphStructure;
import at.fhv.aci.graph.elements.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrix implements GraphStructure {
    private List<Node> _nodes;
    private Integer[][] _adjacencyMatrix;

    public AdjacencyMatrix() {
        _nodes = new ArrayList<>();
        _adjacencyMatrix = new Integer[1][1];
        _adjacencyMatrix[0][0] = 0;
    }

    @Override
    public void addNode(Node node) {
        _nodes.add(node);
        // expand adjacency matrix
        expandMatrix();
    }

    @Override
    public void addEdge(Node firstNode, Node secondNode, int weight) {
        _adjacencyMatrix[_nodes.indexOf(firstNode)][_nodes.indexOf(secondNode)] = weight;
    }

    @Override
    public List<Node> getNeighbours(Node node) {
        // new list of neighbours
        List<Node> neighbours = new LinkedList<>();

        // check if node is present in the list
        if (_nodes.contains(node)) {
            // iterate through list of nodes
            for (int i = 0; i < _nodes.size(); i++) {
                // iterate through matrix
                for (int j = 0; j < _nodes.size(); j++) {
                    // check if node has neighbour
                    if (_adjacencyMatrix[i][j] != 0) {
                        // add neighbour to list of neighbours
                        neighbours.add(_nodes.get(j));
                    }
                }
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

    private void expandMatrix() {
        // new matrix
        Integer[][] expandedMatrix = new Integer[_nodes.size()][_nodes.size()];

        // fill new matrix with 0
        for (int i = 0; i < _nodes.size(); i++) {
            Arrays.fill(expandedMatrix[i], 0);
        }

        // iterate through old matrix and copy values to nex matrix
        for (int i = 0; i < _nodes.size() - 1; i++) {
            for (int j = 0; j < _nodes.size() - 1; j++) {
                expandedMatrix[i][j] = _adjacencyMatrix[i][j];
            }
        }
        // replace old matrix with new matrix
        _adjacencyMatrix = expandedMatrix;
    }
}
