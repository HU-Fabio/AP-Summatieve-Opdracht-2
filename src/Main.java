public class Main {
    public static void main(String[] args) throws Exception {
        FSM FSM = new FSM("textFSM");

        Node n1 = new Node("N1", false);
        Node n2 = new Node("N2", false);
        Node n3 = new Node("N3", true);
        Node n4 = new Node("N4", true);

        FSM.setStartNode(n1);

        n1.addNode("0.5", n2);
        n1.addNode("0.3", n3);
        n1.addNode("0.2", n4);

        System.out.println(FSM.nextKey());
    }
}
