package parser2;

import java.util.ArrayList;
import java.util.List;

public class JSONArray extends JSONDocument {

	@Override
	public JSONArray getAsArray() {
		return this;
	}

	private List<JSONDocument> jsonList = new ArrayList<>();

	public void addToArray(JSONDocument document) {
		jsonList.add(document);
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

	public JSONDocument get(int i) {
		return jsonList.get(i);
	}

	public List<JSONDocument> getList() {
		return jsonList;
	}
}
