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
