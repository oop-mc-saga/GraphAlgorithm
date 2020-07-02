package graph;

/**
 * 頂点のクラス
 * @author tadaki
 */
public class Node{
    public final String label;//頂点のラベル

    public Node(String label) {
        this.label = label;
    }

    @Override
    public String toString(){return label;}

}
