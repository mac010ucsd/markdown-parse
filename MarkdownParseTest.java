import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    public String readFile(String file) throws IOException {
        Path fileName = Path.of(file);
        String contents = Files.readString(fileName);

        return contents;
    }

    @Test
    public void testBaseCase() throws IOException {
        String contents = readFile("test-file.md");
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        ArrayList<String> Reallinks =
                new ArrayList<>(List.of("https://something.com", "some-page.html"));

        assertArrayEquals(links.toArray(), Reallinks.toArray());


    }

    @Test
    public void testEmptyCase() throws IOException {
        String contents = readFile("test-empty.md");
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        ArrayList<String> Reallinks = new ArrayList<>(List.of());

        assertArrayEquals(links.toArray(), Reallinks.toArray());

    }


    @Test
    public void testNewLine() throws IOException {
        String contents = readFile("test-file-newline.md");
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        ArrayList<String> Reallinks =
                new ArrayList<>(List.of("https://something.com", "some-page.html"));
        assertArrayEquals(links.toArray(), Reallinks.toArray());


    }


}
