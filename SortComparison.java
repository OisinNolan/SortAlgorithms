import java.io.*;
import java.util.Scanner;

// -------------------------------------------------------------------------


/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Oisín Nolan
 *  @version HT 2019
 */

 class SortComparison {
    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double[] insertionSort (double[] a){
    		double tmp;
    		for(int i=1; i<a.length; i++) {
    			for(int j=i; j>0; j--) {
    				if((a[j-1]) > a[j]) {
    					tmp = a[j];
    					a[j] = a[j-1];
    					a[j-1] = tmp;
    				}
    			}
    		}
    		return a;
    }//end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    
    /** 
     * 
     * Quicksort needs to partition array and recursively quicksort both halves of 
     * the partition.
     * 
     * The partition function is a separate function that orders the array
     * such that one number, namely the pivot, is in the correct position
     * and all elements with lower index have value less than pivot
     * (although not necessarily in order) and all higher index values
     * are great than pivot.
     * 
     * */
    
    // Non-recursive method initiates recursive method with suitable initial values.
    // The same array, a, is edited throughout and then returned after sorting.
    static double [] quickSort (double a[]){
    		quickSortRecursive(a, 0, a.length-1);
    		return a;
    }
    
    static void quickSortRecursive(double a[], int lo, int hi) {
    		if(lo < hi) {
    			int pivotIndex = partition(a, lo, hi);
    			quickSortRecursive(a, lo, pivotIndex-1);
    			quickSortRecursive(a, pivotIndex+1, hi);
    		}
    }
    
    static int partition(double a[], int lo, int hi) {
    		double pivot = a[hi]; // pivot is last element in array
    		int i = lo-1; // i pointer is 1 index less than first element
    		
    		for(int j=lo; j<hi; j++) { // iterate through list
    			if(a[j] <= pivot) { // when jth element is <= pivot, iterate i & swap
    				i++;
    				swap(a, i, j);
    			}
    		}
    		
    		swap(a, i+1, hi);
    	
    		return i+1;
    }
    
    static void swap(double a[], int i, int j) {
    		double temp = a[i];
    		a[i] = a[j];
    		a[j] = temp;
    }

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
    		int N = a.length;
    		double[] aux = new double[N];
    		for(int size = 1; size<N; size += size) {
    			for(int lo=0; lo < N-size; lo += 2*size) {
    				merge(a, aux, lo, lo+size-1, Math.min(lo+size+size-1, N-1));
    			}
    		}
    		return a;
    }//end mergesortIterative
    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
    		double[] aux = new double[a.length];
    		sort(a, aux, 0, a.length - 1);
    		return a;
   }//end mergeSortRecursive
    
    static void sort(double[] a, double[] aux, int lo, int hi) {
    		if(hi <= lo) return;
		int mid = lo + (hi-lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		merge(a, aux, lo, mid, hi);
    }
    	
    static void merge(double a[], double aux[], int lo, int mid, int hi) {
    	
    		for(int k=lo; k<= hi; k++) {
    			aux[k] = a[k];
    		}
    	
    		int i = lo;
    		int j = mid+1;
    		for(int k=lo; k <= hi; k++) {
    			if (i>mid) a[k] = aux[j++];
    			else if (j > hi) a[k] = aux[i++];
    			else if (aux[j] < aux[i]) a[k] = aux[j++];
    			else a[k] = aux[i++];
    		}
    }
    
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
    		int min=0;
    		double smallest;
    		int smallestIndex = 0;
    		while(min < a.length) {
    			smallest = a[min];
    			smallestIndex = min;
    			for(int j=min+1; j<a.length; j++) {
    				if(a[j] < smallest) {
    					smallest = a[j];
    					smallestIndex = j;
    				}
    			}
    			swap(a, min, smallestIndex);
    			min++;
    		}
    		return a;
    }//end selectionsort

   


    //public static void main(String[] args) throws FileNotFoundException {
    	//	printArray(importArray("numbersSorted1000.txt", 1000));
    	//	printArray(quickSort(importArray("numbersSorted1000.txt", 1000)));
    //}
    
//  public static double[] importArray(String fileName, int arraySize) throws FileNotFoundException {
    	// Input numbers from file and store them in an array
	//	File file = new File(fileName);
	//	BufferedReader br = new BufferedReader(new FileReader(file));
	//	Scanner input = new Scanner(br);
	//	double[] list = new double[arraySize];
	//	for(int i=0; input.hasNextDouble(); i++) {
	//		list[i] = input.nextDouble();
	//	}
	//	input.close();
	//	return list;
//  }
    
//  public static void printArray(double a[]) {
    	//	for(int i=0; i<a.length; i++) {
    	//		System.out.print(a[i] + ", ");
    	//	}
    //	System.out.println();
//   }

 }//end class