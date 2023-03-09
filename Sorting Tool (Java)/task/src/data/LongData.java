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

        while (scanner.hasNext()) {
            if (scanner.hasNextLong())
                data.add(scanner.nextLong());
            else
                System.out.println("\"" + scanner.next() + "\" is not a long. It will be skipped.");
        }
    }

    protected String dataAsString(){
        return data.stream().map(String::valueOf).collect(Collectors.joining(dataType.getSeparator()));
    }

}
