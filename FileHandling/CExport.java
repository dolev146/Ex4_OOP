package FileHandling;

import Interfaces.DirectedWeightedGraph;
import Interfaces.EdgeData;
import Interfaces.NodeData;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CExport {

    public static boolean GAsave(String fileName, DirectedWeightedGraph graph) {
        Gson gson = new Gson();
//        var p_nodes =
//        StoreNE ne = new StoreNE(graph)
        var Nodes = new ArrayList<NodeData>();
        var Edges = new ArrayList<EdgeData>();
        var niter = graph.nodeIter();
        while (niter.hasNext()) {
            var node = niter.next();
//            NodeData nc = new CNode(node);
            Nodes.add(node);
        }
        var eiter = graph.edgeIter();
        while (eiter.hasNext()) {
            var e = eiter.next();
//            this.connect(e.getSrc(), e.getDest(), e.getWeight());
            Edges.add(e);
        }
//                StoreNE ne = new StoreNE(graph)
        ArrNE arr = new ArrNE(Nodes, Edges);
        String json = gson.toJson(arr);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(json);
            myWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
