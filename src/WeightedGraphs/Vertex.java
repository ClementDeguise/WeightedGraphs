package WeightedGraphs;



import java.util.Arrays;
import java.util.Objects;

/** define a Vertex
 *
 * package-private access = no explicit modifier
 * **/
class Vertex {

    private int[] coordinates;
    int cost;

    // obstacles can be seen as vertices with cost infinity

    Vertex(int[] coordinates, int cost) {
        this.coordinates = coordinates;
        this.cost = cost;
    }

    int[] getCoord() { return coordinates; }
    int getX() { return coordinates[0]; }
    int getY() { return coordinates[1]; }
    int getCost() { return this.cost; }


    //equals and hashcode need to be overridden or 2 instances with same label will have a different hashcode thus being recognized as different
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // if this class instance is equals to the object (here vertex)
        if (o == null || getClass() != o.getClass()) return false;  // if null parameter or different classes
        Vertex vertex = (Vertex) o;  // convert the object to a vertex
        return Arrays.equals(this.coordinates, vertex.coordinates) && cost == vertex.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates,cost);
    }
/*
    @Override
    boolean equals(Vertex v) {
        if (v == null) return false;
        return v.label.equals(this.label);
    }

    // simple but efficient implementation with prime numbers
    @Override
    int hashcode() {
        int hash = 7;
        hash = 31 * hash + (label == null ? 0 : label.hashCode());
        return hash;
    }
*/




}
