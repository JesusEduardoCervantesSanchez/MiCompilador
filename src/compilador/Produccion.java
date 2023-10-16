/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author CSjes
 */
public class Produccion {

    public String NT;
    public String P;
    public int CE;

    public Produccion(String NT, String P, int CE) {
        this.NT = NT;
        this.P = P;
        this.CE = CE;
    }

    public String getNT() {
        return NT;
    }

    public void setNT(String NT) {
        this.NT = NT;
    }

    public String getP() {
        return P;
    }

    public void setP(String P) {
        this.P = P;
    }

    public int getCE() {
        return CE;
    }

    public void setCE(int CE) {
        this.CE = CE;
    }

}
