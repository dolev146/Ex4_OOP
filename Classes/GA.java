package Classes;


import FileHandling.CImport;
import Interfaces.DirectedWeightedGraph;
import Interfaces.DirectedWeightedGraphAlgorithms;
import Interfaces.EdgeData;
import Interfaces.NodeData;

import java.io.File;
import java.util.*;

import static FileHandling.CExport.GAsave;

public class GA implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    //  private HashMap<Integer, Integer> nodesDegree = new HashMap<>(this.graph.nodeSize());

    /**
     * Inits the graph on which this set of algorithms operates on.
     *
     * @param g DirectedWeightedGraph
     */
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;

    }

    /**
     * Returns the underlying graph of which this class works.
     *
     * @return DirectedWeightedGraph graph
     */
    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    /**
     * Computes a deep copy of this weighted graph.
     * using copy constructor that build in DWGraph class
     *
     * @return DirectedWeightedGraph with deep copy
     */
    @Override
    public DirectedWeightedGraph copy() {
        return new G(this.graph);
    }

    /**
     * This method check if strongly connected by using BFS
     * NOTE: assume directional graph (all n*(n-1) ordered pairs).
     * wiki: https://en.wikipedia.org/wiki/Breadth-first_search
     *
     * @return true if the graph is strongly connected, else false
     */
    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0) {
            return true;
        }
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData n = it.next();
            boolean check = this.bfs(n);
            resetTags();
            if (!check) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the length of the shortest path between 2 nodes
     * we use Dijkstra algorithm to compute that.
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return the shortestPath if existed. ath between src to dest
     * * Note: Due to problems with weight calculation we decided we will make diffrent functions
     */
    @Override
    public double shortestPathDist(int src, int dest) {

        resetInfo();
        resetTags();
        resetWeight();

        double ans = DijkstraDist(this.graph.getNode(src), this.graph.getNode(dest));
        if (ans == Double.MAX_VALUE) {
            return -1;
        }
        return ans;
    }


    private double DijkstraDist(NodeData src, NodeData dest) {

        double shortestPath = Double.MAX_VALUE;
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(), Comparator.comparingDouble(NodeData::getWeight));
        src.setWeight(0.0); //init the src node
        pq.add(src);
        while (!pq.isEmpty()) {
            NodeData pointerNode = pq.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(pointerNode.getKey()); //iterate all the edges that connect to pointerNode
            while (it.hasNext()) {
                EdgeData curr = it.next(); //current edge in the iterator
                NodeData neighborNode = this.graph.getNode(curr.getDest()); //create a neighbor node
                if (Objects.equals(neighborNode.getInfo(), "White")) { //check if we already visit the node
                    if (pointerNode.getWeight() + curr.getWeight() < neighborNode.getWeight()) { // compare between the neighbor node w and pointerNode + the edge that connect to the neighbor
                        neighborNode.setWeight(Math.min(pointerNode.getWeight() + curr.getWeight(), neighborNode.getWeight()));
                        neighborNode.setTag(pointerNode.getKey()); //to track where he came from
                    }
                    pq.add(neighborNode);
                }
            }
            pointerNode.setInfo("Black"); //after we check all pointerNode neighbors make him black - not relevant
            if (pointerNode.getKey() == dest.getKey()) {// if we get to the dest node
                return pointerNode.getWeight();
            }

        }
        return shortestPath;
    }


    /**
     * This method Computes the shortest path between src to dest - as an ordered List of
     * by using Dijkstra algorithm and with the fact that all node contain the prev node that
     * was before him (the tag) and after we're done with Dijkstra we can go back and find
     * the shortestPath of the nodes and pot then into a list
     * if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return List<NodeData> shortestPath of nodes
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        if (this.graph.getNode(src) == null || this.graph.getNode(dest) == null) {
            throw new RuntimeException("bad input");
        }
        if (src == dest) {
            ans.add(this.graph.getNode(src));
            return ans;
        }
        if (shortestPathDist(src, dest) == -1) {//if no such path
            return null;
        }
        resetInfo();
        resetTags();
        resetWeight();
        double d = DijkstraDist(this.graph.getNode(src), this.graph.getNode(dest));
        if (d != Double.MAX_VALUE) {
            int stop = this.graph.getNode(src).getKey(); // were to stop - when we get to the src node
            int ptr = this.graph.getNode(dest).getKey(); // start from the last node
            while (ptr != stop) {
                NodeData curr = this.graph.getNode(ptr);
                ans.add(0, curr); // add in th first place
                ptr = this.graph.getNode(ptr).getTag();
            }
            NodeData first = this.graph.getNode(stop);
            ans.add(0, first);
        }
        return ans;
    }


    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, else return null.
     *
     * @return the Node data which have max the shortest path to all the other node is minimized.
     */
    public NodeData center() {
        if (!isConnected()) {
            return null;
        }
        Iterator<NodeData> nodeIter = graph.nodeIter();
        NodeData ans = graph.getNode(0);
        double minDist = Integer.MAX_VALUE;

        while (nodeIter.hasNext()) {
            NodeData pointerNode = nodeIter.next();
            double maxDist = maxPath(pointerNode.getKey());//return the max weight
            if (maxDist < minDist) {
                minDist = maxDist;
                ans = pointerNode;
            }
        }
        return ans;
    }

    /**
     * calc the max path by using DijkstraCenter from the src node
     * @param src node
     * @return the max weight of path
     */
    private double maxPath(int src) {
        double maxWeight = Integer.MIN_VALUE;
        DijkstraCenter(this.graph.getNode(src));
        Iterator<NodeData> nodeIter = this.graph.nodeIter();
        while (nodeIter.hasNext()) {
            double currWeight = nodeIter.next().getWeight();
            if (currWeight > maxWeight) {
                maxWeight = currWeight;
            }
        }
        return maxWeight;
    }

    /**
     * Creates a PriorityQueue with the specified initial capacity (nodeSize in the graph),
     * and orders its elements according to the specified comparator (the weight of the nodes).
     * the explanation to the code is inside the code
     *
     * @param src the id of the sre node
     * @return the weight (double) of the shortest path between the src and the dest
     */
    public double DijkstraCenter(NodeData src) {
        resetAll();
        double shortestPath = Double.MAX_VALUE;
        PriorityQueue<NodeData> pq = new PriorityQueue<>(this.graph.nodeSize(), Comparator.comparingDouble(NodeData::getWeight));
        src.setWeight(0.0); //init the src node
        pq.add(src);
        while (!pq.isEmpty()) {
            NodeData pointerNode = pq.poll();
            Iterator<EdgeData> it = this.graph.edgeIter(pointerNode.getKey()); //iterate all the edges that connect to pointerNode
            while (it.hasNext()) {
                EdgeData curr = it.next(); //current edge in the iterator
                NodeData neighborNode = this.graph.getNode(curr.getDest()); //create a neighbor node
                if (neighborNode.getInfo().equals("White")) { //check if we already visit the node
                    if (pointerNode.getWeight() + curr.getWeight() < neighborNode.getWeight()) { // compare between the neighbor node w and pointerNode + the edge that connect to the neighbor
                        neighborNode.setWeight(pointerNode.getWeight() + curr.getWeight());
                    }
                    pq.add(neighborNode);
                }
            }
            pointerNode.setInfo("Black");
        }
        return shortestPath;
    }


    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is
     * the "cost" of the solution - the lower, the better.
     *
     * @param cities list of nodes.
     * @return the list that have the lowest weight .
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        List<NodeData> bestWay = new ArrayList<>();
        NodeData pointerNode = cities.remove(0);
        bestWay.add(pointerNode);
        while (!cities.isEmpty()) { //check if we iter all the cities
            NodeData chosen = findPath(cities, pointerNode.getKey()); //return the closest node to src
            if (chosen != pointerNode) {
                List<NodeData> path = shortestPath(pointerNode.getKey(), chosen.getKey());
                path.remove(0); //because we start with 0
                bestWay.addAll(path); //add the list that we got
                cities.remove(chosen);
                pointerNode = chosen;
            } else {
                return null; // there isn't path
            }
        }

        return bestWay;

    }


    /**
     * this method choose the closest node that in the list to the src node by using Dijkstra
     *
     * @param cities list of nodes
     * @param src    the node that check
     * @return the closest node from the list
     */
    private NodeData findPath(List<NodeData> cities, int src) {
        double min = Integer.MAX_VALUE;
        DijkstraCenter(this.graph.getNode(src));
        NodeData ans = this.graph.getNode(src);
        for (NodeData node : cities) {
            if (node.getWeight() < min) {
                min = node.getWeight();
                ans = node;
            }
        }
        return ans;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     *
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        return GAsave(file, getGraph());
    }

    /**
     * This method loads a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     *
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            if (new File(file).isFile() && new File(file).canRead()) {
                DirectedWeightedGraph g = CImport.GAload(file);
                init(g);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * This method check the sum of the nodes in the graph by iterate with BFS
     * Using queue we
     *
     * @param n node that the search start from
     * @return true if the number of nodes that visited and count are equals to the sum of the nodes in the graph
     */
    public boolean bfs(NodeData n) {
        resetTags();
        Queue<NodeData> queue = new LinkedList<>();
        n.setTag(1); //visit
        int count = 1; // count the sum of nodes that has visited
        queue.add(n);
        while (!queue.isEmpty()) { //contain all the nodes that we need to check
            NodeData np = queue.poll();
            var key = np.getKey();
            Iterator<EdgeData> it = this.graph.edgeIter(key);
            while (it.hasNext()) {
                var edge = it.next();
                var edgeNeighbor = edge.getDest();
                NodeData AdjNode = this.graph.getNode(edgeNeighbor); //get the neighbor nodes
                if (AdjNode.getTag() == -1) { //check his tag - if we didn't visit yet:
                    AdjNode.setTag(1); //change to visit
                    queue.add(AdjNode);
                    count++; //increase the counter
                }
            }

        }
        return (count == this.graph.nodeSize()); //if true - the graph is strongly connected
    }

    /**
     * This method reset all the tags of the graph nodes
     * by iterate all the nodes in the graph
     */
    private void resetTags() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setTag(-1);
        }
    }

    /**
     * This method reset all the info of the graph nodes
     * by iterate all the nodes in the graph
     */
    private void resetInfo() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setInfo("White");
        }
    }

    private void resetWeightInfo() {
        Iterator<NodeData> it = this.graph.nodeIter();

        while (it.hasNext()) {
            var node = it.next();
            node.setInfo("White");
            node.setWeight(Double.MAX_VALUE);
        }
    }

    private void resetAll() {
        Iterator<NodeData> it = this.graph.nodeIter();

        while (it.hasNext()) {
            var node = it.next();
            node.setInfo("White");
            node.setTag(-1);
            node.setWeight(Double.MAX_VALUE);
        }
    }

    /**
     * This method reset all the weight of the graph nodes to MAX_VALUE
     * by iterate all the nodes in the graph
     */
    private void resetWeight() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            it.next().setWeight(Double.MAX_VALUE);
        }
    }


}
