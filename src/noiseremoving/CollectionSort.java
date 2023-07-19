/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noiseremoving;

/**
 * Question 1:
 *      Is quick sort the best way of finding median? Why?
 * Answer:
 *      No, quick sort is not the best way because it has the time complexity of O(n log n) and
 *      requires more methods to make it work. Whereas there are more efficient algorithms.
 * 
 * Question 2:
 *      What is another good way of finding median? Please provide your explanation.
 * Answer:
 *      Insertion sort and selection sort are both good ways for finding the median 
 *      in a small window of 9 pixels. They have the time complexity of O(n^2), however, 
 *      due to the small window size, their performance impact is minimal. Both algorithms
 *      are simple to make and can sort the small window more efficiently.
 * 
 * @author rh200
 */
public class CollectionSort <E extends Comparable> {
    
    public E[] array;
    
    /**
     * Takes a reference of an array and passes the reference to the "array" field.
     * 
     * @param array 
     */
    public void setArray(E[] array)
    {
        this.array = array;
    }
    
    /**
     * Runs quick sort algorithm and sorts array in order using quickSort() recursive method.
     */
    public void quickSort() 
    {
        if (array == null || array.length <= 1) 
        {
            return;
        }
        quickSort(0, array.length - 1);
    }

    /**
     * Recursively performs the quick sort algorithm on the array.
     * 
     * @param low (The lower index of the array)
     * @param high (The higher index of the array)
     */
    private void quickSort(int low, int high) 
    {
        if (low < high) 
        {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    /**
     * Partitions the array based on a pivot element.
     * 
     * @param low (The lower index of the array)
     * @param high (The higher index of the array)
     * @return The index of the pivot element
     */
    private int partition(int low, int high) 
    {
        E pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) 
        {
            if (array[j].compareTo(pivot) <= 0) 
            {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Swaps two elements in the array.
     * 
     * @param i (The index of the first element)
     * @param j (The index of the second element)
     */
    private void swap(int i, int j) 
    {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
