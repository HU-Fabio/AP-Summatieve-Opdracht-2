import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

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

    public FSM createFSMMachine() {
        System.out.println("Name of the FSM machine:");

        Scanner FSMInputName = new Scanner(System.in);
        String FSMName = FSMInputName.nextLine();

        System.out.println("Type of FSM machine (enter ? to see all types)");

        Scanner FSMTypeInput = new Scanner(System.in);
        String FSMType = FSMTypeInput.nextLine();
        
        if (FSMName == null || FSMType == null) {
            System.out.println("Name or Type cannot be null of the FSM machine");
            return null;
        } else if (FSMType.toUpperCase().equals("TEXT") || FSMType.toUpperCase().equals("CHANCE")) {
            FSM newFSM = new FSM(FSMName, FSMType);
            addMachine(newFSM);

            System.out.println("FSM Generated! Now let's add some nodes to it!");

            return newFSM;
        } else if(FSMType.equals("?")) {
            System.out.println("FSM Types");
            System.out.println("* chance");
            System.out.println("* text");
            return null;
        } else {
            System.out.println("Sorry, couldn't determine which type of FSM");
            return null;
        }
    }

    public Serializable createNode() {
        System.out.println("What is the name of the node?");

        Scanner nodeInputName = new Scanner(System.in);
        String nodeName = nodeInputName.nextLine();

        if (nodeName.equals("")) {
            System.out.println("You have to give the node a name!");
            return null;
        }

        System.out.println("Is it an end node? (true or false)");

        Scanner nodeInputEnd = new Scanner(System.in);
        String nodeEnd = nodeInputEnd.nextLine();

        System.out.println("To which FSM is this node related?");

        for (int i = 0; i < this.machines.size(); i++) {
            System.out.println("[ " + i + " ] " + this.machines.get(i).getName() + " Type: " +  this.machines.get(i).getType());
        }

        Scanner FSMRelationInput = new Scanner(System.in);
        String FSMRelation = FSMRelationInput.nextLine();

        FSM machine = null;

        if (FSMRelation.equals("")) {
            System.out.println("This machine doesn't exist!");
            return null;
        } else {
            try {
                machine = this.machines.get(Integer.parseInt(FSMRelation));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("This machine doesn't exist: " + e);
                return null;
            } catch (NumberFormatException e) {
                System.out.println("Sorry, we couldn't determine which machine this node is related to: " + e);
                return null;
            }

            if (machine == null) {
                System.out.println("No machine defined");
                return null;
            }
        }

        boolean end;
        if (nodeEnd.toUpperCase().equals("FALSE")) {
            end = false;
        } else if (nodeEnd.toUpperCase().equals("TRUE")){
            end = true;
        } else {
            System.out.println("Sorry, we couldn't determine if it's an end node");
            return null;
        }
        Node newNode = new Node(nodeName, end, machine);
        addNode(newNode);

        System.out.println("Do you want to create another node? (true or false)");

        Scanner createAnotherInput = new Scanner(System.in);
        String createAnother = createAnotherInput.nextLine();

        if (createAnother.toUpperCase().equals("TRUE")) {
            return false;
        } else if (createAnother.toUpperCase().equals("FALSE")) {
            return true;
        } else {
            System.out.println("Sorry we couldn't determine if you wanted to make another");
            return null;
        }


    }

    public Node createTransition() {
        System.out.println("These are the current created nodes:");
        return null;
    }
}
