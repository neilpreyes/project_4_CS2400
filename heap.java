import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class heap implements heapinterface{
    private int[] heap;
    private int lastIndex;
    private boolean integrityStatus = false;
    private static int countHeap = 0;
    
    private static final int DEFAULT_SIZE = 25;
    private static final int MAX_SIZE = 1000;

    public heap(){
        this(DEFAULT_SIZE);
    }

    public heap(int size){
        if(size > MAX_SIZE){
            throw new ArrayIndexOutOfBoundsException("The array size is out of range (max = 1000).");
        }
        else if(size < DEFAULT_SIZE){
            size = DEFAULT_SIZE;
        }

        heap = new int[size];

        lastIndex = 0;
        integrityStatus = true;
    }

    //smart constructor
    public heap(int[] inputs){
        this(inputs.length + 1);
        lastIndex = inputs.length;

        for(int i = 0; i < inputs.length; i++){
            heap[i+1] = inputs[i];
        }
        for(int i = lastIndex/2; i > 0; --i){
            reheap(i);
        }
    }

    @Override
    public void add(int newEntry){
        checkIntegrity();
        checkCapacityAndResize();

        int newIndex = lastIndex + 1;
        int mainIndex = newIndex / 2;

        while(mainIndex > 0 && newEntry < heap[mainIndex]){
            heap[newIndex] = heap[mainIndex];
            newIndex = mainIndex;
            mainIndex = mainIndex/2;
        }

        heap[mainIndex] = newEntry;
        lastIndex++;
    }

    @Override
    public int removeMax(){
        checkIntegrity();

        int result = 0;
        if(!isEmpty()){
            result = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        
        return result;
    }

    @Override
    public int getMax(){
        checkIntegrity();

        int root = 0;
        if(!isEmpty()){
            root = heap[1];
        }
        return root;
    }

    @Override
    public boolean isEmpty(){
        return lastIndex < 1;
    }

    @Override
    public int getSize(){
        int size = 0;
        for(int i: heap){
            if(i != 0){
                size++;
            }
        }
        return size;
    }

    @Override
    public void clear(){
        checkIntegrity();

        while(lastIndex > 0){
            heap[lastIndex] = 0;
            lastIndex--;
        }

        lastIndex = 0;
    }

    @Override
    public void reheap(int rootIndex){
        boolean finished = false;
        int root = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        int rightChildIndex, largerChild;

        while(!finished && (leftChildIndex <= lastIndex)){
            largerChild = leftChildIndex;
            rightChildIndex = leftChildIndex + 1;

            if((rightChildIndex <= lastIndex) && heap[rightChildIndex] > heap[largerChild]){
                largerChild = rightChildIndex;
            }

            if(root < heap[largerChild]){
                heap[rootIndex] = heap[largerChild];
                rootIndex = largerChild;
                leftChildIndex = 2 * rootIndex;
                countHeap++;
            }
            else{
                finished = true;
            }
        }
        heap[rootIndex] = root;
    }

    public void seqMethod(int[] data)
    {
        for(int index = 0; index < data.length; index++)
        {
            add(data[index]);
        }
    }

    public void optMethod(int[] data)
    {
      for(int index = 0; index < data.length; index++){
        heap[index+1] = data[index];
      }
    
      lastIndex = data.length;
      for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
       {
            reheap(rootIndex);
       }     
    }

    public void checkIntegrity(){
        if(!integrityStatus){
            throw new IllegalStateException("Heap is not or has not been properly initialized.");
        }
    }

    public void checkCapacityAndResize(){
        if(lastIndex + 1 >= heap.length){
            heap = resize();
        }
    }

    private int[] resize(){
        int[] result = new int[(heap.length) + 1];
        for(int i = 0; i < heap.length; i++){
            result[i] = heap[i];
        }
        return result;
    }

    public int[] heapCopy(){
        int[] copy = new int[heap.length];
        copy = heap;
        return copy;
    }

    public int[] getFirstTen(){
        int[] firstTen = new int[10];
        int[] arr = heapCopy();

        for(int i = 1; i < 10; i++){
            firstTen[i-1] = arr[i];
 
        }

        return firstTen;
    }

    public int getCountHeap(){
        int count = countHeap;
        return count;
    }
}
