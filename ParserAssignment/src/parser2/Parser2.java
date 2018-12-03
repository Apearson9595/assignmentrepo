package parser2;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;

import parser2.JsonSymbol.Type;

public class Parser2 {

	public JSONDocument parse(Reader in) throws IOException {
		LexerParser lex = new LexerParser(in);
		JSONDocument Jsondoc = null;
		JsonSymbol symbol = lex.next();
		if (symbol.type == Type.OPEN_ARRAY) {
			Jsondoc = parseArray(lex);	
		}
		else if (symbol.type == Type.OPEN_CURLY) {
			Jsondoc = parseObject(lex);	
		}
		
		return Jsondoc;
		}
		
		public JSONDocument parseObject(LexerParser lex) throws IOException {
			JSONObject object = new JSONObject();
			JsonSymbol symbol = lex.next();
			String key = symbol.value;
			JsonSymbol colon = lex.next();
			JsonSymbol value = lex.next();
			JSONString string = new JSONString(value.value);
			object.addToMap(key,string);
			return object;
			}
	
	public JSONDocument parseArray(LexerParser lex) throws IOException {
		JSONArray array = new JSONArray();
		JsonSymbol symbol = lex.next();
		JSONString string = new JSONString(symbol.value);
		array.addToArray(string);
		return array;
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
		private JSONObject element(LexerParser lex) throws IOException {
			// TODO
			return null;

		}
	}
