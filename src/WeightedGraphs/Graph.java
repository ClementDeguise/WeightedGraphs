package WeightedGraphs;

import java.util.*;
import java.util.stream.Collectors;

class Graph {

    /** define a Graph using adjency list: each element of the vertex list contains a list of neighbouring vertices
     * We call methods with labels only for it is simpler, no need to instantiate new Vertices
     *
     *
     * package-private access = no explicit modifier
     *
     * **/

    private Map<Vertex, List<Vertex>> neighbours;

    // be sure to initialize neighbours with something
    Graph() {
        neighbours = new HashMap<>();
    }



    void addVertex(String label, int cost) {
        // If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
        List<Vertex> A = new ArrayList<>();
       // A.add(new Vertex("b"));
        //System.out.println(A.get(0).label);
       // System.out.println(A.size());

        neighbours.putIfAbsent(new Vertex(label,cost), A);

    }

    void removeVertex(String label, int cost) {
        Vertex v = new Vertex(label,cost);
        neighbours.values()
                // parse all values
                .stream()
                // TODO define what happens
                .map(e -> e.remove(v))
                .collect(Collectors.toList());
        neighbours.remove(new Vertex(label,cost));
    }


    // method for adding an edge = neighbours to the vertex
    void addNeighbours(String label1, String label2, int c1, int c2) {
        Vertex v1 = new Vertex(label1,c1);
        Vertex v2 = new Vertex(label2,c2);

        //System.out.println("2: " + v1.hashCode());
        //System.out.println("3: " + v2.hashCode());


        //System.out.println(neighbours.containsKey(v1));


       // neighbours.get(v2).remove(B);

        neighbours.get(v1).add(v2); // get v1 neighbours list, and add v2 to the list as its neighbour
        neighbours.get(v2).add(v1); // do the opposite for v2
    }


    // method for removing edge
    void removeNeighbours(String label1, String label2, int c1, int c2) {
        Vertex v1 = new Vertex(label1,c1);
        Vertex v2 = new Vertex(label2,c2);
        List<Vertex> V1neighbours = neighbours.get(v1);  // get will return the value of the key, here the list
        List<Vertex> V2neighbours = neighbours.get(v2);

        if (V1neighbours != null)
            V1neighbours.remove(v2);
        if (V2neighbours != null)
            V2neighbours.remove(v1);
    }


    private List<Vertex> getNeighbours(String label,int cost) {
        Vertex v = new Vertex(label,cost);
        return neighbours.get(v);

    }




    boolean getVertex(String label,int cost) {
        return neighbours.containsKey(new Vertex(label,cost));
    }

    int getSize() {
        return neighbours.size();
    }


    void getElements() {
        Set<Vertex> S = neighbours.keySet();  // return a Set view of the keys contained in the map

        //String[] V = S.toArray(new String[]);

        for (Vertex v : S) {
            //List<Vertex> L = neighbours.get(v);
            System.out.println("node " + v.label + " | cost: " + v.cost);
            /*for (Vertex c : L) {
                System.out.print(c.label + " ");
            }
            System.out.print("\n");*/
        }

    }


    /**--------------- Dijkstra's algorithm, LOWER COST --------------
     *
     * cost account for the distance between each nodes, or steepness, or terrain, etc
     *
     *
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/
    Map<String,String> Dijkstra(Graph graph, String source, String goal, int costG) {

        Map<String,String> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<String,Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15,new VertexComparator());


        Vertex Source = new Vertex(source,0);
        Vertex Goal = new Vertex(goal, costG);


        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(source,null); //the source doesn't have any parents
        costSoFar.put(source,0);





        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            Vertex topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if(topVertex.equals(Goal)) break;

            //System.out.println(topVertex.label + " cost " + topVertex.cost);
            //System.out.println("hashcode : " + topVertex.hashCode());
            //System.out.println("is in graph : " + graph.getVertex(topVertex.label,topVertex.cost));





            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex.label,topVertex.cost)) {

                int newCost = costSoFar.get(topVertex.label) + v.cost;            // add cost of current vertex and cost of

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!costSoFar.containsKey(v.label) || newCost < costSoFar.get(v.label)) {
                    costSoFar.put(v.label,newCost);

                    cameFrom.put(v.label,topVertex.label);  // add it to the visited list
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(new Vertex(v.label,v.cost)); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }
        return cameFrom;  // we can do something with visited, for pathfinding and path memory

    }




    List<String> Path_reconstruct(Map<String,String> cameFrom, String source, String goal) {

        List<String> path = new ArrayList<>();  //initiate the path
        String current = goal; // start from the goal and navigate the map

        while (!current.equals(source)) {  // while we haven't reach the source
            path.add(current);  // add to path
            current = cameFrom.get(current);  //get the parent in the map
        }

        path.add(source); // add the source for completion
        Collections.reverse(path);
        return path;

    }











    /**--------------- A* --------------
     *
     * cost account for the distance between each nodes, or steepness, or terrain, etc
     *
     *
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/
    Map<String,String> Astar(Graph graph, String source, String goal, int costG) {

        Map<String,String> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<String,Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15,new VertexComparator());


        Vertex Source = new Vertex(source,0);
        Vertex Goal = new Vertex(goal, costG);


        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(source,null); //the source doesn't have any parents
        costSoFar.put(source,0);





        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            Vertex topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if(topVertex.equals(Goal)) break;

            //System.out.println(topVertex.label + " cost " + topVertex.cost);
            //System.out.println("hashcode : " + topVertex.hashCode());
            //System.out.println("is in graph : " + graph.getVertex(topVertex.label,topVertex.cost));





            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex.label,topVertex.cost)) {

                int newCost = costSoFar.get(topVertex.label) + v.cost;            // add cost of current vertex and cost of

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!costSoFar.containsKey(v.label) || newCost < costSoFar.get(v.label)) {
                    costSoFar.put(v.label,newCost);

                    cameFrom.put(v.label,topVertex.label);  // add it to the visited list
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(new Vertex(v.label,v.cost)); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }
        return cameFrom;  // we can do something with visited, for pathfinding and path memory

    }



































}
