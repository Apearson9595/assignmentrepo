package parser2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import parser2.JsonSymbol.Type;
import parser2.LexerParser;


public class TestLexer {
	@Test
	public void testEmpty() throws IOException {
		LexerParser lex = new LexerParser(new StringReader(""));
		JsonSymbol symbol = lex.next();
		System.out.println(symbol);
		assertNull(symbol);
	}
@Test
public void testWord() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("\"hello\""));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.STRING, symbol.type);
	assertEquals("\"hello\"", symbol.value);
}
@Test
public void testSpace() throws IOException {
	LexerParser lex = new LexerParser(new StringReader(" "));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.SPACE, symbol.type);
	assertNull(lex.next());
}
@Test
public void testSingleOpen() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("{"));
	assertNextSymbol(lex, Type.OPEN_CURLY);
}
@Test
public void testSingleClose() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("}"));
	assertNextSymbol(lex, Type.CLOSE_CURLY);
}
@Test
public void testSingleComma() throws IOException {
	LexerParser lex = new LexerParser(new StringReader(","));
	assertNextSymbol(lex, Type.COMMA);
}
@Test
public void testSingleOpenArray() throws IOException{
	LexerParser lex = new LexerParser(new StringReader("["));
	assertNextSymbol(lex, Type.OPEN_ARRAY);
}
@Test
public void testCombination() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("{\"ugh\"}"));
	assertNextSymbol (lex, Type.OPEN_CURLY);
	assertNextSymbol (lex, Type.STRING, "\"ugh\"");
	assertNextSymbol (lex, Type.CLOSE_CURLY);
	assertNull(lex.next());
}
@Test
public void testTaskCombination() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("\"/task/123\""));
	assertNextSymbol(lex, Type.STRING, "\"/task/123\"");
	assertNull(lex.next());
}
@Test
public void testFullTaskCombination() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("{\"id\":\"S195206\"  ,\"tasks\":[\"/task/123\"]}"));
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


void assertNextSymbol(LexerParser lex, Type type, String value) throws
IOException {
JsonSymbol symbol = lex.next();
assertEquals(type, symbol.type);
assertEquals(value, symbol.value);
}

void assertNextSymbol(LexerParser lex, Type type) throws IOException {
assertNextSymbol(lex, type, null);
}


}
