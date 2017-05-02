package genetics;

import java.util.Random;

/**
 * This abstract class represents all chromosomes whose information
 * is encoded in a binary fashion. Therefore both the mutation method
 * as well as the crossover method can be implemented here. In contrast,
 * the method that calculated the fitness depends on the respective subclass,
 * i.e. the way that the information is encoded in the binary gene string.
 * 
 * @author Jakob Dhondt
 *
 */
public abstract class BinaryChromosome implements IChromosome {

	private String genes;
	protected Random r;
	protected int fitness;

	/**
	 * This constructor takes only the size of the binary gene string as
	 * input and generates a chromosome with a random gene string.
	 * 
	 * @param size The size of the chromosome to be constructed.
	 */
	public BinaryChromosome(int size) {
		this.genes = "";
		r = new Random();
		for (int i = 0; i<size; i++){
			this.genes += Integer.toString(r.nextInt(2));
		}
	}
	
	/**
	 * This constructor takes a given gene string as initial value.
	 * 
	 * @param genes The initial gene string for this chromosome.
	 */
	public BinaryChromosome(String genes) {
		this.r = new Random();
		this.genes = genes;
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#getGenes()
	 */
	@Override
	public String getGenes() {
		return genes;
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#setGenes(java.lang.String)
	 */
	@Override
	public void setGenes(String new_genes) {
		this.genes = new_genes;
		calculateFitness();
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#mutate(double)
	 */
	@Override
	public void mutate(double mutation_rate) {
		String mutated_genes = "";
		for (int i = 0, n = genes.length(); i < n; i++){
			int t = r.nextInt((int) (1/mutation_rate));
			if (t == 0)
				if (genes.charAt(i) == '0')
					mutated_genes += "1";
				else
					mutated_genes += "0";
			else
				mutated_genes += genes.charAt(i);
		}
		setGenes(mutated_genes);
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#crossover(genetics.IChromosome, double)
	 */
	@Override
	public BinaryChromosome crossover(IChromosome c, double crossover_rate) {
		if (c.getGenes().length() != getGenes().length())
			throw new IllegalArgumentException("Chromosomes must be of equal length.");
		int luck = r.nextInt(1000);
		BinaryChromosome child = this.clone();
		if (luck < crossover_rate*1000){
			int pos = r.nextInt(genes.length());
			child.setGenes(getGenes().substring(0, pos) + c.getGenes().substring(pos));
		}
		return child;
	}
	
	/* (non-Javadoc)
	 * @see genetics.IChromosome#getFitness()
	 */
	@Override
	public int getFitness() {
		return this.fitness;
	}

	/**
	 * This method calculates the fitness of this chromosome. The implementation
	 * can be done in many different ways in the subclasses. The fitness
	 * of a perfectly developed chromosome must always be -1.
	 */
	public abstract void calculateFitness();

	/* (non-Javadoc)
	 * @see genetics.IChromosome#getInfo()
	 */
	@Override
	public String getInfo() {
		String result = "The chromosome: " + getGenes() + "\n";
		result += "has a fitness of: " + getFitness() + "\n";
		return result;
	}

	/* (non-Javadoc)
	 * @see genetics.IChromosome#clone()
	 */
	@Override
	public abstract BinaryChromosome clone();
}