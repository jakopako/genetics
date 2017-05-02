package math;

import java.util.HashMap;
import java.util.Map;

public class Number implements Operand {

	private static final Map<String, Integer> BIN_TO_NUM = new HashMap<String, Integer>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("0000", 0);
			put("0001", 1);
			put("0010", 2);
			put("0011", 3);
			put("0100", 4);
			put("0101", 5);
			put("0110", 6);
			put("0111", 7);
			put("1000", 8);
			put("1001", 9);
		}
	};
	private String value;
	
	public Number(String value) {
		if (BIN_TO_NUM.get(value) != null)
			this.value = value;
		else
			throw new IllegalArgumentException();
	}
	
	@Override
	public int getNumVal() {
		return BIN_TO_NUM.get(this.value);
	}

	@Override
	public String getBinRep() {
		return this.value;
	}

	@Override
	public String getDecRep() {
		return Integer.toString(BIN_TO_NUM.get(this.value));
	}

}
