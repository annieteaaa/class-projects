//NAME: Annie Tong
//ID: A15770705
//EMAIL: actong@ucsd.edu

/**
 * FILE HEADER
 */

import java.util.Arrays;

/**
 * CLASS HEADER
 */
class Sorting<E extends Comparable<E>> {
    
    public void insertionSort(E[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                throw new NullPointerException();
            }
        }
        System.out.println(Arrays.toString(array));
        for(int i = 1; i < array.length; i++) {
            int minIdx = i;
            for(int j = i-1; j >= 0; j--) {
                if(array[i].compareTo(array[j]) < 0) {
                    minIdx = j;
                }
            }
            E insertThis = array[i];
            for(int j = i; j > minIdx; j--) {
                E temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }
            array[minIdx] = insertThis;
            System.out.println(Arrays.toString(array));
        }
    }

    public void mergeSort(E[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null) {
                throw new NullPointerException();
            }
        }
        int leftBound = 0;
        int rightBound = array.length-1;
        if(array.length < 2) {
            //base case
        }
	else if(array.length < 4) {
            int midPoint = (leftBound + rightBound)/2;
            if(leftBound != midPoint) {
                E[] leftPart = Arrays.copyOfRange(array, 0, midPoint);
                E[] rightPart = Arrays.copyOfRange(array, midPoint, array.length);
                mergeSort(leftPart);
                mergeSort(rightPart);
                merge(array, leftPart, rightPart, leftBound, rightBound);
            }
            else {
                E[] leftPart = Arrays.copyOfRange(array, 0, 1);
                E[] rightPart = Arrays.copyOfRange(array, 1, array.length);
                mergeSort(leftPart);
                mergeSort(rightPart);
                merge(array, leftPart, rightPart, leftBound, rightBound);
            }
            System.out.println(Arrays.toString(array));
        }
        else {
            int midPoint = array.length/4;
            E[] leftPart = Arrays.copyOfRange(array, 0, midPoint);
            E[] rightPart = Arrays.copyOfRange(array, midPoint, array.length);
            mergeSort(leftPart);
            mergeSort(rightPart);
            merge(array, leftPart, rightPart, leftBound, rightBound);
            System.out.println(Arrays.toString(array));
        }
    }

    public void merge(E[] array, E[] leftArray, E[] rightArray, int left, 
            int right) {
        int mergePos = 0;
        int leftPos = 0;
        int rightPos = 0;
        while(leftPos < leftArray.length && rightPos < rightArray.length) {
            if(leftArray[leftPos].compareTo(rightArray[rightPos]) <= 0) {
                array[mergePos] = leftArray[leftPos];
                leftPos++;
            }
            else {
                array[mergePos] = rightArray[rightPos];
                rightPos++;
            }
            mergePos++;
        }
        while(leftPos < leftArray.length && mergePos < array.length) {
            array[mergePos] = leftArray[leftPos];
            leftPos++;
            mergePos++;
        }
        while(rightPos < rightArray.length && mergePos < array.length) {
            array[mergePos] = rightArray[rightPos];
            rightPos++;
            mergePos++;
        }
    }

}
