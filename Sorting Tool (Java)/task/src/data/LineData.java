package data;

import java.util.ArrayList;
import java.util.Scanner;

public class LineData<String> extends StringData {

    protected LineData(boolean naturalSort) {
        super(DataType.LINE);
        this.naturalSort = naturalSort;
    }

    protected void readData(Scanner scanner){
        data = new ArrayList<>();

        while (scanner.hasNextLine()) {
            data.add(scanner.nextLine());
        }
    }

}
