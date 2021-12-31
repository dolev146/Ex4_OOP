package Classes;

import Interfaces.DirectedWeightedGraph;
import Interfaces.EdgeData;
import Interfaces.NodeData;

import java.util.*;

public class G implements DirectedWeightedGraph {

    public HashMap<Integer, NodeData> Nodes;
    public HashMap<String, EdgeData> Edges; // String ="src_" + src + "_dest_" + dest
    public HashMap<Integer, List<EdgeData>> EdgesById;
    public int modeCount;

    int it_change;
    boolean first_time;

    /**
     * constructor
     */
    public G() {
        this.EdgesById = new HashMap<>();
        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
        modeCount = 0;
        it_change = 0;
        first_time = true;

    }

    public G(HashMap<Integer, NodeData> nodes, HashMap<String, EdgeData> edges) {
        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
        this.EdgesById = new HashMap<>();
        nodes.forEach((key, value) -> {
            this.addNode(new CNode(value));
            this.EdgesById.put(key, new ArrayList<>());
        });
        edges.forEach((key, edge) -> this.connect(edge.getSrc(), edge.getDest(), edge.getWeight()));

        // edges.forEach((key, edge) -> this.connect(edge.getSrc(), edge.getDest(),
        // edge.getWeight()));
        this.modeCount = 0;
        it_change = 0;
        first_time = true;
    }

    public G(G other) {
        this.EdgesById = new HashMap<>();

        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
        other.Nodes.forEach((key, value) -> {
            this.addNode(new CNode(value));
            this.EdgesById.put(key, new ArrayList<>());
        });
        other.Edges.forEach((key, edge) -> this.connect(edge.getSrc(), edge.getDest(), edge.getWeight()));
        this.modeCount = other.getMC();
        it_change = 0;
        first_time = true;
    }

    public G(DirectedWeightedGraph other) {
        this.EdgesById = new HashMap<>();

        this.Nodes = new HashMap<>();
        this.Edges = new HashMap<>();
        var niter = other.nodeIter();
        while (niter.hasNext()) {
            var node = niter.next();
            this.addNode(new CNode(node));
            this.EdgesById.put(node.getKey(), new ArrayList<>());
        }
        var eiter = other.edgeIter();
        while (eiter.hasNext()) {
            var e = eiter.next();
            this.connect(e.getSrc(), e.getDest(), e.getWeight());
        }
        this.modeCount = other.getMC();
        it_change = 0;
        first_time = true;
    }

    // /**
    // *
    // * @param i
    // * @param j
    // * @return
    // */
    // public boolean hasEdge(int i, int j) {
    // return edges.containsKey("src_" + i + "_dest_" + j);
    // }

    @Override
    public NodeData getNode(int key) {
        return this.Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.Edges.containsKey("src_" + src + "_dest_" + dest)) {
            return this.Edges.get("src_" + src + "_dest_" + dest);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        if (n != null) {
            if (!Nodes.containsKey(n.getKey())) {
                NodeData nc = new CNode(n);
                Nodes.put(n.getKey(), nc);
                this.EdgesById.put(n.getKey(), new ArrayList<>());
                modeCount++;
            }
        }
    }

    /**
     * this function connect 2 Nodes by create new edge between them
     * eventually, we add the new edge to outEdges hashMap
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc)
     *             between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (w < 0) {
            throw new RuntimeException("Only Positive Numbers");
        }
        if (Nodes.containsKey(src) && Nodes.containsKey(dest) && src != dest) {
            EdgeData e = new CEdge(src, dest, w);
            if (this.EdgesById.containsKey(src)) {
                this.EdgesById.get(src).add(e);
            } else {
                var list = new ArrayList<EdgeData>();
                list.add(e);
                this.EdgesById.put(src, list);
            }

            Edges.put("src_" + src + "_dest_" + dest, e);
            modeCount++;
        }

    }

    /**
     * This method returns an Iterator for the
     * collection representing all the Nodes in the graph.
     * Note: if the graph was changed since the iterator was constructed - a
     * RuntimeException should be thrown.
     *
     * @return Iterator<node_data>
     */

    @Override
    public Iterator<NodeData> nodeIter() {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }
        if (it_change == modeCount) {
            List<NodeData> list = new ArrayList<>(this.Nodes.values());
            return list.iterator();
        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    /**
     * This method returns an Iterator for all the Edges in this graph.
     * Note: if any of the Edges going out of this node were changed since the
     * iterator was constructed - a RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter() {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }

        if (it_change == modeCount) {
            return this.Edges.values().iterator();

        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    /**
     * This method returns an Iterator for Edges getting out of the given node (all
     * the Edges starting (source) at the given node).
     * Note: if the graph was changed since the iterator was constructed - a
     * RuntimeException should be thrown.
     *
     * @return Iterator<EdgeData>
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (first_time) {
            it_change = modeCount;
            first_time = false;
        }
        if (it_change == modeCount) {

            return this.EdgesById.get(node_id).iterator();
        }
        throw new RuntimeException("graph changed since the iterator was constructed");
    }

    @Override
    public NodeData removeNode(int NodeId) {
        if (this.Nodes.containsKey(NodeId)) {
            NodeData n = new CNode(this.Nodes.get(NodeId));
            String strSrc = Integer.toString(NodeId);
            this.Edges.keySet().removeIf((str) ->{
             String[] arr = str.split("_");
                if (Arrays.asList(arr).contains(strSrc)) {
                    if(arr[3].equals(strSrc)){
                        this.EdgesById.get(Integer.parseInt(arr[1])).removeIf((edge)->edge.getDest() == NodeId );
                    }
                    return true;
                }
                return false;
            });
            this.EdgesById.remove(NodeId);
            this.Nodes.remove(NodeId);
            modeCount++;

            return n;
        }
        return null;
    }

    public void removeEdge(String key) {
        Edges.remove(key);
        modeCount++;
    }


    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (Edges.containsKey("src_" + src + "_dest_" + dest)) {
            // need to check if return by value or address
            EdgeData e = new CEdge(Edges.get("src_" + src + "_dest_" + dest));
            Edges.remove("src_" + src + "_dest_" + dest);
            this.EdgesById.get(src).removeIf(edge -> edge.getDest() == dest);
            modeCount++;
            return e;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return this.Nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.Edges.size();
    }

    @Override
    public int getMC() {
        return this.modeCount;
    }

    @Override
    public String toString() {
        return "G{" +
                "Nodes=" + Nodes +
                ", Edges=" + Edges +
                ", modeCount=" + modeCount +
                '}';
    }
}