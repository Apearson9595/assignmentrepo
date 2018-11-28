package parser2;
import java.io.IOException;
import java.io.Reader;
import java.io.PushbackReader;

public class LexerParser {

			private final PushbackReader reader;

			public LexerParser(Reader reader) {
				this.reader = new PushbackReader(reader);
			}
			public JsonSymbol next() throws IOException {
				int c = reader.read();
				if (-1 == c) return null; // no more symbols
				if ('{' == c) return new JsonSymbol(Type.OPEN_CURLY);
				if ('}' == c) return new JsonSymbol(Type.CLOSE_CURLY);
				if (',' == c) return new JsonSymbol(Type.COMMA);
				if (Character.isWhitespace(c)) {
					while (Character.isWhitespace(c)) { // eat extra whitespace
						c = reader.read();
					}
					return new JsonSymbol(Type.SPACE);
				}
				if (Character.isLetterOrDigit(c)) {
					StringBuffer value = new StringBuffer();
					while (Character.isLetterOrDigit(c)) { // collect extra digits
						value.append((char)c);
						c = reader.read();
					}
					if (-1 != c) reader.unread(c);
					return new JsonSymbol(Type.STRING, value.toString());
				}
				return new JsonSymbol(Type.OTHER, Character.toString((char)c));
}
}


