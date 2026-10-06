package Data_Structures.class_problems;

import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSum {
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> seenNumbers = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (seenNumbers.contains(complement)) {
                return true;
            }
            seenNumbers.add(num);
        }

        return false;
    }
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Sample 1 (Optimal): " + hasPairWithSum(nums1, target1)); // Expected: true

        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Sample 2 (Optimal): " + hasPairWithSum(nums2, target2)); // Expected: false
    }
}