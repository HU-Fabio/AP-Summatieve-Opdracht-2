import java.util.ArrayList;

public interface FSM {
    public Node next(String key);

    public ArrayList<Node> generateEndNodes();
}
