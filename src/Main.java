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

                if (currentNode.equals(true)) {
                    nodeCreated = false;
                }

            }

            boolean transitionCreated = true;

            while(transitionCreated) {

            }


            running = false;
        }
    }
}
