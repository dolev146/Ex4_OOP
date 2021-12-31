package gui.graph;

import Interfaces.NodeData;

import javax.swing.*;
import java.util.ArrayList;

public class tspCalc {
    public static void tspConvert(String input) {
        String[] inputArr = input.split(",");
        ArrayList<Integer> inputI = new ArrayList<>();
        for (String s : inputArr) {
            inputI.add(Integer.parseInt(s));
        }
        ArrayList<NodeData> cities = new ArrayList<>();
        for (int key : inputI) {
            cities.add(GFrame.GFrameG.getNode(key));
        }
        GraphPanel.nodesTsp = GFrame.GFrameGA.tsp(cities);
        String resultS = "";
        for (NodeData node :  GraphPanel.nodesTsp ) {
            resultS = resultS.concat(node.getKey() + "->");
        }
        if (!resultS.equals("")) {
            resultS = resultS.substring(0, resultS.length() - 2);
            JOptionPane.showMessageDialog(null, "result: " + resultS, "Tsp result",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "no result " , "Tsp result",
                    JOptionPane.INFORMATION_MESSAGE);
        }


        GraphPanel.NodeState = "showShortestPathNode";
    }
}
