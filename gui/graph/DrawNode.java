package gui.graph;

import Interfaces.NodeData;
import gui.Scale;

import java.awt.*;

import static gui.graph.GraphPanel.points;

public class DrawNode {

    public static void drawNodesRegular(Graphics g) {
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            g.fillOval(xPosScale, yPosScale, 10, 10);
        }
    }

    public static void drawNodesCenter(Graphics g) {
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); // set font of text
        var centerNodeKey = GraphPanel.centerNode.getKey();
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            if (centerNodeKey == p.getKey()) {
                g.drawString(String.valueOf(p.getKey()), xPosScale, yPosScale);
                g.setColor(Color.blue);
                g.fillOval(xPosScale, yPosScale, 15, 15);
            } else {
                g.fillOval(xPosScale, yPosScale, 10, 10);
            }
        }
    }

    public static void drawNodesGeoLocation(Graphics g) {
        g.setFont(new Font("Font.SERIF", Font.PLAIN, 15)); // set font of text
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            String xPosS = String.valueOf(xPos).substring(0, String.valueOf(xPos).indexOf(".") + 4);
            String yPosS = String.valueOf(yPos).substring(0, String.valueOf(yPos).indexOf(".") + 4);
            g.drawString("x: " + xPosS, xPosScale - 10, yPosScale - 15);
            g.drawString("y: " + yPosS, xPosScale + 10, yPosScale);
            g.fillOval(xPosScale, yPosScale, 10, 10);

        }

    }

    public static void drawNodesID(Graphics g) {
        g.setFont(new Font("MV Boli", Font.PLAIN, 25)); // set font of text
        for (NodeData p : points) {
            g.setColor(Color.black);
            double xPos = p.getLocation().x();
            double yPos = p.getLocation().y();
            int xPosScale = (int) Scale.scale(xPos, minMaxVal.minX, minMaxVal.maxX, 50, GFrame.width - 70);
            int yPosScale = (int) Scale.scale(yPos, minMaxVal.minY, minMaxVal.maxY, 50, GFrame.height - 150);
            g.drawString(String.valueOf(p.getKey()), xPosScale, yPosScale);
            g.fillOval(xPosScale, yPosScale, 10, 10);
        }
    }

}
