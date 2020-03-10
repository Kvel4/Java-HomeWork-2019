package expression.parser;

public class StringSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public boolean hasNext() {
        return pos < data.length();
    }

    public char next() {
        return data.charAt(pos++);
    }
}
