import org.junit.jupiter.api.Test;

import javax.xml.soap.Text;

import static org.junit.jupiter.api.Assertions.*;

class TextFSMTest {

    @Test
    void Name() {
        TextFSM textFSM = new TextFSM("test");
        textFSM.setName("test1");
        assertEquals("test1", textFSM.getName());
    }

    @Test
    void StartNode() {
        TextFSM textFSM = new TextFSM("test");
        Node node = new Node("testNode", false);

        textFSM.setStartNode(node);

        assertEquals(node, textFSM.getStartNode());
    }

    @Test
    void CurrentNode() {
        TextFSM textFSM = new TextFSM("test");
        Node node = new Node("testNode", false);

        textFSM.setCurrentNode(node);
        assertEquals(node, textFSM.getCurrentNode());
    }

    @Test
    void next() {
        TextFSM textFSM = new TextFSM("test");
        Node node = new Node("testNode", false);

        textFSM.setStartNode(node);

        Node node2 = new Node("testNode2", false);
        Node node3 = new Node("testNode3", true);

        node.addNode("A", node2);
        node.addNode("B", node3);

        assertEquals(node2, textFSM.next("A"));
    }
}