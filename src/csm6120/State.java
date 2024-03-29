package csm6120;

import java.util.ArrayList;

/**
 * This class has methods and variables to hold an input state. This will be
 * used to hold the start and goal state object.
 *
 * @author stefan
 */
public class State {

    private ArrayList<Integer> state;

    /**
     * Constructor for the State object creates an empty arrayList in which the
     * state data will be saved
     */
    public State() {
        this.state = new ArrayList();
    }

    /**
     * Constructor for the State object creates a deep clone of the state object
     * which is specified in the parameter field
     *
     * @param s The state to clone
     */
    public State(State s) {
        this.state = new ArrayList();
        state.addAll(s.state);
    }

    /**
     * Method to add an integer to the arrayList
     *
     * @param toAdd The integer to add
     */
    public void addState(int toAdd) {
        this.state.add(toAdd);
    }

    /**
     * Print the ArrayList
     */
    public void printArray() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("\n");
            for (int j = 0; j < 3; j++) {
                System.out.print(this.state.get(counter));
                counter++;
            }
        }
        System.out.print("\n");

    }

    /**
     * Method to return the index of a specific item in the ArrayList
     *
     * @param i The item to search for
     * @return The position of the item in the ArrayList
     */
    public int returnIndex(int i) {
        return this.state.indexOf(i);

    }

    /**
     * Method to exchange to tiles
     *
     * @param i Index of the tile to change
     * @param j Index of the Empty tile to change
     */
    public void changeTiles(int i, int j) {
        int temp;

        temp = this.state.get(i);
        this.state.remove(i);
        this.state.add(i, 0); //move empty tile where i was
        this.state.remove(j);
        this.state.add(j, temp);

    }

    /**
     * This method clones the arrayList and returns it
     *
     * @return The cloned arrayList
     */
    public ArrayList clone() {
        ArrayList clone = new ArrayList();
        for (Integer state1 : state) {
            clone.add(state1);
        }
        return clone;
    }

    /**
     * Method to compare this object to another state object
     *
     * @param s The state to compare too
     * @return True if the states are the same, false if not
     */
    public boolean compare(State s) {
        int match = 0;
        for (int i = 0; i < s.state.size(); i++) {
            if (this.state.get(i).equals(s.state.get(i))) {
                match++;
            }
        }
        if (match == 9) {
            return true;
        }
        return false;
    }

    /**
     * Method to return the string representation of the "state" ArrayList
     *
     * @return The toString representation of the "state" ArrayList
     */
    public String getStringtoString() {
        String s = this.state.toString();
        return s;
    }

    /**
     * Method to return how many integers in this object compared to another
     * object match
     *
     * @param s The state to compare too
     * @return The number of matching ints
     */
    public int compareMatching(State s) {
        int match = 0;
        for (int i = 0; i < s.state.size(); i++) {
            if (this.state.get(i).equals(s.state.get(i))) {
                match++;
            }
        }
        return match;
    }

    /**
     * Method to return the size of the state array
     *
     * @return int value of the state array size
     */
    public int getArraySize() {
        return this.state.size();
    }

    /**
     * Method to return the state ArrayList
     *
     * @return The state ArrayList
     */
    public ArrayList getStateArray() {
        return this.state;
    }
}
