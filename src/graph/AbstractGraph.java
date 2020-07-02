package graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Utils;

/**
 * グラフの抽象クラス
 *
 * @author tadaki
 */
public abstract class AbstractGraph {

    protected final List<Node> nodes;//全ての頂点
    protected final Map<Node, List<Arc>> node2arc;//頂点を始点とする弧
    public final String title;//グラフの名前
    private boolean directed = true;//有向・無向

    public AbstractGraph(String title) {
        this.title = title;
        nodes = Utils.createList();
        node2arc = Utils.createMap();
    }

    /**
     * 頂点の追加
     * @param node 
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    /**
     * 弧の追加
     * @param from
     * @param to
     * @param label 
     */
    public void addArc(Node from, Node to, String label) {
        Arc arc = new Arc(from, to, label);
        if (!node2arc.containsKey(from)) {
            List<Arc> arcList = Utils.createList();
            node2arc.put(from, arcList);
        }
        node2arc.get(from).add(arc);
        if (!directed) {
            if (!node2arc.containsKey(to)) {
                List<Arc> arcList = Utils.createList();
                node2arc.put(to, arcList);
            }
            node2arc.get(to).add(arc);
        }
    }

    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * 弧の一覧を生成して返す
     *
     * @return
     */
    public List<Arc> getArcs() {
        Set<Arc> arcSet = new HashSet<>();
        node2arc.keySet().stream().map(node -> node2arc.get(node)).
                forEach(arcs -> {
                    arcs.forEach(a -> arcSet.add(a));
                });
        List<Arc> arcList = Utils.createList();
        arcSet.stream().forEach(a -> arcList.add(a));
        return arcList;
    }

    /**
     * 指定した頂点を始点とする弧の一覧を返す
     *
     * @param node
     * @return
     */
    public List<Arc> getArcs(Node node) {
        return node2arc.get(node);
    }

    public boolean isDirected() {
        return directed;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    /**
     * 具体的にグラフを構成するメソッド
     */
    abstract public void construct();
}
