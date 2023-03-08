package data;

public class DataFactory {

    public static <T extends Comparable<T>> Data<T> createData(DataType dataType, boolean naturalSort) {
        return switch (dataType) {
            case LONG -> new LongData(naturalSort);
            case WORD -> new WordData(naturalSort);
            case LINE -> new LineData(naturalSort);
        };
    }
}
