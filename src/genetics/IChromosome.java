package genetics;

/**
 * This is an interface for a chromosome. The following methods are mandatory for
 * any class that implements this interface and hence pretends to be a chromosome
 * of any kind.
 * @author Jakob Dhondt
 *
 */
public interface IChromosome {

	/**
	 * This method returns a string of the genes from this chromosome. This
	 * might be a binary string but as well any other type of string, depending
	 * on the implementing subclass of IChromosome.
	 * @return The genes of this chromosome.
	 */
	String getGenes();

	/**
	 * This method allows to set a new string of genes.
	 * @param new_genes The new genes that will be set.
	 */
	void setGenes(String new_genes);

	/**
	 * This method let's the chromosome mutate according to the given mutation rate.
	 * @param mutation_rate The rate that dictates the mutation.
	 */
	void mutate(double mutation_rate);

	/**
	 * This method executes the crossover process between two genes. The result
	 * is a child gene with properties of one or both of the parents.
	 * @param c The second (parent) chromosome.
	 * @param crossover_rate The rate that dictates the crossover process.
	 * @return The new child chromosome that results from the crossover.
	 */
	IChromosome crossover(IChromosome c, double crossover_rate);

	/**
	 * This method returns the fitness of a chromosome.
	 * @return An integer value of the fitness of this chromosome.
	 */
	int getFitness();

	/**
	 * This method returns detailed information about this chromosome.
	 * @return A string with detailed information about this chromosome.
	 */
	String getInfo();

	/**
	 * This method returns an exact copy of this chromosome.
	 * @return An exact copy of this chromosome.
	 */
	IChromosome clone();

	/**
	 * This method returns the essential information about this chromosome.
	 * @return A string with the essential information about this chromosome.
	 */
	String getShortInfo();

}