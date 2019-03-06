import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Oisín Nolan
 *  @version HT 2019
 */

/** 

					Insertion		Quick	 	Merge iterative		Merge recursive		Selection
10 random			3.054			0.581		0.594				0.101				0.053
100 random			1.872			1.181		0.885				2.142				1.296
1000 random			60.469			4.777		3.724				5.621				49.147
1000 few unique		5.297			0.832		2.127				1.315				3.973
1000 nearly ordered	5.636			0.910		1.554				15.229				3.575
1000 reverse order	8.027			9.979		1.734				0.631				5.620	
1000 sorted			5.041			16.052		2.845				0.678				3.281



a. Which of the sorting algorithms does the order of input have an impact on? Why?
Insertion sort and Quicksort
Insertion sort loops through every element between i and 0, each time comparing the current value until it
finds one which is lower. In the case that the list is in order, it will simply loop through once,
in linear time. If the list is in reverse order, it will have to compare every value at i with i-1 other elements
before finding the right location, yielding O(n^2) time complexity.

With quicksort it's due to the pivot chosen. If an array is ordered and the pivot is chosen at the beginning or end, then 
the partition will end up very unbalanced and thus cause a need for more steps.

b. Which algorithm has the biggest difference between the best and worst performance, based
on the type of input, for the input of size 1000? Why?
Insertion sort.
Insertion sort only starts comparing when an element is out of order. It follows that if the array is sorted or nearly
sorted, the algorithm will have to perform very few comparisons, whereas a reversed list would require the 
maximum possible amount of comparisons. It is for this reason that there is such a big difference,
the algorithm can sort at best O(n) and worst O(n^2) depending on input.

c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
based on the input size? Please consider only input files with random order for this answer.

Best scalability is quicksort or mergesort. They both sort the list using divide and
conquer technique, and therefore generally sort an array in O(n log(n)) time. This
lends itself to be scaled as log functions begin to plateau at a point.

Worst scalability is insertion sort. For a typical array this algorithm sorts in O(n^2) time,
which scales terribly as it drastically increases in time as the input size (n) increases.

d. Did you observe any difference between iterative and recursive implementations of merge
sort?

Iterative performs better for the random list of 1000 doubles.
Recursive performs better for the list containing duplicates.
Recursive performs better for list containing 10 random.

e. Which algorithm is the fastest for each of the 7 input files? 

10 random - quick, merge recursive
100 random - merge iterative, merge recursive
1000 random	- merge iterative, merge recursive
1000 few unique - insertion
1000 nearly ordered - merge iterative, merge recursive, selection sort
1000 reverse order - merge iterative, merge recursive
1000 sorted - All algorithms except for selection sort.



 * 
 * 
 * 
 * */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
    		double[] empty = {};
    		assertEquals("Testing empty array", empty, SortComparison.selectionSort(empty));
    		assertEquals("Testing empty array", empty, SortComparison.insertionSort(empty));
    		assertEquals("Testing empty array", empty, SortComparison.quickSort(empty));
    		assertEquals("Testing empty array", empty, SortComparison.mergeSortIterative(empty));
    		assertEquals("Testing empty array", empty, SortComparison.mergeSortRecursive(empty));
    }
    
    double[] unsorted10 = { 2377.88, 2910.66, 8458.14, 1522.08, 5855.37, 1934.75, 8106.23, 1735.31, 4849.83, 1518.63,};
    double[] sorted10 = {1518.63, 1522.08, 1735.31, 1934.75, 2377.88, 2910.66, 4849.83, 5855.37, 8106.23, 8458.14};
    
    @Test
    public void s10() {
		assertEquals(Arrays.toString(sorted10), Arrays.toString(SortComparison.selectionSort(unsorted10)));
    }
    
    @Test
    public void i10() {
		assertEquals(Arrays.toString(sorted10), Arrays.toString(SortComparison.insertionSort(unsorted10)));
    }
    
    @Test
    public void q10() {
		assertEquals(Arrays.toString(sorted10), Arrays.toString(SortComparison.quickSort(unsorted10)));
    }
    
    @Test
    public void mi10() {
		assertEquals(Arrays.toString(sorted10), Arrays.toString(SortComparison.mergeSortIterative(unsorted10)));
    }
    
    @Test
    public void mr10() {
		assertEquals(Arrays.toString(sorted10), Arrays.toString(SortComparison.mergeSortRecursive(unsorted10)));
    }
    
    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws FileNotFoundException 
     *
     */
    public static void main(String[] args) throws FileNotFoundException
    {
    		String[] fileNames = {"numbers10.txt", "numbers100.txt", "numbers1000.txt",
    							"numbers1000Duplicates.txt", "numbersNearlyOrdered1000.txt",
    							"numbersReverse1000.txt", "numbersSorted1000.txt"};
    		getTimesFromFiles(fileNames);
    }
    
    public static void getTimesFromFiles(String[] fileNames) throws FileNotFoundException {
    		for(int i=0; i<fileNames.length; i++) {
    			System.out.println(fileNames[i]);
	    		File file = new File(fileNames[i]);
	    		BufferedReader br = new BufferedReader(new FileReader(file));
	    		Scanner input = new Scanner(br);
	    		ArrayList<Double> bufferList = new ArrayList<Double>();
	    		while(input.hasNextDouble()) {
	    			bufferList.add(input.nextDouble());
	    		}
	    		input.close();
	    		double[] array = new double[bufferList.size()];
	    		for(int j=0; j<bufferList.size(); j++) {
	    			array[j] = bufferList.get(j);
	    		}
	    		getTimes(array);
    		}
    }
    
    public static void getTimes(double[] a) {
    		for(int i=0; i<sortFunctions.length; i++) {
    			double total = 0;
    			for(int j=0; j<3; j++) {
    				double[] copy = a.clone();
	    			double before = System.nanoTime();
	    			sortFunctions[i].sort(copy);
	    			double after = System.nanoTime();
	    			total += after-before;
    			}
    			double time = total/3;
    			time /= 100000;
    			System.out.print(sortFunctions[i].getName() + " : ");
    			System.out.printf("%.3f", time);
    			System.out.println();
    		}
    		System.out.println();
    }
    
    public static SortFunction[] sortFunctions = new SortFunction[] {
    		new SortFunction() { 
    			public double[] sort(double[] a) {
    				return SortComparison.insertionSort(a);
    			} 
    			public String getName() {
    				return "Insertion sort";
    			}
    		},
    		new SortFunction() { 
    			public double[] sort(double[] a) {
    				return SortComparison.quickSort(a);
    			} 
    			public String getName() {
    				return "Quick sort";
    			}
    		},
    		new SortFunction() { 
    			public double[] sort(double[] a) {
    				return SortComparison.mergeSortIterative(a);
    			} 
    			public String getName() {
    				return "Merge sort iterative";
    			}
    		},
    		new SortFunction() { 
    			public double[] sort(double[] a) {
    				return SortComparison.mergeSortRecursive(a);
    			} 
    			public String getName() {
    				return "Merge sort recursive";
    			}
    		},
    		new SortFunction() { 
    			public double[] sort(double[] a) {
    				return SortComparison.selectionSort(a);
    			} 
    			public String getName() {
    				return "Selection sort";
    			}
    		},
    };
    
    interface SortFunction {
    		double[] sort(double[] a);
    		String getName();
    }

}