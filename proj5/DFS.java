package edu.iastate.cs228.proj5;
/*
 *  @author Lucas Keller
 *
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class DFS {

    /**
     * This method creates a color map and a pred map (see example Graph.java under Week 13 of Lecture notes) and an
     * empty stack of type Stack<V>. It colors each vertex "white" and sets the value of each vertex to null in the pred
     * map (see Graph.java). Then as long as there is a "white" vertex w left, the method calls visitDFS(...) on the
     * vertex w along with other parameters. If visitDFS(...) returns false, then this method returns null. Otherwise,
     * it returns the stack containing the list of all vertices in a topological order, which is produced by the
     * visitDFS(...) method. If aGraph is null, then it throws IllegalArgumentException.
     */
    public static <V> Stack<V> depthFirstSearch(DiGraph<V> aGraph) {
        if (aGraph == null)
            throw new IllegalArgumentException();

        java.util.Stack<V> topOrder = new java.util.Stack<V>();
        HashMap<V, String> color = new HashMap<V, String>();
        HashMap<V, V> pred = new HashMap<V, V>();

        for (V e : aGraph.vertices()) {
            color.put(e, "white");
            pred.put(e, null);
        }

        for (V e : aGraph.vertices()) {
            if (color.get(e).equals("white")) {
                if (!visitDFS(aGraph, e, color, pred, topOrder))//if visitDFS == false, not acyclic
                    return null;
            }
        }

        return topOrder;
    }

    /**
     * This method implements an iterative depth-first search algorithm for checking if the given graph is acyclic (has
     * no cycles) and if so, generates a stack (named topoOrder) of all vertices in a topological order and returns
     * true. Otherwise, it returns false. An iterative depth-first search algorithm is given in under lecture notes for
     * an undirected graph (Week 13 of Lecture Notes). Here, you need to modify the algorithm to make it work for a
     * directed graph. Note that the edge iterator citer (inside Graph.java under Lecture Notes) should be changed from
     * type Iterator<V> to type Iterator<Edge<V, Integer>>, and that citer.next().getVertex(), instead of citer.next(),
     * gives the vertex w. If the vertex w is "gray", then the graph has a cycle. So the method returns false. Whenever
     * a vertex is colored "black", the vertex is pushed onto the stack topoOrder. If the graph has no cycles (the
     * execution reaches the end of the method), then the method returns true.
     */
    protected static <V> boolean visitDFS(DiGraph<V> aGraph, V s, HashMap<V, String> color, HashMap<V, V> pred,
                                          Stack<V> topoOrder) {
        color.put(s, "gray");
        Stack<V> nodeStack = new Stack<>();
        Stack<Iterator<Edge<V, Integer>>> edgeStack = new Stack<>();
        Iterator<Edge<V, Integer>> siter = aGraph.adjacentTo(s).iterator();

        nodeStack.push(s);
        edgeStack.push(siter);

        while (!nodeStack.isEmpty()) {
            V c = nodeStack.peek();
            Iterator<Edge<V, Integer>> citer = edgeStack.peek();

            if (citer.hasNext()) {
                V e = citer.next().getVertex();

                if (color.get(e).equals("white")) {
                    color.put(e, "gray");
                    pred.put(e, c);
                    Iterator<Edge<V, Integer>> witer = aGraph.adjacentTo(e).iterator();//getting edge iterator of
                    // current node
                    nodeStack.push(e);//adds current node to stack
                    edgeStack.push(witer);//through the depths
                } else if (color.get(e).equals("gray")) { // found a grey vertex means contains cycles
                    return false;
                }
            } else {
                color.put(c, "black"); //no untouched neighbors, ascending back and marking finished
                topoOrder.push(c); // altering current topological order
                nodeStack.pop(); //now finished with current node
                edgeStack.pop(); //now finished with current edge iterator

            }
        }

        return true;
    }
}
