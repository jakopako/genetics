package genetics;

import java.util.Random;

/**
 * This class implements the IChromosome-interface. An ASCIIChromosome is
 * represented by a gene-string that consists of a subset of the ASCII characters.
 * The randomLetter-method basically determines which subset of the ASCII
 * characters is being used.
 * @author Jakob Dhondt
 *
 */
public class ASCIIChromosome implements IChromosome {

	private String goal;
	private String genes;
	private Random r;
	private int fitness;
	public static int[] letters = possibleLetters();
	
	/**
	 * This constructor initializes a new ASCIIChromosome with the given goal
	 * and a random initial gene-string.
	 * @param goal The goal that this chromosome needs to evolve to.
	 */
	public ASCIIChromosome(String goal){
		this.goal = goal;
		this.r = new Random();
		String new_genes = "";
		for (int i = 0; i < goal.length(); i++) {
			new_genes += randomLetter();
		}
		setGenes(new_genes);
	}
	
	/**
	 * This constructor initializes a new ASCIIChromosome with the given goal
	 * and the given gene-string.
	 * @param goal The goal that this chromosome needs to evolve to.
	 * @param genes The genes that this chromosome's genes should be set to.
	 */
	public ASCIIChromosome(String goal, String genes) {
		this.r = new Random();
		this.goal = goal;
		setGenes(genes);
		
	}
	
	/**
	 * This method generates an integer array with all allowed ASCII characters.
	 * 
	 * @return An integer array with all allowed ASCII characters.
	 */
	private static int[] possibleLetters(){
		letters = new int[57];
		for (int i = 0; i<57; i++){
			if (i < 26)
				letters[i] = i+65; //A-Z
			else if (i < 52)
				letters[i] = i+71; //a-z
			else if (i == 52)
				letters[i] = 32; //space
			else if (i == 53)
				letters[i] = 33; //!
			else if (i == 54)
				letters[i] = 44; //,
			else if (i == 55)
				letters[i] = 46; //.
			else if (i == 56)
				letters[i] = 63; //?
		}
		return letters;
	}
	
	/**
	 * Check whether the given character is allowed as a character for an 
	 * ASCII Chromosome.
	 * 
	 * @param letter The letter to check.
	 * @return True if the given letter is allowed, False otherwise.
	 */
	public static boolean isPossibleLetter(char letter){
		if (contains(letters, (int) letter))
			return true;
		return false;
	}
	
	/**
	 * This method checks whether a given integer array contains a given letter.
	 * 
	 * @param letters2 The integers array.
	 * @param charAt The character to check.
	 * @return True if the integer array contains the character. False otherwise.
	 */
	private static boolean contains(int[] letters2, int charAt) {
        for (int i = 0; i < letters2.length; i++) {
            if (letters2[i] == charAt) {
                return true;
            }
        }
        return false;
	}

	/**
	 * This method returns a random ASCII-character from the following set:
	 * [A-Z,a-z,space,dot,comma,!,?], defined in the static parameter 'letters'.
	 * @return A random ASCII-character.
	 */
	public String randomLetter() {
		return (new Character((char) letters[r.nextInt(letters.length)])).toString();
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#getGenes()
	 */
	@Override
	public String getGenes() {
		return this.genes;
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#setGenes(java.lang.String)
	 */
	@Override
	public void setGenes(String new_genes) {
		this.genes = new_genes;
		calculateFitness();
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#mutate(double)
	 */
	@Override
	public void mutate(double mutation_rate) {
		String mutated_genes = "";
		for (int i = 0, n = genes.length(); i < n; i++){
			int t = r.nextInt((int) (1/mutation_rate));
			if (t == 0)
				mutated_genes += randomLetter();
			else
				mutated_genes += genes.charAt(i);
		}
		setGenes(mutated_genes);
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#crossover(genetics.IChromosome, double)
	 */
	@Override
	public ASCIIChromosome crossover(IChromosome c, double crossover_rate) {
		if (c.getGenes().length() != getGenes().length())
			throw new IllegalArgumentException("Chromosomes must be of equal length.");
		int luck = r.nextInt(1000);
		ASCIIChromosome child = this.clone();
		if (luck < crossover_rate*1000){
			int pos = r.nextInt(genes.length());
			child.setGenes(getGenes().substring(0, pos) + c.getGenes().substring(pos));
		}
		return child;
	}

	/**
	 * This method calculates the fitness of this ASCII-chromosome. The basic fitness
	 * is one. For every additional character that is at the correct position one is
	 * added to the fitness. A perfect fitness corresponds to minus one.
	 */
	private void calculateFitness() {
		this.fitness = 1;
		for (int i = 0; i < genes.length(); i++) {
			if (genes.charAt(i) == goal.charAt(i))
				this.fitness += 1;
		}
		if (this.fitness == genes.length()+1)
			this.fitness = -1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#getFitness()
	 */
	@Override
	public int getFitness() {
		return this.fitness;
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#getInfo()
	 */
	@Override
	public String getInfo() {
		return "This chromosome has a fitness of " + getFitness() +
				"\nIts genes are: " + getGenes();
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#clone()
	 */
	@Override
	public ASCIIChromosome clone() {
		return new ASCIIChromosome(goal, genes);
	}

	/*
	 * (non-Javadoc)
	 * @see genetics.IChromosome#getShortInfo()
	 */
	@Override
	public String getShortInfo() {
		return getGenes();
	}

}
