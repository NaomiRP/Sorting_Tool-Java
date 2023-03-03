package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Long> numbers = new ArrayList<>();

        while (scanner.hasNextLong()) {
            numbers.add(scanner.nextLong());
        }

        long max = 0;
        int count = 0;

        for (Long n : numbers) {
            if (n > max) {
                max = n;
                count = 1;
            } else if (n == max) {
                count++;
            }
        }

        System.out.println("Total numbers: " + numbers.size() + ".");
        System.out.println("The greatest number: " + max + " (" + count + " time(s)).");
    }
}
