import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataFileGenerator {
    public static void main(String[] args) {
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"));
            Random random = new Random();
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 1000; j++) {
                    int randomNumber = random.nextInt(1000000); // Generate numbers from 0 to 999,999
                    writer.write(randomNumber + " ");
                }
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}