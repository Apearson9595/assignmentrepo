package parser;

import java.io.IOException;
import java.io.Reader;

import json.JsonSymbol;
import json.JsonSymbol.Type;

import java.io.PushbackReader;

/**
 * class to perform Lexical Analysis of JSON
 */
public class JSONLexer {

	private final PushbackReader reader;

	public JSONLexer(Reader reader) {
		this.reader = new PushbackReader(reader);
	}

	/**
	 * reads the JSON and returns the next JSON symbol
	 * 
	 * @return the next JSON symbol
	 * @throws IOException if characters can not be read
	 */
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
		if ('[' == c)
			return new JsonSymbol(Type.OPEN_ARRAY);
		if (']' == c)
			return new JsonSymbol(Type.CLOSE_ARRAY);
		if (Character.isWhitespace(c)) {
			do {
				c = reader.read();
			} while (Character.isWhitespace(c)); // eat extra whitespace
			if (-1 != c)
				reader.unread(c);
			return new JsonSymbol(Type.SPACE);
		}

		if ('"' == c) {
			StringBuilder value = new StringBuilder();
			value.append((char) c);
			c = reader.read();
			do {
				value.append((char) c);
				c = reader.read();
			} while (Character.isLetterOrDigit(c) || '/' == c || c != '"');
			value.append((char) c);
			return new JsonSymbol(Type.STRING, value.toString());

		}
		if (Character.isLetter(c)) {
			StringBuilder value = new StringBuilder();
			value.append((char) c);
			c = reader.read();
			do {
				value.append((char) c);
				c = reader.read();
			} while (Character.isLetter(c));
			if (-1 != c)
				reader.unread(c);
			return new JsonSymbol(Type.STRING, value.toString());

		}
		if (Character.isDigit(c)) {
			StringBuilder value = new StringBuilder();
			value.append((char) c);
			c = reader.read();
			do {
				value.append((char) c);
				c = reader.read();
			} while (Character.isDigit(c));
			if (-1 != c)
				reader.unread(c);
			return new JsonSymbol(Type.STRING, value.toString());

		}
		return new JsonSymbol(Type.OTHER, Character.toString((char) c));
	}

}
