package WeightedGraphs;

import java.util.Comparator;

public class CostComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex v1, Vertex v2)
    {
        if (v1.getCost() < v2.getCost())
            return -1;
        if (v1.getCost() > v2.getCost())
            return 1;
        return 0;
    }
}
