package graph;

import java.util.List;
import utils.Utils;

/**
 * ネットワークの抽象クラス：グラフに加えて、弧に値が付加されている
 * @author tadaki
 */
public abstract class AbstractNetwork extends AbstractGraph {

    public AbstractNetwork(String title) {
        super(title);
    }

    @Override
    public void addArc(Node from, Node to, String label) {
        addArc(from, to, label, 1.);
    }

    public void addArc(Node from, Node to, String label, double weight) {
        Arc arc = new Arc(from, to, label, weight);
        if (!node2arc.containsKey(from)) {
            List<Arc> arcList = Utils.createList();
            node2arc.put(from, arcList);
        }
        node2arc.get(from).add(arc);
    }

}
