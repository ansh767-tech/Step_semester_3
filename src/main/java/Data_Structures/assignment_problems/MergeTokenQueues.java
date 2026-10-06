package Data_Structures.assignment_problems;

import java.util.Arrays;

public class MergeTokenQueues {
    public static int[] mergeTokens(int[] counterA, int[] counterB) {
        int m = counterA.length;
        int n = counterB.length;
        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        
        while (i < m && j < n) {
            if (counterA[i] <= counterB[j]) {
                merged[k++] = counterA[i++];
            } else {
                merged[k++] = counterB[j++];
            }
        }

        
        while (i < m) {
            merged[k++] = counterA[i++];
        }

        
        while (j < n) {
            merged[k++] = counterB[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] counterA1 = {3, 8, 15, 20};
        int[] counterB1 = {5, 8, 12};
        System.out.println("Sample 1: " + Arrays.toString(mergeTokens(counterA1, counterB1))); 

        int[] counterA2 = {};
        int[] counterB2 = {4, 9};
        System.out.println("Sample 2: " + Arrays.toString(mergeTokens(counterA2, counterB2))); 
    }
}