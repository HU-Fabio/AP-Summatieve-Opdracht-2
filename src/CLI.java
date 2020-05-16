import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
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
            FSM newFSM = new FSM(FSMName, FSMType.toUpperCase());
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

        FSM machine = findMachine();

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

        if (machine.getStartNode() == null) {
            System.out.println("Is this new node the starting node of the machine? (true or false)");
            if (Objects.equals(continueChoice(), false)) {
                machine.setStartNode(newNode);
            }
        }

        System.out.println("Do you want to create another node? (true or false)");

        return continueChoice();

    }

    public Serializable createTransition() {
        System.out.println("For which machine do you want to create transitions?");
        FSM machine = findMachine();

        System.out.println("These are the current created nodes for the chosen machine.");
        System.out.println("For which node do you want to create a transition?");
        Node node = findNode(machine);

        System.out.println("These are the current created nodes for the chosen machine.");
        System.out.println("Where should " + node.getName() + " transition to?");
        Node nodeTo = findNode(machine);

        HashMap<String, Node> transitions = node.getTransition();

        if(transitions.containsValue(nodeTo)) {
            System.out.println("The current selected node already has a transition to this node!");
            return null;
        } else if(node.getEndNode()) {
            System.out.println("Sorry, this node is an end node!");
            return null;
        }

        String machineType = machine.getType();

        if (machineType.equals("CHANCE")) {
//          TODO: Fix error with same percentage (If key already exists add 0.1 percentage to it
            if (transitions.keySet().size() == 0) {
                System.out.println("You have 100% left to divide in the transitions");
            } else {
                float leftToDivde = 1;
                for(String key : transitions.keySet()) {
                    leftToDivde -= Float.parseFloat(key);
                }
                System.out.println("You have " + leftToDivde * 100 + "% left to divide in the transitions");
            }

            System.out.println("How much chance should this transition have? (In percentage)");

            Scanner chanceInput = new Scanner(System.in);
            String chance = chanceInput.nextLine();

            float chanceF = Float.parseFloat(chance) / 100;

            node.addNode(Float.toString(chanceF), nodeTo);

        } else if (machineType.equals("TEXT")) {
            System.out.println("What is the key of the transition?");

            Scanner keyInput = new Scanner(System.in);
            String key = keyInput.nextLine();

            node.addNode(key, nodeTo);

        } else {
            System.out.println("Couldn't determine machine type!");
            return null;
        }

        System.out.println("Do you want to create an another transition? (true or false)");

        return continueChoice();
    }

    public boolean simulate() throws Exception {
        System.out.println("Which machine do you want to simulate?");

        FSM machine = findMachine();

        ArrayList<String> pathHistory = new ArrayList<>();

        if (machine.getType().equals("CHANCE")) {
            while(!machine.getCurrentNode().getEndNode()) {
                pathHistory.add(machine.getCurrentNode().getName());
                machine.next(machine.nextKey());
                pathHistory.add(machine.getCurrentNode().getName());
            }
        } else if (machine.getType().equals("TEXT")) {
            System.out.println("What path should the simulation take? (eg. A>B>B>A)");

            Scanner sequenceInput = new Scanner(System.in);
            String sequenceFull = sequenceInput.nextLine();

            String[] sequenceParts = sequenceFull.split(">");

            for (String sequencePart : sequenceParts) {
                Node currentNode = machine.getCurrentNode();
                pathHistory.add(currentNode.getName());
                if (currentNode.getTransition().containsKey(sequencePart)) {
                    machine.next(sequencePart);
                } else {
//                  TODO: Add detailed error path
                    System.out.println("Sorry, this transition path doesn't exist!");
                    return false;
                }
            }
        }

        System.out.println("The simulation has been finished");
        System.out.println();
        System.out.println("The simulation walked through these nodes:");

        for (int i = 0; i < pathHistory.size(); i++) {
            try {
                System.out.println(pathHistory.get(i) + " -> " + pathHistory.get(i + 1));
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }

        return true;

    }

    public Node findNode(FSM machine) {
        int counter = 0;
        for (Node node : this.nodes) {
            if(node.getMachine().equals(machine)) {
                System.out.println("[ " + counter + " ] " + node.getName() + " Transitions to: " + node.getTransition());
            }
            counter++;
        }

        Scanner nodeChoiceInput = new Scanner(System.in);
        String nodeChoice = nodeChoiceInput.nextLine();

        Node node = null;

//      TODO: check if this can be moved to the catch
        if (nodeChoice.equals("")) {
            System.out.println("This node doesn't exist");
            return null;
        } else {
            try {
                node = this.nodes.get(Integer.parseInt(nodeChoice));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Sorry we couldn't find the node: " + e);
                return null;
            }
        }

        return node;
    }

    public FSM findMachine() {
        for (int i = 0; i < this.machines.size(); i++) {
            System.out.println("[ " + i + " ] " + this.machines.get(i).getName() + " Type: " +  this.machines.get(i).getType());
        }

        Scanner FSMRelationInput = new Scanner(System.in);
        String FSMRelation = FSMRelationInput.nextLine();

        FSM machine = null;

//      TODO: check if this can be moved to the catch
        if (FSMRelation.equals("")) {
            System.out.println("This machine doesn't exist!");
            return null;
        } else {
            try {
                machine = this.machines.get(Integer.parseInt(FSMRelation));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Sorry, we couldn't find the machine: " + e);
                return null;
            }

            if (machine == null) {
                System.out.println("No machine defined");
                return null;
            }
        }

        return machine;
    }

    private Serializable continueChoice() {
        Scanner transitionInput = new Scanner(System.in);
        String transition = transitionInput.nextLine();

        if (transition.toUpperCase().equals("TRUE")) {
            return false;
        } else if (transition.toUpperCase().equals("FALSE")) {
            return true;
        } else {
            System.out.println("Sorry we couldn't determine if you wanted to make another");
            return null;
        }
    }
}
