import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Ferry {

    public static void main(String[] args) {
         // replace with your actual file path

        try {
            // Read input from file
            String filename = args[0];
            BufferedReader sc = new BufferedReader(new FileReader(filename));
            String[] firstLine = sc.readLine().split(" ");
            int numCars = Integer.parseInt(firstLine[0]);
            int ferryLength = Integer.parseInt(firstLine[1]);
            int[] carLengths = new int[numCars];

            for (int i = 0; i < numCars; i++) {
                carLengths[i] = Integer.parseInt(sc.readLine().trim());
            }
            sc.close();

            
            int maxCars = getMaxCars(ferryLength, carLengths);
            System.out.println(maxCars);
            ferrys(ferryLength, carLengths, maxCars);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getMaxCars(int ferryLength, int[] carLengths) {
        int[][] dp = new int[carLengths.length + 1][ferryLength + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        dp[0][0] = 0;

        for (int i = 1; i <= carLengths.length; i++) {
            for (int j = 0; j <= ferryLength; j++) {
                // Try to put the car on the left side
                if (j >= carLengths[i - 1] && dp[i - 1][j - carLengths[i - 1]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - carLengths[i - 1]]);
                }


                int leftspace = dp[i - 1][j];
                if (leftspace != -1 && ferryLength - leftspace >= carLengths[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], j);
                }
            }
        }

        for (int i = carLengths.length; i >= 0; i--) {
            for (int j = ferryLength; j >= 0; j--) {
                if (dp[i][j] != -1) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static void ferrys(int ferryLength, int[] carLengths, int maxCars) {
        boolean[] side = new boolean[maxCars]; 
        int[][] dp = new int[carLengths.length + 1][ferryLength + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        dp[0][0] = 0;


        for (int i = 1; i <= carLengths.length; i++) {
            for (int j = 0; j <= ferryLength; j++) {

                if (j >= carLengths[i - 1] && dp[i - 1][j - carLengths[i - 1]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - carLengths[i - 1]]);
                }


                int leftspace = dp[i - 1][j];
                if (leftspace != -1 && ferryLength - leftspace >= carLengths[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], j);
                }
            }
        }

        // Find which side each car goes on
        int j = -1;
        for (int i = ferryLength; i >= 0; i--) {
            if (dp[maxCars][i] != -1) {
                j = i;
                break;
            }
        }

        if (j == -1) {
            System.out.println("No arrangement possible.");
            return;
        }

        for (int i = maxCars; i > 0; i--) {
            if (j >= carLengths[i - 1] && dp[i - 1][j - carLengths[i - 1]] != -1) {
                side[i - 1] = false;
                j -= carLengths[i - 1];
            } else {
                side[i - 1] = true;
            }
        }


        for (int i = 0; i < maxCars; i++) {
            System.out.println("Car " + (i + 1) + " goes to the " + (side[i] ? "right" : "left"));
        }
    }
}