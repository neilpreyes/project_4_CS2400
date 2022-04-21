import java.io.*;
import java.util.*;

public class heaptest {
    
    public static void main(String[] args) throws FileNotFoundException{
        final int MAX_CAPACITY = 101;

        //file input
        File file = new File("data_sorted.txt");
        Scanner readIn = new Scanner(file);

        //array to store the ints from the file
        int[] data = new int[MAX_CAPACITY];

        //reading the data into the array
        int i = 0;
        while(readIn.hasNext()){
            data[i+1] = readIn.nextInt();
            i++;
        }

        //optimal test
        heap heapTest = new heap(data);
        /**
         * test for optical method (not working)
         * heap heapTest = new heap();
         * heapTest.optMethod(data);
         */

        System.out.println("Heap built using optimal method: " + Arrays.toString(heapTest.getFirstTen()));
        System.out.println("Number of swaps in the heap creation: " + heapTest.getCountHeap());
        for (int j = 0; j < 10; j++){
            int remove = heapTest.removeMax();
        }
        System.out.println("Heap after 10 removals: " + Arrays.toString(heapTest.getFirstTen()));

        //sequential test
        heap heapTest2 = new heap();

        //add data inputs one by one
        heapTest2.seqMethod(data);
        heapTest2.reheap(1);

        System.out.println("Heap built using sequential method: " + Arrays.toString(heapTest2.getFirstTen()));
        System.out.println("Number of swaps in the heap creation: " + heapTest2.getCountHeap());
        for (int j = 0; j < 10; j++){
            int remove = heapTest2.removeMax();
        }
        System.out.println("Heap after 10 removals: " + Arrays.toString(heapTest2.getFirstTen()));



    }

}
