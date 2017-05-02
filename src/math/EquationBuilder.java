package math;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.Stack;

public class EquationBuilder {

	/**
	 * This is a strange way of building the equation (abstract syntax tree). It doesn't take the basic mathematical
	 * rules into account. * and / before + and -.
	 * @param binary_string The string that has to be turned into an Operand object.
	 * @return an Operand object that can be asked its numerical value.
	 */
	public static Operand buildEquationStrangely(String binary_string){
		Operand result = null;
		boolean mustBeNumber = true;
		Operator operator = null;
		Operand right_tmp = null;
		outerloop:
		for (int i = 0, n = binary_string.length(); i < n; i+=4){
			String nextString = "";
			for (int j = 0; j < 4; j++){
				try {
					nextString += binary_string.charAt(i + j);
				} catch (IndexOutOfBoundsException io){
					break outerloop;
				}
			}
			if(mustBeNumber){
				try {
					if (result == null)
						result = new Number(nextString);
					else {
						right_tmp = new Number(nextString);
						operator.setLeft(result);
						operator.setRight(right_tmp);
						result = operator;
					}
					mustBeNumber = false;
				} catch (IllegalArgumentException iae) {
					continue;
				}
			} else {
				switch (nextString) {
				case Operator.BIN_PLUS:
					operator = new Plus();
					break;
				case Operator.BIN_MIN:
					operator = new Min();
					break;
				case Operator.BIN_MUL:
					operator = new Mul();
					break;
				case Operator.BIN_DIV:
					operator = new Div();
					break;
				default:
					continue;
				}
				mustBeNumber = true;
			}
		}
		return result;
	}
	
	public static Operand buildEquation(String binary_string){
		Operator result = null;
		Stack<Number> numbers = new Stack<Number>();
		Stack<Operator> operators = new Stack<Operator>();
		Comparator<Operator> comp = new Comparator<Operator>() {
			@Override
			public int compare(Operator a, Operator b) {
				if ((a instanceof Mul | a instanceof Div) & (b instanceof Plus | b instanceof Min)) {
					return 1;
				} else if ((b instanceof Plus | b instanceof Min) & (a instanceof Mul | a instanceof Div)) {
					return -1;
				} else
					return 0;
			}
		};
		boolean mustBeNumber = true;
		outerloop:
		for (int i = 0, n = binary_string.length(); i < n; i+=4){
			String nextString = "";
			for (int j = 0; j < 4; j++){
				try {
					nextString += binary_string.charAt(i + j);
				} catch (IndexOutOfBoundsException io){
					break outerloop;
				}
			}
			if(mustBeNumber){
				try {
					numbers.push(new Number(nextString));
					mustBeNumber = false;
				} catch (IllegalArgumentException iae) {
					continue;
				}
			} else {
				Operator op = null;
				switch (nextString) {
					case Operator.BIN_PLUS:
						op = new Plus();
						break;
					case Operator.BIN_MIN:
						op = new Min();
						break;
					case Operator.BIN_MUL:
						op = new Mul();
						break;
					case Operator.BIN_DIV:
						op = new Div();
						break;
					default:
						break;
				}
				if (op == null) {
					continue;
				}
				if (operators.empty()) {
					operators.push(op);
				} else {
					try {
						while (comp.compare(op, operators.peek()) <= 0) {
							Number right = numbers.pop();
							Operand left = result == null ? numbers.pop() : result;
							result = operators.pop();
							result.setLeft(left);
							result.setRight(right);
						}
						operators.push(op);
					} catch (EmptyStackException ese) {
						operators.push(op);
					}
				}
				mustBeNumber = true;
			}
		}
		if(mustBeNumber){
			try {
				operators.pop();
			}
			catch (EmptyStackException ese) {
				return result;
			}
		}
		if(operators.empty() && !numbers.empty())
			return numbers.pop();
		else
			while (!operators.empty()){
				Number right = numbers.pop();
				Operand left = result == null ? numbers.pop() : result;
				result = operators.pop();
				result.setLeft(left);
				result.setRight(right);
			}
		return result;
	}
}