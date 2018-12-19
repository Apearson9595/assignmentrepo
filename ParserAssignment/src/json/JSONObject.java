package json;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a JSONObject
 *
 */
public class JSONObject extends JSONDocument {
	private Map<String, JSONDocument> mapObject = new HashMap<>();

	@Override
	public JSONObject getAsObject() {
		return this;
	}

	/**
	 * adds the key and value to the JSON object
	 * 
	 * @param key   - of the pair
	 * @param value - of the pair
	 */
	public void addToMap(String key, JSONDocument value) {
		mapObject.put(key, value);
	}

	/**
	 * retrieves the JSONDocument associated with the key provided if key is not
	 * present returns null
	 * 
	 * @param key - the key to look up
	 * @return the value of given key.
	 */
	public JSONDocument get(String key) {
		return mapObject.get(key);
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append('{');
		for (Map.Entry<String, JSONDocument> entry : mapObject.entrySet()) {
			stringBuffer.append(entry.getKey());
			stringBuffer.append('=');
			stringBuffer.append(entry.getValue());
			stringBuffer.append(',');
		}
		String s = stringBuffer.toString();
		if (s.charAt(s.length() - 1) == ',') {
			s = s.substring(0, s.length() - 1);
		}
		s = s + "}";
		return s;
	}

}
