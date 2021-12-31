package FileHandling;

import java.util.HashMap;

import Interfaces.EdgeData;
import Interfaces.NodeData;

public class StoreNE {
    public HashMap<Integer, NodeData> nodes;
    public HashMap<String, EdgeData> edges;

    public StoreNE(HashMap<Integer, NodeData> hp_nodes, HashMap<String, EdgeData> hp_edges) {
        this.nodes = hp_nodes;
        this.edges = hp_edges;
    }

}
