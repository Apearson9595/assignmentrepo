package parser2;

import java.util.ArrayList;
import java.util.List;

public class JSONArray extends JSONDocument {

	@Override public JSONArray getAsArray() {return this;}
	private List <JSONDocument> jsonList = new ArrayList<>();

	public void addToArray(JSONDocument document) {
		jsonList.add(document);
		}
	@Override
	public String toString() {
		return jsonList.toString().replace(" ", "");
		}
	public JSONDocument get(int i) {return jsonList.get(i); }
	
	public List <JSONDocument> getList(){
		return jsonList;
	}
}

