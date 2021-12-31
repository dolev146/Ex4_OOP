package gui.graph;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static gui.graph.GraphPanel.ARR_SIZE;
import static gui.graph.GraphPanel.lines;

public class DrawEdges {

    public static void drawEdgesWeight(Graphics g) {
        g.setFont(new Font("Font.SERIF", Font.PLAIN, 15)); // set font of text
        for (Line l : lines) {
            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g.create();
            String str = l.w;
            var x1 = l.x1;
            var x2 = l.x2;
            var y1 = l.y1;
            var y2 = l.y2;
            if (l.x1 > l.x2) {
                g.drawString(str, (int) ((l.x2 + l.x1) / 2) , (int) ((l.y2 + l.y1) / 2) );
                y1 = y1 - 5;
                y2 = y2 - 5;
            } else {
                g.drawString(str, (int) ((l.x2 + l.x1) / 2) + 50, (int) ((l.y2 + l.y1) / 2) + 50);
                y1 = y1 + 10;
                y2 = y2 + 10;
            }

            double dx = x2 - x1, dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx * dx + dy * dy);
            AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
            at.concatenate(AffineTransform.getRotateInstance(angle));
            g2.transform(at);
            g2.drawLine(0, 0, len, 0);
            g2.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
    }



    public static void drawEdgesRegular(Graphics g) {
        for (Line l : lines) {
            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g.create();

            var x1 = l.x1;
            var x2 = l.x2;
            var y1 = l.y1;
            var y2 = l.y2;

            if (l.x1 > l.x2) {
                y1 = y1 - 5;
                y2 = y2 - 5;
            } else {
                y1 = y1 + 10;
                y2 = y2 + 10;
            }


            double dx = x2 - x1, dy = y2 - y1;
            double angle = Math.atan2(dy, dx);
            int len = (int) Math.sqrt(dx * dx + dy * dy);
            AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
            at.concatenate(AffineTransform.getRotateInstance(angle));
            g2.transform(at);
            g2.drawLine(0, 0, len, 0);
            g2.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
    }



}
