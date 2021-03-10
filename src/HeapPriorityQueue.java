import java.util.Iterator;
import java.util.ArrayList;

class HeapPriorityQueue<K extends Comparable<K>> implements MaxPriorityQueue<K> {
   private K data[];
   private int size;
   private int capacity;
   private K minimum;

   // constructors
   public HeapPriorityQueue() {
      capacity = 100;
      size = 0;
      data = (K[]) new Comparable[capacity];
      minimum = null;
   }

   public HeapPriorityQueue(int c) {
      capacity = c;
      size = 0;
      data = (K[]) new Comparable[capacity];
      minimum = null;
   }

   // required priority queue methods
   public boolean isEmpty(){ return size==0; }

   public void add(K x) throws Exception {
      if (size >= capacity - 1)
         throw new Exception("Priority Queue Full");
      if (minimum == null || x.compareTo(minimum) <= 0) minimum = x;
      data[size++] = x;
      bubbleUp(size - 1);
   }

   public K removeMax() throws Exception {
      if (size <= 0)
         throw new Exception("Priority Queue Empty");
      swapData(0, --size);
      bubbleDown(0);
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
      if (dn.compareTo(dp) <= 0)
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
	   if (size == 0) return null;
	   return minimum;
   }

   public static void main(String[] args){
	   HeapPriorityQueue<String> heap = new HeapPriorityQueue<String>();
	   try{
	   	heap.add("q");
	   	heap.add("w");
	   	heap.add("e");
	   	heap.add("r");
	   	heap.add("t");
	   	heap.add("y");
	   	System.out.println(heap.min());
		while (!heap.isEmpty()) System.out.print(heap.removeMax());
		System.out.println();
	   } catch (Exception e){System.out.println("Error " + e.toString()); }

   }
}

interface MaxPriorityQueue<K extends Comparable<K>> {
   public void add(K x) throws Exception;
   public K removeMax() throws Exception;
   public boolean isEmpty();
}

