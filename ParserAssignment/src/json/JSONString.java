package json;

/**
 * class to represent a JSONString
 *
 */
public class JSONString extends JSONDocument {

	private String jsonString;
	
	public JSONString(String jsonString) {
		this.jsonString = jsonString;		
	}
	@Override
	public String toString() {
		return jsonString;
		}
}
