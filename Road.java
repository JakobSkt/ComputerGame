/**
 * Models a road
 * Roads connect two cities in one direction with a distance
 * @author Jakob Skøt Nielsen 202407223
 * @author Daniel Dupont 202407440
 * @version 06-11-2024
 *
*/

public class Road implements Comparable<Road>{
    private City from; // City the road is starting in
    private City to; // City the road is ending in
    private int length; // Length of the road

    /**
    * Creates a road object
    * @param from   City to start the road from
    * @param to     City to end the road in
    * @param length Length of the road
    */
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }
    /**
    * Returns starting city of road
    * @return starting city
     */
    public City getFrom() { return from; }

    /**
     * Returns destination city of road
     * @return destination city
     */
    public City getTo() { return to; }

    /**
     * Returns length of the road
     * @return road length
     */
    public int getLength() { return length; }

    /**
    * Creates a String representation of road
    * @return String model of object
     */
    @Override
    public String toString() {
        return from.toString() + " -> " + to.toString() + " : " + length;
    }

    /**
    * Compare two Road objects
    * @param other  Other object to compare against
    * @return int based on comparisons in the following order: starting city, ending city, length
     */
    @Override
    public int compareTo(Road other) {
       if(this.from.equals(other.from)) {
           if(this.to.equals(other.to)) {
               return this.length - other.length;
           };
           return to.compareTo(other.to);
       };
        return from.compareTo(other.from);
    }

    /**
     * Generate hash code based on starting city field and destination city field and prime numbers
     * @return generated int hash code
     */
    @Override
    public int hashCode() {
        return from.hashCode() * 31 +
                to.hashCode() * 11 +
                 Integer.hashCode(length) * 19;
    }

    /**
     * Check if Object is equal to this based on starting city field, destination city field and length field
     * @param obj    Object to check if equal to
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Road other = (Road) obj;
        return from.equals(other.from) && to.equals(other.to) && length == other.length;
    }

}
