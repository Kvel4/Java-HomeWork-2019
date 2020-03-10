package paragraph;

import java.util.List;


abstract class Markdown implements MarkdownInterface{
    private List<MarkdownInterface> markElements;
    private String markdownSymbol;
    private String htmlSymbol;


    public Markdown(List<MarkdownInterface> markElements, String markdownSymbol, String htmlSymbol) {
        this.markElements = markElements;
        this.markdownSymbol = markdownSymbol;
        this.htmlSymbol = htmlSymbol;
    }

    private void addMarkSymbol(StringBuilder stringBuilder){
        stringBuilder.append(markdownSymbol);
    }

    private void addHtmlSymbol(StringBuilder stringBuilder, boolean open){
        if (open) {
            stringBuilder.append("<");
        } else {
            stringBuilder.append("</");
        }
        stringBuilder.append(htmlSymbol).append(">");

    }

    @Override
    public StringBuilder toMarkdown(StringBuilder stringBuilder) {
        addMarkSymbol(stringBuilder);
        for (MarkdownInterface element: markElements) {
            element.toMarkdown(stringBuilder);
        }
        addMarkSymbol(stringBuilder);
        return stringBuilder;
    }

    @Override
    public StringBuilder toHtml(StringBuilder stringBuilder) {
        addHtmlSymbol(stringBuilder, true);
        for (MarkdownInterface element : markElements) {
            element.toHtml(stringBuilder);
        }
        addHtmlSymbol(stringBuilder, false);
        return stringBuilder;
    }

}
