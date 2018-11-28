package parser2;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;
import parser2.JsonSymbol;
import parser2.LexerParser;
import parser2.Type;


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
	LexerParser lex = new LexerParser(new StringReader("hello"));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.STRING, symbol.type);
	assertEquals("hello", symbol.value);
}
@Test
public void testSpace() throws IOException {
	LexerParser lex = new LexerParser(new StringReader(" \n\t  "));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.SPACE, symbol.type);
	assertNull(lex.next());
}
@Test
public void testSingleOpen() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("{"));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.OPEN_CURLY, symbol.type);
}
@Test
public void testSingleClose() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("}"));
	
	JsonSymbol symbol = lex.next();
	assertEquals(Type.CLOSE_CURLY, symbol.type);
}
@Test
public void testSingleSlash() throws IOException {
	LexerParser lex = new LexerParser(new StringReader(","));
	JsonSymbol symbol = lex.next();
	assertEquals(Type.COMMA, symbol.type);
}
@Test
public void testCombination() throws IOException {
	LexerParser lex = new LexerParser(new StringReader("{ugh}"));
	assertNextSymbol (lex, Type.OPEN_CURLY);
	assertNextSymbol (lex, Type.STRING, "ugh");
	assertNextSymbol (lex, Type.CLOSE_CURLY);
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
