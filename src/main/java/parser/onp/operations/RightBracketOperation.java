package parser.onp.operations;

import parser.onp.strategies.OperationStrategy;
import parser.onp.strategies.OperatorType;

public class RightBracketOperation extends Operation {

  private final char operation = ')';
  private final OperatorType type = OperatorType.BRACKET;
  private final int prio = 1;

  public RightBracketOperation(OperationStrategy operationStrategy) {
    super(operationStrategy);
  }

  @Override
  int priority() {
    return prio;
  }

  @Override
  char getOperation() {
    return operation;
  }

  @Override
  public OperatorType operatorType() {
    return type;
  }

}
