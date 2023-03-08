package data;

public enum DataType {

    LONG("number", " "),
    LINE("line", "\n"),
    WORD("word", " ");

    private final String label;
    private final String separator;

    DataType(String label, String separator) {
        this.label = label;
        this.separator = separator;
    }

    public String getLabel() {
        return label;
    }

    public String getSeparator() {
        return separator;
    }
}
