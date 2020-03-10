package paragraph;

import java.util.List;

public class Strikeout extends Markdown {
    private static String markSymbol = "~";
    private static String htmlSymbol = "s";

    public Strikeout(List<MarkdownInterface> markElements) {
        super(markElements, markSymbol, htmlSymbol);
    }
}
