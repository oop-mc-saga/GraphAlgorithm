package samples;

import circuits.Hamilton;
import graph.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tadaki
 */
public class Graph3 extends AbstractGraph {

    private final int n = 5;

    public Graph3(String title) {
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
        addArc(nodes.get(3), nodes.get(4), "a_" + String.valueOf(k));
        k++;
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Graph3 graph = new Graph3("graph-3");
        graph.construct();
        Pajek.outputPajekData("graph-3.net", graph);
        Node start = graph.nodes.get(0);
        Hamilton hamilton = new Hamilton(graph, start);
        List<List<Node>> list = hamilton.startEnumerate();
        for (List<Node> nList : list) {
            System.out.println(nList);
        }
    }

}
