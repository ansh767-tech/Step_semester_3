package Data_Structures.assignment_problems;

import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        public String item;
        public int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    
    public static PopularItemResult mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) return new PopularItemResult("", 0);

        Map<String, Integer> frequencyMap = new HashMap<>();
        int maxCount = 0;

       
        for (String item : orders) {
            int newCount = frequencyMap.getOrDefault(item, 0) + 1;
            frequencyMap.put(item, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
            }
        }

        
        for (String item : orders) {
            if (frequencyMap.get(item) == maxCount) {
                return new PopularItemResult(item, maxCount);
            }
        }

        return new PopularItemResult("", 0);
    }

    public static void main(String[] args) {
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println("Sample 1: " + mostPopular(orders1)); 

        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println("Sample 2: " + mostPopular(orders2)); 
    }
}