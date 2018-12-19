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
	 * 
	 * @param string - the JSON string to parse
	 * @return - return JSONDocument representation of JSON
	 * @throws IOException - If it is not valid JSON.
	 */
	public JSONDocument parse(String string) throws IOException {
		JSONLexer lex = new JSONLexer(new StringReader(string));
		JSONDocument Jsondoc = null;
		JsonSymbol symbol = lex.next();
		if (symbol.getType() == Type.OPEN_CURLY) {
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
			Type type = symbol.getType();
			if (type.equals(Type.STRING) && lastType.equals(Type.COMMA)) {
				key = symbol.getValue();
				lastType = Type.STRING;
			} else if (type.equals(Type.COLON)) {
				lastType = Type.COLON;
			} else if (type.equals(Type.STRING) && lastType.equals(Type.COLON)) {
				value = new JSONString(symbol.getValue());
				object.addToMap(key, value);
				lastType = Type.STRING;

			} else if (type.equals(Type.COMMA)) {
				if (lastType.equals(Type.COMMA)) {
					throw new IOException("Not expecting comma followed by comma");
				} else if (value == null) {
					throw new IOException("Value required to create next key");
				}
				lastType = Type.COMMA;
			}

			else if (type == Type.OPEN_CURLY) {
				if (lastType != Type.COLON) {
					throw new IOException("Objects must start with valid key");
				}
				JSONDocument addToMap = parseObject(lex);
				object.addToMap(key, addToMap);
				lastType = type;

			} else if (type == Type.OPEN_ARRAY) {
				if (lastType != Type.COLON) {
					throw new IOException("Objects must start with valid key");
				}
				JSONDocument Jsondoc = parseArray(lex);
				lastType = type;
				object.addToMap(key, Jsondoc);

			}

			else if (type == Type.CLOSE_CURLY) {

				return object;
			} else if (type == Type.SPACE) {
				// do nothing
			} else
				throw new IOException("not valid object");
		}
	}

	private JSONDocument parseArray(JSONLexer lex) throws IOException {
		JSONArray array = new JSONArray();
		Type lastType = Type.COMMA;
		while (true) {
			JsonSymbol symbol = lex.next();
			Type type = symbol.getType();
			if (type.equals(Type.STRING) && lastType.equals(Type.COMMA)) {
				JSONString string = new JSONString(symbol.getValue());
				array.addToArray(string);
				System.out.println(string);
				lastType = type;
			} else if (type.equals(Type.COMMA)) {
				if (lastType == Type.COMMA) {
					throw new IOException("double comma not valid");
				}
				lastType = type;
			} else if (type.equals(Type.CLOSE_ARRAY)) {
				return array;
			} else if (type == Type.OPEN_CURLY && lastType.equals(Type.COMMA)) {
				JSONDocument Jsondoc = parseObject(lex);
				array.addToArray(Jsondoc);
				lastType = type;
			} else if (type == Type.OPEN_ARRAY && lastType.equals(Type.COMMA)) {
				JSONDocument Jsondoc = parseArray(lex);
				lastType = type;
				array.addToArray(Jsondoc);
			} else if (type == Type.SPACE) {
				// do nothing
			} else
				throw new IOException("not valid array");
		}
	}
}
