package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.FileIO;

/**
 * Pajek用データ生成、読み込み
 *
 * @author tadaki
 */
public class Pajek {
    //改行文字（OSによる差を考慮）
    private static final String NL
            = System.getProperty("line.separator");

    public static enum ReadMode {
        ReadNode, ReadArcs, ReadEdges, None;
    }

    private Pajek() {
    }

    /**
     * pajek 用のデータを作成
     *
     * @param network
     * @return
     */
    public static String generatePajekData(AbstractGraph network) {
        boolean b = (network instanceof AbstractNetwork);
        StringBuilder sb = new StringBuilder();
        List<Node> nodes = network.getNodes();
        //頂点一覧を文字列化
        sb.append("*Vertices ").append(nodes.size()).append(NL);
        nodes.stream().forEach(
                node -> {
                    int nodeID = nodes.indexOf(node) + 1;
                    sb.append(nodeID).append(" ").append("\"").
                            append(node.label).append("\"");
                    sb.append(NL);
                });
        //辺の情報の文字列化
        if (network.isDirected()) {
            sb.append("*Arcs").append(NL);
            for (Node startNode : nodes) {
                int startNodeID = nodes.indexOf(startNode) + 1;
                List<Arc> arcs = network.getArcs(startNode);
                if (arcs != null) {
                    for (Arc arc : arcs) {
                        Node endNode = arc.getAnotherEnd(startNode);
                        int endNodeID = nodes.indexOf(endNode) + 1;
                        sb.append(startNodeID).append(" ");
                        sb.append(endNodeID).append(" ");
                        if (b) {
                            sb.append(arc.weight);
                        }
                        sb.append(NL);
                    }
                }
            }
        } else {
            sb.append("*Edges").append(NL);
            for (Node startNode : nodes) {
                int startNodeID = nodes.indexOf(startNode) + 1;
                List<Arc> arcs = network.getArcs(startNode);
                if (arcs != null) {
                    for (Arc arc : arcs) {
                        Node endNode = arc.getAnotherEnd(startNode);
                        int endNodeID = nodes.indexOf(endNode) + 1;
                        if (endNodeID > startNodeID) {
                            sb.append(startNodeID).append(" ");
                            sb.append(endNodeID).append(" ");
                            if (b) {
                                sb.append(arc.weight);
                            }
                            sb.append(NL);
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * pajek 用のデータを出力
     *
     * @param filename
     * @param network
     * @throws java.io.IOException
     */
    public static void outputPajekData(
            String filename, AbstractGraph network) throws IOException {
        String str = generatePajekData(network);
        try (BufferedWriter writer = FileIO.openWriter(filename)) {
            writer.write(str);
            writer.flush();
        }
    }

    /**
     * pajekファイルからネットワークを構成
     *
     * @param filename
     * @param title
     * @return
     * @throws IOException
     */
    public static AbstractNetwork createNetworkFromFile(
            String filename, String title) throws IOException {
        //ネットワーククラスのインスタンスを生成
        AbstractNetwork network = new AbstractNetwork(title) {
            @Override
            public void construct() {
            }
        };
        try (BufferedReader reader = FileIO.openReader(filename)) {
            readFile(reader, network);
        }
        return network;
    }

    private static void readFile(BufferedReader reader,
            AbstractNetwork network) throws IOException {
        ReadMode mode = ReadMode.None;
        String line;
        int numNode = 0;
        //頂点の情報の読み始め
        while ((line = reader.readLine()) != null) {
            Pattern p = Pattern.compile("^\\*Vertices\\s+(\\d+)");
            Matcher m = p.matcher(line);
            if (m.find()) {
                numNode = Integer.valueOf(m.group(1));
                mode = ReadMode.ReadNode;
                break;
            }
        }
        if (mode != ReadMode.ReadNode) {
            return;
        }
        Node nodeArray[] = new Node[numNode];
        while ((line = reader.readLine()) != null) {
            if (line.contains("*Arcs")) {//辺の情報が始まったら次へ
                mode = ReadMode.ReadArcs;
                network.setDirected(true);
                break;
            }
            if (line.contains("*Edges")) {//辺の情報が始まったら次へ
                mode = ReadMode.ReadEdges;
                network.setDirected(false);
                break;
            }
            //実際に頂点の情報を読み込む
            readNodeFromString(line, nodeArray);
        }
        for (Node nn : nodeArray) {
            network.addNode(nn);
        }
        if (mode != ReadMode.ReadArcs && mode != ReadMode.ReadEdges) {
            return;
        }

        while ((line = reader.readLine()) != null) {
            connectNodes(line, network);
        }
    }

    /**
     * 実際に辺の情報を読み込む
     *
     * @param line
     * @param network
     */
    static private void connectNodes(String line, AbstractNetwork network) {
        String str[] = line.split("\\s");
        int i = Integer.valueOf(str[0]);
        int j = Integer.valueOf(str[1]);
        Node f = network.getNodes().get(i - 1);
        Node t = network.getNodes().get(j - 1);
        if (str.length == 3) {
            double w = Double.valueOf(str[2]);
            network.addArc(f, t, "", w);
        } else {
            network.addArc(f, t, "");
        }
    }

    /**
     * 実際に頂点の情報を読み込む
     *
     * @param line
     * @param nodeArray
     */
    static private void readNodeFromString(String line, Node nodeArray[]) {
        Pattern p = Pattern.compile("^(\\d+)\\s+\"(\\S+)\""
                + "(\\s+(\\d+\\.\\d+)\\s+(\\d+\\.\\d+)"
                + "\\s+(\\d+\\.\\d+))?");
        Matcher m = p.matcher(line);
        if (m.find()) {
            String label = m.group(2);
            int index = Integer.valueOf(m.group(1)) - 1;
            Node node = new Node(label);
            if (m.group(3) != null) {
                double x = Double.valueOf(m.group(4));
                double y = Double.valueOf(m.group(5));
                double z = Double.valueOf(m.group(6));
            }
            nodeArray[index] = node;
        }
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        AbstractNetwork network = createNetworkFromFile("test.net", "er");
        outputPajekData("out.net", network);
    }

}
