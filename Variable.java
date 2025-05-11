package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import classes.Fraction;

public class Variable {
    String type;
    String var;
    Fraction constante;
    boolean  visited = false;

    public Variable(String var, Fraction constante, String type) throws  Exception {
        setVar(var);
        setConstante(constante);
        setType(type);
    }
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    public boolean  getVisited (){
        return this.visited;
    }

    public void setConstante(Fraction constante) {
        this.constante = constante;
    }

    public void setType(String type) throws Exception {
          List<String> type_data = new ArrayList<>(List.of("xi", "si", "ai" , "ci"));
          type = type.toLowerCase();
          if (!type_data.contains(type)){
            throw new Exception("Type de variable invalide");
          }
        this.type = type;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Fraction getConstante() {
        return constante;
    }

    public String getType() {
        return type;
    }

    public String getVar() {
        return var;
    }

    public String signeTostring(){
        if(this.getSigne() <  0){
                return "-";
        }
        return "+";

    }

    public String toString() {
        return getConstante().toString() + "" + getVar();
    }

    public Integer getSigne() {
        double const_value = Double.parseDouble(getConstante().getValue().toString());
        if (const_value < 0) {
            return -1;
        }
        if (const_value > 0) {
            return 1;
        }
        return null;
    }

}
