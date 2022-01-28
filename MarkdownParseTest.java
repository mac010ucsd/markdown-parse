import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

public class MarkdownParseTest {

    public String readfile(String fileName) throws IOException{
        String contents = Files.readString(Path.of(fileName));
        return contents;
    }

    public ArrayList<String> getLinksFromFile(String fileName) throws IOException{
        String contents = readfile(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        System.out.println(links);
        return links;
    }

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testFile1() throws IOException {
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(getLinksFromFile("test-file.md"), expect);
    }

    @Test
    public void testBackslashes() throws IOException {
        assertEquals(getLinksFromFile("test-backslash-escapes.md"), List.of("/close_bracket", "/single_)bracket", "/double_\\",
        "/triple_\\)bracket", "/quad_\\\\", "/open_(paren"));
    }

    @Test
    public void testEmpty() throws IOException {
        assertEquals(getLinksFromFile("test-empty.md"), List.of());
    }
    
}
