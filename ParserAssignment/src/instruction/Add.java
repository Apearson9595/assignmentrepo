package instruction;

public class Add implements Instruction {

	public Add() {
	}

	@Override
	public String doInstruction(String param1, String param2) {

		int i = Integer.parseInt(param1);
		int n = Integer.parseInt(param2);
		int result = i + n;
		return Integer.toString(result);

	}
}
