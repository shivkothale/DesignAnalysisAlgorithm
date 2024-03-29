//Name-Shivkumar Kothale
//BNumber-B010235183

// Compile- javac MergeSort.java
// Run- java MergeSort input1 input2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
//Code for sorting the input array
//Input 2 arguments
//Arg1- File to be processed
//Arg2- nth element to be printed
public class MergeSort {
    public static void main(String[] args) {
        boolean b=true;
        int n=-1;
        int inputLength;
        int[] arr;
        try {
            String filename = args[0];
            Scanner read = new Scanner(new File(filename));
            inputLength=Integer.parseInt(read.nextLine());
            arr=new int[inputLength];
            int i=0;
            while (read.hasNextLine() && i<inputLength){
                arr[i]=(Integer.parseInt(read.nextLine()));
                i++;
            }
            read.close();
            if (inputLength<=0){
                System.out.println("There are no inputs in the file");
                return;
            }

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Please enter a file to process on");
            return;
        }catch(FileNotFoundException e){
            System.out.println("PLease give the correct input file for Merge Sort");
            return;
        }catch (NoSuchElementException e){
            System.out.println("Input File is Empty");
            return;
        }

        try{
            n=Integer.parseInt(args[1]);
            if(n<0){
                System.out.println("Enter the value of input 2 that is greater than 0");
                return;
            }
        }catch(NumberFormatException e){
            System.out.println("Please enter a integer value of input2");
            return;
        }
        catch(ArrayIndexOutOfBoundsException e){
            b=false;
        }
        if(n>=inputLength){
            System.out.println("Please enter the value of input 2 less than the size of input elements in the file");
            return;
        }
        mergeSort(arr);
        if(b){
            System.out.println("The "+n+"th element is : "+arr[n]);
        }else{

            for (int j : arr) System.out.println(j);}
    }
    public static void mergeSort(int[] arr){
        if(arr.length<=1) return;

        int mid=arr.length/2;
        int []leftArr= new int[mid];
        int []rightArr=new int[arr.length-mid];
        int j=0;
        for(int i=0;i<arr.length;i++)
            if (i < mid)
                leftArr[i] = arr[i];
            else {
                rightArr[j] = arr[i];
                j++;
            }
        mergeSort(leftArr);
        mergeSort(rightArr);
        merge(leftArr,rightArr,arr);

    }

    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int leftIdx=0;
        int rightIdx=0;
        int leftHigh=arr.length/2;
        int rightHigh=arr.length-leftHigh;
        int arrIdx=0;
        while(leftIdx<leftHigh && rightIdx<rightHigh){
            if(leftArr[leftIdx]<rightArr[rightIdx]){
                arr[arrIdx]=leftArr[leftIdx];
                leftIdx++;
            }else{
                arr[arrIdx]=rightArr[rightIdx];
                rightIdx++;
            }
            arrIdx++;
        }
        while(leftIdx<leftHigh){
            arr[arrIdx]=leftArr[leftIdx];
            leftIdx++;
            arrIdx++;
        }
        while(rightIdx<rightHigh){
            arr[arrIdx]=rightArr[rightIdx];
            rightIdx++;
            arrIdx++;
        }

    }
}
