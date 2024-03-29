//Name-Shivkumar Kothale
//BNumber-B01035183
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Kruskals {
        public static class Edge {

        int u;
        int v;
        int weight;
        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

    }
    public static class DisjointSet {
        int[] rank, parent;
        int size;
        public DisjointSet(int vertices){
            rank=new int[vertices];
            parent=new int[vertices];
            size=vertices;
            createDs();

        }
        public void insertList(List<Edge> list){
            for(Edge e:list){
                union(e.u,e.v);
            }
        }

        private void createDs() {
            for(int i=0;i<size;i++){
                parent[i]=i;
            }
        }
        public void union(int i, int j) {
            int repI=find(i);
            int repJ=find(j);
            if(repI==repJ){

                return;
            }
            if(rank[repI]<rank[repJ]){
                parent[repI]=repJ;
            }else if(rank[repJ] < rank[repI]){
                parent[repJ]=repI;
            }else{
                parent[repJ] = repI;
                rank[repI]=rank[repJ]+1;
            }

        }

        private int find(int i) {
            if(parent[i]!=i){
                parent[i]=find(parent[i]);
            }
            return parent[i];
        }
    }
    public static void main(String[] args) {
        PriorityQueue<Edge> pq=new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        int edges=0;
        int vertices=0;
        try {
            Scanner sc=new Scanner(new File(args[0]));
            vertices=Integer.parseInt(sc.next());
            edges=Integer.parseInt(sc.next());
            while (sc.hasNextLine()){
                int u=Integer.parseInt(sc.next());
                int v=Integer.parseInt(sc.next());
                int w=Integer.parseInt(sc.next());
                Edge e=new Edge(u,v,w);
                pq.add(e);
            }
            sc.close();
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a file to process on");
            return;
        }catch(FileNotFoundException e){
            System.out.println("PLease give the correct input file");
            return;
        }catch (NoSuchElementException e){
            System.out.println("Input File is Empty");
            return;
        }
        List<Edge> result=new ArrayList<>();
        DisjointSet disjointSet=new DisjointSet(vertices);
        int cost=0;
        while(!pq.isEmpty()){
            Edge e=pq.poll();
            if(disjointSet.find(e.u)!=disjointSet.find(e.v)){
                disjointSet.union(e.u,e.v);
                cost+=e.weight;
                result.add(e);
            }
        }
        System.out.println("The cost of the graph is "+cost);
    }
}

