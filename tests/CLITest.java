import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {

    @Test
    void machines() {
        FSM fsm = new FSM("test", "CHANCE");

        CLI cli = new CLI();

        cli.addMachine(fsm);

        ArrayList<FSM> fsms = new ArrayList<>();

        fsms.add(fsm);

        assertEquals(cli.getMachines(), fsms);

    }

    @Test
    void nodes() {
        FSM fsm = new FSM("test", "CHANCE");
        CLI cli = new CLI();
        Node node = new Node("test", false, fsm);

        cli.addNode(node);

        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(node);

        assertEquals(cli.getNodes(), nodes);
    }

    @Test
    void FSMMachineConstructor() {
        FSM machine = new FSM("test", "text");

        assertEquals(machine.getName(), "test");
        assertEquals(machine.getType(), "TEXT");


    }

    @Test
    void FSMMachineAddNode() {
        FSM machine = new FSM("test", "text");

        Node node = new Node("test", false, machine);

        machine.setStartNode(node);
        machine.setCurrentNode(node);

        assertEquals(machine.getCurrentNode(), node);
        assertEquals(machine.getStartNode(), node);
    }

    @Test
    void createFSM() {
        CLI cli = new CLI();

        assertEquals(cli.createFSMMachine("test", "text").getName(), "test");
        assertEquals(cli.createFSMMachine("test", "text").getType(), "TEXT");
    }

    @Test
    void findMachineNoMachine() {
        CLI cli = new CLI();

        assertNull(cli.findMachine());

    }

    @Test
    void findMachineNormal() {
        CLI cli = new CLI();

        cli.createFSMMachine("test", "text");

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        assertNotNull(cli.findMachine());

    }

    @Test
    void findMachineUndefined() {
        CLI cli = new CLI();

        cli.createFSMMachine("test", "text");

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        assertNull(cli.findMachine());

    }

    @Test
    void findNodeNoNode() {
        CLI cli = new CLI();

        FSM machine = cli.createFSMMachine("test", "text");

        assertNull(cli.findNode(machine));
    }

    @Test
    void findNodeNormal() {
        CLI cli = new CLI();

        FSM machine = cli.createFSMMachine("test", "text");

        Node node = new Node("s1", false, machine);

        cli.addNode(node);

        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);

        assertEquals(cli.findNode(machine), node);
    }

    @Test
    void findNodeUndefined() {
        CLI cli = new CLI();

        FSM machine = cli.createFSMMachine("test", "text");

        Node node = new Node("s1", false, machine);

        cli.addNode(node);

        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        assertNull(cli.findNode(machine));
    }
}