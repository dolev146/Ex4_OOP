package gui.graph;

import Classes.CGeo;
import Classes.CNode;
import Classes.G;
import Classes.GA;
import Interfaces.NodeData;
import gui.Scale;

import java.util.HashMap;

import static gui.graph.GraphPanel.lines;

public class Change {

    public static void addEdge(int src, int dest, double w) {
        var loadGraph = new G(GFrame.GFrameG);
        var loadGA = new GA();
        loadGraph.connect(src, dest, w);
        loadGA.init(loadGraph);
        GFrame.GFrameG = loadGraph;
        GFrame.GFrameGA = loadGA;
        GraphPanel.points.clear();
        HashMap<Integer, NodeData> graphNodes = ((G) GFrame.GFrameG).Nodes;
        minMaxVal.getBorderValues();
        graphNodes.forEach((key, node) -> GraphPanel.points.add(node));
        lines.clear();
        ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
            var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var line = new Line(x1, x2, y1, y2, edge.getWeight());
            lines.add(line);
        });
    }

    public static void removeEdge(int src, int dest) {
        var loadGraph = new G(GFrame.GFrameG);
        var loadGA = new GA();
        loadGraph.removeEdge(src, dest);
        loadGA.init(loadGraph);
        GFrame.GFrameG = loadGraph;
        GFrame.GFrameGA = loadGA;
        GraphPanel.points.clear();
        HashMap<Integer, NodeData> graphNodes = ((G) GFrame.GFrameG).Nodes;
        minMaxVal.getBorderValues();
        graphNodes.forEach((key, node) -> GraphPanel.points.add(node));
        lines.clear();
        ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
            var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var line = new Line(x1, x2, y1, y2, edge.getWeight());
            lines.add(line);
        });
    }

    public static void addNode( int id, double x, double y) {

        var loadGraph = new G(GFrame.GFrameG);
        var loadGA = new GA();
        CNode n = new CNode(id, new CGeo(x, y, 0.0), 0.0, "White", -1);
        loadGraph.addNode(n);
        loadGA.init(loadGraph);
        GFrame.GFrameG = loadGraph;
        GFrame.GFrameGA = loadGA;
        GraphPanel.points.clear();
        HashMap<Integer, NodeData> graphNodes = ((G) GFrame.GFrameG).Nodes;
        minMaxVal.getBorderValues();
        graphNodes.forEach((key, node) -> GraphPanel.points.add(node));
        lines.clear();
        ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
            var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var line = new Line(x1, x2, y1, y2, edge.getWeight());
            lines.add(line);
        });

    }

    public static void removeNode(int n) {

        var loadGraph = new G(GFrame.GFrameG);
        var loadGA = new GA();
        loadGraph.removeNode(n);
        loadGA.init(loadGraph);
        GFrame.GFrameG = loadGraph;
        GFrame.GFrameGA = loadGA;
        GraphPanel.points.clear();
        HashMap<Integer, NodeData> graphNodes = ((G) GFrame.GFrameG).Nodes;
        minMaxVal.getBorderValues();
        graphNodes.forEach((key, node) -> GraphPanel.points.add(node));
        lines.clear();
        ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
            var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX,
                    minMaxVal.maxX, 50, GFrame.width - 70);
            var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY,
                    minMaxVal.maxY, 50, GFrame.height - 150);
            var line = new Line(x1, x2, y1, y2, edge.getWeight());
            lines.add(line);
        });
    }





}
