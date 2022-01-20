
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) {
                break;
            }
            int nextNewline = markdown.indexOf("\n", nextOpenBracket);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            if (nextNewline != -1 && nextCloseBracket > nextNewline) {
                // Invalid link because there's a newline before the close bracket
                currentIndex = nextNewline;
                continue;
            }
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            int nextOpenParen = markdown.indexOf("(", openParen + 1);
            if (nextOpenParen != -1 && closeParen > nextOpenParen) {
                // Invalid link because there's an open paren before the close paren
                currentIndex = closeParen;
                continue;
            }
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}
