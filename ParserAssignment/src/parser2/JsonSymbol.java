package parser2;

public class JsonSymbol {
	public enum Type {
		OPEN_CURLY, CLOSE_CURLY, COMMA, COLON, STRING, SPACE, OPEN_ARRAY, CLOSE_ARRAY, OTHER
	}

	public final Type type;
	public final String value;

	public JsonSymbol(Type type, String value) {
		this.type = type;
		this.value = value;
	}

	public JsonSymbol(Type type) {
		this(type, null);
	}
}
