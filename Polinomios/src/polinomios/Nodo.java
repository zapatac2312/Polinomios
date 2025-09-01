/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package polinomios;

public class Nodo {

    private int Coe, Exp;
    private Nodo Liga;

    public Nodo(int Coe, int Exp) {
        this.Coe = Coe;
        this.Exp = Exp;
        this.Liga = null;//Liga no se inicializa porque el valor se lo vamos a dar despues, porque es la direccion de memoria del siguiente nodo.
    }
   
    public int getCoe() {
        return Coe;
    }

    public void setCoe(int Coe) {
        this.Coe = Coe;
    }

    public int getExp() {
        return Exp;
    }

    public void setExp(int Exp) {
        this.Exp = Exp;
    }

    public Nodo getLiga() {
        return Liga;
    }

    public void setLiga(Nodo Liga) {
        this.Liga = Liga;
    }
    
    
}
