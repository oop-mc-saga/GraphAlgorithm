package graphSearch;

import graph.Arc;
import graph.AbstractGraph;
import graph.Node;
import java.util.List;
import utils.Utils;

/**
 * 深さ優先及び幅優先探索の共通クラス
 *
 * @author tadaki
 */
abstract public class GraphSearch {

    protected List<Arc> arcList;//探索結果となる弧のリスト
    protected List<Node> nodeList;//すでに探索した頂点のリスト
    protected final AbstractGraph graph;//対象となるグラフ

    /**
     * コンストラクタ
     *
     * @param graph 対象となるグラフ
     */
    public GraphSearch(AbstractGraph graph) {
        this.graph = graph;
    }

    /**
     * 始点を指定して探索を実行
     *
     * @param start 探索の始点
     * @return 結果（弧のリスト）
     */
    abstract public List<Arc> doSearch(Node start);

    protected void initialize() {
        arcList = Utils.createList();
        nodeList = Utils.createList();
    }

    public AbstractGraph getResult() {
        AbstractGraph resultGraph = new AbstractGraph("result") {
            @Override
            public void construct() {
            }
        };
        graph.getNodes().forEach(node -> resultGraph.addNode(node));
        arcList.forEach(
                arc -> resultGraph.addArc(arc.head, arc.end, arc.label));
        return resultGraph;
    }

    /**
     * 探索の実装
     *
     * @param v 注目している頂点
     */
    abstract protected void searchSub(Node v);
}
