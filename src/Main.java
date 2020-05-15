import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to FSM creator");
            System.out.println();
            CLI cli = new CLI();

            boolean fsmCreated = true;
            while(fsmCreated) {
                System.out.println("Name of the FSM machine:");

                Scanner FSMInputName = new Scanner(System.in);
                String FSMName = FSMInputName.nextLine();

                System.out.println("Type of FSM machine");

                Scanner FSMTypeInput = new Scanner(System.in);
                String FSMType = FSMTypeInput.nextLine();

                fsmCreated = cli.createFSMMachine(FSMName, FSMType);
            }


            running = false;
        }
    }
}
