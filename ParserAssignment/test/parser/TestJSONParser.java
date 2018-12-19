package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import org.junit.Test;

import json.JSONDocument;
import parser.JSONParser;

public class TestJSONParser {
	@Test
	public void testObjectToMap() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":\"b\"}");
		assertEquals("{\"a\"=\"b\"}", data.toString());
	}

	@Test
	public void testObjectWithMultiValueArray() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"z\":[\"a\",\"b\",\"c\"]}");
		assertEquals("{\"z\"=[\"a\",\"b\",\"c\"]}", data.toString());
	}

	@Test
	public void testMultiValueObject() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"}");
		assertEquals("{\"c\"=\"d\",\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testMultiLayerArray() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"z\":[\"a\",[\"b\"]]}");
		assertEquals("{\"z\"=[\"a\",[\"b\"]]}", data.toString());
	}

	@Test
	public void testObjectInArray() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"z\":[\"a\",{\"b\":\"c\"},\"d\"]}");
		assertEquals("{\"z\"=[\"a\",{\"b\"=\"c\"},\"d\"]}", data.toString());
	}

	@Test
	public void testFalseArray() throws IOException {
		JSONParser parser = new JSONParser();
		try {
			parser.parse("[\"a\",,\"d\"]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testFalseArrayNoComma() throws IOException {
		JSONParser parser = new JSONParser();
		try {
			parser.parse("[\"d\",[\"a\"][\"b\"]]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testArrayWithComma() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"z\":[\"d\",[\"a\"],[\"b\"]]}");
		assertEquals("{\"z\"=[\"d\",[\"a\"],[\"b\"]]}", data.toString());
	}

	@Test
	public void testFalseArrayWithComma() throws IOException {
		JSONParser parser = new JSONParser();
		try {
			parser.parse("[\"d\",[\"a\":],[\"b\"]]");
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testArrayinObject() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":[\"d\",\"g\",\"h\"],\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"=[\"d\",\"g\",\"h\"],\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testObjectinObject() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":{\"d\":\"g\",\"h\":\"i\"},\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"={\"d\"=\"g\",\"h\"=\"i\"},\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testArrayinObjectinObjectin() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":\"b\",\"c\":{\"d\":\"g\",\"h\":[\"i\",\"j\",\"k\"]},\"e\":\"f\"}");
		System.out.println(data.toString());
		assertEquals("{\"c\"={\"d\"=\"g\",\"h\"=[\"i\",\"j\",\"k\"]},\"a\"=\"b\",\"e\"=\"f\"}", data.toString());
	}

	@Test
	public void testDubleCommaInObject() throws IOException {
		try {
			JSONParser parser = new JSONParser();
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
			JSONParser parser = new JSONParser();
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
			JSONParser parser = new JSONParser();
			JSONDocument data = parser.parse("{\"a\";\"b\",\"c\":\"f\"}");
			System.out.println(data.toString());
			fail("expected exception to be thrown");
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	@Test
	public void testJSONTaskResponse() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser
				.parse("{\"id\":\"s195206\",\"tasks\":[\"/task/3070\",\"/task/5105\",\"/task/5586\",\"/task/5590\"]}");
		System.out.println(data.toString());
		assertEquals("{\"id\"=\"s195206\",\"tasks\"=[\"/task/3070\",\"/task/5105\",\"/task/5586\",\"/task/5590\"]}",
				data.toString());

	}

	@Test
	public void testbollean() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a\":[true, false, null]}");
		System.out.println(data.toString());
		assertEquals("{\"a\"=[true,false,null]}", data.toString());
	}

	@Test
	public void testbolleanSpace() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"z\":[\"te st\"]}");
		System.out.println(data.toString());
		assertEquals("{\"z\"=[\"te st\"]}", data.toString());
	}

	@Test
	public void testObjectSpaces() throws IOException {
		JSONParser parser = new JSONParser();
		JSONDocument data = parser.parse("{\"a b\" : \" c d \"}");
		assertEquals("{\"a b\"=\" c d \"}", data.toString());
	}

}
