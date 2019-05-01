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
    private Map<Vertex,Integer> costMapping;

    private int Height;
    private int Width;


    // be sure to initialize neighbours with something
    Graph(int H, int W) {
        neighbours = new HashMap<>();
        costMapping = new HashMap<>();
        Height = H;
        Width = W;
    }



    void addVertex(int x, int y, Integer cost) {
        int [] coord = new int[]{x,y};
        Vertex v = new Vertex(coord);
        v.setCost(cost);
        // If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
        neighbours.putIfAbsent(v, new ArrayList<>());  //list of neighbours
        costMapping.putIfAbsent(v,cost);
        //System.out.println(" | hashcode: " + Arrays.hashCode(HVerif));
        System.out.println("node " + x + "," + y + " | cost: " + v.getCost() ); // + " | hashcode: " + Arrays.hashCode(HVerif));
    }

    /*void removeVertex(int x, int y, Integer cost) {
        Vertex v = new Vertex(new int[]{x,y},cost);
        neighbours.values()
                // parse all values
                .stream()
                // remove the key
                .map(e -> e.remove(v))
                .collect(Collectors.toList());
        neighbours.remove(new Vertex(new int[]{x,y},cost));  //remove the value
        costMapping.remove(new int[]{x,y},cost);
    }*/


    private List<Vertex> setEntourage(int x, int y) {
        List<Vertex> entourage = new ArrayList<>();
        System.out.print("node " + x + "," + y + " has neighbours: ");
        // for the square around
        for (int i = x-1; i<= x+1; i++) {
            for (int j = y-1; j<= y+1; j++) {

                // remove OOB values and x,y value, a vertex is not neighbour of himself
                if (i >= 0 && j >= 0 && i < Width && j < Height && i+j != x+y) {

                    // System.out.println(i + "," + j);
                    int[] H = new int[]{i,j};
                    Vertex v = new Vertex(H);
                   // System.out.println(Arrays.hashCode(Hverif));
                   // System.out.println(Arrays.hashCode(Vverif));
                   // System.out.println(Arrays.equals(Hverif,Vverif));

                    Integer EntCost = costMapping.get(v);
                    v.setCost(EntCost);
                    //System.out.println(;// lets note that primitive data type do not have problems with hash codes and equals
                    entourage.add(v);
                    System.out.print(" " + i + "," + j);

                }
            }
        }
        System.out.println("\n");
        return entourage;
    }


    /* method for adding an edge = neighbours to the vertex.
     * For 2D coordinates, the individual cost of each node is already define at graph creation, add neighbours
     * simply list every neighbouring coordinates and get their cost, then map them as values into the hash map
    */
    void addNeighbours() {

        Set<Vertex> set = neighbours.keySet();

        // another way of iterating in a Set or List
        Iterator it = set.iterator();
        while(it.hasNext()) {
            Vertex nxt = (Vertex) it.next(); // iterator returns an object, need to instantiate it as Vertex
            int x = nxt.getX();
            int y = nxt.getY();

            List<Vertex> Entourage = setEntourage(x,y);
            Entourage.forEach(v -> neighbours.get(nxt).add(v));  //recreate the list
        }
    }


    // method for removing edge
   /* void removeNeighbours(String label1, String label2, int c1, int c2) {
        Vertex v1 = new Vertex(label1,c1);
        Vertex v2 = new Vertex(label2,c2);
        List<Vertex> V1neighbours = neighbours.get(v1);  // get will return the value of the key, here the list
        List<Vertex> V2neighbours = neighbours.get(v2);

        if (V1neighbours != null)
            V1neighbours.remove(v2);
        if (V2neighbours != null)
            V2neighbours.remove(v1);
    }*/


    private List<Vertex> getNeighbours(int[] coord) {
        Vertex v = new Vertex(coord);
        return neighbours.get(v);

    }

    Integer Cost(int x, int y) {
        return costMapping.get(new Vertex(new int[]{x,y}));
    }


    int getSize() { return neighbours.size(); }

    void addCost(int x, int y, Integer cost) {
        costMapping.put(new Vertex(new int[]{x,y}),cost);
    }






   /* boolean getVertex(String label,int cost) {
        return neighbours.containsKey(new Vertex(label,cost));
    }*/




    void getElements() {
        Set<Vertex> S = neighbours.keySet();  // return a Set view of the keys contained in the map

        for (Vertex v : S) {
            System.out.println("node " + v.getCoord()[0] + "," + v.getCoord()[1]);
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
    Map<int[],int[]> Dijkstra(Graph graph, int[] source, int[] goal) {

        Map<int[],int[]> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<int[],Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15,new CostComparator());


        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        Integer costG = Cost(Goal.getX(),Goal.getY());
        Integer costS = Cost(Source.getX(),Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);

        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(Source.getCoord(),null); //the source doesn't have any parents
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
            for (Vertex v : graph.getNeighbours(topVertex.getCoord())) {

                int newCost = costSoFar.get(topVertex.getCoord()) + v.getCost();            // add cost of current vertex and cost of

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!costSoFar.containsKey(v.getCoord()) || newCost < costSoFar.get(v.getCoord())) {
                    costSoFar.put(v.getCoord(),newCost);

                    cameFrom.put(v.getCoord(),topVertex.getCoord());  // add it to the visited list
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(new Vertex(v.getCoord())); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }
        return cameFrom;  // we can do something with visited, for pathfinding and path memory

    }




    List<int[]> Path_reconstruct(Map<int[],int[]> cameFrom, int[] source, int[] goal) {

        List<int[]> path = new ArrayList<>();  //initiate the path
        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        /*Integer costG = Cost(Goal.getX(),Goal.getY());
        Integer costS = Cost(Source.getX(),Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);*/



        int[] current = goal; // start from the goal and navigate the map

        while (!Arrays.equals(current,source)) {  // while we haven't reach the source
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
   /* Map<String,String> Astar(Graph graph, String source, String goal, int costG) {

        Map<String,String> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<String,Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15,new CostComparator());


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

*/

































}
