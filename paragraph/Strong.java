package paragraph;

import java.util.List;

public class Strong extends Markdown {
    private static String markSymbol = "__";
    private static String htmlSymbol = "strong";

    public Strong(List<MarkdownInterface> markElements) {
        super(markElements, markSymbol, htmlSymbol);
    }
}
