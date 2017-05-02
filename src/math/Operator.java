package math;

public abstract class Operator implements Operand {

	private Operand left;
	private Operand right;
	static public final String BIN_PLUS = "1010";
	static public final String BIN_MIN = "1011";
	static public final String BIN_MUL = "1100";
	static public final String BIN_DIV = "1101";
	
	public Operator(){
		this.left = null;
		this.right = null;
	}
	
	public Operand getLeft(){
		return this.left;
	}
	
	public Operand getRight(){
		return this.right;
	}
	
	public void setLeft(Operand left){
		this.left = left;
	}
	
	public void setRight(Operand right){
		this.right = right;
	}

}
