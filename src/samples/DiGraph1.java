package samples;

import graph.*;
import graphSearch.*;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author tadaki
 */
public class DiGraph1 extends AbstractGraph {
    
    private final int n = 10;
    
    public DiGraph1(String title) {
        super(title);
    }
    
    @Override
    public void construct() {
        for (int i = 0; i < n; i++) {
            Node nn = new Node(String.valueOf(i));
            this.addNode(nn);
        }
        int k=0;
        addArc(nodes.get(0), nodes.get(1), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(0), nodes.get(2), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(0), nodes.get(3), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(1), nodes.get(3), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(1), nodes.get(5), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(1), nodes.get(6), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(3), nodes.get(2), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(4), nodes.get(1), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(5), nodes.get(4), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(6), nodes.get(2), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(6), nodes.get(8), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(7), nodes.get(4), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(8), nodes.get(4), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(8), nodes.get(5), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(8), nodes.get(7), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(8), nodes.get(9), "a_"+String.valueOf(k));k++;
        addArc(nodes.get(9), nodes.get(2), "a_"+String.valueOf(k));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        DiGraph1 graph = new DiGraph1("graph-1");
        graph.construct();
        Pajek.outputPajekData("graph-1.net", graph);
        Node start = graph.nodes.get(0);
        showResult(new DFS(graph),"DFS",start);
//        showResult(new DFSNonRecursive(graph),"DFS nonRecuresive",start);
//        showResult(new BFS(graph),"BFS",start);
    }
    
    private static void showResult(GraphSearch sys,String title, Node start) throws IOException{
        System.out.println("------------");
        System.out.println(title);
        List<Arc> arcList = sys.doSearch(start);
        arcList.stream().forEachOrdered(a->System.out.println(a));    
        Pajek.outputPajekData(title+".net",sys.getResult());
    }
}
