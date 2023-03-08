package data;

import java.util.*;
import java.util.stream.Collectors;

public class LongData<Long> extends NumberData {

    protected LongData(boolean naturalSort) {
        super(DataType.LONG);
        this.naturalSort = naturalSort;
    }

    protected void readData(Scanner scanner){
        data = new ArrayList<>();

        while (scanner.hasNextLong()) {
            data.add(scanner.nextLong());
        }
    }

    protected String dataAsString(){
        return data.stream().map(String::valueOf).collect(Collectors.joining(dataType.getSeparator()));
    }

}
