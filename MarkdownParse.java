// AA
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static int indexOfUnescaped(String str, String search, int startIndex) {
        int currIndex = startIndex - 1;
        int backslashCount;
        do {
            currIndex = str.indexOf(search, currIndex + 1);
            backslashCount = 0;
            int index = currIndex - 1;
            while (index >= 0 && str.charAt(index) == '\\') {
                index--;
                backslashCount++;
            }
        } while (backslashCount % 2 == 1);
        return currIndex;
    }

    public static ArrayList<String> getLinksFromLine(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        while (currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            if (nextOpenBracket == -1) {
                return toReturn;
            }
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
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
