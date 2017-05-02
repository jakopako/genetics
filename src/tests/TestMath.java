package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import math.EquationBuilder;
import math.Operand;
import math.StringDistance;

public class TestMath {

	@Test
	public void testEquationBuilder1() {
		String s = "011010100101110001001101001010100001";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		assertEquals("6 + 5 * 4 / 2 + 1", o.getDecRep());
		assertEquals(s, o.getBinRep());
		System.out.println(o.getDecRep());
	}
	@Test
	public void testEquationBuilder2() {
		String s = "0010001010101110101101110010";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		assertEquals("2 + 7", o.getDecRep());
		assertEquals("001010100111", o.getBinRep());
		System.out.println(o.getDecRep());
	}
	@Test
	public void testEquationBuilder3() {
		String s = "00100010101001011101";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		assertEquals("2 + 5", o.getDecRep());
		assertEquals("001010100101", o.getBinRep());
		System.out.println(o.getDecRep());
	}
	
	@Test
	public void testEquationBuilder4() {
		String s = "00100010101001011101";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		assertEquals(7, o.getNumVal());
		System.out.println(o.getNumVal());
	}
	
	@Test
	public void testEquationBuilder5() {
		String s = "010111010010";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		System.out.println(o.getNumVal());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationBuilder6() {
		String s = "01011100010011010000";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		System.out.println(o.getDecRep());
		o.getNumVal();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEquationBuilder7() {
		String s = "0101110001001101000010100011";
		Operand o = EquationBuilder.buildEquationStrangely(s);
		System.out.println(o.getDecRep());
		o.getNumVal();
	}
	
	@Test
	public void testEquationBuilder8() {
		String s = "00100010101001011101";
		Operand o = EquationBuilder.buildEquation(s);
		System.out.println(o.getDecRep());
		System.out.println(o.getBinRep());
		assertEquals("2 + 5", o.getDecRep());
		assertEquals("001010100101", o.getBinRep());
		assertEquals(7, o.getNumVal());
	}
	
	@Test
	public void testEquationBuilder9() {
		String s = "011010100101110001001101001010100001";
		Operand o = EquationBuilder.buildEquation(s);
		assertEquals(17, o.getNumVal());
		System.out.println(o.getDecRep());
	}
	
	@Test
	public void testEquationBuilder10() {
		String s = "010111010010";
		Operand o = EquationBuilder.buildEquation(s);
		assertEquals(2, o.getNumVal());
		System.out.println(o.getNumVal());
	}
	
	@Test
	public void testEquationBuilder11() {
		String s = "00111011";
		Operand o = EquationBuilder.buildEquation(s);
		assertEquals(3, o.getNumVal());
	}
	
	@Test
	public void testEquationBuilder12() {
		String s = "1010";
		Operand o = EquationBuilder.buildEquation(s);
		assertNull(o);
	}
	
	@Test
	public void testEquationBuilder13() {
		String s = "101000";
		Operand o = EquationBuilder.buildEquation(s);
		assertNull(o);
	}
	
	@Test
	public void testLevenshtein1() {
		assertEquals(0, StringDistance.levenshtein("Hello", "Hello"));
	}
	
	@Test
	public void testLevenshtein2() {
		assertEquals(1, StringDistance.levenshtein("Mama", "Mamam"));
	}
	
	@Test
	public void testLevenshtein3() {
		assertEquals(4, StringDistance.levenshtein("HansPeter", "anseTEr"));
	}
}
