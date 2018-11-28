package parser2;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestParser2 {
		@Test public void testEmpty() throws IOException {
			Parser2 parser = new Parser2();
			Object data = parser.parse(new StringReader(""));
			assertEquals("", data);
		}
		@Test public void testText() throws IOException {
			Parser2 parser = new Parser2();
			Object data = parser.parse(new StringReader("say what?"));
			assertEquals("say what?", data.text());
		}
	}
