/**
 * Class that holds a model of a flight, that is represented by the 
 * abreviation of the destination airporta and a thre-number unique 
 * code that indentifies it    
 * 
 * @author Bruno Concli
 * @author Miguel Lopes
 */
public class Flight {
    private String airportCode;
    private String flightNumber;
    
    /**
     * Class constructor 
     * 
     * @param airportCode
     * @param flightNumber
     */
    public Flight(String airportCode, String flightNumber) {
        this.airportCode = airportCode;
        this.flightNumber = flightNumber;
    }
    
    /**
     * 
     * @return attribute airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * 
     * @return attribute flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns a string containing the attributes airportCode and flightNumber
     */
    @Override
    public String toString() {
        return this.airportCode + " " + this.getFlightNumber();
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

        Flight f = (Flight) obj;

        if (!this.airportCode.equals(f.airportCode)) return false;
        if (!this.flightNumber.equals(f.flightNumber)) return false;

        return true;
    }
}
