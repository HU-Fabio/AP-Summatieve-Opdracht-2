import java.io.Serializable;
import java.util.Scanner;

public class Menu {

    public boolean mainMenu (CLI cli) throws Exception {
        System.out.println("Choose an option to run: ");
        System.out.println();
        System.out.println("[ 0 ] Create an FSM");
        System.out.println("[ 1 ] Create a Node");
        System.out.println("[ 2 ] Create a transition");
        System.out.println("[ 3 ] Simulate a FSM");
        System.out.println("[ 4 ] Quit");

        Scanner optionInput = new Scanner(System.in);
        String option = optionInput.nextLine();

        return menuChoice(option, cli);


    }

    public boolean menuChoice(String input, CLI cli) throws Exception {
        switch (input) {
            case "0":
                boolean fsmCreated = true;

                while (fsmCreated) {
                    System.out.println("Name of the FSM machine:");

                    Scanner FSMInputName = new Scanner(System.in);
                    String FSMName = FSMInputName.nextLine();

                    System.out.println("Type of FSM machine (enter ? to see all types)");

                    Scanner FSMTypeInput = new Scanner(System.in);
                    String FSMType = FSMTypeInput.nextLine();

                    FSM currentMachine = cli.createFSMMachine(FSMName, FSMType);

                    if (currentMachine != null) {
                        fsmCreated = false;
                    }
                }

                return true;
            case "1":
                boolean nodeCreated = true;

                while (nodeCreated) {
                    System.out.println("What is the name of the node?");

                    Scanner nodeInputName = new Scanner(System.in);
                    String nodeName = nodeInputName.nextLine();

                    if (nodeName.equals("")) {
                        System.out.println("You have to give the node a name!");
                        break;
                    }

                    System.out.println("Is it an end node? (true or false)");

                    Scanner nodeInputEnd = new Scanner(System.in);
                    String nodeEnd = nodeInputEnd.nextLine();


                    Serializable currentNode = cli.createNode(nodeEnd, nodeName);

                    if (currentNode != null) {
                        if (currentNode.equals(true)) {
                            nodeCreated = false;
                        }
                    } else if (currentNode == null) {
                        break;
                    }

                }

                return true;
            case "2":
                boolean transitionCreated = true;

                while (transitionCreated) {
                    System.out.println("For which machine do you want to create transitions?");
                    FSM machine = cli.findMachine();

                    System.out.println("These are the current created nodes for the chosen machine.");
                    System.out.println("For which node do you want to create a transition?");
                    Node node = cli.findNode(machine);

                    System.out.println("These are the current created nodes for the chosen machine.");
                    System.out.println("Where should " + node.getName() + " transition to?");
                    Node nodeTo = cli.findNode(machine);

                    Serializable x = cli.createTransition(machine, node, nodeTo);

                    if (x != null) {
                        if (x.equals(true)) {
                            transitionCreated = false;
                        }
                    }
                }

                return true;
            case "3":
                boolean simulation = true;

                while (simulation) {
                    System.out.println("Which machine do you want to simulate?");

                    FSM machine = cli.findMachine();

                    boolean y = cli.simulate(machine);

                    if (y) {
                        simulation = false;
                    }
                }

                return true;
            case "4":
                System.exit(0);
                return true;
            default:
                System.out.println("Sorry we couldn't find this option");
                return true;
        }
    }

}
