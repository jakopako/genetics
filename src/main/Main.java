package main;

import java.util.ArrayList;
import java.util.List;

import genetics.*;

/**
 * This is the main class of the genetic algorithm code. The contained main
 * method can be called with various parameters. For details see the documentation
 * of the main method.
 * 
 * @author Jakob Dhondt
 *
 */
public class Main {
	/**
	 * This method initiates the evolutionary process. Various parameters can
	 * be set. <br>
	 * -s population size<br>
	 * -t chromosome type, options:<br>
	 * 		eq: Equation chromosome<br>
	 * 		str: String chromosome<br>
	 * 		asc: ASCII chromosome<br>
	 * -g goal to which the chromosome needs to evolve<br>
	 * -h print the help text. Prints this explanation.<br>
	 * 
	 * @param args The arguments to set the previously explained parameters.
	 */
	public static void main(String[] args)  {
		int population_size = 200; //default value.
		String string_goal = "This is the default goal"; //default value.
		String help_string = 
				"This class initiates the evolutionary algorithm. The following parameters can be set.\n" 
				+ "-s population size\n"
				+ "-t chromosome type, options:\n"
				+ "		eq: Equation chromosome\n"
				+ "		str: String chromosome\n"
				+ "		asc: ASCII chromosome\n"
				+ "-g goal to which the chromosome needs to evolve\n"
				+ "-h print the help text. Prints this explanation.";
		String default_info =
				"Called the function with the default values:\n"
				+ "Population size: " + population_size + "\n"
				+ "Chromosome type: ASCII chromosome\n"
				+ "Goal string: " + string_goal;
		int chromosome_type = 3; //default value. 1= eq, 2 = str, 3 = asc.
		int integer_goal = 89;
		if (args.length == 0){
			ASCIIEvolution(string_goal, population_size);
			System.out.println(default_info);
			System.exit(0);
			//equationEvolution(58, population_size);
			//stringEvolution("This is the goal sentence.", population_size);
		} else if (args.length == 1) {
			if (args[0].equals("-h") ) {
				System.out.println(help_string);
				System.exit(0);
			} else {
				System.out.println("The arguments are not correct.");
				System.out.println(help_string);
				System.exit(-1);
			}
		} else {
			for (int i = 1; i<args.length; i+=2){
				if (args[i-1].equals("-s"))
					try {
						population_size = Integer.parseInt(args[i]);
					} catch (NumberFormatException nfe) {
						System.out.println("The argument -s must be followed by an integer value.");
						System.exit(-1);
					}
				else if (args[i-1].equals("-t")) {
					if (args[i].equals("eq"))
						chromosome_type = 1;
					else if (args[i].equals("str"))
						chromosome_type = 2;
					else if (args[i].equals("asc"))
						chromosome_type = 3;
					else {
						System.out.println("The argument -t was not followed by any legit value.\n"
								+ "Consider calling the function with the -h option for more information.");
						System.exit(-1);
					}
				} else if (args[i-1].equals("-g")) {
					try {
						integer_goal = Integer.parseInt(args[i]);
					} catch (NumberFormatException nfe) {
						string_goal = args[i];
					}
				}
			}
		}
		if (chromosome_type == 1){
			equationEvolution(integer_goal, population_size);
		} else if (chromosome_type == 2){
			stringEvolution(string_goal, population_size);
		} else if (chromosome_type == 3){
			for (int j = 0; j<string_goal.length(); j++) {
				if (!ASCIIChromosome.isPossibleLetter(string_goal.charAt(j))){
					System.out.println("For this chromosome type for now the goal cannot contain any character other than [A-Z,a-z,space].\n"
							+ "This will be changed in the future.");
					System.exit(-1);
				}
			}
			ASCIIEvolution(string_goal, population_size);
		}
	}
	
	/**
	 * This method initiates an evolution with Equation chromosomes.
	 * 
	 * @param goal The goal that the equation chromosomes have to evolve to.
	 * @param size The size of the population.
	 */
	public static void equationEvolution(int goal, int size) {
		List<IChromosome> pop = new ArrayList<IChromosome>();
		for (int i = 0; i<size; i++){
			pop.add(new EquationChromosome(32, goal));
		}
		Evolution E = new Evolution(pop);
		E.evolve();
	}
	
	/**
	 * This method initiates an evolution with String chromosomes.
	 * 
	 * @param goal The goal that the string chromosomes have to evolve to.
	 * @param size The size of the population.
	 */
	public static void stringEvolution(String goal, int size) {
		List<IChromosome> pop = new ArrayList<IChromosome>();
		for (int i = 0; i<size; i++){
			pop.add(new StringChromosome(goal));
		}
		Evolution E = new Evolution(pop);
		E.evolve();
	}
	
	/**
	 * This method initiates an evolution with ASCII chromosomes.
	 * 
	 * @param goal The goal that the ASCII chromosomes have to evolve to.
	 * @param size The size of the population.
	 */
	public static void ASCIIEvolution(String goal, int size) {
		List<IChromosome> pop = new ArrayList<IChromosome>();
		for (int i = 0; i<size; i++){
			pop.add(new ASCIIChromosome(goal));
		}
		Evolution E = new Evolution(pop);
		E.evolve();
	}
	
	public static double average(int[] data) {
		int sum = 0;
		for (int d : data) {
			sum += d;
		}
		double average = 1.0d * sum / data.length;
		return average;
	}
}
