package circuits;

import graph.*;
import java.util.List;
import utils.Utils;

/**
 * Hamilton閉路の列挙
 *
 * @author tadaki
 */
public class Hamilton {

    final private List<List<Node>> curcuitList;//閉路のリスト
    final private AbstractGraph g;//対象となる無向グラフ
    final private Node start;//始点
    final private int N;//頂点の総数

    public Hamilton(AbstractGraph g, Node start) {
        curcuitList = Utils.createList();
        this.start = start;
        this.g = g;
        N = g.getNodes().size();
    }

    /**
     * 列挙の開始
     *
     * @return 見つけた弧のリストのリスト
     */
    public List<List<Node>> startEnumerate() {
        List<Node> path = Utils.createList();
        path.add(start);
        enumerateSub(start, path);
        return curcuitList;
    }

    /**
     * 再帰的探索
     *
     * @param v
     * @param path
     */
    private void enumerateSub(Node v, List<Node> path) {

    }
}
