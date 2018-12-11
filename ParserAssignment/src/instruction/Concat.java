package instruction;

public class Concat implements Instruction{

	public Concat() {
	}
	@Override
		public String doInstruction(String param1, String param2) {
			
			String result = param1 + param2;
			
			return result;
			// 3979,1990
	}
}
