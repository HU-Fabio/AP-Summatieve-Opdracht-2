import java.util.HashMap;

public class Node {
    private String name;
    private HashMap<String, Node> transition = new HashMap<>();
    private Boolean isEndNode;

    public Node(String name, Boolean isEndNode) {
        this.name = name;
        this.isEndNode = isEndNode;
    }

    public void addNode(String k, Node n) {
        this.transition.put(k, n);
    }

    public void removeNode(String k) {
        this.transition.remove(k);
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Node> getTransition() {
        return transition;
    }

    public Boolean getEndNode() {
        return isEndNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", transition=" + transition +
                ", isEndNode=" + isEndNode +
                '}';
    }
}