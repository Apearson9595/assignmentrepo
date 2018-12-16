package parser2;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import parser2.JsonSymbol.Type;

public class Parser2 {

	public JSONDocument parse(String string) throws IOException {
		LexerParser lex = new LexerParser(new StringReader(string));
		JSONDocument Jsondoc = null;
		JsonSymbol symbol = lex.next();
		if (symbol.type == Type.OPEN_ARRAY) {
			Jsondoc = parseArray(lex);
		} else if (symbol.type == Type.OPEN_CURLY) {
			Jsondoc = parseObject(lex);
		} else {
			throw new IOException("not valid Json");
		}
		return Jsondoc;
	}
	JSONDocument parseObject(LexerParser lex) throws IOException {
		JSONObject object = new JSONObject();
		Type lastType = Type.COMMA;
		String key = null;
		JSONDocument value = null;
		while (true) {
			JsonSymbol symbol = lex.next();
			if (symbol.type.equals(Type.STRING) && lastType.equals(Type.COMMA)) {
				key = symbol.value;
				lastType = Type.STRING;
			} else if (symbol.type.equals(Type.COLON)) {
				lastType = Type.COLON;
			} else if (symbol.type.equals(Type.STRING) && lastType.equals(Type.COLON)) {
				value = new JSONString(symbol.value);
				object.addToMap(key, value);
				lastType = Type.STRING;

			} else if (symbol.type.equals(Type.COMMA)) {
				if (lastType.equals(Type.COMMA)) {
					throw new IOException("Not expecting comma followed by comma");
				} else if (value == null) {
					throw new IOException("Value required to create next key");
				}
				lastType = Type.COMMA;
			}

			else if (symbol.type == Type.OPEN_CURLY) {
				if (lastType != Type.COLON) {
					throw new IOException("Objects must start with valid key");
				}
				JSONDocument addToMap = parseObject(lex);
				object.addToMap(key, addToMap);
				lastType = symbol.type;

			} else if (symbol.type == Type.OPEN_ARRAY) {
				if (lastType != Type.COLON) {
					throw new IOException("Objects must start with valid key");
				}
				JSONDocument Jsondoc = parseArray(lex);
				lastType = symbol.type;
				object.addToMap(key, Jsondoc);

			}

			else if (symbol.type == Type.CLOSE_CURLY) {

				return object;
			}  else if (symbol.type == Type.SPACE) {
			//do nothing	
			}	else
				throw new IOException("not valid object");
		}
	}

	public JSONDocument parseArray(LexerParser lex) throws IOException {
		JSONArray array = new JSONArray();
		Type lastType = Type.COMMA;
		while (true) {
			JsonSymbol symbol = lex.next();
			if (symbol.type.equals(Type.STRING) && lastType.equals(Type.COMMA)) {
				JSONString string = new JSONString(symbol.value);
				array.addToArray(string);
				System.out.println(string);
				lastType = symbol.type;
			} else if (symbol.type.equals(Type.COMMA)) {
				if (lastType == Type.COMMA) {
					throw new IOException("double comma not valid");
				}
				lastType = symbol.type;
			} else if (symbol.type.equals(Type.CLOSE_ARRAY)) {
				return array;
			} else if (symbol.type == Type.OPEN_CURLY && lastType.equals(Type.COMMA)) {
				JSONDocument Jsondoc = parseObject(lex);
				array.addToArray(Jsondoc);
				lastType = symbol.type;
			} else if (symbol.type == Type.OPEN_ARRAY && lastType.equals(Type.COMMA)) {
				JSONDocument Jsondoc = parseArray(lex);
				lastType = symbol.type;
				array.addToArray(Jsondoc);
			} else if (symbol.type == Type.SPACE) {
				//do nothing
			}
			else
				throw new IOException("not valid array");
		}
	}

	private String text(LexerParser lex) throws IOException {
		StringBuilder ret = new StringBuilder();
		sym: for (JsonSymbol symbol = lex.next(); symbol != null; symbol = lex.next()) {
			switch (symbol.type) {
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
			// TODO need to add the rest from Type
			// TODO See page 22 and implement
			}
		}
		return ret.toString();

	}

	private JSONObject element(LexerParser lex) throws IOException {
		// TODO
		return null;

	}
}
