package at.fhv.aci.graph.algorithms;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;
import at.fhv.aci.graph.types.ObjectGraph;

import java.util.*;

public class Dijkstra {
    private Graph _graph;

    public Dijkstra(Graph graph) {
        _graph = graph;
    }

    public void dijkstra(Node v) {
        // markiere alle Knoten als unbesucht
        List<Node> visitedNodes = new ArrayList<>();

        // setze Kosten aller Knoten auf unendlich
        Map<Node, Integer> lengthList = new HashMap<>();
        _graph.getNodes().forEach(n -> lengthList.put(n, Integer.MAX_VALUE));

        // setze Kosten V auf 0
        lengthList.put(v, 0);

        // solange es unbesuchte Knoten gibt
        while (visitedNodes.size() < _graph.getNodes().size()) {
            // wähle darunter den Knoten w mit minimaler Länge (w)
            Node w = getNodeMinLength(visitedNodes, lengthList);
            // markiere w als besucht
            visitedNodes.add(w);
            // für alle Kanten (w, z) zu unbesuchten Knoten z
            for (Edge edge : w.getEdges()) {
                Node z = edge.getNodes()[1];
                if (!visitedNodes.contains(z)) {
                    // wenn Länge(w) + Gewicht(v, w) < Länge(z)
                    if (edge.getWeight() + lengthList.get(w) < lengthList.get(z)) {
                        // Länge(z) = Länge(w) + Gewicht(v, w)
                        lengthList.put(z, edge.getWeight() + lengthList.get(w));
                    }
                }
            }
        }

        lengthList.forEach((node, length) -> System.out.println("Node: " + node.getName() + " | Cost: " + length.toString()));

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
