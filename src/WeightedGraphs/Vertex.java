package WeightedGraphs;


import java.util.Comparator;
import java.util.Objects;

/** define a Vertex
 *
 * package-private access = no explicit modifier
 * **/
class Vertex {

    String label;
    int cost;

    Vertex(String label, int cost) {
        this.label = label;
        this.cost = cost;
    }

    //equals and hashcode need to be overridden or 2 instances with same label will have a different hashcode thus being recognized as different

    /*String getLabel() {
        return this.label;
    }
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // if this class instance is equals to the object (here vertex)
        if (o == null || getClass() != o.getClass()) return false;  // if null parameter or different classes
        Vertex vertex = (Vertex) o;  // convert the object to a vertex
        return label.equals(vertex.label) && cost == vertex.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label,cost);
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
