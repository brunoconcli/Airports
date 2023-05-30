import LinkedList.LinkedList;

public class App {
    public static void main(String[] args) {
        try {       
            Airport brasilia = new Airport("Brasilia", "BSB"); 
            brasilia.pushLast(new Flight(5, "107"));
            brasilia.pushLast(new Flight(4, "270"));
            brasilia.pushLast(new Flight(3, "285"));
            brasilia.pushLast(new Flight(2, "090"));
            
            
            System.out.println(brasilia.getFlightList());
            brasilia.popFlight(3);
            System.out.println(brasilia.getFlightList());


        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
