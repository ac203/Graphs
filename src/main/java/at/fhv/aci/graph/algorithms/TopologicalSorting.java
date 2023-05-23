package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.HashMap;
import java.util.Map;

public class TopologicalSorting {
    private Graph _graph;

    public TopologicalSorting(Graph graph) {
        _graph = graph;
    }

    public Map<Node, Integer> sort() {
        int i = 1;
        Map<Node, Integer> sortedNodes = new HashMap<>();
        while(!_graph.getNodes().isEmpty()) {
            if (hasZeroDegree(_graph.getFirstNode())) {
                sortedNodes.put(_graph.getFirstNode(), i);
                _graph.getNodes().remove(_graph.getFirstNode());
                i++;
            }
        }
        return sortedNodes;
    }

    private boolean hasZeroDegree(Node node) {
        boolean hasZeroDegree = false;
        for (Node n : _graph.getNodes()) {
            for (Edge e : n.getEdges()) {
                if (e.getNodes()[1] == node) {
                    hasZeroDegree = true;
                }
            }
        }
        return hasZeroDegree;
    }
}
