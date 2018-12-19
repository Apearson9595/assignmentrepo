package instruction;

public class Multiply implements Instruction {

	@Override
	public String doInstruction(String param1, String param2) {

		int result = Integer.parseInt(param1) * Integer.parseInt(param2);
		return Integer.toString(result);

	}
}
