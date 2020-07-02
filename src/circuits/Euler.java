package circuits;

import graph.*;
import java.util.List;
import utils.Utils;

/**
 * Euler閉路の列挙
 * @author tadaki
 */
public class Euler {

    final private List<List<Arc>> curcuitList;//閉路のリスト
    final private AbstractGraph g;//対象となる無向グラフ
    final private Node start;//始点
    final private int L;//弧の総数

    public Euler(AbstractGraph g, Node start) {
        curcuitList = Utils.createList();
        this.start = start;
        this.g = g;
        L = g.getArcs().size();
    }

    /**
     * 列挙の開始
     * @return 見つけた弧のリストのリスト
     */
    public List<List<Arc>> startEnumerate() {
        List<Arc> aEuler = Utils.createList();
        enumerateSub(start, aEuler);
        return curcuitList;
    }

    /**
     * 再帰的探索
     * @param v
     * @param aEuler 
     */
    private void enumerateSub(Node v, List<Arc> aEuler) {
        if (v == start && aEuler.size() == L) {
            //見つけた閉路を保存
            curcuitList.add(aEuler);
            return;
        }
        //vを始点とする全ての弧について調べる
        List<Arc> arcList = g.getArcs(v);
        if (arcList != null) {
            for (Arc a : arcList) {
                if (!aEuler.contains(a)) {
                    List<Arc> newList = Utils.createList();
                    newList.addAll(aEuler);
                    newList.add(a);
                    Node w = a.getAnotherEnd(v);
                    enumerateSub(w, newList);
                }
            }
        }
    }
}
