package graphSearch;

import graph.*;
import java.util.List;
import java.util.Stack;

/**
 * 深さ優先探索(非再帰版)
 *
 * @author tadaki
 */
public class DFSNonRecursive extends GraphSearch {

    public DFSNonRecursive(AbstractGraph graph) {
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
        Stack<Arc> stack = new Stack<>();
        //Start from the initial node
        if (!pushArcs(stack, v)) {
            return;
        }

        while (!stack.isEmpty()) {
            Arc a = stack.pop();
            Node w = a.end;
            if (!nodeList.contains(w)) {
                nodeList.add(w);
                arcList.add(a);
                pushArcs(stack, w);
            }
        }
    }

    /**
     * push arcs from the start node
     * @param stack
     * @param start
     * @return 
     */
    private boolean pushArcs(Stack<Arc> stack, Node start) {
        List<Arc> arcs = graph.getArcs(start);
        if (arcs == null) {
            return false;
        }
        for (int i = arcs.size() - 1; i >= 0; i--) {
            Arc a = arcs.get(i);
            if (!nodeList.contains(a.end)) {
                stack.push(a);
            }
        }
        return true;
    }
}
