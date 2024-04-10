import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MultiThreadedSort {
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

        int[] threadCounts = {1, 2, 4, 8, 16};
        for (int threads : threadCounts) {
            long startTime = System.currentTimeMillis();
            Thread[] sortingThreads = new Thread[threads];
            int rowsPerThread = 16 / threads;
            for (int i = 0; i < threads; i++) {
                int startRow = i * rowsPerThread;
                int endRow = (i + 1) * rowsPerThread;
                if (i == threads - 1) {
                    endRow = 16;
                }
                sortingThreads[i] = new Thread(new SortRunnable(numbers, startRow, endRow));
                sortingThreads[i].start();
            }
            
            for (Thread thread : sortingThreads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Sorting time with " + threads + " threads: " + (endTime - startTime) + " ms");
        }
    }

    static class SortRunnable implements Runnable {
        private final int[][] numbers;
        private final int startRow;
        private final int endRow;

        SortRunnable(int[][] numbers, int startRow, int endRow) {
            this.numbers = numbers;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                Arrays.sort(numbers[i]);
            }
        }
    }
}
