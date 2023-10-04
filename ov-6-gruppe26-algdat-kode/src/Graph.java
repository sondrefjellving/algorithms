public class Graph {
    private int numberOfNodes;
    private int numberOfEdges;
    private Node[] nodes;

    /**
     * Constructor.
     *
     * @param numberOfNodes the number of nodes in the graph.
     * @param numberOfEdges the number of edges in the graph.
     */
    public Graph(int numberOfNodes, int numberOfEdges) {
        this.numberOfNodes = numberOfNodes;
        this.numberOfEdges = numberOfEdges;
        this.nodes = new Node[numberOfNodes];
    }

    /**
     * Adds new node to graph.
     *
     * @param node the node that gets added to the graph.
     * @param i the index that the node gets added to.
     */
    public void addNode(Node node, int i) {
        this.nodes[i] = node;
    }

    /**
     * Returns the node list from graph.
     *
     * @return the nodes list.
     */
    public Node[] getNodes() {
        return this.nodes;
    }


    /**
     * Returns the node from the nodes list at index i.
     *
     * @param i the index of the node that gets returned.
     * @return the node at index i.
     */
    public Node getNode(int i) {
        return nodes[i];
    }


    /**
     * Returns a string representation of the graph and the connection between nodes.
     *
     * @return string representation of the graph.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodes: " + numberOfNodes + " Edges: " + numberOfEdges).append("\n");

        for (int i = 0; i < numberOfNodes; i++) {
            sb.append(nodes[i].getNodeDataAsString()).append(" --> ").append(nodes[i].toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    class Topo_lst {
        boolean found;
        Node next;
    }

    /**
     * Recursive method that finds the topological order of the graph.
     * @param n starting node.
     * @param l node list
     * @return the topological order of the graph.
     */
    Node df_topo(Node n, Node l) {
        Topo_lst nodeData = (Topo_lst) n.getNodeData();
        if (nodeData.found) return l;
        nodeData.found = true;
        for (Edge e = n.getFirstEdge(); e != null; e = e.getNextEdge()) {
            l = df_topo(e.getToNode(), l);
        }
        nodeData.next = l;
        return n;
    }

    /**
     * Method that finds the topological order of the graph.
     * @return  the topological order of the graph.
     */
    Node topologisort() {
        Node l = null;
        for (int i = numberOfNodes; i-- > 0;) {
            nodes[i].setNodeData(new Topo_lst());
        }

        for (int i = N; i-- > 0;) {
            l = df_topo(nodes[i], l);
        }
        return l;
    }
}
