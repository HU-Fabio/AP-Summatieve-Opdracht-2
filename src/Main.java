import java.io.Serializable;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to FSM creator");
            System.out.println();
            CLI cli = new CLI();

            Menu menu = new Menu();
            boolean inMainMenu = true;

            while (inMainMenu) {
                inMainMenu = menu.mainMenu(cli);
            }

            running = false;
        }
    }
}
