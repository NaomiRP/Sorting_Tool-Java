package data;

public abstract class StringData extends Data<String> {

    protected StringData(DataType dataType) {
        super(dataType);
    }

    protected String dataAsString(){
        return String.join(dataType.getSeparator(), data);
    }

}
