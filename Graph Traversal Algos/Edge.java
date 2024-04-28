public class Edge implements Comparable<Edge> {

    private int from;

    private int to;

    private int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.getWeight()) {
            return 0;
        }
        if (this.weight > o.getWeight()) {
            return 1;
        } else {
            return -1;
        }
    }
}