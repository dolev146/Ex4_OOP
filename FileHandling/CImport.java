package FileHandling;

import Classes.CEdge;
import Classes.CNode;
import Classes.G;
import Interfaces.DirectedWeightedGraph;
import Interfaces.EdgeData;
import Interfaces.NodeData;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//https://www.youtube.com/watch?v=HSuVtkdej8Q&t=1139s&ab_channel=BrianFraser 
// all the code here is according to this ^ video 

public class CImport {
    public static StoreNE importJson(String json_file) {

        File input = new File(json_file);
        if (input.exists() && !input.isDirectory()) {
            try {
                JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
                JsonObject fileObject = fileElement.getAsJsonObject();

                // process all data:
                JsonArray jsonArrayNodes = fileObject.get("Nodes").getAsJsonArray();
                JsonArray jsonArrayEdges = fileObject.get("Edges").getAsJsonArray();

                List<NodeData> nodes = new ArrayList<>();
                List<EdgeData> edges = new ArrayList<>();

                for (JsonElement jnode : jsonArrayNodes) {
                    JsonObject nodeJsonObject = jnode.getAsJsonObject();
                    String pos = null;
                    if (nodeJsonObject.has("pos")) {
                        pos = nodeJsonObject.get("pos").getAsString();
                    }

                    String id = null;
                    if (nodeJsonObject.has("id")) {
                        id = nodeJsonObject.get("id").getAsString();
                    }

                    NodeData node = new CNode(pos, id);
                    nodes.add(node);
                }

                /// edges
                for (JsonElement jedge : jsonArrayEdges) {
                    JsonObject edgeJsonObject = jedge.getAsJsonObject();
                    String src = null;
                    if (edgeJsonObject.has("src")) {
                        src = edgeJsonObject.get("src").getAsString();
                    }

                    String dest = null;
                    if (edgeJsonObject.has("dest")) {
                        dest = edgeJsonObject.get("dest").getAsString();
                    }

                    String w = null;
                    if (edgeJsonObject.has("w")) {
                        w = edgeJsonObject.get("w").getAsString();
                    }

                    CEdge edge = new CEdge(Integer.parseInt(src),
                            Integer.parseInt(dest),
                            Double.parseDouble(w));
                    edges.add(edge);
                }

                HashMap<Integer, NodeData> hp_nodes = convertNodesList(nodes);
                HashMap<String, EdgeData> hp_edges = convertEdgesList(edges);

                StoreNE ne = new StoreNE(hp_nodes, hp_edges);
                return ne;

            } catch (FileNotFoundException e) {
                System.err.println("Error file not found ");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error processing input file!");
                e.printStackTrace();
            }
        }else{
            System.out.println("file not found");
            return null;
        }
        return null;
        
    }

    public static HashMap<Integer, NodeData> convertNodesList(List<NodeData> list) {
        HashMap<Integer, NodeData> map = new HashMap<>();
        for (NodeData node : list) {
            map.put(node.getKey(), node);
        }
        return map;
    }

    public static HashMap<String, EdgeData> convertEdgesList(List<EdgeData> edgeDataList) {
        HashMap<String, EdgeData> map = new HashMap<>();
        for (EdgeData edge : edgeDataList) {
            CEdge e = new CEdge(edge.getSrc(), edge.getDest(), edge.getWeight());
            map.put("src_" + edge.getSrc() + "_dest_" + edge.getDest(), e);
        }
        return map;
    }

    public static DirectedWeightedGraph GAload(String fileName) {
        StoreNE NodeEdges = importJson(fileName);

        G g = new G(NodeEdges.nodes, NodeEdges.edges);
        return g;

    }
}
