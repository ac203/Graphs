package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends GraphTraverse {
    private Graph _graph;
    private Stack<Node> _openList = new Stack<>();
    private List<Node> _closeList = new ArrayList<>();

    public DepthFirstSearch(Graph graph) {
        _graph = graph;
    }

    @Override
    public void traverse(Node node) {
        _openList.add(node);

        while (!_openList.isEmpty()) {
            Node w = _openList.pop();

            if(!_closeList.contains(w)) {
                _closeList.add(w);
                System.out.println("Current node: " + w);
                _graph.getNeighbours(w).forEach(neighbour -> _openList.push(neighbour));
            }
        }
        System.out.println("Closelist: ");
        _closeList.forEach(visitedNode -> System.out.print("[" + visitedNode.getName() + "] "));
        System.out.println();
    }
}
