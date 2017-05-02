package genetics;

import math.EquationBuilder;
import math.Operand;

/**
 * This class is a chromosome that represents an equation containing the four 
 * basic mathematical operations. The closer the equation's result is to
 * a given goal, the fitter the chromosome is.
 * @author Jakob Dhondt
 *
 */
public class EquationChromosome extends BinaryChromosome {

	private int goal;
	private Operand o;
	
	/**
	 * A constructor for an EquationChromosome, a chromosome that represents an
	 * equation containing the four basic mathematical operations. This chromosome's
	 * equation's result must after successful evolution equal the given goal. 
	 * This constructor initializes the chromosome with a random gene-string.
	 * 
	 * @param size The length of the chromosome in bits.
	 * @param goal The goal value that should be achieved after evolution.
	 */
	public EquationChromosome(int size, int goal){
		super(size);
		this.goal = goal;
		this.o = EquationBuilder.buildEquation(getGenes());
		calculateFitness();
	}
	
	/**
	 * This constructor for the EquationChromosome takes a string of given genes
	 * and a given goal to evolve to.
	 * 
	 * @param genes The gene-string that this chromosome should have.
	 * @param goal The goal value that should be achieved after evolution.
	 */
	public EquationChromosome(String genes, int goal){
		super(genes);
		this.goal = goal;
		this.o = EquationBuilder.buildEquation(genes);
		calculateFitness();
	}
	
	/**
	 * This method returns the goal that this EquationChromosome should
	 * evolve to.
	 * @return An integer that represents the goal of this EquationChromosome.
	 */
	public int getGoal() {
		return this.goal;
	}
	
	/**
	 * The numerical value of this EquationChromosome.
	 * @return An integer of the numerical value of this EquationChromosome.
	 */
	public int getNumVal(){
		try {
			return o.getNumVal();
		}
		catch (IllegalArgumentException | NullPointerException ne) {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#setGenes(java.lang.String)
	 */
	@Override
	public void setGenes( String new_genes) {
		this.o = EquationBuilder.buildEquation(new_genes);
		super.setGenes(new_genes);
	}
	
	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#calculateFitness()
	 */
	@Override
	public void calculateFitness() {
		int diff = Math.abs(this.goal - getNumVal());
		if (diff == 0)
			this.fitness = -1;
		else
			this.fitness = (int) (100*(goal / (double) diff));
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#clone()
	 */
	@Override
	public EquationChromosome clone() {
		return new EquationChromosome(getGenes(), getGoal());
	}
	
	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#getInfo()
	 */
	@Override
	public String getInfo() {
		String result = super.getInfo();
		result += "Its equation is: " + o.getDecRep();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#getShortInfo()
	 */
	@Override
	public String getShortInfo() {
		return o.getDecRep();
	}
}
