package json;


/**
 * This is the parent class of all JSON elements
 * ie JSONArray, JSONObject, JSONString etc.
 *
 */
public class JSONDocument {

	/**
	 * casts the JSONDocument to a JSONArray if it is valid
	 * otherwise throws an unsupported operation exceptions 
	 * @return the JSONArray
	 */
	public JSONArray getAsArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * /**
	 * casts the JSONDocument to an JSONObject if it is valid
	 * otherwise throws an unsupported operation exceptions 
	 * @return the JSONObject
	 */
	public JSONObject getAsObject() {
		throw new UnsupportedOperationException();
	}

}
