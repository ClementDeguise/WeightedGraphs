package WeightedGraphs;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex v1, Vertex v2)
    {
        if (v1.cost < v2.cost)
            return -1;
        if (v1.cost > v2.cost)
            return 1;
        return 0;
    }
}
