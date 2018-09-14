package parser.onp.operations;

import parser.onp.strategies.OperationStrategy;
import parser.onp.strategies.OperatorType;

public class AddOperation extends Operation {

  private final char operation = '+';
  private final OperatorType type = OperatorType.STANDRAD;
  private final int prio = 1;

  public AddOperation(OperationStrategy operationStrategy) {
    super(operationStrategy);
  }

  @Override
  int priority() {
    return prio;
  }

  @Override
  public char getOperation() {
    return operation;
  }

  @Override
  public OperatorType operatorType() {
    return type;
  }

}
