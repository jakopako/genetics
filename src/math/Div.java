package math;

public class Div extends Operator {

	public Div() {
		super();
	}
	
	@Override
	public int getNumVal() {
		int den = getRight().getNumVal();
		if (den == 0)
			throw new IllegalArgumentException("Divisor is 0!");
		else
			return getLeft().getNumVal() / getRight().getNumVal();
	}

	@Override
	public String getBinRep() {
		return getLeft().getBinRep() + BIN_DIV + getRight().getBinRep();
	}

	@Override
	public String getDecRep() {
		return getLeft().getDecRep() + " / " + getRight().getDecRep();
	}

}
