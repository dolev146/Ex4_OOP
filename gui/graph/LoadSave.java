package gui.graph;

import java.io.File;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Classes.G;
import Classes.GA;
import FileHandling.StoreNE;
import Interfaces.NodeData;
import gui.Scale;

import static FileHandling.CImport.importJson;
import static gui.graph.GraphPanel.lines;

public class LoadSave {

    public static boolean load(GFrame frame, GraphPanel panel) {
        GraphPanel.NodeState = "showNodeLocation";
        GraphPanel.EdgeState = "regularEdge";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("load");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            StoreNE ne = importJson(selectedFile.getPath());
            if (ne != null) {
                var loadGraph = new G(ne.nodes, ne.edges);
                var loadGA = new GA();
                loadGA.init(loadGraph);
                GFrame.GFrameG = loadGraph;
                GFrame.GFrameGA = loadGA;
                GraphPanel.points.clear();
                HashMap<Integer, NodeData> graphNodes = ((G) GFrame.GFrameG).Nodes;
                minMaxVal.getBorderValues();
                graphNodes.forEach((key, node) -> GraphPanel.points.add(node));
                lines.clear();
                ((G) GFrame.GFrameG).Edges.forEach((key, edge) -> {
                    var x1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                    var x2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().x(), minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
                    var y1 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getSrc()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                    var y2 = (int) Scale.scale(GFrame.GFrameG.getNode(edge.getDest()).getLocation().y(), minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
                    var line = new Line(x1, x2, y1, y2, edge.getWeight());
                    lines.add(line);
                });


                frame.setVisible(true);
                frame.setSize(GFrame.width, GFrame.height);
                panel.setSize(GFrame.width, GFrame.height);
                panel.repaint();

                return true;
            }
        }
        return false;
    }

    public static boolean save() {
        String fileName = JOptionPane.showInputDialog("insert name of the File : for example: G5");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        int save_input = fileChooser.showOpenDialog(null);
        if (save_input == JFileChooser.APPROVE_OPTION) {
            String pathToSave = fileChooser.getSelectedFile().getAbsolutePath();
            if (System.getProperty("os.name").contains("win")) {
                GFrame.GFrameGA.save(pathToSave + "\\" + fileName + ".json");
                return true;
            } else {
                GFrame.GFrameGA.save(pathToSave + "/" + fileName + ".json");
                return true;
            }
        }

        return false;
    }

}
