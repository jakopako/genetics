package genetics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represents the evolution. It contains a number of chromosomes that
 * evolve, generation by generation, to a specified goal. The two important parameters
 * that determine the evolutionary process are the mutation rate and the crossover rate.
 * 
 * @author Jakob Dhondt
 *
 */
public class Evolution {

	private List<IChromosome> initial_population;
	private List<IChromosome> population;
	private Random r;
	private double MUTATION_RATE = 0.001;
	private double CROSSOVER_RATE = 0.7;
	private int nr_generations;

	/**
	 * This is a constructor for the Evolution class. It takes a list of chromosomes
	 * as input.
	 * 
	 * @param population A list of chromosomes that represents the population.
	 */
	public Evolution(List<IChromosome> population){
		this.population = population;
		initial_population = new ArrayList<IChromosome>();
		for (IChromosome c : population) {
			initial_population.add(c.clone());
		}
		r = new Random();
	}
	
	/**
	 * This is a constructor for the Evolution class. It takes list of chromosomes,
	 * a value for the mutation rate and a value for the crossover rate as input.
	 * 
	 * @param population A list of chromosomes that represents the population.
	 * @param m_rate The mutation rate.
	 * @param cr_rate The crossover rate.
	 */
	public Evolution(List<IChromosome> population, double m_rate, double cr_rate) {
		this(population);
		setMUTATION_RATE(m_rate);
		setCROSSOVER_RATE(cr_rate);
	}
	
	/**
	 * This method returns the current number of generations.
	 * 
	 * @return The number of generations.
	 */
	public int getNr_generations() {
		return nr_generations;
	}

	/**
	 * This method sets the mutation rate.
	 * 
	 * @param mUTATION_RATE The desired mutation rate.
	 */
	public void setMUTATION_RATE(double mUTATION_RATE) {
		MUTATION_RATE = mUTATION_RATE;
	}

	/**
	 * This method sets the crossover rate.
	 * 
	 * @param cROSSOVER_RATE The desired crossover rate.
	 */
	public void setCROSSOVER_RATE(double cROSSOVER_RATE) {
		CROSSOVER_RATE = cROSSOVER_RATE;
	}
	
	/**
	 * This is the central method of the Evolution class. It starts the evolutionary
	 * process and only ends if at least one chromosome has achieved the desired goal.
	 */
	public void evolve() {
		//System.out.println("Evolving...");
		IChromosome fittest_c = getFittest();
		while (fittest_c.getFitness() != -1) {
			nr_generations++;
			nextGeneration();
			fittest_c = getFittest();
			System.out.println(fittest_c.getShortInfo());
		}
		System.out.println("Found a solution after " + nr_generations + " iterations.");
		System.out.println(fittest_c.getInfo());
	}
	
	/**
	 * This method lets the gene pool evolve one generation. It executes all the
	 * necessary steps: selection, crossover and mutation.
	 */
	public void nextGeneration(){
		List<IChromosome> new_population = new ArrayList<IChromosome>();
		IChromosome[] matingPool = prepareSelection();
		int s = matingPool.length;
		for (int i = 0; i<population.size(); i++) {
			IChromosome c1 = select(matingPool, s);
			IChromosome c2 = select(matingPool, s);
			IChromosome child = c1.crossover(c2, CROSSOVER_RATE);
			child.mutate(MUTATION_RATE);
			new_population.add(child);
		}
		this.population = new_population;
	}
	
	/**
	 * This method prepares the selection process in the sense that it generates
	 * an array of chromosomes where each chromosome is represented multiple times
	 * proportional to its fitness. The array is then returned. This method needs
	 * to be called once per generation.
	 * 
	 * @return The array of chromosomes.
	 */
	private IChromosome[] prepareSelection() {
		List<IChromosome> matingPool = new ArrayList<IChromosome>();
		for (IChromosome c : population) {
			int f = c.getFitness();
			for (int i = 0; i<f; i++) {
				matingPool.add(c);
			}
		}
		IChromosome[] matingArr = new IChromosome[matingPool.size()];
		return matingPool.toArray(matingArr);
	}
	
	/**
	 * This method returns a random element of the given mating pool with the
	 * given length.
	 * 
	 * @param matingPool The mating pool from which a random element is selected.
	 * @param s The length of the mating pool.
	 * @return A random element from the given mating pool with the given length.
	 */
	private IChromosome select(IChromosome[] matingPool, int s) {
		int lucky_number = r.nextInt(s);
		return matingPool[lucky_number];
	}
	
	/**
	 * This method lets the pool of chromosomes 'evolve' randomly, i.e. without
	 * the selection or the crossover, just random mutation.
	 */
	public void evolveRandomly() {
		//System.out.println("Evolving randomly...");
		IChromosome fittest_c = getFittest();
		while (fittest_c.getFitness() != -1) {
			nr_generations++;
			for (IChromosome c : population){
				c.mutate(0.5);
			}
			fittest_c = getFittest();
			System.out.println(fittest_c.getShortInfo());
		}
		//System.out.println("Found a solution after " + nr_generations + " iterations.");
		//System.out.println(getFittest().getInfo());
	}
	
	/**
	 * This method returns the fittest chromosome of the current population.
	 * @return The fittest chromosome of the current population.
	 */
	public IChromosome getFittest(){
		IChromosome fittest = population.get(0);
		if (fittest.getFitness() == -1)
			return fittest;
		for (int i = 1; i < population.size(); i++) {
			IChromosome tmp_c = population.get(i);
			if (tmp_c.getFitness() == -1){
				return tmp_c;
			}
			else if (tmp_c.getFitness() > fittest.getFitness()){
				fittest = tmp_c;
			}
		}
		return fittest;
	}
	
	/**
	 * This method returns the current population.
	 * @return The current population.
	 */
	public List<IChromosome> getPopulation(){
		return this.population;
	}
	
	/**
	 * This method resets the current population to the initial population.
	 */
	public void resetPopulation() {
		nr_generations = 0;
		population = new ArrayList<IChromosome>();
		for (IChromosome c : initial_population) {
			population.add(c.clone());
		}
	}
}
