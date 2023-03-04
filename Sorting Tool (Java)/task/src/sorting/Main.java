package sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String SORT_INT_ARG = "-sortIntegers";
    private static final String DATA_TYPE_ARG = "-dataType";

    //private static boolean sortIntMode;
    //private static DataType dataProcessingMode;

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> runOptions = Arrays.asList(args);

        if (runOptions.contains(SORT_INT_ARG)) {
            sortIntegerData(scanner);
            return;
        }

        int dataTypeIndex = runOptions.indexOf(DATA_TYPE_ARG) + 1;

        if (dataTypeIndex < runOptions.size()) {
            switch (runOptions.get(dataTypeIndex)) {
                case "long" -> processLongData(scanner);
                case "line" -> processLineData(scanner);
                default -> processWordData(scanner);
            }
        } else {
            processWordData(scanner);
        }
    }

    private static void sortIntegerData(Scanner scanner) {
        List<Long> numbers = readIntData(scanner);
        Collections.sort(numbers);
        System.out.println("Total numbers: " + numbers.size() + ".");
        System.out.println("Sorted data: " + stringifyNumericList(numbers));
    }

    @NotNull
    private static List<Long> readIntData(Scanner scanner) {
        List<Long> numbers = new ArrayList<>();

        while (scanner.hasNextLong()) {
            numbers.add(scanner.nextLong());
        }
        return numbers;
    }

    private static void processLongData(Scanner scanner) {
        List<Long> numbers = readIntData(scanner);

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

        int total = numbers.size();

        print(DataType.LONG, String.valueOf(max), total, count);
    }

    private static void processLineData(Scanner scanner) {
        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        processStrings(lines, DataType.LINE);
    }

    private static void processWordData(Scanner scanner) {
        List<String> words = new ArrayList<>();

        while (scanner.hasNext()) {
            words.add(scanner.next());
        }

        processStrings(words, DataType.WORD);
    }

    private static void processStrings(List<String> strings, DataType dataType) {
        String longest = "";
        int count = 0;

        for (String l : strings) {
            if (l.length() > longest.length()) {
                longest = l;
                count = 1;
            } else if (l.length() == longest.length()) {
                int compareResult = longest.compareTo(l);
                if (compareResult == 0) {
                    count++;
                } else if (compareResult > 0) {
                    longest = l;
                    count = 1;
                }
            }
        }

        print(dataType, longest, strings.size(), count);
    }

    private static void print(DataType dataType, String mostValue, int total, int count) {
        int percentage = count * 100 / total;
        System.out.println("Total " + dataType.getLabel() + "s: " + total + ".");
        System.out.println("The " + dataType.getMostLabel() + " " + dataType.getLabel() + ":" + dataType.getSeparator() + mostValue + dataType.getSeparator() + "(" + count + " time(s), " + percentage + "%).");
    }

    private static String stringifyNumericList(List<Long> numbers) {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

}
