package polinomios;

import static polinomios.Main.*;

public class Forma1 {
//atributos
    private int Du, VPF1[];
//metodos

    public Forma1() {
        Du = 0;
    }

    public Forma1(int grado){
        Du = grado +1;
        VPF1 = new int[Du+1];
        VPF1[0]= grado;
    }

    public int getDu() {
        return Du;
    }

    public void setDu(int du) {
        Du = du;
    }

    public int[] getVPF1() {
        return VPF1;
    }

    public void setVPF1(int[] VPF1) {
        this.VPF1 = VPF1;
    }

    public int getVPF1(int i) {
        return VPF1[i];
    }

    public void setVPF1(int d, int i) {
        this.VPF1[i] = d;
    }

    public void ConstruirVPF1(String Vs[]) {
        int contador = 1;
        int grado= this.Du-1;

        for (int i = 1; i < Vs.length; i+=2) {
            if (Integer.parseInt(Vs[i])==grado){
                VPF1[contador] = Integer.parseInt(Vs[i-1]);
            }else{
                VPF1[contador] = 0;
                i-=2;
            }
            grado--;
            contador++;
        }
    }
    public void imprimirVectorForma1_2() {
        if (VPF1 == null) {
            System.out.println("El vector está vacío (null).");
            return;
        }
        System.out.print("Vector: ");
        for (int numero : VPF1) {
            System.out.print("| " + numero + " |");
        }
        System.out.println();
    }
}

