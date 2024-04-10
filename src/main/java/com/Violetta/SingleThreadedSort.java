import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SingleThreadedSort {
    public static void main(String[] args) {
        int[][] numbers = new int[16][1000];
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 16) {
                String[] tokens = line.trim().split("\\s+");
                for (int col = 0; col < 1000; col++) {
                    numbers[row][col] = Integer.parseInt(tokens[col]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        long startTime = System.currentTimeMillis();
        
        for (int[] row : numbers) {
            Arrays.sort(row);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Single threaded sorting time: " + (endTime - startTime) + " ms");
    }
}
