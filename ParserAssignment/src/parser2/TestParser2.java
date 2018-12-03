package parser2;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestParser2 {
		@Test public void testArray() throws IOException {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse(new StringReader("[a]"));
			assertEquals("[a]", data.toString());
		}
		@Test public void testObjectToMap() throws IOException {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse(new StringReader("{a:b}"));
			assertEquals("{a=b}", data.toString());
		}
		@Test public void testMultiValueArray() throws IOException {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse(new StringReader("[a,b,c]"));
			assertEquals("[a,b,c]", data.toString());
		}
		@Test public void testMultiValueObject() throws IOException {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse(new StringReader("{a:b,c:d,e:f}"));
			assertEquals("{a=b,c=d,e=f}", data.toString());
		}
	}
