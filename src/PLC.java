import syntax.Node;
import visitor.CtdGenerator;
import visitor.PrettyPrint;
import visitor.Visitor;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class PLC {	   
	public static void main(String[] argv) {
    try {
        Reader in = new InputStreamReader(System.in);
        Visitor visitor = new CtdGenerator();
        if (argv.length>0) {
            in = new FileReader(argv[0]);
        }
        if (argv.length>1 && argv[1].equals("-d")){
            visitor = new PrettyPrint();
        }
        parser p = new parser(new Yylex(in));
        ((Node) p.parse().value).accept(visitor);
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
