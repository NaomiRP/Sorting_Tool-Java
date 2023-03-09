package sorting;

import data.Data;
import data.DataFactory;
import data.DataType;

import java.util.*;

public class Main {

    private static final String SORT_TYPE_ARG = "-sortingType";
    private static final String DATA_TYPE_ARG = "-dataType";

    private static final List<String> VALID_SORT_TYPES = List.of("natural", "byCount");

    private static boolean naturalSort = true;
    private static DataType dataProcessingMode = DataType.WORD;

    public static <T extends Comparable<T>> void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> runOptions = Arrays.asList(args);

        for (int i = 0; i < runOptions.size(); i++) {
            String opt = runOptions.get(i);
            if (isArgument(opt)) {
                switch (opt) {
                    case SORT_TYPE_ARG -> {
                        if (i+1 < runOptions.size() && VALID_SORT_TYPES.contains(runOptions.get(i+1))) {
                            naturalSort = VALID_SORT_TYPES.get(0).equals(runOptions.get(i+1));
                            i++;
                        } else {
                            System.out.println("No sorting type defined!");
                            return;
                        }
                    }
                    case DATA_TYPE_ARG -> {
                        try {
                            dataProcessingMode = DataType.valueOf(runOptions.get(i+1).toUpperCase());
                            i++;
                        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                            System.out.println("No data type defined!");
                            return;
                        }
                    }
                    default -> System.out.println("\"" + opt + "\" is not a valid parameter. It will be skipped.");
                }
            }
        }

        Data<T> data = DataFactory.createData(dataProcessingMode, naturalSort);
        data.processData(scanner);
    }

    private static boolean isArgument(String potentialArg) {
        return potentialArg != null && potentialArg.startsWith("-");
    }

}
