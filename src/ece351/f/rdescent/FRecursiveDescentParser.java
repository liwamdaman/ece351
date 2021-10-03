/* *********************************************************************
 * ECE351 
 * Department of Electrical and Computer Engineering 
 * University of Waterloo 
 * Term: Fall 2021 (1219)
 *
 * The base version of this file is the intellectual property of the
 * University of Waterloo. Redistribution is prohibited.
 *
 * By pushing changes to this file I affirm that I am the author of
 * all changes. I affirm that I have complied with the course
 * collaboration policy and have not plagiarized my work. 
 *
 * I understand that redistributing this file might expose me to
 * disciplinary action under UW Policy 71. I understand that Policy 71
 * allows for retroactive modification of my final grade in a course.
 * For example, if I post my solutions to these labs on GitHub after I
 * finish ECE351, and a future student plagiarizes them, then I too
 * could be found guilty of plagiarism. Consequently, my final grade
 * in ECE351 could be retroactively lowered. This might require that I
 * repeat ECE351, which in turn might delay my graduation.
 *
 * https://uwaterloo.ca/secretariat-general-counsel/policies-procedures-guidelines/policy-71
 * 
 * ********************************************************************/

package ece351.f.rdescent;

import ece351.common.ast.AndExpr;
import ece351.common.ast.AssignmentStatement;
import ece351.common.ast.ConstantExpr;
import ece351.common.ast.Constants;
import ece351.common.ast.Expr;
import ece351.common.ast.NotExpr;
import ece351.common.ast.OrExpr;
import ece351.common.ast.VarExpr;
import ece351.f.ast.FProgram;
import ece351.util.CommandLine;
import ece351.util.Lexer;



public final class FRecursiveDescentParser implements Constants {
   
	// instance variables
	private final Lexer lexer;

    public FRecursiveDescentParser(String... args) {
    	final CommandLine c = new CommandLine(args);
        lexer = new Lexer(c.readInputSpec());
    }
    
    public FRecursiveDescentParser(final Lexer lexer) {
        this.lexer = lexer;
    }

    public static void main(final String arg) {
    	main(new String[]{arg});
    }
    
    public static void main(final String[] args) {
    	parse(args);
    }

    public static FProgram parse(final String... args) {
        final FRecursiveDescentParser p = new FRecursiveDescentParser(args);
        return p.parse();
    }
    
    public FProgram parse() {
        return program();
    }

    FProgram program() {
    	FProgram fp = new FProgram();
    	do {
        	fp = fp.append(formula());
        } while (!lexer.inspectEOF());
        lexer.consumeEOF();
        assert fp.repOk();
        return fp;
    }

    AssignmentStatement formula() {
        final VarExpr var = var();
        lexer.consume("<=");
        final Expr expr = expr();
        lexer.consume(";");
        return new AssignmentStatement(var, expr);
    }
    
    Expr expr() {
    	Expr expr = term();
    	while (lexer.inspect("or")) {
    		lexer.consume("or");
    		expr = new OrExpr(expr, term());
    	}
    	return expr;
    }
    
    Expr term() {
    	Expr term = factor();
    	while (lexer.inspect("and")) {
    		lexer.consume("and");
    		term = new AndExpr(term, factor());
    	}
    	return term;
    }
    
    Expr factor() {
    	if (lexer.inspect("not")) {
			lexer.consume("not");
			return new NotExpr(factor());
		} else if (lexer.inspect("(")) {
			lexer.consume("(");
			Expr expr = expr();
			lexer.consume(")");
			return expr;
		} else if (peekConstant()) {
			return constant();
		} else {
			return var();
		}
    }
    
    VarExpr var() {
    	if (lexer.inspectID()) {
			return new VarExpr(lexer.consumeID());
		} else {
			throw new IllegalArgumentException();
		}
    }
    
    ConstantExpr constant() {
    	if (peekConstant()) {
			lexer.consume("'");
			if (lexer.inspect("0", "1")) {
				String val = lexer.consume("0", "1");
				if (peekConstant()) {
					lexer.consume("'");
					return ConstantExpr.make(val);
				}
			}
		}
		
		throw new IllegalArgumentException();
    }

    // helper functions
    private boolean peekConstant() {
        return lexer.inspect("'");
    }

}

