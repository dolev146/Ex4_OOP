package ClassesTest;

import Classes.GA;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.NodeData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GAPerformanceTest {
    static DirectedWeightedGraphAlgorithms GAlgo = new GA();

    @BeforeAll
    static void setup() {
        GAlgo.load("C:\\Users\\Dvir\\Desktop\\LargeConnectedGraphs/10000Nodes.json");
    }

//    @Test
//    void isConnected() {
//
//        assertTrue(GAlgo.isConnected()); //798 ms
//    }

    @Test
    void shortestPathDist() {
        GAlgo.shortestPathDist(1, 10);
    }

    @Test
    void shortestPathCenter() {
      GAlgo.shortestPathDist(1, 120);
    }

//    @Test
//    void center() {
//        GAlgo.center();
//    }

    @Test
    void tsp() {
        List<NodeData> list4 = new LinkedList<>();
        list4.add(GAlgo.getGraph().getNode(0));
        list4.add(GAlgo.getGraph().getNode(1));
        list4.add(GAlgo.getGraph().getNode(2));
        list4.add(GAlgo.getGraph().getNode(3));
        List<NodeData> ans =  GAlgo.tsp(list4);
        System.out.println(ans.toString());

    }
}