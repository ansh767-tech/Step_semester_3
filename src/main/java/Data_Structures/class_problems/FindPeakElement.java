package Data_Structures.class_problems;

public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) {
                
                high = mid;
            } else {
                
                low = mid + 1;
            }
        }

        return low; 
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Peak Index: " + findPeakElement(nums1)); 

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Peak Index: " + findPeakElement(nums2));
    }
}