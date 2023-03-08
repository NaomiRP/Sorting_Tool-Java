package sorting;

import data.Data;
import data.DataFactory;
import data.DataType;

import java.util.*;

public class Main {

    private static final String SORT_TYPE_ARG = "-sortingType";
    private static final String DATA_TYPE_ARG = "-dataType";

    private static boolean naturalSort = true;
    private static DataType dataProcessingMode = DataType.WORD;

    public static <T extends Comparable<T>> void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> runOptions = Arrays.asList(args);

        int sortTypeIndex = runOptions.indexOf(SORT_TYPE_ARG) + 1;

        if (sortTypeIndex < runOptions.size() && "byCount".equals(runOptions.get(sortTypeIndex))) {
            naturalSort = false;
        }

        int dataTypeIndex = runOptions.indexOf(DATA_TYPE_ARG) + 1;

        if (dataTypeIndex < runOptions.size()) {
            dataProcessingMode = switch (runOptions.get(dataTypeIndex)) {
                case "long" -> DataType.LONG;
                case "line" -> DataType.LINE;
                default -> DataType.WORD;
            };
        }

        Data<T> data = DataFactory.createData(dataProcessingMode, naturalSort);
        data.processData(scanner);
    }

}
