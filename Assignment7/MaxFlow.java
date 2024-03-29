//Name-Shivkumar Kothale
//BNumber-B01035183

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.*;

public class MaxFlow {
    static class Edge{
        int u;
        int v;
        int weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
    };
    static class Graph {
        int [][]matrix;
        public int[][] getMatrix() {
            return matrix;
        }


        Graph(int size) {
            matrix = new int[size][size];
        }
        public void addEdge(int src,int dst,int weight){
            matrix[src][dst]=weight;
        }


    };
    boolean bfs(int rGraph[][], int s, int t, int parent[])
    {
       
        boolean visited[] = new boolean[rGraph.length];
        for (int i = 0; i <visited.length; ++i)
            visited[i] = false;

       
        LinkedList<Integer> queue
                = new LinkedList<Integer>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < visited.length; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                   
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        
        return false;
    }

    
    int fordFulkerson(int graph[][], int s, int t)
    {
        int u, v;

        
        int rGraph[][] = new int[graph.length][graph.length];

        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++)
                rGraph[u][v] = graph[u][v];

        
        int parent[] = new int[graph.length];

        int max_flow = 0; 

       
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }
            
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }

           
            max_flow += path_flow;
        }

        
        return max_flow;
    }

    
    public static void main(String[] args) {
        int vertices;
        try {
            String filename =args[0] ;
            Scanner sc=new Scanner(new File(filename));
            vertices=Integer.parseInt(sc.next());
            int edges=Integer.parseInt(sc.next());
            int src=0;
            int dst=0;
            Graph g=new Graph(vertices);
            while (sc.hasNextLine()){
                int u=Integer.parseInt(sc.next());
                src=0;
                int v=Integer.parseInt(sc.next());
                dst=Math.max(dst,v);
                int w=Integer.parseInt(sc.next());
                g.addEdge(u,v,w);
            }
            int graph[][]=g.getMatrix();
            MaxFlow m = new MaxFlow();

            System.out.println("The maximum possible flow is "
                    + m.fordFulkerson(graph, src, dst));

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a file to process on");
            return;
        }catch(FileNotFoundException e){
            System.out.println("PLease give the correct input file");
            return;
        }catch (NoSuchElementException e){
            System.out.println("Input File is Empty");
            return;
        }


    }
}
