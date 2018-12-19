package instruction;

public interface Instruction {

	/**
	 * @param param1 - first parameter to carry out instruction on
	 * @param param2 - second parameter to carry out instruction on
	 * @return - returns the result of the instruction
	 */
	String doInstruction(String param1, String param2);
}
