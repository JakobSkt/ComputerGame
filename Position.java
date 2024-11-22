/**
 * Models a position
 * Positions consists of a starting city, a destination city, distance left and total distance between the two cities
 * @author Jakob Skøt Nielsen 202407223
 * @author Daniel Dupont 202407440
 * @version november 2024
 *
 */

public class Position {
    private City from; // City left by player
    private City to; // City player is headed to
    private int distance; // Distance left for player
    private int total; // Total distance between cities

    /**
    * Creates a Position
    * @param from   City the player has left
    * @param to     City the player is headed to
    * @param distance   Distance between the cities
    */
    public Position(City from, City to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.total = distance;
    }
    /**
    * Returns the city the player just left
    * @return left city object
    */
    public City getFrom() { return from; }

    /**
     * Returns the city the player is headed to
     * @return destination city object
     */
    public City getTo() { return to; }

    /**
     * Returns the distance left between the cities
     * @return int distance left
    */
    public int getDistance() { return distance; }

    /**
     * Returns total distance between the cities
     * @return int total distance
    */
    public int getTotal() { return total; }

    /**
     * Moves the player by decreasing distance left
     * @return true if distance was changed
    */
    public boolean move() {
        if(distance > 0) {
            distance--;
            return true;
        }
        return false;
    }

    /**
     * Turns the player around by swapping starting city and destination city
     */
    public void turnAround() {
        City tempCity = from;
        this.from = this.to;
        this.to = tempCity;
        distance = total - distance;
    }

    /**
     * Checks if player has arrived at destination city
     * @return true if distance is less than 0
     */
    public boolean hasArrived() {
        return distance == 0;
    }

    /**
     * Makes String representation of Position
     * @return String representation of object
     */
    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString() + " : " + distance + "/" + total;
    }

    /**
     * Generate hash code based on starting city field, destination city field, distance field, total field and prime numbers
     * @return generated int hash code
     */
    @Override
    public int hashCode() {
        return from.hashCode() * 31 +
                to.hashCode() * 11 +
                Integer.hashCode(distance) * 19 +
                Integer.hashCode(total) * 13;
    }

    /**
     * Check if Object is equal to this based on starting city field, destination city field, distance field and total field
     * @param obj    Object to check if equal to
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Position other = (Position) obj;
        return from.equals(other.from) && to.equals(other.to) && distance == other.distance && total == other.total;
    }
}
