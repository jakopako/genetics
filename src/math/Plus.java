package math;

public class Plus extends Operator {

	public Plus() {
		super();
	}

	@Override
	public int getNumVal() {
		return getLeft().getNumVal() + getRight().getNumVal();
	}

	@Override
	public String getBinRep() {
		return getLeft().getBinRep() + BIN_PLUS + getRight().getBinRep();
	}

	@Override
	public String getDecRep() {
		return getLeft().getDecRep() + " + " + getRight().getDecRep();
	}

}
