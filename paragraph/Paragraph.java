package paragraph;
import java.util.List;

public class Paragraph{
    private List<MarkdownInterface> markElements;

    public Paragraph(List<MarkdownInterface> markElements) {
        this.markElements = markElements;
    }

    public StringBuilder toMarkdown(StringBuilder stringBuilder){
        for (MarkdownInterface element : markElements){
            element.toMarkdown(stringBuilder);
        }
        return stringBuilder;
    }

    public StringBuilder toHtml(StringBuilder stringBuilder){
        for (MarkdownInterface element : markElements){
            element.toHtml(stringBuilder);
        }
        return stringBuilder;
    }
}
