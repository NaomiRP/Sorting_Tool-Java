package data;

import java.util.ArrayList;
import java.util.Scanner;

public class WordData<String> extends StringData {

    protected WordData(boolean naturalSort) {
        super(DataType.WORD);
        this.naturalSort = naturalSort;
    }

    protected void readData(Scanner scanner){
        data = new ArrayList<>();

        while (scanner.hasNext()) {
            data.add(scanner.next());
        }
    }

}
