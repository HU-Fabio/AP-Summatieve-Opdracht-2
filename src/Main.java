import java.io.Serializable;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to FSM creator");
            System.out.println();
            CLI cli = new CLI();

            boolean fsmCreated = true;
            while(fsmCreated) {
                FSM currentMachine = cli.createFSMMachine();

                if(currentMachine != null) {
                    fsmCreated = false;
                }
            }

            boolean nodeCreated = true;

            while(nodeCreated) {
                Serializable currentNode = cli.createNode();

                if (currentNode != null) {
                    if (currentNode.equals(true)) {
                        nodeCreated = false;
                    }
                }

            }

            boolean transitionCreated = true;

            while(transitionCreated) {
                Serializable x = cli.createTransition();

                if (x != null) {
                    if (x.equals(true)) {
                        transitionCreated = false;
                    }
                }
            }

            boolean simulation = true;

            while(simulation) {
                boolean y = cli.simulate();

                if (y) {
                    simulation = false;
                }
            }

            running = false;
        }
    }
}
