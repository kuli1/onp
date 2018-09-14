package parser.onp.operations;

import parser.onp.strategies.OperationStrategy;
import parser.onp.strategies.OperatorType;

public class SubstractOperation extends Operation {

  private static final char OPERATION = '-';
  private final OperatorType type = OperatorType.STANDRAD;
  private final int prio = 1;

  public SubstractOperation(OperationStrategy operationStrategy) {
    super(operationStrategy);
  }

  @Override
  int priority() {
    return prio;
  }

  @Override
  public char getOperation() {
    return OPERATION;
  }

  @Override
  public OperatorType operatorType() {
    return type;
  }

}
