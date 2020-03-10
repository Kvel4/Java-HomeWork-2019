package markdownToHtml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


public class Md2Html {
    public static void main(String[] args) {
        String s;
        StringBuilder paragraphSB;
        StringBuilder htmlMarkupSB = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8))) {
            while ((s = br.readLine()) != null) {
                paragraphSB = new StringBuilder();
                while (s != null && !s.isBlank()) {
                    paragraphSB.append(s).append('\n');
                    s = br.readLine();
                }
                if (paragraphSB.length() > 0) {
                    parseParagraph(paragraphSB, htmlMarkupSB);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        try (PrintWriter pr = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
            pr.print(htmlMarkupSB.toString());
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    private static void parseParagraph(StringBuilder paragraphSB, StringBuilder htmlMarkupSB) {
        int i = 0;
        HtmlBlock paragraphType;
        if (paragraphSB.charAt(i) == '#') {
            while (paragraphSB.charAt(i) == '#') {
                i++;
            }
            if (Character.isWhitespace(paragraphSB.charAt(i))) {
                paragraphType = new HtmlBlock("h", i);
                i++;
            } else {
                paragraphType = new HtmlBlock("p");
                i = 0;
            }
        } else {
            paragraphType = new HtmlBlock("p");
        }
        paragraphType.printHtmlMark(htmlMarkupSB);

        for (; i < paragraphSB.length(); i++) {
            String tag = String.valueOf(paragraphSB.charAt(i));
            // проверяем на двойные html теги
            if (i < paragraphSB.length() - 1 && paragraphSB.charAt(i) == paragraphSB.charAt(i + 1)
                    && HtmlBlock.inBlockTags.containsKey(tag + paragraphSB.charAt(i + 1))) {
                HtmlBlock.inBlockTags.get(tag + paragraphSB.charAt(i + 1)).printHtmlMark(htmlMarkupSB);
                i += 1;
            } else if (HtmlBlock.inBlockTags.containsKey(tag) && // на одиночные html теги
                    !(Character.isWhitespace(paragraphSB.charAt(i - 1)) && Character.isWhitespace(paragraphSB.charAt(i + 1))
                            || paragraphSB.charAt(i - 1) == '\\')) {
                HtmlBlock.inBlockTags.get(tag).printHtmlMark(htmlMarkupSB);
            } else if (HtmlBlock.specialSymbols.containsKey(tag)) { //спецсимволы
                htmlMarkupSB.append(HtmlBlock.specialSymbols.get(tag));
            } else {
                htmlMarkupSB.append(paragraphSB.charAt(i));
            }
        }
        if (htmlMarkupSB.charAt(htmlMarkupSB.length() - 1) == '\n') {
            htmlMarkupSB.deleteCharAt(htmlMarkupSB.length() - 1);
        }

        paragraphType.printHtmlMark(htmlMarkupSB);
        htmlMarkupSB.append('\n');
    }
}
