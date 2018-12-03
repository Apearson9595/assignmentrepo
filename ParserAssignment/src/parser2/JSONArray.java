package parser2;

import java.util.ArrayList;
import java.util.List;

public class JSONArray extends JSONDocument {

	private List <JSONDocument> jsonList = new ArrayList<>();

	public void addToArray(JSONDocument document) {
		jsonList.add(document);
		}
	@Override
	public String toString() {
		return jsonList.toString();
		}
}
