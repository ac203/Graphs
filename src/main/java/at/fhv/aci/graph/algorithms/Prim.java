package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prim {
    private Graph _graph;

    public Prim(Graph graph) {
        _graph = graph;
    }

    public Map<Node, Node> prim() {
        Map<Node, Node> spanningTree = new HashMap<>();

        // wähle Knoten V angrenzend an kürzeste Kante
        Node v = getNodeShortestEdge();

        // markiere alle Knoten als unbesucht
        List<Node> visitedNodes = new ArrayList<>();

        // setze Kosten aller Knoten auf unendlich
        Map<Node, Integer> costList = new HashMap<>();
        _graph.getNodes().forEach(n -> costList.put(n, Integer.MAX_VALUE));

        // für alle Kanten (v, w)
        v.getEdges().forEach(edge -> {
            // setze Kosten(W) = Gewicht(V,W)
            costList.put(edge.getNodes()[1], edge.getWeight());
            // setze Kante(W) = (V,W)
            if (edge.getNodes()[1] != v)
                spanningTree.put(edge.getNodes()[1], v);
        });


        // solange es unbesuchte Knoten gibt
        while (visitedNodes.size() < _graph.getNodes().size()) {
            // wähle darunter den Knoten w mit minimaler Länge (w)
            Node w = getNodeMinLength(visitedNodes, costList);
            // markiere w als besucht
            visitedNodes.add(w);

            // für alle Kanten (w, z) zu unbesuchten Knoten z
            for (Edge edge : w.getEdges()) {
                Node z = edge.getNodes()[1];
                if (!visitedNodes.contains(z) && z != w) {
                    // wenn Gewicht(v, w) < Kosten(z)
                    if (edge.getWeight() < costList.get(z)) {
                        // setze Kosten(z) = Gewicht(v, w)
                        costList.put(z, edge.getWeight());
                        // setze Kante (z) = (v, w)
                        spanningTree.put(z, w);
                    }
                }
            }
        }

        costList.forEach((node, length) -> System.out.println("Node: " + node.getName() + " | Cost: " + length.toString()));
        spanningTree.forEach((successor, predecessor) -> System.out.println("Predecessor of [" + successor + "] = [" + predecessor +"]"));

        return spanningTree;
    }

    private Node getNodeShortestEdge() {
        Edge shortestEdge = null;

        // Iteriere über alle Knoten im Graph bis kürzester Knoten gefunden wurde
        for (Node node : _graph.getNodes()) {
            // Iteriese über alle Kanten des Knoten
            for (Edge edge : node.getEdges()) {
                // Wenn noch keine Kante gesetzt oder kürzere Kante gefunden --> V = Knoten mit kürzester Kante
                if (shortestEdge == null || edge.getWeight() < shortestEdge.getWeight()) {
                    shortestEdge = edge;
                }
            }
        }

        return shortestEdge.getNodes()[0];
    }

    private Node getNodeMinLength(List<Node> visitedNodes, Map<Node, Integer> lengthlist) {
        Node w = null;
        int min = Integer.MAX_VALUE;

        // Gehe alle Knoten in Längenliste durch, bis Knoten mit minimaler Länge gefunden wurde
        for (Node node : lengthlist.keySet()) {
            // Wenn Kosten < min und Knoten nicht besucht -> w = Knoten
            if (lengthlist.get(node) != null && lengthlist.get(node) < min && !visitedNodes.contains(node)) {
                min = lengthlist.get(node);
                w = node;
            }
        }

        return w;
    }
}
