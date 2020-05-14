import java.util.ArrayList;
import java.util.HashMap;

public class TextFSM implements FSM {
    private String name;
    private Node startNode;
    private Node currentNode;

    public TextFSM(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        if (currentNode == null) {
            setCurrentNode(startNode);
        }
        this.startNode = startNode;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    @Override
    public Node next(String key) {
//      Get transitions of the current node
        HashMap<String, Node> transitions = getCurrentNode().getTransition();
//      Set the new current node
        setCurrentNode(transitions.get(key));

        return transitions.get(key);
    }

    @Override
    public ArrayList<Node> generateEndNodes() {
        return null;
    }
}
