//Name-Shivkumar Kothale
//BNumber-B01035183

//compile-javac Huffman.java
//run java Huffman [input].txt
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Huffman {
    static class Node {
        int value;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        int [] value;
        int len;
        try {
            String filename =args[0];
            Scanner sc=new Scanner(new File(filename));
            len=Integer.parseInt(sc.nextLine());
            value=new int[len];
            int i=0;
            while(sc.hasNextLine()){
                value[i]=Integer.parseInt(sc.nextLine());
                i++;
            }

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
        PriorityQueue<Node> pq=new PriorityQueue<Node>(Comparator.comparingInt(a -> a.value));
        for(int i=0;i<value.length;i++){
            Node n=new Node();
            n.value=value[i];
            n.left=null;
            n.right=null;
            pq.add(n);
        }
        Node root=null;
        while(pq.size()>1){
            Node n1=pq.peek();
            pq.poll();
            Node n2=pq.peek();
            pq.poll();
            Node n=new Node();
            n.value=n1.value+n2.value;
            if(n1.value<n2.value){
                n.left=n1;
                n.right=n2;
            }else {
                n.left = n2;
                n.right = n1;
            }
            root=n;
            pq.add(n);
        }
        int cost=printNodes(root,"",0);
        System.out.println("The cose of the huffman encode is : "+cost);

    }

    private static int  printNodes(Node root, String s,int cost) {
        if(root.left==null && root.right==null){
            cost+=(root.value*(s.length()));
            return cost;
        }


        int left= printNodes(root.left,s+"0",cost);
        int right=printNodes(root.right,s+"1",cost);
        return left+right;
    }
}
