package Classes;

import Interfaces.GeoLocation;
import Interfaces.NodeData;

import java.util.Objects;

public class CNode implements NodeData {

    private final int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

    public CNode(int uniqueKey, CGeo g, double weight, String info, int tag ) {
        this.key = uniqueKey;
        this.location = new CGeo(g);
        this.weight = weight;
        this.info = info;
        this.tag = tag;
    }

    public CNode(NodeData other) {
        this.key = other.getKey();
        this.location = new CGeo(other.getLocation());
        this.weight = other.getWeight();
        this.info = other.getInfo();
        this.tag = other.getTag();

    }

    public CNode(String pos, String id) {
        this.key = Integer.parseInt(id);
        String[] arr = pos.split(",");
        this.location = new CGeo(Double.parseDouble(arr[0]), Double.parseDouble(arr[1]), Double.parseDouble(arr[2]));
        this.weight = Double.MAX_VALUE;
        this.info = "White";
        this.tag = -1;
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = new CGeo(p.x(), p.y(), p.z());

    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;

    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", location=" + location +
                ", weight=" + weight +
                ", info='" + info +
                ", tag=" + tag +
                '}';
    }


   /**
    * @param o - an Object.
    * @return true if the arguments are equal to each other and false otherwise.
    */
   @Override
   public boolean equals(Object o) {
       if (this == o)
           return true;
       if (o == null || getClass() != o.getClass())
           return false;
       CNode node = (CNode) o;
       return key == node.key &&
               Double.compare(node.weight, weight) == 0 &&
               tag == node.tag &&
               Objects.equals(location, node.location) &&
               Objects.equals(info, node.info);
   }

   /**
    * Override hashcode
    *
    * @return hashcode.
    */
   @Override
   public int hashCode() {
       return Objects.hash(key, location.x(), location.y(), location.z(), weight, info, tag);
   }

}
