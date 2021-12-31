package gui.graph;

public class minMaxVal {
    public static double minX = Double.MAX_VALUE;
    public static double maxX = Double.MIN_VALUE;
    public static double maxY = Double.MIN_VALUE;
    public static double minY = Double.MAX_VALUE;

        /*
     * getting the max score using Math();
     * getMaxScore is an accessor method
     * 
     * @Return the maxScore;
     */
     /*
     * getting the min score using Math();
     * getMinScore is an accessor method
     * 
     * @Return the minScore
     */

    public static void getBorderValues() {
        GFrame.GFrameG.nodeIter().forEachRemaining((score)->{
            minX = Math.min(minX, score.getLocation().x());
            maxX = Math.max(maxX, score.getLocation().x());
            minY = Math.min(minY, score.getLocation().y());
            maxY = Math.max(maxY, score.getLocation().y());
        });
    }

    
}
