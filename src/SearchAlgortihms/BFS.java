package SearchAlgortihms;

import SearchTree.TreeNode;
import SearchTree.Graph;
import csm6120.State;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Class file for the Breadth first search algorithm
 *
 * @author stefan
 */
public class BFS {

    private Graph tree;
    private TreeNode node, root;
    private Queue<TreeNode> searchQueue;
    private Queue<TreeNode> exploredQueue;
    private ArrayList<String> expanded;
    private int pathcost;
    private boolean solutionFound;

    /**
     * Constructor of the BFS object
     */
    public BFS() {
        this.tree = new Graph();
        this.searchQueue = new LinkedList();
        this.exploredQueue = new LinkedList();
        this.pathcost = 0;
        this.expanded = new ArrayList();
        this.solutionFound = false;
    }

    /**
     * Breath-First search method
     *
     * @param start The start State of the graph
     * @param goal The goal State of the graph
     */
    public void bfs(State start, State goal) {
        System.out.println("Using Breadth-First Search");
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
        }
        searchQueue.add(root);

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
                exploredQueue.add(node);
                solutionFound = true;
                goal.printArray();
                break;
            }
            //generate the next level based on that node and add
            tree.nextStep(node);

            /*
             add the child of the current node and all its siblings to the queue
             */
            while (node.childrenIsEmpty() != true) {
                /*
                 Add the current node to a an ArrayList of expanded nodes
                 */
                if (expanded.contains(node.getState().getStringtoString()) == false) {
                    expanded.add(node.getState().getStringtoString());
                }
                String s = node.peekChild().getState().getStringtoString();
                if (expanded.contains(s) == false) {
                    expanded.add(s);
                    searchQueue.add(node.getFirstChild());
                } else {
                    node.removeFirstChild();
                }
            }

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
     * Prints the path from the start to the goal state of the puzzle
     */
    public void printPath() {
        System.out.println("The path to the goal is as follows: ");
        while (exploredQueue.isEmpty() == false) {
            exploredQueue.poll().getState().printArray();
        }
    }
}
