import LinkedList.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<String> list = new LinkedList<>();        
        list.pushLast("bus");
        list.pushLast("car");
        list.pushLast("bike");
        list.pushLast("boat");
        list.pushLast("monocycle");
        
        System.out.println(list.getNodeAt(1));
        System.out.println(list.getIndexOf("banana boat"));
        System.out.println(list.getSize());


        System.out.println(list.toString()); 
        list.popNodeAt(0);
        list.popNodeAt(0);
        list.popNodeAt(0);
        list.popNodeAt(0);
        list.popNodeAt(0);
        System.out.println(list); 
    }
}
