package gui.graph;

import javax.swing.*;

import Classes.G;
import Interfaces.NodeData;
import gui.Scale;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
// draw arrow 
/// https://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java 

public class GraphPanel extends JPanel {
    public static final int ARR_SIZE = 4;
    static public LinkedList<NodeData> points = new LinkedList<>();
    static public LinkedList<Line> lines = new LinkedList<>();
    static public NodeData centerNode;
    static public List<NodeData> shortedPathNodes = new ArrayList<>();
    static public List<NodeData> nodesTsp = new ArrayList<>();
    static public HashMap<Integer, NodeData> graphNodes = new HashMap<>();
    static public String NodeState = "showNodeLocation";
    static public String EdgeState = "regularEdge";
    public String message ;

    public GraphPanel() {
        super();
        this.setBackground(new Color(237, 255, 252)); // change color of background
        if (((G) GFrame.GFrameG).Nodes.isEmpty()) {
            message = "load a graph from menu ";
        } else {
            message = "";
            graphNodes = ((G) GFrame.GFrameG).Nodes;
            minMaxVal.getBorderValues();
            graphNodes.forEach((key, node) -> points.add(node));
            ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
                var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                var line = new Line(x1, x2, y1, y2, edge.getWeight());
                lines.add(line);
            });
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);

        switch (NodeState) {
            case "regularNode" -> DrawNode.drawNodesRegular(g);
            case "showNodeID" -> DrawNode.drawNodesID(g);
            case "showNodeLocation" -> DrawNode.drawNodesGeoLocation(g);
            case "showCenterNode" -> DrawNode.drawNodesCenter(g);
            case "showShortestPathNode" -> DrawTspShortestPath.ShortestPathDraw(g);
            case "showTspNode" -> DrawTspShortestPath.TspDraw(g);
            default -> {
            }
        }

        switch (EdgeState) {
            case "regularEdge" -> DrawEdges.drawEdgesRegular(g);
            case "showEdgesWeight" -> DrawEdges.drawEdgesWeight(g);
            default -> {
            }
        }

    }


}
