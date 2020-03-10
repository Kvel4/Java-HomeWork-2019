package paragraph;

public class Text implements MarkdownInterface {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        stringBuilder.append(text);
        return stringBuilder;
    }

    @Override
    public StringBuilder toHtml(StringBuilder stringBuilder) {
        stringBuilder.append(text);
        return stringBuilder;
    }
}
