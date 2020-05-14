public class Main {
    public static void main(String[] args) {
        TextFSM textFSM = new TextFSM("textFSM");

        Node n1 = new Node("N1", false);
        Node n2 = new Node("N2", false);
        Node n3 = new Node("N3", true);

        textFSM.setStartNode(n1);

        n1.addNode("A", n2);
        n1.addNode("B", n3);

        System.out.println(n1.toString());
        System.out.println(textFSM.next("B").toString());
    }
}
