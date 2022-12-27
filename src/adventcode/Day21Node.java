package adventcode;

public class Day21Node {
	private String name;
	private String value;
	
	private Day21Node left;
	private Day21Node right;
	
	public Day21Node (String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public void setLeft(Day21Node left) {
		this.left = left;
	}

	public void setRight(Day21Node right) {
		this.right = right;
	}

	public Day21Node getLeft() {
		return left;
	}

	public Day21Node getRight() {
		return right;
	}

	public long evaluate () {
		if (left == null && right == null) {
			return Long.parseLong(value);
		}
		else {
			if (value.contains("+")) {
				return Long.sum(left.evaluate(), right.evaluate());
			}
			else if (value.contains("-")) {
				return left.evaluate() - right.evaluate();
			}
			else if (value.contains("*")) {
				return left.evaluate() * right.evaluate();
			}
			else if (value.contains("/")) {
				return Long.divideUnsigned(left.evaluate(), right.evaluate());
			}
		}
		return 0;
	}
	
	public boolean isHuman () {
		if (left == null && right == null) {
			return name.equals("humn");
		}
		else {
			return left.isHuman() || right.isHuman();
		}
	}
	
	public long evaluateReverse (long expectedResult) {
		if (name.equals("humn")) {
			return expectedResult;
		}
		if (left == null && right == null) {
			return Long.parseLong(value);
		}
		else {
			Day21Node human = null;
			Day21Node monkey = null;
			if (left.isHuman()) {
				human = left;
				monkey = right;
			}
			else {
				human = right;
				monkey = left;			
			}
			if (value.contains("+")) {
				return human.evaluateReverse(expectedResult - monkey.evaluate());
			}
			else if (value.contains("-")) {
				if (left.isHuman()) {
					return human.evaluateReverse(expectedResult + monkey.evaluate());					
				}
				else {
					return human.evaluateReverse(monkey.evaluate() - expectedResult);
				}
			}
			else if (value.contains("*")) {
				return human.evaluateReverse(expectedResult / monkey.evaluate());
			}
			else if (value.contains("/")) {
				if (left.isHuman()) {
					return human.evaluateReverse(expectedResult * monkey.evaluate());				
				}
				else {
					return human.evaluateReverse(expectedResult / monkey.evaluate());				
					
				}
			}
		}
		return 0;		
	}
}
