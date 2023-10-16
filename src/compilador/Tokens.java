
package compilador;

public enum Tokens {
    id,
    num,
    entero("int"),
    flotante("float"),
    caracter("char"),
    Igual("="),
    Suma("+"),
    Resta("-"),
    Division("/"),
    Producto("*"),
    AbreParentesis("("),
    CierraParentesis(")"),
    Coma(","),
    PuntoComa(";"),
    ERROR;
    
    private final String simbolo;

    private Tokens() {
        this.simbolo = null;
    }

    private Tokens(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
