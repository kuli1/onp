package parser.onp.strategies;

import java.util.Stack;
import parser.onp.exceptions.OnpParserException;
import parser.onp.operations.LeftBracketOperation;
import parser.onp.operations.Operation;

public interface OperationStrategy {

  static OperationStrategy STANDARD_OPERATION() {
    return (Operation operator, Stack<Operation> operators, StringBuilder results) -> {
      if (operators.isEmpty()) {
        operators.push(operator);
        results.append(" ");
        return;
      }
      while (!operators.isEmpty()) {
        Operation first = operators.peek();
        if (first.operatorType().equals(OperatorType.STANDRAD) && operator.isLowerOrEqPrio(first)) {
          results.append(operators.pop().toPrettyString());
        } else {
          break;
        }
      }
      operators.push(operator);
      results.append(" ");
    };
  }

  static OperationStrategy LEFT_BRACKET_OPERATION() {
    return (Operation operator, Stack<Operation> operators, StringBuilder results) -> {
      operators.push(operator);
    };
  }

  static OperationStrategy RIGHT_BRACKET_OPERATION() {
    return (Operation operator, Stack<Operation> operators, StringBuilder results) -> {
      operators.stream().filter(operation -> operation instanceof LeftBracketOperation)
          .findAny()
          .orElseThrow(() -> new OnpParserException("Missing Left Bracket"));

      while (!operators.isEmpty()) {
        Operation op = operators.pop();
        if (op instanceof LeftBracketOperation) {
          break;
        }
        results.append(op.toPrettyString());
      }
    };
  }

  void processOperation(Operation operator, Stack<Operation> operators, StringBuilder results);

}
