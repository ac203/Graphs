package at.fhv.aci;

import at.fhv.aci.graph.Graph;
import at.fhv.aci.graph.GraphType;
import at.fhv.aci.graph.algorithms.*;
import at.fhv.aci.graph.elements.Edge;
import at.fhv.aci.graph.elements.Node;
import at.fhv.aci.graph.types.ObjectGraph;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // traverse();
        dijkstra();
        prim();
    }

    private static void traverse() {
        Node a = new Node("A-1");
        Node b = new Node("B-2");
        Node c = new Node("C-3");
        Node d = new Node("D-4");
        Node e = new Node("E-5");

        Graph graph = new Graph(GraphType.OBJECT_GRAPH);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);

        graph.addEdge(a, b, 2);
        graph.addEdge(a, c, 4);
        graph.addEdge(b, c, 6);
        graph.addEdge(b, e, 7);
        graph.addEdge(c, e, 5);
        graph.addEdge(c, d, 8);

        System.out.println("### DFS Start ###");
        GraphTraverse depthFirstSearch = new DepthFirstSearch(graph);
        depthFirstSearch.traverse(a);

        System.out.println("### BFS Start ###");
        GraphTraverse breadthFirstSearch = new BreadthFirstSearch(graph);
        breadthFirstSearch.traverse(a);

        System.out.println("### DFS-Rec Start ###");
        DepthFirstSearchRecursive depthFirstSearchRecursive = new DepthFirstSearchRecursive(graph);
        depthFirstSearchRecursive.traverse(graph.getFirstNode(), new ArrayList<>());
    }

    private static void dijkstra() {
        Graph graph = new Graph(GraphType.OBJECT_GRAPH);

        Node v = new Node("V");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        graph.addNode(v);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);

        graph.addEdge(v, a, 5);
        graph.addEdge(v, b, 12);
        graph.addEdge(a, c, 3);
        graph.addEdge(a, d, 8);
        graph.addEdge(b, d, 1);
        graph.addEdge(c, b, 2);
        graph.addEdge(c, d, 4);

        System.out.println("### Start Dijkstra ###");
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.dijkstra(v);
        System.out.println("### End Dijkstra ###");

    }

    private static void prim() {
        Graph graph = new Graph(GraphType.OBJECT_GRAPH);

        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.addNode(f);
        graph.addNode(g);
        graph.addNode(h);
        graph.addNode(i);

        graph.addEdge(a, b, 1);
        graph.addEdge(a, d, 2);

        graph.addEdge(b, a, 1);
        graph.addEdge(b, c, 6);
        graph.addEdge(b, e, 9);

        graph.addEdge(c, b, 6);
        graph.addEdge(c, f, 3);

        graph.addEdge(d, a, 2);
        graph.addEdge(d, e, 4);
        graph.addEdge(d, g, 10);

        graph.addEdge(e, b, 9);
        graph.addEdge(e, d, 4);
        graph.addEdge(e, f, 7);
        graph.addEdge(e, h, 12);

        graph.addEdge(f, c, 3);
        graph.addEdge(f, e, 7);
        graph.addEdge(f, i, 5);

        graph.addEdge(g, d, 10);
        graph.addEdge(g, h, 13);

        graph.addEdge(h, g, 13);
        graph.addEdge(h, e, 12);
        graph.addEdge(h, i, 11);

        graph.addEdge(i, f, 5);
        graph.addEdge(i, h, 11);

        System.out.println("### Start Prim ###");
        Prim prim = new Prim(graph);
        Map<Node, Node> spanningTree = prim.prim();
        System.out.println("### End Prim ###");

    }
}