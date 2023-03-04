package sorting;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String SORT_TYPE_ARG = "-sortingType";
    private static final String DATA_TYPE_ARG = "-dataType";

    private static boolean naturalsort = true;
    private static DataType dataProcessingMode = DataType.WORD;

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> runOptions = Arrays.asList(args);

        int sortTypeIndex = runOptions.indexOf(SORT_TYPE_ARG) + 1;

        if (sortTypeIndex < runOptions.size() && "byCount".equals(runOptions.get(sortTypeIndex))) {
            naturalsort = false;
        }

        int dataTypeIndex = runOptions.indexOf(DATA_TYPE_ARG) + 1;

        if (dataTypeIndex < runOptions.size()) {
            dataProcessingMode = switch (runOptions.get(dataTypeIndex)) {
                case "long" -> DataType.LONG;
                case "line" -> DataType.LINE;
                default -> DataType.WORD;
            };
        }

        switch (dataProcessingMode) {
            case LONG -> processLongData(scanner);
            case LINE -> processLineData(scanner);
            case WORD -> processWordData(scanner);
        }
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
        int total = numbers.size();

        if (naturalsort) {
            Collections.sort(numbers);
            print(DataType.LONG, stringifyNumericList(numbers), total);
        } else {
            //TODO
        }
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
        Collections.sort(strings);
        print(dataType, String.join(dataType.getSeparator(), strings), strings.size());
    }

    private static void print(DataType dataType, String sortedData, int total) {
        System.out.println("Total " + dataType.getLabel() + "s: " + total + ".");
        System.out.println("Sorted data:" + dataType.getSeparator() + sortedData);
        //"(" + count + " time(s), " + percentage + "%).");
    }

    private static String stringifyNumericList(List<Long> numbers) {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

}
