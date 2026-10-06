package Data_Structures.class_problems;

public class PrefixSumQueries {

    private int[] prefixSum;
    public PrefixSumQueries(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int rangeSum(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        PrefixSumQueries solver = new PrefixSumQueries(nums);

        System.out.println("Sum range (0, 2): " + solver.rangeSum(0, 2)); 
        System.out.println("Sum range (2, 5): " + solver.rangeSum(2, 5)); 
        System.out.println("Sum range (0, 5): " + solver.rangeSum(0, 5)); 
    }
}