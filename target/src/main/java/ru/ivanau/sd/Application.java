package ru.ivanau.sd;

import ru.ivanau.sd.token.Token;
import ru.ivanau.sd.tokenizer.Tokenizer;
import ru.ivanau.sd.visitor.CalcVisitor;
import ru.ivanau.sd.visitor.ParserVisitor;
import ru.ivanau.sd.visitor.PrintVisitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        final String inputFile = "/home/ivanln/Desktop/sem7/software-design/hw6-visitor/src/test/java/input.txt";
        try (final InputStream is = new FileInputStream(inputFile)) {
            Tokenizer tokenizer = new Tokenizer(is);
            final List<Token> tokens = tokenizer.tokenize();

            final PrintVisitor printVisitor = new PrintVisitor(System.out);
            printVisitor.visit(tokens);

            final ParserVisitor parserVisitor = new ParserVisitor();
            final List<Token> rpnTokens = parserVisitor.visit(tokens);
            printVisitor.visit(rpnTokens);

            final CalcVisitor calcVisitor = new CalcVisitor();
            int value = calcVisitor.visit(rpnTokens);
            System.out.println(value);

            System.out.println(Arrays.toString(tokens.toArray()));
        }
    }
}
