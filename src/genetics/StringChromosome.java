package genetics;

/**
 * This class represents a chromosome whose binary gene-string encodes a readable
 * ASCII string.
 * 
 * @author Jakob Dhondt
 *
 */
public class StringChromosome extends BinaryChromosome {

	private String goal;
	private String genes_decoded;
	
	/**
	 * This constructor for the StringChromosome takes only the goal string as
	 * input. The initial genes are generated randomly.
	 * 
	 * @param goal The goal string that this chromosome needs to evolve to.
	 */
	public StringChromosome(String goal) {
		super(goal.length()*8);
		setGoal(goal);
		calcString(getGenes());
		calculateFitness();
	}
	
	/**
	 * This constructor takes a goal string and an initial gene string (binary).
	 * 
	 * @param goal The goal of this chromosome in ASCII format.
	 * @param genes The initial gene string in binary format.
	 */
	public StringChromosome(String goal, String genes){
		super(genes);
		setGoal(goal);
		calcString(genes);
		calculateFitness();
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#setGenes(java.lang.String)
	 */
	@Override
	public void setGenes(String new_genes) {
		calcString(new_genes);
		super.setGenes(new_genes);
	}
	
	/**
	 * This method calculates the fitness of this chromosome in the following way:
	 * The lowest fitness is one. Then for every letter that is at the correct
	 * position one is added to the fitness.
	 */
	@Override
	public void calculateFitness() {
		this.fitness = 1;
		for (int i = 0; i < this.genes_decoded.length(); i++) {
			if (this.genes_decoded.charAt(i) == goal.charAt(i))
				this.fitness += 1;
		}
		if (this.fitness == goal.length()+1)
			this.fitness = -1;
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#clone()
	 */
	@Override
	public StringChromosome clone() {
		return new StringChromosome(goal, getGenes());
	}
	
	/**
	 * Returns the goal in a readable form.
	 * @return A string that represents the goal.
	 */
	public String getGoal() {
		return this.goal;
	}
	
	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	/**
	 * Calculates the decoded version of the given binary genes.
	 * @param new_genes The binary genes that need to be converted to
	 * 			a humanly readable string.
	 */
	public void calcString(String new_genes) {
		this.genes_decoded = "";
		for(int i = 0; i<new_genes.length(); i+=8){
			int charCode = Integer.parseInt(new_genes.substring(i, i+8), 2);
			this.genes_decoded += new Character((char) charCode).toString();
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#getInfo()
	 */
	@Override
	public String getInfo() {
		String result = super.getInfo();
		result += "String: " + this.genes_decoded;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.BinaryChromosome#getShortInfo()
	 */
	@Override
	public String getShortInfo() {
		return this.genes_decoded;
	}

}
