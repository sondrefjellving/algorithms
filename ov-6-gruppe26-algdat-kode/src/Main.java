import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(Main.class.getResourceAsStream("ø6g1"));
            BufferedReader br = new BufferedReader(isr);

            Graph graph = getGraph(br);

            System.out.println(graph.toString());
            graph.bfs(graph.getNode(1));
            System.out.println(graph.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Graph getGraph(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numberOfNodes = Integer.parseInt(st.nextToken());
        int numberOfEdges = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(numberOfNodes, numberOfEdges);

        for (int i = 0; i < numberOfNodes; ++i) graph.addNode(new Node(i), i); // adds "empty" nodes to graph

        for (int i = 0; i < numberOfEdges; ++i) {
            st = new StringTokenizer(br.readLine());                                // reads new line from file
            int from = Integer.parseInt(st.nextToken());                            // first value represents the 'from' value of a new edge
            int to = Integer.parseInt(st.nextToken());                              // second value represents the 'to' value of a new edge
            System.out.println(from + " ---> " + to);
            Edge edge = new Edge(graph.getNode(to), graph.getNode(from).getFirstEdge()); //
            graph.getNode(from).setFirstEdge(edge);
        }

        return graph;
    }
}
