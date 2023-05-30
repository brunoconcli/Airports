public class Flight {
    private int destinationIndex;
    private String flightNumber;
    
    public Flight(int destination, String flightNumber) {
        this.destinationIndex = destination;
        this.flightNumber = flightNumber;
    }
    
    public int getDestinationIndex() {
        return destinationIndex;
    }
    
    public void setDestinationIndex(int destinationIndex) {
        this.destinationIndex = destinationIndex;
    }

    public String getflightNumber() {
        return flightNumber;
    }
    
    public void setflightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Override
    public String toString() {
        return this.destinationIndex + " " + this.getflightNumber();
    }
}
