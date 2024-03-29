//Name-Shivkumar Kothale
//BNumber-B010235183

//Compile-javac DijkastraAlgorithm.java
//Run- java DijkastraAlgorithm [input file] [src] [dst]


// This code gives the shortest distance from source to destination 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class DijkastraAlgorithm{
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
    static class Node{

        int value;
        int weight;
        public Node(int n,int w){

            value=n;
            weight=w;
        }

        @Override
        public String toString() {
            return                 value +
                    ","+ weight
                    ;
        }
    };
    static class MinHeapDataStructure {

        List<Node> matrix;
        MinHeapDataStructure(){
            matrix=new ArrayList<>();
        }
        public int getParent(int i) {
            return (i-1)/2;
        }

        public int getLeft(int i) {
            return (2*i)+1;
        }

        public int getRight(int i) {
            return (2*i)+2;
        }
        public int getSize(){
            return matrix.size();
        }

        public void insertList(List<Node>list) {
            for(Node n:list){
                insertNode(n);
            }
        }

        public void insertNode(Node n){
            matrix.add(n);
            int i = matrix.indexOf(n);

            while (i >= 0) {
                int parent = i / 2;
                if (matrix.get(parent).weight > matrix.get(i).weight) {
                    swap(parent, i);
                    i = parent;
                } else
                    return;}

        }
        public Node removeTop(){
            swap(0, matrix.size()-1);
            Node n=matrix.remove(matrix.size()-1);
            heapify(0);
            return n;

        }

        private void heapify(int i) {
            int smallest=i;
            int left=getLeft(i);
            int right=getRight(i);
            if(left<matrix.size() &&right<matrix.size() )
            {if(right< matrix.size()){
                smallest=matrix.get(left).weight<matrix.get(right).weight?left:right;
            }else{
                smallest=left;
            }

                if(matrix.get(i).weight> matrix.get(left).weight|| matrix.get(i).weight> matrix.get(right).weight){
                    swap(smallest,i);
                    heapify(smallest);
                }}
        }

        private void swap(int smallest, int i) {
            Node temp=matrix.get(smallest);
            matrix.set(smallest,matrix.get(i));
            matrix.set(i,temp);
        }
        public void print(){
            System.out.println(matrix);
        }


    };
    static List<List<Node>>list;
    static List<Edge>edgeList;
    public void adjacencyList(List<Edge>edges,int n){
        list=new ArrayList<>(n);
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
        for(Edge e:edges){
            list.get(e.u).add(new Node(e.v,e.weight));
        }for(Edge e:edges){
            list.get(e.v).add(new Node(e.u,e.weight));
        }
    }
    public int[] dijkastra(int src, int v) {
        MinHeapDataStructure minHeap;
        int[] distance = new int[v];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[src] = 0;
        minHeap = new MinHeapDataStructure();
        minHeap.insertNode(new Node(src, 0));

        while (minHeap.getSize() != 0) {
            Node n = minHeap.removeTop();
            for (Node cur : list.get(n.value)) {
                if (distance[n.value] + cur.weight < distance[cur.value]) {
                    distance[cur.value] = distance[n.value] + cur.weight;
                    minHeap.insertNode(new Node(cur.value, distance[cur.value]));

                }

            }


        }
        return distance;
    }
    public static void main(String[] args) {
        DijkastraAlgorithm d=new DijkastraAlgorithm();
        int src;
        int dst;
        int vertices;
        try{
            src=Integer.parseInt(args[1]);
            dst=Integer.parseInt(args[2]);
            if(src<0){
                System.out.println("Enter value of src that is greater than or equal to 0");
                return;
            }if(dst<0){
                System.out.println("Enter value of dst that is greater than or equal to 0");
                return;
            }
            
        }catch(NumberFormatException e){
            System.out.println("Please enter a integer value of Inputs");
            return;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter both src and dst");
            return;
        }
        
        try {
            String filename =args[0] ;
            Scanner sc=new Scanner(new File(filename));
            edgeList=new ArrayList<>();
            vertices=Integer.parseInt(sc.next());
            if(src>=vertices || dst>=vertices){
                System.out.println("There is no path between src and dst");
                return;
            }
            int edges=Integer.parseInt(sc.next());
            while (sc.hasNextLine()){
                int u=Integer.parseInt(sc.next());
                int v=Integer.parseInt(sc.next());
                int w=Integer.parseInt(sc.next());
                Edge e=new Edge(u,v,w);
                edgeList.add(e);
            }

            d.adjacencyList(edgeList,vertices);

            int[] ans=d.dijkastra(src,vertices);
            System.out.println("The distance between "+src+" to "+dst+" is "+ans[dst]);
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

