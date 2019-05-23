package WeightedGraphs;


import java.util.*;




public class PathFinding {


    private Map<Vertex,Vertex> cameFrom;
    private Map<Vertex,Integer> costMapping;

    private Graph graph;



    PathFinding(Graph graph) {
        this.graph = graph;
        cameFrom = graph.getCameFrom();
        costMapping = graph.getCostMapping();

    }




    List<int[]> Path_reconstruct(int[] source, int[] goal) {

        List<int[]> path = new ArrayList<>();  //initiate the path
        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        /*Integer costG = Cost(Goal.getX(),Goal.getY());
        Integer costS = Cost(Source.getX(),Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);*/



        Vertex current = Goal; // start from the goal and navigate the map

        while (!current.equals(Source)) {  // while we haven't reach the source
            path.add(current.getCoord());  // add to path
            System.out.print("current "+ current.getCoord()[0] + " " + current.getCoord()[1] + " coming from " );
            current = cameFrom.get(current);  //get the parent in the map
            System.out.print(current.getCoord()[0] + " " + current.getCoord()[1] + "\n" );
        }

        path.add(source); // add the source for completion
        Collections.reverse(path);
        return path;

    }











    /**
     * --------------- Dijkstra algorithm, LOWER COST --------------
     *
     * cost account for the distance between each nodes, or steepness, or terrain, etc
     *
     *
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/

    // TODO call a repaint, every time the cameFrom list update

     void Dijkstra(int[] source, int[] goal) {

       // Map<Vertex, Vertex> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<Vertex, Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15, new CostComparator());


        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        Integer costG = graph.Cost(Goal.getX(), Goal.getY());
        Integer costS = graph.Cost(Source.getX(), Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);

        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(Source, null); //the source doesn't have any parents
        costSoFar.put(Source, 0);


        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            Vertex topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if (topVertex.equals(Goal)) break;

            //System.out.println(topVertex.label + " cost " + topVertex.cost);
            //System.out.println("hashcode : " + topVertex.hashCode());
            //System.out.println("is in graph : " + graph.getVertex(topVertex.label,topVertex.cost));


            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex.getCoord())) {

                int newCost = costSoFar.get(topVertex) + costMapping.get(v);            // add cost of current vertex and cost of

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!costSoFar.containsKey(v) || newCost < costSoFar.get(v)) {
                    costSoFar.put(v, newCost);
                    //Integer CC = graph.Cost(v.getX(), v.getY());
                    v.setCost(newCost);

                    cameFrom.put(v, topVertex);  // add it to the visited list

                    drawGraph();
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(v); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }


    }



    private void drawGraph() {

         for (int i=0; i<10; i++) {
             for (int j=0; j<10; j++) {

                 if (cameFrom.containsKey(new Vertex(new int[]{i,j}))) {
                     System.out.print(" 1 ");
                 }
                 else System.out.print(" 0 ");

             }
             System.out.print("\n");
         }


        System.out.println(" ");
    }





    int heuristic(int[] a, int[] b) {
        //Manhattan distance on a square grid
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }







    // TODO GBFS always start with the same nodes due to neighbours creation order, therefore the path taken always start in a straight line and not in diagonal when it is possible
    /**--------------- Greedy BFS --------------
     *
     * Knows the distance between the current vertex and the goal, and go straight toward this direction.
     * Works well with no obstacles, faster than Djikstra, but loses effectiveness when encountering obstacles.
     *
     *
     * Same as Djikstra but with distance as priority instead of cost. We could implement a distance comparator
     * or go with the same cost comparator but using the distance value on the vertex instead.
     *
     **/
    void GreedyBFS(int[] source, int[] goal) {

        // Map<Vertex, Vertex> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
       // Map<Vertex, Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15, new CostComparator());


        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        Integer costG = graph.Cost(Goal.getX(), Goal.getY());
        Integer costS = graph.Cost(Source.getX(), Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);

        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(Source, null); //the source doesn't have any parents
        //costSoFar.put(Source, 0);


        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            Vertex topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if (topVertex.equals(Goal)) break;

            //System.out.println(topVertex.label + " cost " + topVertex.cost);
            //System.out.println("hashcode : " + topVertex.hashCode());
            //System.out.println("is in graph : " + graph.getVertex(topVertex.label,topVertex.cost));


            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex.getCoord())) {

                //int newCost = costSoFar.get(topVertex) + costMapping.get(v);            // add cost of current vertex and cost of
                int distance = heuristic(Goal.getCoord(),v.getCoord());

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!cameFrom.containsKey(v)) {
                    //costSoFar.put(v, newCost);
                    //Integer CC = graph.Cost(v.getX(), v.getY());
                    v.setCost(distance);

                    cameFrom.put(v, topVertex);  // add it to the visited list

                    drawGraph();
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(v); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }


    }




    /**--------------- A* --------------
     *
     * cost account for the distance between each nodes, or steepness, or terrain, etc
     *
     *
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/
    void Astar(int[] source, int[] goal) {

        // Map<Vertex, Vertex> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken
        Map<Vertex, Integer> costSoFar = new LinkedHashMap<>();


        // the frontier, current visiting vertices
        // we must use it on vertices as direct comparison on strings only works for 1 digit labels
        PriorityQueue<Vertex> queue = new PriorityQueue<>(15, new CostComparator());


        Vertex Source = new Vertex(source);
        Vertex Goal = new Vertex(goal);

        Integer costG = graph.Cost(Goal.getX(), Goal.getY());
        Integer costS = graph.Cost(Source.getX(), Source.getY());
        Source.setCost(costS);
        Goal.setCost(costG);

        //System.out.println(cameFrom.size());

        // initialization, cost 0 for the source
        queue.add(Source);
        cameFrom.put(Source, null); //the source doesn't have any parents
        costSoFar.put(Source, 0);


        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            Vertex topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if (topVertex.equals(Goal)) break;

            //System.out.println(topVertex.label + " cost " + topVertex.cost);
            //System.out.println("hashcode : " + topVertex.hashCode());
            //System.out.println("is in graph : " + graph.getVertex(topVertex.label,topVertex.cost));


            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex.getCoord())) {

                int newCost = costSoFar.get(topVertex) + costMapping.get(v);            // add cost of current vertex and cost of

                // for the first neighbour, since we have nothing to compare with, we add it to the cost so far provided we didn't previously visit it
                // then each neighbour new cost will be compared and replaced if inferior, until the least costly neighbour has been found
                if (!costSoFar.containsKey(v) || newCost < costSoFar.get(v)) {
                    costSoFar.put(v, newCost);


                    // priority : what changes
                    int distance = heuristic(Goal.getCoord(),v.getCoord());
                    v.setCost(newCost + distance);

                    cameFrom.put(v, topVertex);  // add it to the visited list

                    drawGraph();
                    //System.out.println("adding child "+ v.label + " to parent " + topVertex.label );
                    queue.add(v); // add it to the queue as the new frontier, since the frontier expanded
                    // the vertex with lower cost naturally as higher priority
                }
            }
        }


    }







































}