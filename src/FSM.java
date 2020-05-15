import java.util.*;

public class FSM {
    private String name;
    private Node startNode;
    private Node currentNode;
    private String type;

    public FSM(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node next(String key) {
//      Get transitions of the current node
        HashMap<String, Node> transitions = getCurrentNode().getTransition();
//      Set the new current node
        setCurrentNode(transitions.get(key));

        return transitions.get(key);
    }

    public String nextKey() throws Exception {
        HashMap<String, Node> transitions = getCurrentNode().getTransition();
        List<String> keys = new ArrayList<>(transitions.keySet());

        keys.sort(Collections.reverseOrder());

        int counter = 0;
        Random rand = new Random();
        float randomDouble = rand.nextFloat();

        while(counter < keys.size()) {
            if (counter != 0) {
//              Sums up till certain key
                float sum = 0;
                for(int i = 0; i <= counter; i++) {
                    sum += Float.parseFloat(keys.get(i));
                }
                if (randomDouble < sum) {
                    return keys.get(counter);
                }
            } else {
                if (randomDouble < Float.parseFloat(keys.get(counter))) {
                    return keys.get(counter);
                }
            }
            counter++;

        }

        throw new Exception("No key found");
    }
}
