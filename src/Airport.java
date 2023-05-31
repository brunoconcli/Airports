import LinkedList.LinkedList;
import LinkedList.Node;

public class Airport {
    private String city;
    private String code;
    private LinkedList<Flight> flightList;

    public Airport(String city, String code) {
        this.city = city;
        this.code = code;
        this.flightList = new LinkedList<Flight>();
    }

    public Airport(String city, String code, LinkedList<Flight> flightList) {
        this.city = city;
        this.code = code;
        this.flightList = new LinkedList<Flight>();
    }

    public String getCity() {
        return this.city;
    }

    public String getCode() {
        return this.code;
    }

    public LinkedList<Flight> getFlightList() {
        return this.flightList;
    }

    public Flight getFlightByCode(String num) throws Exception {
        Node<Flight> current = this.flightList.getFirst(); 
        while(!current.getInfo().getflightNumber().equals(num)) {
            if (current.getNext() != null)
                current = current.getNext();
            else
                throw new Exception("The passed flight has not been found in this airport");
        }
        return current.getInfo();
    }
    
    public Flight getFlightByDestination(int destination) throws Exception {
        Node<Flight> current;
        for (current = this.flightList.getFirst(); current.getInfo().getDestinationIndex() != destination; current = current.getNext());
        return current.getInfo();
    }

    public int getIndexOfFlight(Flight flight) throws Exception {
        return this.flightList.getIndexOf(flight);
    }

    public void pushLast(Flight flight) {
        this.flightList.pushLast(flight);
    }

    public void popFlight(String flightCode) throws Exception {
        if (code.length() > 3)
            throw new Exception("Flight number passed is not valid");
            
        this.flightList.popNodeAt(this.getIndexOfFlight(this.getFlightByCode(flightCode)));
        }

}
