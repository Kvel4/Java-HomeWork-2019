package markdownToHtml;

import java.util.HashMap;
import java.util.Map;

class HtmlBlock {
    static Map<String, HtmlBlock> inBlockTags = new HashMap<>() {{
        put("*", new HtmlBlock("em"));
        put("**", new HtmlBlock("strong"));
        put("_", new HtmlBlock("em"));
        put("__", new HtmlBlock("strong"));
        put("--", new HtmlBlock("s"));
        put("`", new HtmlBlock("code"));
        put("++", new HtmlBlock("u"));
    }};
    static Map<String, String> specialSymbols = new HashMap<>() {{
        put("<", "&lt;");
        put(">", "&gt;");
        put("&", "&amp;");
        put("\\", "");
    }};

    private String htmlTag;
    private boolean opening = true;

    HtmlBlock(String htmlTag) {
        this.htmlTag = htmlTag;
    }

    HtmlBlock(String htmlTag, int headerDegree) {
        this(htmlTag+headerDegree);
    }

    void printHtmlMark(StringBuilder stringBuilder) {
        if (opening) {
            stringBuilder.append("<");
            opening = false;
        } else {
            stringBuilder.append("</");
            opening = true;
        }
        stringBuilder.append(htmlTag).append(">");
    }
}
