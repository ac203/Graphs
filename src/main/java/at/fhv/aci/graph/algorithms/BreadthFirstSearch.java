package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Node;

import java.util.*;

public class BreadthFirstSearch extends GraphTraverse {
    private Graph _graph;
    private Queue<Node> _openList = new LinkedList<>();
    private List<Node> _closeList = new ArrayList<>();

    public BreadthFirstSearch(Graph graph) {
        _graph = graph;
    }

    @Override
    public void traverse(Node node) {
        _openList.add(node);

        while(!_openList.isEmpty()) {
            Node w = _openList.poll();

            if (!_closeList.contains(w)) {
                _closeList.add(w);
                System.out.println("Current node: " + w);
                _graph.getNeighbours(w).forEach(neighboour -> _openList.add(neighboour));
            }
        }
        System.out.println("Closelist: ");
        _closeList.forEach(visitedNode -> System.out.print("[" + visitedNode.getName() + "] "));
        System.out.println();
    }
}
