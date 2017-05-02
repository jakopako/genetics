package math;

public class Mul extends Operator {

	public Mul() {
		super();
	}
	
	@Override
	public int getNumVal() {
		return getLeft().getNumVal() * getRight().getNumVal();
	}

	@Override
	public String getBinRep() {
		return getLeft().getBinRep() + BIN_MUL + getRight().getBinRep();
	}

	@Override
	public String getDecRep() {
		return getLeft().getDecRep() + " * " + getRight().getDecRep();
	}

}
