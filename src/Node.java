import java.util.HashMap;

public class Node {
    private String name;
    private HashMap<String, Node> transition = new HashMap<>();
    private Boolean isEndNode;
    private FSM machine;

    public Node(String name, Boolean isEndNode, FSM machine) {
        this.name = name;
        this.isEndNode = isEndNode;
        this.machine = machine;
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

    public FSM getMachine() {
        return machine;
    }

    public void setMachine(FSM machine) {
        this.machine = machine;
    }
}
