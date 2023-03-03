package sorting;

public enum DataType {

    LONG("number", "greatest", " "),
    LINE("line", "longest", "\n"),
    WORD("word", "longest", " ");

    private final String label;
    private final String mostLabel;
    private final String separator;

    DataType(String label, String mostLabel, String separator) {
        this.label = label;
        this.mostLabel = mostLabel;
        this.separator = separator;
    }

    public String getLabel() {
        return label;
    }

    public String getMostLabel() {
        return mostLabel;
    }

    public String getSeparator() {
        return separator;
    }
}
