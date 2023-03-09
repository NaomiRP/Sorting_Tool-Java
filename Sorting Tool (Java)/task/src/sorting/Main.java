package sorting;

import data.Data;
import data.DataFactory;
import data.DataType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static final String SORT_TYPE_ARG = "-sortingType";
    private static final String DATA_TYPE_ARG = "-dataType";
    private static final String INPUT_FILE_ARG = "-inputFile";
    private static final String OUTPUT_FILE_ARG = "-outputFile";

    private static final List<String> VALID_SORT_TYPES = List.of("natural", "byCount");

    private static boolean naturalSort = true;
    private static DataType dataProcessingMode = DataType.WORD;

    public static <T extends Comparable<T>> void main(final String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PrintStream out = System.out;

        List<String> runOptions = Arrays.asList(args);

        for (int i = 0; i < runOptions.size(); i++) {
            String opt = runOptions.get(i);
            if (isArgument(opt)) {
                String val = getNextVal(runOptions, i);
                switch (opt) {
                    case SORT_TYPE_ARG -> {
                        if (val != null && VALID_SORT_TYPES.contains(val)) {
                            naturalSort = VALID_SORT_TYPES.get(0).equals(val);
                            i++;
                        } else {
                            System.out.println("No sorting type defined!");
                            return;
                        }
                    }
                    case DATA_TYPE_ARG -> {
                        try {
                            dataProcessingMode = DataType.valueOf(val.toUpperCase());
                            i++;
                        } catch (IllegalArgumentException | NullPointerException e) {
                            System.out.println("No data type defined!");
                            return;
                        }
                    }
                    case INPUT_FILE_ARG -> {
                        if (val != null) {
                            scanner = new Scanner(Path.of(val));
                            i++;
                        }
                    }
                    case OUTPUT_FILE_ARG -> {
                        if (val != null) {
                            out = new PrintStream(new FileOutputStream(val));
                            i++;
                        }
                    }
                    default -> System.out.println("\"" + opt + "\" is not a valid parameter. It will be skipped.");
                }
            }
        }

        Data<T> data = DataFactory.createData(dataProcessingMode, naturalSort);
        data.processData(scanner, out);
    }

    private static boolean isArgument(String potentialArg) {
        return potentialArg != null && potentialArg.startsWith("-");
    }

    private static String getNextVal(List<String> runOpts, int currentIndex) {
        int nextIndex = currentIndex + 1;
        String val = null;
        if (nextIndex < runOpts.size()) {
            val = runOpts.get(nextIndex);
            if (isArgument(val))
                val = null; //arguments aren't values for other arguments
        }
        return val;
    }

}
