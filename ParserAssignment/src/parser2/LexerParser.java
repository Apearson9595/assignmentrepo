package parser2;

import java.io.IOException;
import java.io.Reader;
import parser2.JsonSymbol.Type;
import java.io.PushbackReader;

public class LexerParser {

	private final PushbackReader reader;

	public LexerParser(Reader reader) {
		this.reader = new PushbackReader(reader);
	}

	public JsonSymbol next() throws IOException {
		int c = reader.read();
		if (-1 == c)
			return null; // no more symbols
		if ('{' == c)
			return new JsonSymbol(Type.OPEN_CURLY);
		if ('}' == c)
			return new JsonSymbol(Type.CLOSE_CURLY);
		if (':' == c)
			return new JsonSymbol(Type.COLON);
		if (',' == c)
			return new JsonSymbol(Type.COMMA);
		if ('"' == c)
			return new JsonSymbol(Type.QUOTATION);
		if ('[' == c)
			return new JsonSymbol(Type.OPEN_ARRAY);
		if (']' == c)
			return new JsonSymbol(Type.CLOSE_ARRAY);
		if (Character.isWhitespace(c)) {
			do {
				c = reader.read();
			} while (Character.isWhitespace(c)); // eat extra whitespace
			if (-1 != c)reader.unread(c);
			return new JsonSymbol(Type.SPACE);
		}
		if (Character.isLetterOrDigit(c) || '/' == c) {
			StringBuilder value = new StringBuilder();
			do {
				value.append((char)c);
				c = reader.read();
			} while (Character.isLetterOrDigit(c) || '/' ==c); // eat extra whitespace
			if (-1 != c)reader.unread(c);
			return new JsonSymbol(Type.STRING, value.toString());
			
			}
		return new JsonSymbol(Type.OTHER, Character.toString((char) c));
	}
}
