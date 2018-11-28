package parser2;

public class JsonSymbol {

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


