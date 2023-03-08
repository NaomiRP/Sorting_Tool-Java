package data;

import java.util.*;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Data<T extends Comparable<T>> {
    protected DataType dataType;
    protected boolean naturalSort = true;

    protected List<T> data;

    protected Map<T, Long> countedData;

    protected List<Entry<T, Long>> sortedByCount;

    protected Data(DataType dataType) {
        this.dataType = dataType;
    }

    public void processData(Scanner scanner) {
        readData(scanner);
        sortData();
        printData();
    }

    protected abstract void readData(Scanner scanner);

    protected void sortData(){
        if (naturalSort) {
            Collections.sort(data);
        } else {
            countedData = data.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            sortedByCount = countedData.entrySet().stream()
                    .sorted(Entry.<T, Long> comparingByValue().thenComparing(Entry.comparingByKey()))
                    .collect(Collectors.toList());
        }
    }

    protected void printData() {
        int total = data.size();
        System.out.println("Total " + dataType.getLabel() + "s: " + total + ".");
        if (naturalSort) {
            System.out.println("Sorted data:" + dataType.getSeparator() + dataAsString());
        } else {
            for (Entry<T, Long> entry : sortedByCount) {
                Long count = entry.getValue();
                long percentage = count * 100 / total;
                System.out.println(entry.getKey() + ": " + count + " time(s), " + percentage + "%.");
            }
        }
    }

    protected abstract String dataAsString();

}
