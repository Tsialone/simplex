package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Equation;
import classes.Fraction;
import classes.Ligne;
import classes.Variable;
import classes.Systeme;
import classes.Tableau;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Un programme pour maximiser une fonction sous contrainte: ");
        // declaration des varibles de decision de fob
        List<Variable> eq_fob = new ArrayList<>(
                Arrays.asList(
                        new Variable("x1", new Fraction(2), "xi"),
                        new Variable("x2", new Fraction(3), "xi"),
                        new Variable("x3", new Fraction(1), "xi")));
        // declariation du fonction objective
        Equation objective = new Equation(eq_fob, "=", new Fraction(0));
        System.out.println("Fonction Objective: \n" + objective.toString());

        // eq1
        List<Variable> eq1_xi = new ArrayList<>(
                Arrays.asList(
                        new Variable("x1", new Fraction(1), "xi"),
                        new Variable("x2", new Fraction(1), "xi"),
                        new Variable("x3", new Fraction(1), "xi"),
                        new Variable("s1", new Fraction(1), "si")

                ))

        ;
        Equation eq1 = new Equation(eq1_xi, "=", new Fraction(40));
        // eq2
        List<Variable> eq2_xi = new ArrayList<>(
                Arrays.asList(
                        new Variable("x1", new Fraction(2), "xi"),
                        new Variable("x2", new Fraction(1), "xi"),

                        new Variable("x3", new Fraction(-1), "xi"),
                        new Variable("s2", new Fraction(-1), "si"),
                        new Variable("a1", new Fraction(1), "ai")

                        
                        
                        ));
        Equation eq2 = new Equation(eq2_xi, "=", new Fraction(10));


        // eq3
        List<Variable> eq3_xi = new ArrayList<>(
                Arrays.asList(
                        new Variable("x2", new Fraction(-1), "xi"),
                        new Variable("x3", new Fraction(1), "xi"),
                        new Variable("s3", new Fraction(-1), "si"),
                        new Variable("a2", new Fraction(1), "ai")

                        ));
        Equation eq3 = new Equation(eq3_xi, "=", new Fraction(10));

        // declaration et affichage du systeme du contrainte
        Systeme sc = new Systeme(Arrays.asList(eq1, eq2 , eq3));
        System.out.println("Systeme du contrainte standarise: ");
        System.out.println(sc.toString());

        // en 1 phase
        String temp = "a1x2";
        System.out.println(temp.contains("a"));
        List<Variable> base = sc.getBase();
        List<Variable> variables = sc.getVariable();

        System.out.println(base);

        for (Variable variable : variables) {
            System.out.println(variable.getVar());
        }

        // System.out.println(objective);

        // verification de nombre de phase
        boolean phase1 = true;
        for (Variable variable : base) {
            if (variable.getType().equals("ai")) {
                phase1 = false;
                break;
            }
        }
        // 1 phase
        int iteration = 1;
        if (phase1) {
            Tableau tableau = new Tableau(base, variables, sc, objective, 1);
            System.out.println(tableau);
            while (true) {
                System.out.println("iteration " + iteration);
                tableau.phase1();
                iteration++;
            }
        }
        // 2 phase
        else {
            iteration = 1;
            Tableau tableau = new Tableau(base, variables, sc, objective, 2);
            System.out.println(tableau);
            while (true) {
                tableau.phase2();
            }

        }

    }
}