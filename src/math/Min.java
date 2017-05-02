package math;

public class Min extends Operator {

	public Min() {
		super();
	}
	
	@Override
	public int getNumVal() {
		return getLeft().getNumVal() - getRight().getNumVal();
	}

	@Override
	public String getBinRep() {
		return getLeft().getBinRep() + BIN_MIN + getRight().getBinRep();
	}

	@Override
	public String getDecRep() {
		return getLeft().getDecRep() + " - " + getRight().getDecRep();
	}

}
