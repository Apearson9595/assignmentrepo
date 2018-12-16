package parser2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestParser2 {
	@Test
	public void testArray() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse(("[\"a\"]"));
		assertEquals("[\"a\"]", data.toString());
	}

	@Test
	public void testObjectToMap() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("{\"a\":\"b\"}");
		assertEquals("{\"a\"=\"b\"}", data.toString());
	}

	@Test
	public void testMultiValueArray() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("[\"a\",\"b\",\"c\",]");
		assertEquals("[\"a\",\"b\",\"c\"]", data.toString());
	}

	@Test
	public void testMultiValueObject() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"}");
		assertEquals("{\"c\"=\"d\",\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testMultiLayerArray() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("[\"a\",[\"b\"]]");
		assertEquals("[\"a\",[\"b\"]]", data.toString());
	}

	@Test
	public void testObjectInArray() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("[\"a\",{\"b\":\"c\"},\"d\"]");
		assertEquals("[\"a\",{\"b\"=\"c\"},\"d\"]", data.toString());
	}

	@Test
	public void testFalseArray() throws IOException {
		Parser2 parser = new Parser2();
		try {
			parser.parse("[\"a\",,\"d\"]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testFalseArrayNoComma() throws IOException {
		Parser2 parser = new Parser2();
		try {
			parser.parse("[\"d\",[\"a\"][\"b\"]]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testArrayWithComma() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("[\"d\",[\"a\"],[\"b\"]]");
		assertEquals("[\"d\",[\"a\"],[\"b\"]]", data.toString());
	}

	@Test
	public void testFalseArrayWithComma() throws IOException {
		Parser2 parser = new Parser2();
		try {
			parser.parse("[\"d\",[\"a\":],[\"b\"]]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testArrayinObject() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":[\"d\",\"g\",\"h\"],\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"=[\"d\",\"g\",\"h\"],\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testObjectinObject() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":{\"d\":\"g\",\"h\":\"i\"},\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"={\"d\"=\"g\",\"h\"=\"i\"},\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testArrayinObjectinObjectin() throws IOException {
		Parser2 parser = new Parser2();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":{\"d\":\"g\",\"h\":[\"i\",\"j\",\"k\"]},\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"={\"d\"=\"g\",\"h\"=[\"i\",\"j\",\"k\"]},\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testDubleCommaInObject() throws IOException {
		try {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse("{\"a\":\"b\",,\"c\":\"f\"}");
			System.out.println(data.toString());
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testNoColonInObject() throws IOException {
		try {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse("{\"a\"\"b\",\"c\":\"f\"}");
			System.out.println(data.toString());
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testsemiColonInObject() throws IOException {
		try {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse("{\"a\";\"b\",\"c\":\"f\"}");
			System.out.println(data.toString());
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
		@Test
		public void testJSONTaskResponse() throws IOException {
			Parser2 parser = new Parser2();
			JSONDocument data = parser.parse("{\"id\":\"s195206\",\"tasks\":[\"/task/3070\",\"/task/5105\",\"/task/5586\",\"/task/5590\"]}");
			System.out.println(data.toString());
			assertEquals("{\"id\"=\"s195206\",\"tasks\"=[\"/task/3070\",\"/task/5105\",\"/task/5586\",\"/task/5590\"]}", data.toString());

	}

}


