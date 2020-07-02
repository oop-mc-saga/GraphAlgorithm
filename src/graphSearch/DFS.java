package graphSearch;

import graph.*;
import java.util.List;

/**
 * 深さ優先探索
 *
 * @author tadaki
 */
public class DFS extends GraphSearch {

    public DFS(AbstractGraph graph) {
        super(graph);
    }

    @Override
    public List<Arc> doSearch(Node start) {
        initialize();
        nodeList.add(start);
        searchSub(start);
        return arcList;
    }

    @Override
    protected void searchSub(Node v) {
        List<Arc> arcs = graph.getArcs(v);
        if (arcs == null) {
            return;
        }
        arcs.stream().forEach(
                a -> {
                    Node w = a.end;
                    if (!nodeList.contains(w)) {
                        arcList.add(a);
                        nodeList.add(w);
                        searchSub(w);
                    }
                });

    }

}
