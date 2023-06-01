public class Flight {
    private String airportCode;
    private String flightNumber;
    
    public Flight(String airportCode, String flightNumber) {
        this.airportCode = airportCode;
        this.flightNumber = flightNumber;
    }
    
    public String getAirportCode() {
        return airportCode;
    }
    
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return this.airportCode + " " + this.getFlightNumber();
    }
}
