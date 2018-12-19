package instruction;

public class Concat implements Instruction {

	@Override
	public String doInstruction(String param1, String param2) {

		return param1 + param2;
	}
}
