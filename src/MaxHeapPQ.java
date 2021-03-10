import java.util.Iterator;
import java.util.ArrayList;

class MaxHeapPQ<K extends Comparable<K>> implements MaxPriorityQueue<K> {
   private K data[];
   private K min;
   private int size;
   private int capacity;

   // constructors
   public MaxHeapPQ() {
      capacity = 100;
      size = 0;
      data = (K[]) new Comparable[capacity];
   }

   public MaxHeapPQ(int c) {
      capacity = c;
      size = 0;
      data = (K[]) new Comparable[capacity];
   }

   // required priority queue methods
   public boolean isEmpty(){ //ADD CODE 
	    if (size <= 0) return true;
	    return false;
   }

   public void add(K x) throws Exception {
      //ADD CODE
	   if(size == capacity) 
		   throw new Exception("Queue Full");
	   data[size] = x;
	   size++;
	   bubbleUp(size-1);
	   if (x.compareTo(data[size-1]) < 0) min();  
   }

   public K removeMax() throws Exception {
      if (size <= 0)
         throw new Exception("Priority Queue Empty");
      //ADD CODE*
      swapData(0, --size);
      //System.out.println(data[1]);
      bubbleDown(0);
     // System.out.println(data[1]);
      return data[size];
   }

   // auxiliary utility methods
   private void swapData(int n, int m) {
      K temp = data[n];
      data[n] = data[m];
      data[m] = temp;
   }

   private void bubbleUp(int n) {
      if (n <= 0)
         return; // at root
      K dn = data[n];
      K dp = data[(n - 1) / 2]; // parent data
      //ADD CODE
      if (dn.compareTo(dp) <  0)
          return; // no problems
       swapData(n, (n - 1) / 2);
       bubbleUp((n - 1) / 2);
       
   }

   public void bubbleDown(int n) {
      if (2 * n + 1 >= size)
         return; // at leaf
      K dn = data[n];
      K dl = data[2 * n + 1]; // left child
      K dr = dl;
      //ADD CODE*
      if (2 * n + 2 < size)
          dr = data[2 * n + 2]; // right child
       if (dn.compareTo(dl) > 0 && dn.compareTo(dr) > 0)
          return; // no problems
       if (dr.compareTo(dl) > 0) {
          swapData(n, 2 * n + 2);
          bubbleDown(2 * n + 2);
       } else {
          swapData(n, 2 * n + 1);
          bubbleDown(2 * n + 1);
        }
   }

   // heap creation
   public void heapify(Iterator<K> x) throws Exception {
      while (x.hasNext()) {
         if (size + 1 == capacity)
            break;
         data[size++] = x.next();
      }
      int n = size / 2;
      while (--n >= 0)
         bubbleDown(n);
      if (x.hasNext())
         throw new Exception("Heap is Full");
   }
   
   /* Add a min() method to find the minimum. Your implementation should use constant time and constant extra space.
    * Hint: add an extra instance variable that points to the minimum item. Update it after each call to add(K x).
    * Return null if the heap is empty.
    */
   public K min(){
	   //ADD CODE
	   min = data[size-1];
	   return min;
   }

   public static void main(String[] args){
	   MaxHeapPQ<String> heap = new MaxHeapPQ<String>();
	   try{
	   	heap.add("q");
	   	heap.add("w");
	   	heap.add("e");
	   	heap.add("r");
	   	heap.add("t");
	   	heap.add("z");
	   	System.out.println(heap.min()); // the output would be e
		while (!heap.isEmpty()) System.out.print(heap.removeMax()); // the printout would be ywtrqe
		System.out.println();
	   } catch (Exception e){System.out.println("Error " + e.toString()); }
   }
}

interface MaxPQ<K extends Comparable<K>> {
   public void add(K x) throws Exception;
   public K removeMax() throws Exception;
   public boolean isEmpty();
}

