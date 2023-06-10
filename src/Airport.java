import DataStructure.Exceptions.LinkedListException;
import DataStructure.LinkedList.LinkedList;
public class Airport {
    private String city;
    private String code;
    private LinkedList<Flight> flightList;

    public Airport(String city, String code) throws Exception {
        if (code.length() != 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = new LinkedList<Flight>();
    }

    public Airport(String city, String code, LinkedList<Flight> flightList) throws Exception {
        if (code.length() > 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = flightList;
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

    public Flight getFlightByCode(String code) throws Exception {
        if (code.length() > 3)
            throw new Exception("Flight number passed is not valid");

        for(int i = 0; i < this.flightList.getSize(); i++) {
            Flight fly = this.flightList.getElementAt(i);
            if(fly.getFlightNumber().equals(code))
                return fly;
        }

        throw new Exception("The passed flight has not been found in this airport");
    }
    
    public int getIndexOfFlight(Flight flight) throws Exception {
        return this.flightList.indexOf(flight);
    }

    public void pushFirst(Flight flight) throws LinkedListException {
        this.flightList.addIntoFirst(flight);
    }

    public void pushLast(Flight flight) throws LinkedListException {
        this.flightList.addIntoLast(flight);
    }

    public void popFlightByCode(String code) throws Exception {
        if (code.length() > 3)
            throw new Exception("Flight number passed is not valid");
            
        this.flightList.removeInto(this.getIndexOfFlight(this.getFlightByCode(code)));
    }

    @Override
    public String toString() {
        return this.city + " " + this.code;
    }

    @Override
    public int hashCode() {
        int hash = 2;

        hash = 3 * hash + (this.city).hashCode();
        hash = 5 * hash + (this.code).hashCode();
        hash = 7 * hash + new LinkedList<Flight>().hashCode();
        
        if (hash < 0) hash = -hash;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;

        if (this.getClass() != obj.getClass()) return false;

        Airport a = (Airport) obj;

        if (!this.city.equals(a.city)) return false;
        if (!this.code.equals(a.code)) return false;

        return true;
    }
}
