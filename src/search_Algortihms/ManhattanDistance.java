package search_Algortihms;

import csm6120_assignment2.State;
import java.util.ArrayList;

/**
 * This class is used to calculate the Manhattan distance for the A* algorithm
 *
 * @author Stefan
 */
public class ManhattanDistance {

    private int[][] startArray;
    private int[][] goalArray;

    /**
     * Constructor of the ManhattanDistance class
     *
     * @param start The State to compare to the goal
     * @param goal The goal State to compare too
     */
    public ManhattanDistance(State start, State goal) {
        this.startArray = new int[start.getList().size()][start.getList().size()];
        this.goalArray = new int[goal.getList().size()][goal.getList().size()];

        this.setStartArray(this.convertTo2DArray(this.convertToArray(start.getList())));
        this.setGoalArray(this.convertTo2DArray(this.convertToArray(goal.getList())));
    }

    /**
     * Method to convert an ArrayList to an array
     *
     * @param i The arrayList to convert
     * @return An integer array
     */
    public int[] convertToArray(ArrayList<Integer> i) {
        int[] array = new int[i.size()];
        for (int j = 0; j < array.length; j++) {
            array[j] = i.get(j).intValue();
        }
        return array;
    }

    /**
     * Method to convert an 1D integer array to a 2D integer array
     *
     * @param intArray The integer array to convert
     * @return An 2D integer array
     */
    public int[][] convertTo2DArray(int[] intArray) {
        int[][] array = new int[3][3];
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = intArray[counter];
                counter++;
            }
        }
        return array;
    }

    /**
     * Method to set the startArray
     *
     * @param toSet 2D array to set too
     */
    public void setStartArray(int[][] toSet) {
        this.startArray = toSet;
    }

    /**
     * Method to set the goalArray
     *
     * @param toSet 2D array to set too
     */
    public void setGoalArray(int[][] toSet) {
        this.goalArray = toSet;
    }

    public int[][] findCell(int[][] x, int index) {
        int[][] targetCoordinate = new int[1][1];
        int c;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (x[i][j] == index) {
                    targetCoordinate[i][j] = x[i][j];
                }
            }
        }
        return targetCoordinate;
    }

    public int findXCoordinate(int[][] array, int index) {
        int xCoordinate = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == index) {
                    xCoordinate = j;
                }
            }
        }
        return xCoordinate;
    }

    public int findYCoordinate(int[][] array, int index) {
        int yCoordinate = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (array[i][j] == index) {
                    yCoordinate = i;
                }
            }
        }
        return yCoordinate;
    }

    /**
     * Method to calculate the Manhattan Distance
     *
     * @return The calculate distance
     */
    public int calcManhattanDistance() {
        int counter = 0;
        int totalDistance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x_start = findXCoordinate(startArray, counter);
                int x_goal = findXCoordinate(goalArray, counter);
                int y_start = findYCoordinate(startArray, counter);
                int y_goal = findYCoordinate(goalArray, counter);

                if (startArray[i][j] == goalArray[i][j]) {
                    continue;
                } else if (startArray[i][j] - goalArray[i][j] < 0) {
                    totalDistance += Math.abs(x_start - x_goal)
                            + Math.abs(y_start - y_goal);
                } else if (startArray[i][j] - goalArray[i][j] > 0) {
                    totalDistance += Math.abs(x_goal - x_start)
                            + Math.abs(y_goal - y_start);
                }
                counter++;
            }
            
        }
       
        return totalDistance;
    }
}