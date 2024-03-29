package SearchAlgortihms;

import SearchTree.Graph;
import SearchTree.TreeNode;
import csm6120.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * A* algorithm class
 *
 * @author Stefan
 */
public class AStar {

    private int pathcost;
    private Graph tree;
    private PriorityQueue<TreeNode> searchQueue;
    private Queue<TreeNode> exploredQueue;
    private TreeNode node, root;
    private StateComparator comparator;
    private ArrayList<String> expanded;
    private ManhattanDistance md;
    private int manhattanDistanceSum;
    private boolean solutionFound;

    /**
     * A* class constructor
     */
    public AStar() {
        this.pathcost = 0;
        this.tree = new Graph();
        this.exploredQueue = new LinkedList();
        this.comparator = new StateComparator();
        this.searchQueue = new PriorityQueue(500, comparator);
        this.expanded = new ArrayList<String>();
        this.manhattanDistanceSum = 0;
        this.solutionFound = false;

    }

    /**
     * A* algorithm
     *
     * @param start The start State
     * @param goal The goal State
     */
    public void astar(State start, State goal) {
        System.out.println("Using A* Search");
        /*
         initialise ManhattanDistance
         */
        this.md = new ManhattanDistance(start, goal);

        root = new TreeNode(start);
        /*
         check the root for being the goal state
         */
        if (root.getState().compare(goal)) {
            System.out.println("Solution has been found.\n Path cost: "
                    + pathcost);
            System.out.println("Nodes expanded: " + expanded.size());
            System.out.println("Current node: ");
            root.getState().printArray();
            System.out.println("Goal state: ");
            goal.printArray();
            solutionFound = true;
        }
        searchQueue.add(root);

        manhattanDistanceSum = md.calcManhattanDistance(root.getState(), goal);
        System.out.println("Manhatten distance is: " + manhattanDistanceSum);


        /*
         Iterate while the search queue is not empty
         */
        while (searchQueue.size() > 0) {
            node = searchQueue.poll();

            /*
             check the current node for being the goal node
             */
            if (node.getState().compare(goal)) {
                System.out.println("Solution has been found.\n Path cost: "
                        + pathcost);
                System.out.println("Nodes expanded: " + expanded.size());
                System.out.println("Current node: ");
                node.getState().printArray();
                System.out.println("Goal state: ");
                goal.printArray();
                exploredQueue.add(node);
                solutionFound = true;
                break;
            }
            //generate the next level based on that node and add
            tree.nextStep(node);
            this.addNode(node, goal);

            /*
             add the current node to the explored node and 
             update path cost
             */
            exploredQueue.add(node);
            pathcost = exploredQueue.size();
        }
        if (solutionFound == false) {
            System.out.println("No solution could be found");
        } else {
            this.printPath();
        }
    }

    /**
     * Method to add a new node the the search queue. This method calculates the
     * Manhattan Distance for every child in the current node and only add them
     * to the search queue if the Manhattan Distance is less than the original
     * calculated one.
     *
     * @param current The current node
     * @param goal The goal node, use for the Manhattan Distance calculation
     */
    public void addNode(TreeNode current, State goal) {
        int currentManhattanDistance;
        String s;
        TreeNode temp;

        while (current.childrenIsEmpty() != true) {
            /*
             Add the current node to the list of expanded nodes
             */
            if (expanded.contains(current.getState().getStringtoString()) == false) {
                expanded.add(current.getState().getStringtoString());
            }

            s = current.peekChild().getState().getStringtoString();

            if (expanded.contains(s) == false) {
                temp = current.getFirstChild();
                currentManhattanDistance = md.
                        calcManhattanDistance(temp.getState(), goal);

                if (currentManhattanDistance < manhattanDistanceSum) {
                    expanded.add(s);
                    searchQueue.add(temp);
                }
            } else {
                current.removeFirstChild();
            }
        }
    }

    /**
     * Prints the path from the start to the goal state of the puzzle
     */
    public void printPath() {
        System.out.println("The path to the goal is as follows: ");
        while (exploredQueue.isEmpty() == false) {
            exploredQueue.poll().getState().printArray();
        }
    }
}
