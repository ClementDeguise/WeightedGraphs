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