package parser.onp.operations;

import static parser.onp.strategies.OperationStrategy.LEFT_BRACKET_OPERATION;
import static parser.onp.strategies.OperationStrategy.RIGHT_BRACKET_OPERATION;
import static parser.onp.strategies.OperationStrategy.STANDARD_OPERATION;

import parser.onp.exceptions.OnpParserException;

public class OperationFactory {

  public static Operation of(char c){

    switch (c){
      case '+': return new AddOperation(STANDARD_OPERATION());
      case '-': return new SubstractOperation(STANDARD_OPERATION());
      case '(': return new LeftBracketOperation(LEFT_BRACKET_OPERATION());
      case ')': return new RightBracketOperation(RIGHT_BRACKET_OPERATION());
      default: throw new OnpParserException("Not Allowed Argument");
    }
  }
}
