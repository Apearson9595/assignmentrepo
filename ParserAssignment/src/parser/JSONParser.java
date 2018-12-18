package parser;

import java.io.IOException;
import java.io.StringReader;
import json.JSONArray;
import json.JSONDocument;
import json.JSONObject;
import json.JSONString;
import json.JsonSymbol;
import json.JsonSymbol.Type;

/**
 * Class to parse JSON
 */
public class JSONParser {

	/**
	 * Parsers the given JSON string into the JSONDocument tree
	 * @param string - the JSON string to parse
	 * @return - return JSONDocument representation of JSON
	 * @throws IOException - If it is not valid JSON.
	 */
	public JSONDocument parse(String string) throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(string));
		JSONDocument Jsondoc = null;
		JsonSymbol symbol = lex.next();
		if (symbol.type == Type.OPEN_CURLY) {
			Jsondoc = parseObject(lex);
		} else {
			throw new IOException("not valid Json - expects { to begin JSON ");
		}
		return Jsondoc;
	}
	
	private JSONDocument parseObject(JSONLexer lex) throws IOException {
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

	private JSONDocument parseArray(JSONLexer lex) throws IOException {
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
}
