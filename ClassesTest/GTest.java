package ClassesTest;

import Classes.CEdge;
import Classes.CGeo;
import Classes.CNode;
import Classes.G;
import Interfaces.EdgeData;
import Interfaces.NodeData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class GTest {

    private static CNode n1, n2, n3, n4, n5, n6;
    private static CGeo g1, g2, g3, g4, g5, g6;

    /**
     * check constructor by deep copy with other Graph
     * check that the address are different
     * check that hashmaps are different address
     */
    @Test
    void deep_copy() {
        G graph1 = new G();
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        graph1.addNode(n1);
        graph1.addNode(n2);

        G graph2 = new G(graph1);
        int node_in_g1 = graph1.nodeSize();
        int node_in_g2 = graph2.nodeSize();
        assertEquals(node_in_g1, node_in_g2);
        assertFalse(graph1 == graph2);
        assertFalse(graph1.Nodes == graph2.Nodes);
        assertFalse(graph1.Edges == graph2.Edges);

    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.MILLISECONDS)
    void getNode() {
        G graph = new G();
        g1 = new CGeo(1, 2, 3);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        graph.addNode(n1);
        assertEquals(n1, graph.getNode(1));
        assertEquals(1, graph.nodeSize());
        assertFalse(n1 == graph.getNode(1));
    }

    @Test
    void getEdge() {
        G graph = new G();

        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);

        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        NodeData node3 = new CNode(n3);
        NodeData node4 = new CNode(n4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.connect(n1.getKey(), n3.getKey(), 1.1);
        graph.connect(n2.getKey(), n4.getKey(), 1.2);

        assertEquals(new CEdge(1, 3, 1.1), graph.getEdge(1, 3));
        assertEquals(new CEdge(2, 4, 1.2), graph.getEdge(2, 4));


    }

    @Test
    void addNode() {

    }

    @Test
    void connect() {
        G graph = new G();
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.connect(1, 2, 1.0);
        assertEquals(1, graph.edgeSize());
        assertEquals(new CEdge(1, 2, 1.0), graph.getEdge(1, 2));


    }

    @Test
    void nodeIter() {
        G graph = new G();
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        graph.addNode(node1);
        graph.addNode(node2);

        Iterator<NodeData> iterator = graph.nodeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(count, 2);

        g3 = new CGeo(-4, 7, 1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        NodeData node3 = new CNode(n3);
        graph.addNode(node3);

        Exception exception = assertThrows(RuntimeException.class, () ->
                graph.nodeIter() // trow RuntimeException: node were changed since the iterator was constructed
        );

        String expectedMessage = "graph changed since the iterator was constructed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void edgeIter() {

        G graph = new G();

        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);

        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        NodeData node3 = new CNode(n3);
        NodeData node4 = new CNode(n4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.connect(n1.getKey(), n3.getKey(), 1.1);
        graph.connect(n2.getKey(), n4.getKey(), 1.2);

        Iterator<EdgeData> iterator = graph.edgeIter();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            iterator.next();
        }
        assertEquals(count, 2);
        // check if after changing the graph
        g5 = new CGeo(-3, 6, 4);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        NodeData node5 = new CNode(n5);
        graph.addNode(node5);

        graph.connect(n2.getKey(), n5.getKey(), 1.6);

        Exception exception = assertThrows(RuntimeException.class, () ->
                graph.edgeIter()
        );

        String expectedMessage = "graph changed since the iterator was constructed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testEdgeIterNodeID() {

        G graph = new G();

        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);

        NodeData node1 = new CNode(n1);
        NodeData node2 = new CNode(n2);
        NodeData node3 = new CNode(n3);
        NodeData node4 = new CNode(n4);

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        graph.connect(n1.getKey(), n3.getKey(), 1.2);
        graph.connect(n1.getKey(), n4.getKey(), 1.4);

        Iterator<EdgeData> iterator = graph.edgeIter(n1.getKey());
        int count = 0;
        while (iterator.hasNext()) {
            // didn't go inside cause the graph was changed since the iterator was
            // constructed
            count++;
            iterator.next();
        }

        assertEquals(2, count);

        // check if after changing the graph
        g5 = new CGeo(-3, 6, 4);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        NodeData node5 = new CNode(n5);
        graph.addNode(node5);
        // need to show dvir that if i remove the node id from the inside it is problem
        Exception exception = assertThrows(RuntimeException.class, () ->
                graph.edgeIter(n1.getKey())
        );

        String expectedMessage = "graph changed since the iterator was constructed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void removeNode() {
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        n6 = new CNode(6, g6, 6.0, "white", -1);

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        assertEquals(6, graph.nodeSize());
        assertEquals(new CNode(n5), graph.getNode(5));
        graph.removeNode(5);
        assertEquals(5, graph.nodeSize());
        assertNull(graph.getNode(5));
    }

    @Test
    void removeEdge() {
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        n6 = new CNode(6, g6, 6.0, "white", -1);

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.connect(1, 2, 1.0);
        assertEquals(1, graph.edgeSize());
        assertEquals(new CEdge(1, 2, 1.0), graph.getEdge(1, 2));
        graph.removeEdge(1, 2);
        assertEquals(0, graph.edgeSize());
        assertNull(graph.getEdge(1, 2));


    }

    @Test
    void nodeSize() {

        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        n6 = new CNode(6, g6, 6.0, "white", -1);

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        int node_in_graph = graph.nodeSize();
        assertEquals(node_in_graph, 6);
    }

    @Test
    void edgeSize() {

        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        n6 = new CNode(6, g6, 6.0, "white", -1);

        G graph = new G();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);

        graph.connect(1, 2, 1.0);
        graph.connect(1, 3, 1.0);
        graph.connect(5, 3, 1.0);

        int edges_in_graph = graph.edgeSize();
        assertEquals(edges_in_graph, 3);
    }

    @Test
    void getMC() {
        g1 = new CGeo(1, 2, 3);
        g2 = new CGeo(2, 1, 3);
        g3 = new CGeo(-4, 7, 1);
        g4 = new CGeo(-5, 8, 2);
        g5 = new CGeo(-3, 6, 4);
        g6 = new CGeo(-1, 5, 4);
        n1 = new CNode(1, g1, 1.0, "white", -1);
        n2 = new CNode(2, g2, 2.0, "white", -1);
        n3 = new CNode(3, g3, 3.0, "white", -1);
        n4 = new CNode(4, g4, 4.0, "white", -1);
        n5 = new CNode(5, g5, 5.0, "white", -1);
        n6 = new CNode(6, g6, 6.0, "white", -1);

        G graph = new G();
        assertEquals(0, graph.getMC());
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        assertEquals(6, graph.getMC());
    }
}