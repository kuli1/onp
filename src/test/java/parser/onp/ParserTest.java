package parser.onp;


import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import parser.onp.Parser;
import parser.onp.exceptions.OnpParserException;

@RunWith(JUnitParamsRunner.class)
public class ParserTest {

  @Test
  @Parameters(method = "parameters")
  public void shouldParseValidAdd(String expression, String expectedExpression) {
    //given
    String onpNotation = Parser.parseExpression(expression);
    //expected
    assertThat(onpNotation).isEqualTo(expectedExpression);
  }

  @Test
  public void shouldThrowExeptionForToManyLeftBrackets() {
    //expected
    assertThatThrownBy(() -> Parser.parseExpression("((2+2)"))
        .isInstanceOf(OnpParserException.class);
  }

  @Test
  public void shouldThrowExceptionForToManyRightBrackets() {
    //expected
    assertThatThrownBy(() -> Parser.parseExpression("(2+2)))"))
        .isInstanceOf(OnpParserException.class);
  }

  @Test
  public void shouldThrowExceptionForLetters() {
    //expected
    assertThatThrownBy(() -> Parser.parseExpression("(2D+2)))"))
        .isInstanceOf(OnpParserException.class);
  }

  private Object[] parameters() {
    return new Object[]{
        new Object[]{"(22+33)", "22 33 +"},
        new Object[]{"(22.2+33.3)", "22.2 33.3 +"},
        new Object[]{"(22-33)", "22 33 -"},
        new Object[]{"22-33", "22 33 -"},
        new Object[]{"22+33", "22 33 +"},
        new Object[]{"5+(10-5)+2+(8+2)", "5 10 5 - + 2 + 8 2 + +"},
        new Object[]{"88-99+100-(1+5-6)", "88 99 - 100 + 1 5 + 6 - -"}
    };
  }


}