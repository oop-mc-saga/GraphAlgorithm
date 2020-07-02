package circuits;

import graph.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import utils.Utils;

/**
 * Euler閉路の列挙
 *
 * @author tadaki
 */
public class EulerNonRecursive {

    final private List<List<Arc>> curcuitList;//閉路のリスト
    final private AbstractGraph g;//対象となる無向グラフ
    final private Node start;//始点
    final private int L;//弧の総数

    class PathAndLastNode {

        public List<Arc> path;
        public Node lastNode;

        public PathAndLastNode(List<Arc> path, Node lastNode) {
            this.path = path;
            this.lastNode = lastNode;
        }

    }

    public EulerNonRecursive(AbstractGraph g, Node start) {
        curcuitList = Utils.createList();
        this.start = start;
        this.g = g;
        L = g.getArcs().size();
    }

    /**
     * 列挙の開始
     *
     * @return 見つけた弧のリストのリスト
     */
    public List<List<Arc>> startEnumerate() {
        Queue<PathAndLastNode> queue = initializeQueue();
        if (queue == null) {
            return null;
        }
        while (!queue.isEmpty()) {
            PathAndLastNode tmpPath = queue.poll();
            List<Arc> path = tmpPath.path;//現在の弧のリスト
            Node v = tmpPath.lastNode;//到達した頂点
            if (v == start && path.size() == L) {//Euler閉路になっている場合
                curcuitList.add(path);
                break;
            }
            List<Arc> arcs = g.getArcs(v);
            if (arcs != null) {
                for (Arc a : arcs) {
                    PathAndLastNode newPath = update(a, v, path);
                    if (newPath != null) {
                        queue.offer(newPath);
                    }
                }
            }
        }
        return curcuitList;
    }

    private PathAndLastNode update(Arc a, Node v, List<Arc> path) {
        if (path.contains(a)) {
            return null;
        }
        List<Arc> newPath = Utils.createList();
        newPath.addAll(path);
        newPath.add(a);
        Node w = a.getAnotherEnd(v);
        return new PathAndLastNode(newPath, w);
    }

    /**
     * 待ち行列の初期化
     *
     * @return
     */
    private Queue<PathAndLastNode> initializeQueue() {
        Queue<PathAndLastNode> queue = new LinkedList<>();

        List<Arc> arcs = g.getArcs(start);
        if (arcs == null) {
            return null;
        }
        List<Arc> path = Utils.createList();
        for (Arc a : arcs) {
            PathAndLastNode newPath = update(a, start, path);
            if (newPath != null) {
                queue.offer(newPath);
            }
        }
        return queue;
    }
}
