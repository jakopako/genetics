package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import genetics.BinaryChromosome;
import genetics.EquationChromosome;

public class TestChromosomes {

	@Test
	public void test1() {
		EquationChromosome c = new EquationChromosome(8, 34);
		System.out.println(c.getGenes());
		System.out.println(c.getNumVal());
		c.mutate(0.1);
		System.out.println(c.getGenes());
		System.out.println(c.getNumVal());
	}
	
	@Test
	public void test2() {
		EquationChromosome c = new EquationChromosome(8, 34);
		EquationChromosome c2 = new EquationChromosome(8, 34);
		System.out.println(c.getGenes());
		System.out.println(c2.getGenes());
		c.crossover(c2, 1.0);
		System.out.println(c.getGenes());
		System.out.println(c2.getGenes());
	}

}
