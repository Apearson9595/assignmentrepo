package json;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a JSON Array
 *
 */
public class JSONArray extends JSONDocument {
	private List<JSONDocument> jsonList = new ArrayList<>();

	@Override
	public JSONArray getAsArray() {
		return this;
	}

	/**
	 * adds the JSON document to the Array
	 * 
	 * @param document is the JSONDocument to add to the Array
	 */
	public void addToArray(JSONDocument document) {
		jsonList.add(document);
	}

	/**
	 * @param i - the index of the JSONDocument to get from the array
	 * @return - The JSONdocument at the current index of i
	 */
	public JSONDocument get(int i) {
		return jsonList.get(i);
	}

	/**
	 * @return A list representation of the JSONArray
	 */
	public List<JSONDocument> getList() {
		return jsonList;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append('[');
		for (int i = 0; i < jsonList.size(); i++) {
			if (i != 0) {
				stringBuffer.append(',');
			}
			stringBuffer.append(jsonList.get(i));
		}
		stringBuffer.append(']');
		return stringBuffer.toString();
	}
}
