package parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

import json.JsonSymbol;
import json.JsonSymbol.Type;
import parser.JSONLexer;

public class TestLexer {
	@Test
	public void testEmpty() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(""));
		JsonSymbol symbol = lex.next();
		assertNull(symbol);
	}

	@Test
	public void testColon() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(":"));
		assertNextSymbol(lex, Type.COLON);
	}

	@Test
	public void testWord() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("\"hello\""));
		JsonSymbol symbol = lex.next();
		assertEquals(Type.STRING, symbol.getType());
		assertEquals("\"hello\"", symbol.getValue());
	}

	@Test
	public void testTab() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("	"));
		JsonSymbol symbol = lex.next();
		assertEquals(Type.SPACE, symbol.getType());
		assertNull(lex.next());
	}

	@Test
	public void testSpace() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(" "));
		JsonSymbol symbol = lex.next();
		assertEquals(Type.SPACE, symbol.getType());
		assertNull(lex.next());
	}

	@Test
	public void testSingleOpen() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("{"));
		assertNextSymbol(lex, Type.OPEN_CURLY);
	}

	@Test
	public void testSingleClose() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("}"));
		assertNextSymbol(lex, Type.CLOSE_CURLY);
	}

	@Test
	public void testSingleComma() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(","));
		assertNextSymbol(lex, Type.COMMA);
	}

	@Test
	public void testSingleOpenArray() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("["));
		assertNextSymbol(lex, Type.OPEN_ARRAY);
	}

	@Test
	public void testSingleCloseArray() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("]"));
		assertNextSymbol(lex, Type.CLOSE_ARRAY);
	}

	@Test
	public void testCombination() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("{\"ugh\"}"));
		assertNextSymbol(lex, Type.OPEN_CURLY);
		assertNextSymbol(lex, Type.STRING, "\"ugh\"");
		assertNextSymbol(lex, Type.CLOSE_CURLY);
		assertNull(lex.next());
	}

	@Test
	public void testTaskCombination() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("\"/task/123\""));
		assertNextSymbol(lex, Type.STRING, "\"/task/123\"");
		assertNull(lex.next());
	}

	@Test
	public void testFullTaskCombination() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("{\"id\":\"S195206\"  ,\"tasks\":[\"/task/123\"]}"));
		assertNextSymbol(lex, Type.OPEN_CURLY);
		assertNextSymbol(lex, Type.STRING, "\"id\"");
		assertNextSymbol(lex, Type.COLON);
		assertNextSymbol(lex, Type.STRING, "\"S195206\"");
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.STRING, "\"tasks\"");
		assertNextSymbol(lex, Type.COLON);
		assertNextSymbol(lex, Type.OPEN_ARRAY);
		assertNextSymbol(lex, Type.STRING, "\"/task/123\"");
		assertNextSymbol(lex, Type.CLOSE_ARRAY);
		assertNextSymbol(lex, Type.CLOSE_CURLY);
		assertNull(lex.next());
	}

	public void assertNextSymbol(JSONLexer lex, Type type, String value) throws IOException {
		JsonSymbol symbol = lex.next();
		assertEquals(type, symbol.getType());
		assertEquals(value, symbol.getValue());
	}

	public void assertNextSymbol(JSONLexer lex, Type type) throws IOException {
		assertNextSymbol(lex, type, null);
	}

	@Test
	public void testStringWithSpace() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("{\"DAVE BOB\"}"));
		assertNextSymbol(lex, Type.OPEN_CURLY);
		assertNextSymbol(lex, Type.STRING, "\"DAVE BOB\"");
		assertNextSymbol(lex, Type.CLOSE_CURLY);
		assertNull(lex.next());
	}

	@Test
	public void testArrayWithNumbers() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("[3979, 1990]"));
		assertNextSymbol(lex, Type.OPEN_ARRAY);
		assertNextSymbol(lex, Type.STRING, "3979");
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.STRING, "1990");
		assertNextSymbol(lex, Type.CLOSE_ARRAY);
		assertNull(lex.next());
	}

	@Test
	public void testArrayWithBooleanNumbersStringNull() throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader("[3979, true, false, \"hello\", null]"));
		assertNextSymbol(lex, Type.OPEN_ARRAY);
		assertNextSymbol(lex, Type.STRING, "3979");
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.STRING, "true");
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.STRING, "false");
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.STRING, "\"hello\"");
		assertNextSymbol(lex, Type.COMMA);
		assertNextSymbol(lex, Type.SPACE);
		assertNextSymbol(lex, Type.STRING, "null");
		assertNextSymbol(lex, Type.CLOSE_ARRAY);
		assertNull(lex.next());
	}

}
