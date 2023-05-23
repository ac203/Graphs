package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.*;

public class Kruskal {
    private Graph _graph;

    public Kruskal(Graph graph) {
        _graph = graph;
    }

    public Set<Edge> kruskal() {
        Set<Edge> spanningTree = new HashSet<>();

        // vergleicht Kanten nach Gewicht
        Comparator<Edge> comparator = Comparator.comparingInt(Edge::getWeight);
        // Queue mit Knoten, welches nach Gewicht der Knoten geordnet ist
        Queue<Edge> edges = new PriorityQueue<>(comparator);
        getAllEdges().forEach(edge -> edges.add(edge));

        // Jeder Knoten ist in einem seperaten Set (jeder Knoten ist ein Teilbaum)
        List<Set<Node>> nodeSetList = new LinkedList<>();

        _graph.getNodes().forEach(node -> {
            // Erstelle Set für den Knoten
            Set<Node> set = new HashSet<>();
            // füge Knoten in den Set
            set.add(node);
            // füge Set in die Liste der Sets
            nodeSetList.add(set);
        });

        // Iteriere solang bis n-1 Knoten
        while (spanningTree.size() < _graph.getNodes().size()) {
            // nimm die erste Kante aus der Queue
            Edge e = edges.poll();
            // Überprüfe ob Kante schon im SpanningTree ist
            if (!spanningTree.contains(e)) {
                // Überprüfe ob die Knoten am Kantenende disjunkt sind
                if (isDisjoint(e, nodeSetList)) {
                    // wenn ja, füge Kante zur besuchten Kanten hinzu
                    spanningTree.add(e);
                    joinTrees(nodeSetList, e.getNodes()[0], e.getNodes()[1]);
                }
            }
        }

        return spanningTree;
    }

    private void joinTrees(List<Set<Node>> nodeSetList, Node firstNode, Node secondNode) {
        // Platzhalter für Set vom ersten Node
        Set<Node> firstNodeSet = new HashSet<>();
        // Platzhalter für Set vom ersten Node
        Set<Node> secondNodeSet = new HashSet<>();

        // Finde eigenständige Sets und speichere sie
        for (Set<Node> nodeSet : nodeSetList) {
            if (nodeSet.contains(firstNode)) {
                firstNodeSet.add(firstNode);
            }
            if (nodeSet.contains(secondNode)) {
                secondNodeSet.add(secondNode);
            }
        }

        // Lösche eigenständige Sets aus bestehender Liste
        nodeSetList.remove(firstNodeSet);
        nodeSetList.remove(secondNodeSet);

        // Vereinige beide Sets
        firstNodeSet.addAll(secondNodeSet);

        // Füge Set wieder in die Liste hinzu
        nodeSetList.add(firstNodeSet);
    }

    private boolean isDisjoint(Edge edge, List<Set<Node>> nodeSetList) {
        Node firstNode = edge.getNodes()[0];
        Node secondNode = edge.getNodes()[1];
        boolean isDisjoint = true;

        for (Set<Node> nodeSet : nodeSetList) {
            if (nodeSet.contains(firstNode) && nodeSet.contains(secondNode)) {
                isDisjoint = false;
                break;
            }
        }

        return isDisjoint;
    }

    private Set<Edge> getAllEdges() {
        Set<Edge> edges = new HashSet<>();

        _graph.getNodes().forEach(node -> node.getEdges().forEach(edge -> edges.add(edge)));

        return edges;
    }
}
