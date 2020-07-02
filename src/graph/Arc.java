package graph;

/**
 * 弧のクラス
 * @author tadaki
 */
public class Arc {

    public final Node head;
    public final Node end;
    public final String label;
    public final double weight;

    public Arc(Node head, Node end, String label) {
        this.head = head;
        this.end = end;
        this.label = label;
        weight = 1.;
    }

    public Arc(Node head, Node end, String label, double weight) {
        this.head = head;
        this.end = end;
        this.label = label;
        this.weight = weight;
    }

    public Node getAnotherEnd(Node node){
        if (node==end)return head;
        return end;
    }
    
    @Override
    public String toString() {
        return label;
    }

}
