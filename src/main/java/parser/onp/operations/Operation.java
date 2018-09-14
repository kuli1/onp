package parser.onp.operations;

import java.util.Stack;
import parser.onp.strategies.OperationStrategy;
import parser.onp.strategies.OperatorType;

public abstract class Operation {

  private OperationStrategy operationStrategy;

  public Operation(OperationStrategy operationStrategy) {
    this.operationStrategy = operationStrategy;
  }

  abstract int priority();

  abstract char getOperation();

  public abstract OperatorType operatorType();

  public String toPrettyString(){
    return String.format(" %s",getOperation());
  }

  public void update(Stack<Operation> operations, StringBuilder outputResult){
    operationStrategy.processOperation(this, operations, outputResult);
  }

  public boolean isLowerOrEqPrio(Operation operation){
    return this.priority() <= operation.priority();
  }
}
