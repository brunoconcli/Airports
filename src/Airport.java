/**
 * @author Bruno Concli
 * @author Miguel Lopes
 */
import DataStructure.Exceptions.LinkedListException;
import DataStructure.LinkedList.LinkedList;
public class Airport {
    private String city;
    private String code;
    private LinkedList<Flight> flightList;

    /**
     * Class constructor
     * 
     * @param city
     * @param code
     * @throws Exception in case code's length is different than 3
     */
    public Airport(String city, String code) throws Exception {
        if (code.length() != 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = new LinkedList<Flight>();
    }

    /**
     * Class constructor that receives a linked list of flights  
     * 
     * @param city
     * @param code
     * @param flightList
     * @throws Exception in case code's length is different than 3
     */
    public Airport(String city, String code, LinkedList<Flight> flightList) throws Exception {
        if (code.length() != 3)
            throw new Exception("Airport code cannot be bigger than 3 letters");
        this.city = city;
        this.code = code;
        this.flightList = flightList;
    }

    /**
     * 
     * @return attribute city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 
     * @return attribute code of airport
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 
     * @return attribute list of flights
     */
    public LinkedList<Flight> getFlightList() {
        return this.flightList;
    }

    /**
     *  
     * @param code
     * @return an object of Flight according to the passed flight number (code) 
     * @throws Exception
     */
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
    
    /**
     * Returns position in which the first passed object of Flight can be found
     * 
     * @param flight
     * @return
     * @throws Exception
     */
    public int getIndexOfFlight(Flight flight) throws Exception {
        return this.flightList.indexOf(flight);
    }

    /**
     * Adds an object of flight into the first position of flightList
     * 
     * @param flight
     * @throws LinkedListException according to the miss-passed parameter flight as null 
     */
    public void pushFirst(Flight flight) throws LinkedListException {
        this.flightList.addIntoFirst(flight);
    }

    /**
     * Adds an object of flight into the last position of flightList
     * 
     * @param flight
     * @throws LinkedListException according to the miss-passed parameter flight as null 
     */
    public void pushLast(Flight flight) throws LinkedListException {
        this.flightList.addIntoLast(flight);
    }

    /**
     * Removes the element that contains the passed code out of the attribute flightList 
     * 
     * @param code
     * @throws Exception in case code's length is different than 3
     */
    public void popFlightByCode(String code) throws Exception {
        if (code.length() != 3)
            throw new Exception("Flight number passed is not valid");
            
        this.flightList.removeInto(this.getIndexOfFlight(this.getFlightByCode(code)));
    }

    /** 
     * Returns a string containing the object's values of the attributes city and code 
     */
    @Override
    public String toString() {
        return this.city + " " + this.code;
    }

    /**
     * Returns the hash value of the class Airport, which is used for 
     * comparisions between the attributes of the class and must be consistent
     */
    @Override
    public int hashCode() {
        int hash = 2;

        hash = 3 * hash + (this.city).hashCode();
        hash = 5 * hash + (this.code).hashCode();
        hash = 7 * hash + new LinkedList<Flight>().hashCode();
        
        if (hash < 0) hash = -hash;
        return hash;
    }

    /**
     * Returns a boolean value, being True in case all the attributes of the 
     * passed object are the same as this class, and False in case any of them don't
     */
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
