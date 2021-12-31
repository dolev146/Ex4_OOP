package gui.graph;

import javax.swing.*;

import Classes.G;
import Classes.GA;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static gui.buttons.MenuBarExample.scaleImageIcon;

public class GFrame extends JFrame implements ActionListener {
    public static DirectedWeightedGraph GFrameG = new G();
    public static DirectedWeightedGraphAlgorithms GFrameGA = new GA();

    GraphPanel panel;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu NodeMenu;
    JMenu EdgeMenu;
    JMenu AlgoMenu;
    ImageIcon loadIcon;
    ImageIcon saveIcon;
    ImageIcon exitIcon;

    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem centerItem;
    JMenuItem addNodeItem;
    JMenuItem removeNodeItem;
    JMenuItem showEdgeWeightItem;
    JMenuItem showNodeGeoLocationItem;
    JMenuItem showNodeIDItem;
    JMenuItem addEdgeItem;
    JMenuItem removeEdgeItem;
    JMenuItem shortestPathDistItem;
    JMenuItem isConnectedItem;
    JMenuItem shortestPathItem;
    JMenuItem tspItem;
    JMenuItem regularNodeViewItem;
    JMenuItem regularEdgeViewItem;
    public static int height;
    public static int width;

    public GFrame(DirectedWeightedGraphAlgorithms alg) {
        super();
        // copying alg
        GFrameG = alg.getGraph();
        GFrameGA.init(GFrameG);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        height = (int) (dim.getHeight() * 0.8);
        width = (int) (dim.getWidth() * 0.8);
        ImageIcon image = new ImageIcon("./src/gui/resources/logo.png");
        this.setIconImage(image.getImage());
        panel = new GraphPanel();
        this.add(panel);
        this.repaint();

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadIcon = new ImageIcon("./src/gui/resources/load.jpg");
        saveIcon = new ImageIcon("./src/gui/resources/save.png");
        exitIcon = new ImageIcon("./src/gui/resources/exit.jpg");

        loadIcon = scaleImageIcon(loadIcon, 20, 20);
        saveIcon = scaleImageIcon(saveIcon, 20, 20);
        exitIcon = scaleImageIcon(exitIcon, 20, 20);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("Menu");
        NodeMenu = new JMenu("Edit Node");
        EdgeMenu = new JMenu("Edit Edge");
        AlgoMenu = new JMenu("Algorithm");

        // fileMenu.setMnemonic(KeyEvent.VK_F); // Alt + f for file
        // loadItem.setMnemonic(KeyEvent.VK_L); // l for load
        // saveItem.setMnemonic(KeyEvent.VK_S); // s for save
        // exitItem.setMnemonic(KeyEvent.VK_E); // e for exit

        // adding values here
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        centerItem = new JMenuItem("center ()");
        addNodeItem = new JMenuItem("add Node()");
        removeNodeItem = new JMenuItem("remove Node()");
        showEdgeWeightItem = new JMenuItem("show edges weight");
        showNodeGeoLocationItem = new JMenuItem("show Node GeoLocation");
        showNodeIDItem = new JMenuItem("show Node id");
        addEdgeItem = new JMenuItem("add edge");
        removeEdgeItem = new JMenuItem("remove edge");
        shortestPathDistItem = new JMenuItem("shortest path Dist ()");
        isConnectedItem = new JMenuItem("is connected()");
        shortestPathItem = new JMenuItem("shortest path ()");
        tspItem = new JMenuItem("tsp calc()");
        regularNodeViewItem = new JMenuItem("clean Node information ");
        regularEdgeViewItem = new JMenuItem("clean Edge information ");
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        centerItem.addActionListener(this);
        addNodeItem.addActionListener(this);
        removeNodeItem.addActionListener(this);
        showEdgeWeightItem.addActionListener(this);
        showNodeGeoLocationItem.addActionListener(this);
        showNodeIDItem.addActionListener(this);
        addEdgeItem.addActionListener(this);
        removeEdgeItem.addActionListener(this);
        shortestPathDistItem.addActionListener(this);
        isConnectedItem.addActionListener(this);
        shortestPathItem.addActionListener(this);
        tspItem.addActionListener(this);
        regularNodeViewItem.addActionListener(this);
        regularEdgeViewItem.addActionListener(this);

        loadItem.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);

        this.setJMenuBar(menuBar);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        AlgoMenu.add(centerItem);
        NodeMenu.add(addNodeItem);
        NodeMenu.add(removeNodeItem);
        EdgeMenu.add(showEdgeWeightItem);
        NodeMenu.add(showNodeGeoLocationItem);
        NodeMenu.add(showNodeIDItem);
        EdgeMenu.add(addEdgeItem);
        EdgeMenu.add(removeEdgeItem);
        AlgoMenu.add(shortestPathDistItem);
        AlgoMenu.add(isConnectedItem);
        AlgoMenu.add(shortestPathItem);
        AlgoMenu.add(tspItem);
        NodeMenu.add(regularNodeViewItem);
        EdgeMenu.add(regularEdgeViewItem);

        menuBar.add(fileMenu);
        menuBar.add(AlgoMenu);
        menuBar.add(NodeMenu);
        menuBar.add(EdgeMenu);

        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loadItem) {
            if (LoadSave.load(this, panel)) {
                JOptionPane.showMessageDialog(null, "load OK!", "load", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "load Error", "load", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // ************ */
        if (e.getSource() == saveItem) {
            if (LoadSave.save()) {
                JOptionPane.showMessageDialog(null, "save OK!", "save", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "save Error", "save", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // ************ */
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

        if (e.getSource() == centerItem) {
            JOptionPane.showMessageDialog(null, "finding center", "center", JOptionPane.PLAIN_MESSAGE);
            GraphPanel.centerNode = GFrame.GFrameGA.center();
            GraphPanel.NodeState = "showCenterNode";
            repaint();
        }
        // ******** */

        if (e.getSource() == addNodeItem) {
            int key = Integer.parseInt(JOptionPane.showInputDialog("key :  ? "));
            double x = Double.parseDouble(JOptionPane.showInputDialog(" x :  ? "));
            double y = Double.parseDouble(JOptionPane.showInputDialog(" y :  ? "));
            Change.addNode(key, x, y);
            repaint();
        }
        // ******** */
        if (e.getSource() == removeNodeItem) {
            int key = Integer.parseInt(JOptionPane.showInputDialog("key :  ? "));
            Change.removeNode(key);
            repaint();
        }
        // ******** */
        if (e.getSource() == showEdgeWeightItem) {
            GraphPanel.EdgeState = "showEdgesWeight";
            repaint();
        }
        // ******** */
        if (e.getSource() == showNodeGeoLocationItem) {
            GraphPanel.NodeState = "showNodeLocation";
            repaint();
        }
        // ******** */
        if (e.getSource() == showNodeIDItem) {
            GraphPanel.NodeState = "showNodeID";
            repaint();
        }
        // ******** */
        if (e.getSource() == addEdgeItem) {
            int src = Integer.parseInt(JOptionPane.showInputDialog("src :  ? "));
            int dest = Integer.parseInt(JOptionPane.showInputDialog(" dest :  ? "));
            double w = Double.parseDouble(JOptionPane.showInputDialog(" weight :  ? "));
            Change.addEdge(src, dest, w);
            repaint();
        }
        // ******** */
        if (e.getSource() == removeEdgeItem) {
            int src = Integer.parseInt(JOptionPane.showInputDialog("src :  ? "));
            int dest = Integer.parseInt(JOptionPane.showInputDialog(" dest :  ? "));
            Change.removeEdge(src, dest);
            repaint();
        }
        // ******** */
        if (e.getSource() == shortestPathDistItem) {
            int src = Integer.parseInt(JOptionPane.showInputDialog("src :  ? "));
            int dest = Integer.parseInt(JOptionPane.showInputDialog(" dest :  ? "));
            double result = GFrame.GFrameGA.shortestPathDist(src, dest);
            if ((int) Math.floor(result) != -1) {
                JOptionPane.showMessageDialog(null, "result : " + result, "shortest path dist ()", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, " no Path ", "shortest path dist ()", JOptionPane.WARNING_MESSAGE);
            }
        }
        // ******** */
        if (e.getSource() == isConnectedItem) {
            if (GFrame.GFrameGA.isConnected()) {
                JOptionPane.showMessageDialog(null, "Graph connected", "is connected ()", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Graph Not! connected", "is connected ()", JOptionPane.WARNING_MESSAGE);
            }
        }
        // ******** */
        if (e.getSource() == shortestPathItem) {
            int src = Integer.parseInt(JOptionPane.showInputDialog("src :  ? "));
            int dest = Integer.parseInt(JOptionPane.showInputDialog(" dest :  ? "));
            GraphPanel.shortedPathNodes = GFrame.GFrameGA.shortestPath(src, dest);
            GraphPanel.NodeState = "showShortestPathNode";
            repaint();

        }
        // ******** */
        if (e.getSource() == tspItem) {
            String input = (JOptionPane.showInputDialog("insert Id separated by comma : for example : 1,2,3 "));
            tspCalc.tspConvert(input);
            GraphPanel.NodeState ="showTspNode";
            repaint();
        }
        // ******** */
        if (e.getSource() == regularNodeViewItem) {
            GraphPanel.NodeState = "regularNode";
            repaint();
        }
        // ******** */
        // ******** */
        if (e.getSource() == regularEdgeViewItem) {
            GraphPanel.EdgeState = "regularEdge";
            repaint();
        }
        // ******** */
    }
}
