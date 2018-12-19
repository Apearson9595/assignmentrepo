package json;

/**
 * POJO to hold value and type of JSON elements *
 */
public class JsonSymbol {
	/**
	 * enum representing all the JSON elements
	 *
	 */
	public enum Type {
		OPEN_CURLY, 
		CLOSE_CURLY, 
		COMMA, 
		COLON, 
		STRING, 
		SPACE, 
		OPEN_ARRAY, 
		CLOSE_ARRAY, 
		OTHER
	}

	private final Type type;
	private final String value;

	public JsonSymbol(Type type, String value) {
		this.type = type;
		this.value = value;
	}

	public JsonSymbol(Type type) {
		this(type, null);
	}

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
}
