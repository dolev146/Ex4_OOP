package Classes;

import java.util.Objects;

import Interfaces.EdgeData;

public class CEdge implements EdgeData {
    private int src;
    private int dest;
    private double w;
    private String info;
    private int tag;

    /**
     * Constructor:
     * 
     * @param s - source.
     * @param d - destination.
     * @param w - weight.
     */
    public CEdge(int s, int d, double w) {
        this.src = s;
        this.dest = d;
        this.w = w;
        this.info = "white";
        this.tag = -1;
    }

    /**
     * Deep copy constructor.
     * 
     * @param other - EdgeData.
     */
    public CEdge(EdgeData other) {

        this.src = other.getSrc();
        this.dest = other.getDest();
        this.w = other.getWeight();
        this.info = other.getInfo();
        this.tag = other.getTag();
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public String getInfo() {
        return  this.info;
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
        return  "CEdge{ " +
        "src="+ this.src +
        ", w="+ this.w + 
        ", dest=" + this.dest+
        '}';
    }

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       CEdge edge = (CEdge) o;
       return src == edge.src &&
               dest == edge.dest &&
               Double.compare(edge.w, w) == 0 &&
               tag == edge.tag &&
               Objects.equals(info, edge.info);
   }

   @Override
   public int hashCode() {
       return Objects.hash(src, dest, w, info, tag);
   }


}
