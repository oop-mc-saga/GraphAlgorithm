package graphSearch;

import graph.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 幅優先探索
 *
 * @author tadaki
 */
public class BFS extends GraphSearch {

    public BFS(AbstractGraph graph) {
        super(graph);
    }

    @Override
    public List<Arc> doSearch(Node start) {
        initialize();
        searchSub(start);
        return arcList;
    }

    @Override
    protected void searchSub(Node start) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node v = queue.poll();
            List<Arc> aList = graph.getArcs(v);
            if (aList != null) {
                aList.stream().forEach(
                        a -> {
                            Node w = a.end;
                            if (!nodeList.contains(w) && !queue.contains(w)) {
                                queue.add(w);
                                arcList.add(a);
                            }
                        });
            }
            nodeList.add(v);
        }
    }

}
