package parser2;

import java.util.List;

public class JSONString extends JSONDocument {

	private String jsonString;
	
	JSONString(String jsonString) {
		this.jsonString = jsonString;		
	}
	@Override
	public String toString() {
		return jsonString;
		}
}
