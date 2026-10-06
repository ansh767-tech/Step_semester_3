package Data_Structures.assignment_problems;

public class HotWeatherAlertWindows {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k) return 0;

        int alertCount = 0;
        long windowSum = 0;
        long targetSum = (long) k * threshold; 

        
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }

        if (windowSum >= targetSum) {
            alertCount++;
        }

        
        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k]; 
            if (windowSum >= targetSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        System.out.println("Alert Count: " + countAlerts(readings, k, threshold)); 
    }
}