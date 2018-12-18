package instruction;

/**
 * The add instruction implementation 
 */
public class Add implements Instruction {

	@Override
	public String doInstruction(String param1, String param2) {

		int i = Integer.parseInt(param1);
		int n = Integer.parseInt(param2);
		int result = i + n;
		return Integer.toString(result);

	}
}
