
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinksFromLine(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) {
                break;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            int nextOpenParen = markdown.indexOf("(", openParen + 1);
            if (nextOpenParen != -1 && closeParen > nextOpenParen) {
                // Invalid link because there's an open paren before the close paren
                int openBracket = markdown.indexOf("[", openParen);
                if (openBracket != -1) {
                    // Maybe there's another link
                    currentIndex = openBracket;
                    continue;
                } else {
                    break;
                }
            }
            if (closeParen == -1) {
                break;
            }
            // Check that this isn't an image link
            if (!(nextOpenBracket > 0 && markdown.charAt(nextOpenBracket - 1) == '!')) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        for (String line : markdown.split("\n")) {
            toReturn.addAll(getLinksFromLine(line));
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
