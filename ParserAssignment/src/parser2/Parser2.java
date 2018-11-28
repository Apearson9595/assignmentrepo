package parser2;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class Parser2 {

	public Document parse(Reader in) throws IOException {
		LexerParser lex = new LexerParser(in);
			return document(lex);
		}
		private Document document(LexerParser lex) throws IOException {
			Element element = element(lex);
			if (null == element) {
				 return new Document(text(lex));
			}
			return new Document(element);
		}
		private String text(LexerParser lex) throws IOException {
			StringBuilder ret = new StringBuilder();
			sym: for (JsonSymbol symbol = lex.next(); symbol != null; symbol = lex.next()) {
				switch(symbol.type) {
				case STRING:
					ret.append(symbol.value);
					break;
				case SPACE:
					ret.append(" ");
					break;
				case OTHER:
					ret.append(symbol.value);
					break;
				case COMMA:
					ret.append(",");
					break;
				default:
					 break sym; // to stop loop
				//TODO need to add the rest from Type
				//TODO See page 22 and implement
				}
			}
			return ret.toString();

		}
		private Element element(LexerParser lex) throws IOException {
			// TODO
			return null;

		}
	}
