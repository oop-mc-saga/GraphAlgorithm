package samples;

import circuits.*;
import graph.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 */
public class Graph2 extends AbstractGraph {

    private final int n = 5;

    public Graph2(String title) {
        super(title);
        setDirected(false);
    }

    @Override
    public void construct() {
        for (int i = 0; i < n; i++) {
            Node nn = new Node(String.valueOf(i));
            this.addNode(nn);
        }
        int k = 0;
        addArc(nodes.get(0), nodes.get(1), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(0), nodes.get(2), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(1), nodes.get(2), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(1), nodes.get(3), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(1), nodes.get(4), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(2), nodes.get(3), "a_" + String.valueOf(k));
        k++;
        addArc(nodes.get(2), nodes.get(4), "a_" + String.valueOf(k));
        k++;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Graph2 graph = new Graph2("graph-2");
        graph.construct();
        Pajek.outputPajekData("graph-2.net", graph);
        Node start = graph.nodes.get(0);
//        Euler euler = new Euler(graph, start);
        EulerNonRecursive euler = new EulerNonRecursive(graph, start);
        List<List<Arc>> list = euler.startEnumerate();
        for (List<Arc> aList : list) {
            System.out.println(aList);
        }
    }

}
