import LinkedList.LinkedList;

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

    public LinkedList<Flight> getLinkedList() {
        return this.flightList;
    }

    public void pushLast(Flight flight) {
        this.flightList.pushLast(flight);
    }
    //                                              Flight
// int destination part of info (this.flightList.getInfo().getDestinationIndex())
    public void popFlightAt(int destination) throws Exception {
        if (destination < 0)
            throw new Exception("Destination passed is not valid");
        this.popFlightAt(destination);
    }

}
