package FileHandling;

import Interfaces.EdgeData;
import Interfaces.NodeData;

import java.util.ArrayList;

public class ArrNE {
    ArrayList<JsonNode> Nodes;
    ArrayList<JsonEdge> Edges;

    public ArrNE(ArrayList<NodeData> Nodes, ArrayList<EdgeData> Edges) {

        this.Nodes = new ArrayList<>();
        Nodes.forEach((node) ->
                this.Nodes.add(new JsonNode(node.getLocation().x(), node.getLocation().y(), node.getLocation().z(), node.getKey()))
        );


        this.Edges = new ArrayList<>();
        Edges.forEach((edge) ->
                this.Edges.add(new JsonEdge(edge.getSrc(), edge.getDest(), edge.getWeight()))
        );

    }

}
