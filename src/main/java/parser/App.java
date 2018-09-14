package parser;

import static java.lang.String.format;

import java.util.Scanner;
import parser.onp.Parser;
import parser.onp.exceptions.OnpParserException;


public class App 
{
    public static void main( String[] args )
    {
        Scanner in = new Scanner(System.in);
        System.out.println( "Please provide expression to parse" );
        String expressionToParse = in.nextLine();
        String result;
        try {
            result = Parser.parseExpression(expressionToParse);
        }
        catch (OnpParserException oe){
            result = format("Error parsing expression : %s", oe.getMessage());
        }

        System.out.println(format("ONP parsed expression : %s" , result));
    }
}
