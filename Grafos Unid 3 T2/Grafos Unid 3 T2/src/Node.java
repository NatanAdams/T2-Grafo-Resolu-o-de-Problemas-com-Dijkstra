public class Node implements Comparable<Node> {
    public int id;
    public long dist;

    public Node(int id, long dist) {
        this.id = id;
        this.dist = dist;
    }

    @Override
    public int compareTo(Node other) {
        return Long.compare(this.dist, other.dist);
    }
}