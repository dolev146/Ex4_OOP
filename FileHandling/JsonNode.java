package FileHandling;

public class JsonNode {
    String pos;
    int id;

    public JsonNode(double Xlocation, double Ylocation, double Zlocation, int id) {
        this.pos = Xlocation + "," + Ylocation + "," + Zlocation;
        this.id = id;
    }
}
