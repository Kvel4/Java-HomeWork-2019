package paragraph;

import java.util.List;

public class Emphasis extends Markdown {
    private static String markSymbol = "*";
    private static String htmlSymbol = "em";

    public Emphasis(List<MarkdownInterface> markElements) {
        super(markElements, markSymbol, htmlSymbol);
    }
}
