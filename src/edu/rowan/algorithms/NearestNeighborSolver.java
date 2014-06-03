

package edu.rowan.algorithms;

import java.util.ArrayList;

/**
 *
 * @author Nacer Abreu and Emmanuel Bonilla
 */
public class NearestNeighborSolver {

    private final Tour tour;
    double[][] adjacencyMatrix;
    
    private final int X_COORD = 1;
    private final int Y_COORD = 2;

    ArrayList<Integer> visitedCities = new ArrayList<Integer>();
    ArrayList<Integer> solution = new ArrayList<Integer>();
    double tourCost;
    
    public NearestNeighborSolver(Tour tour) {
    
        this.tour = tour;
        this.adjacencyMatrix = tour.getAdjacencyMatrix();
        tourCost = 0.0;
    }

    /**
     * This function recursively visit every node specified in the .tsp file
     * by always choosing the nearest node.
     * @return  An array representing the shortest tour. The solution.
     */
    ArrayList<Integer> getShortestTour() {

        int node = 0; //Start Node
        
        processInitialNode(node);

        while (visitedCities.size() < tour.getDimension()) {
            System.out.println("Node: " + (node + 1));
            node = getNearestNode(node);
            visitedCities.add(node);
            solution.add(node + 1);
            System.out.println("\n");
        }
        
        processFinalNode(node);
        
        return solution;
    }//end of getShortestTour()   
    
    
    /**
     * This function process the very first node that will be process.
     * @param node Initial node
     */
    private void processInitialNode(int node) {
        solution.add(node + 1); // Add the starting node to the solution array.
        visitedCities.add(node); // Add the starting node to the visited cities
    }
    
    private void processFinalNode(int node) {
        System.out.println("Node: " + (node + 1));
        System.out.println("Nearest Node: 1" + "; Edge: " + adjacencyMatrix[node][0]);
        tourCost += adjacencyMatrix[node][0];
        System.out.println("\nTotal cost = " + tourCost);
    }

    private int getNearestNode(int node) {
        double edge = -1.0;
        int nearestNode = -1;

        for (int i = (tour.getDimension() - 1); i > -1; i--) {

            
            if (isMarkedVisited(i)){
                // This city has already been visited by some node.
                continue;
            }
            
            if (-1 == adjacencyMatrix[node][i]){
                // Same city. Current and destination cities are the same.
                continue;
            }
            
            if ((tour.getXCoord(node+1) == tour.getXCoord(i+1)) &&
            	(tour.getYCoord(node+1) == tour.getYCoord(i+1))) {
            	//Different City Node -- Same coordinates (Skip)
                
                // Current city (Debug purposes)
//                double x1 = tour.getXCoord(node + 1);
//                double y1 = tour.getYCoord(node + 1);
                
                // Destination city (Debug purposes)
//                double x2 = tour.getXCoord(i + 1);
//                double y2 = tour.getYCoord(i + 1);
                              
//                System.out.println("Debug Node (Current)    : " + (node + 1)
//                        + "; x1 : " + x1 + "; y1 : " + y1);
//                System.out.println("Debug Node (Destination): " + (i + 1)
//                        + "; x2 : " + x1 + "; y2 : " + y2);
                
            	continue;
            }
            
            if ((-1.0 == edge) && (-1 != adjacencyMatrix[node][i])) {
               edge = adjacencyMatrix[node][i];
                // initiliaze this variable with the edge value of the
                // first city, that is not the current city. This solves the 
                // node[0][0] issues, where it is originally set to -1.
            }            
            
            
            if ((adjacencyMatrix[node][i] <= edge)) { 
                edge = adjacencyMatrix[node][i];
                System.out.println("Nearest Node: " + (i + 1)  + "; Edge: " + edge);
                nearestNode = i; //save off nearest neighbour.
            }
        }//end of for...loop
        
        tourCost += adjacencyMatrix[node][nearestNode];
        return nearestNode;
    }//end of getNearestNode()  
    
    
    
    /**
     * Test if the node argument was already visited.
     * @param node Node to be checked against the visited cities array.
     * @return True, if the node was already visited; otherwise, false.
     */
    private boolean isMarkedVisited(int node) {
        boolean found = false;

        for (int i = 0; i < visitedCities.size(); i++) {
            if (node == visitedCities.get(i)) {
                found = true;
                break;
            }
        }
        return found;
    }//end of isMarkedVisited();
    
}//end of class
