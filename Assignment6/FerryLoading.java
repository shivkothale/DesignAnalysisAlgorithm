import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FerryLoading {
    public static void main(String[] args) {
        int inputLength;
        int[] cars;
        int length;
        try {
            String filename = args[0];
            Scanner read = new Scanner(new File(filename));
            inputLength=Integer.parseInt(read.next());
            length=Integer.parseInt(read.next());
            cars=new int[inputLength];
            int i=0;
            while (read.hasNextLine()&& i<inputLength){
                cars[i]=Integer.parseInt(read.next());
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
        int ans=ferryHelper(cars,0,new int[]{0,0},length,0);
        System.out.println(ans);
    }

    private static int ferryHelper(int[] cars, int i, int[] ferrys, int length,int count) {
        if(ferrys[0]>length || ferrys[1]>length){
            return count;
        }
        if((ferrys[0]>0 && ferrys[0]<=length) || (ferrys[1]>0 && ferrys[1]<=length) )
            count++;
        if(i>=cars.length){
            return count;
        }
        int j=i+1;
        int left=ferryHelper(cars,j,new int[]{ferrys[0]+cars[i],ferrys[1]},length,count);
        int right=ferryHelper(cars,j,new int[]{ferrys[0],cars[i]+ferrys[1]},length, count);
        return Math.max(left,right);

    }
}
