import java.util.ArrayList;

public class CLI {
    private ArrayList<FSM> machines;
    private ArrayList<Node> nodes;

    public CLI() {
        this.machines = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public ArrayList<FSM> getMachines() {
        return machines;
    }

    public void addMachine(FSM machine) {
        this.machines.add(machine);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public boolean createFSMMachine(String name, String type) {
        if (name == null || type == null) {
            System.out.println("Name or Type cannot be null of the FSM machine");
            return true;
        } else if (type.toUpperCase().equals("TEXT") || type.toUpperCase().equals("CHANCE")) {
            FSM newFSM = new FSM(name, type);
            addMachine(newFSM);

            System.out.println("FSM Generated! Now let's add some nodes to it!");

            return false;
        } else {
            System.out.println("Sorry, couldn't determine which type of FSM");
            return true;
        }
    }
}
