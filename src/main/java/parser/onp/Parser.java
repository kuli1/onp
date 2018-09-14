package parser.onp;

import static java.lang.Character.isDigit;
import static org.apache.commons.lang3.StringUtils.*;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import parser.onp.exceptions.OnpParserException;
import parser.onp.operations.LeftBracketOperation;
import parser.onp.operations.Operation;
import parser.onp.operations.OperationFactory;

/**
 * Alghoritm from Edsger Dijkstra
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation#Algorytm_konwersji_z_notacji_infiksowej_do_ONP_(rekurencyjny)
 */
public class Parser {

  private static final List<Character> ALLOWED_CHARS = Arrays.asList('.', ',');

  public static String parseExpression(String expressionToParse) {

    StringBuilder outputResult = new StringBuilder();
    Stack<Operation> operations = new Stack<>();

    for (char c : deleteWhitespace(expressionToParse).toCharArray()) {
      if (validOutput(c)) {
        outputResult.append(c);
        continue;
      }
      executeOperation(outputResult, operations, c);
    }

    cleanUp(outputResult, operations);
    return outputResult.toString();
  }

  private static void executeOperation(StringBuilder outputResult, Stack<Operation> operations,
      char c) {
    Operation op = OperationFactory.of(c);
    op.update(operations, outputResult);
  }

  /**
   * Only Digits and . and , sings are allowed , rest if are not operators are rejected.
   * @param c
   * @return
   */
  private static boolean validOutput(char c) {
    return isDigit(c) || ALLOWED_CHARS.contains(c);
  }

  private static void cleanUp(StringBuilder outputResult, Stack<Operation> operations) {
    validateBrackets(operations);
    printOutStack(outputResult, operations);
  }

  private static void printOutStack(StringBuilder outputResult, Stack<Operation> operations) {
    while (!operations.isEmpty()) {
      Operation op = operations.pop();
      outputResult.append(op.toPrettyString());
    }
  }

  private static void validateBrackets(Stack<Operation> operations) {
    boolean bracketExist = operations.stream()
        .anyMatch(operation -> operation instanceof LeftBracketOperation);
    if (bracketExist) {
      throw new OnpParserException("Some left brackets are not closed");
    }
  }

}
