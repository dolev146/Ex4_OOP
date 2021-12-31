package gui.graph;

import Interfaces.NodeData;
import gui.Scale;

import java.awt.*;
import java.util.List;

import static gui.graph.GraphPanel.points;

public class DrawTspShortestPath {

    public static void TspDraw(Graphics g) {
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); // set font of text
        List<NodeData> tspList = GraphPanel.nodesTsp;
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            for (NodeData n : tspList) {
                if (n.getKey() == p.getKey()) {
                    g.drawString(String.valueOf(p.getKey()), xPosScale, yPosScale);
                    g.setColor(Color.cyan);
                    g.fillOval(xPosScale, yPosScale, 15, 15);
                } else {
                    g.fillOval(xPosScale, yPosScale, 10, 10);
                }
            }
        }
    }

    public static void ShortestPathDraw(Graphics g) {
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); // set font of text
        List<NodeData> shortList = GraphPanel.shortedPathNodes;
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            for (NodeData n : shortList) {
                if (n.getKey() == p.getKey()) {
                    g.drawString(String.valueOf(p.getKey()), xPosScale, yPosScale);
                    g.setColor(Color.green);
                    g.fillOval(xPosScale, yPosScale, 15, 15);
                } else {
                    g.fillOval(xPosScale, yPosScale, 10, 10);
                }

            }
        }
    }

}
