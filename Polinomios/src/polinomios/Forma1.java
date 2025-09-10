package polinomios;

import java.util.Objects;
import java.util.Scanner;

import static polinomios.Main.*;

public class Forma1 {
//atributos
    private int Du, VPF1[], VPF1Operado[];
    private String Vs[];
    boolean multiplicado, sumado;
//metodos

    public Forma1() {
        Du = 0;
    }

    public Forma1(int grado){
        Du = grado +1;
        VPF1 = new int[Du+1];
        VPF1[0]= grado;
        multiplicado = false;
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
        LogicaPolinomios ob= new LogicaPolinomios();
        int contador = 1;
        int grado;
        this.Vs = Vs;

        if (multiplicado || sumado){
            grado = ob.ObtenerGrado(Vs);
            VPF1Operado = new int [grado+2];
            VPF1Operado[0]= grado;

            for (int i = 1; i < Vs.length; i+=2) {
                if (Integer.parseInt(Vs[i])==grado){
                    VPF1Operado[contador] = Integer.parseInt(Vs[i-1]);
                }else{
                    VPF1Operado[contador] = 0;
                    i-=2;
                }
                grado--;
                contador++;
            }
        }else {
            grado = ob.ObtenerGrado(Vs);
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

    public void imprimirVectorForma1Operqado() {
        if (VPF1Operado == null) {
            System.out.println("El vector está vacío (null).");
            return;
        }
        System.out.print("Vector: ");
        for (int numero : VPF1Operado) {
            System.out.print("| " + numero + " |");
        }
        System.out.println();
    }

    public String[] SumarPoli(String[] Vs2) {
        LogicaPolinomios ob= new LogicaPolinomios();
        String[] resultadoTemp = new String[Vs.length + Vs2.length];

        for (int i = 0; i < Vs.length; i++) {
            resultadoTemp[i] = Vs[i];
        }

        int tamañoActual = Vs.length;

        for (int j = 0; j < Vs2.length; j += 2) {
            String coefVs2 = Vs2[j];
            String expVs2 = Vs2[j + 1];
            boolean exponenteEncontrado = false;

            for (int i = 1; i < tamañoActual; i += 2) {
                if (Objects.equals(resultadoTemp[i], expVs2)) {
                    int coefEnResultado = Integer.parseInt(resultadoTemp[i - 1]);
                    int coefNuevo = Integer.parseInt(coefVs2);
                    resultadoTemp[i - 1] = String.valueOf(coefEnResultado + coefNuevo);
                    exponenteEncontrado = true;
                    break;
                }
            }
            if (!exponenteEncontrado) {
                resultadoTemp[tamañoActual] = coefVs2;
                resultadoTemp[tamañoActual + 1] = expVs2;
                tamañoActual += 2;
            }
        }
        String[] resultadoFinal = new String[tamañoActual];
        for (int i = 0; i < tamañoActual; i++) {
            resultadoFinal[i] = resultadoTemp[i];
        }
        sumado = true;
        ConstruirVPF1(ob.Reconstruir(resultadoFinal));
        return resultadoFinal;
    }

    public String[] MultiplicarPoli(String[] Vs2) {

        String[] resultadoTemp = new String[Vs.length * Vs2.length];
        LogicaPolinomios ob= new LogicaPolinomios();
        int tamañoActual = 0;

        for (int i = 0; i < Vs.length; i += 2) {
            int coef1 = Integer.parseInt(Vs[i]);
            int exp1 = Integer.parseInt(Vs[i + 1]);

            for (int j = 0; j < Vs2.length; j += 2) {
                int coef2 = Integer.parseInt(Vs2[j]);
                int exp2 = Integer.parseInt(Vs2[j + 1]);

                int nuevoCoef = coef1 * coef2;
                int nuevoExp = exp1 + exp2;

                boolean exponenteEncontrado = false;
                for (int k = 1; k < tamañoActual; k += 2) {
                    if (Integer.parseInt(resultadoTemp[k]) == nuevoExp) {
                        int coefExistente = Integer.parseInt(resultadoTemp[k - 1]);
                        resultadoTemp[k - 1] = String.valueOf(coefExistente + nuevoCoef);
                        exponenteEncontrado = true;
                        break;
                    }
                }
                if (!exponenteEncontrado) {
                    resultadoTemp[tamañoActual] = String.valueOf(nuevoCoef);
                    resultadoTemp[tamañoActual + 1] = String.valueOf(nuevoExp);
                    tamañoActual += 2;
                }
            }
        }
        String[] resultadoFinal = new String[tamañoActual];
        for (int i = 0; i < tamañoActual; i++) {
            resultadoFinal[i] = resultadoTemp[i];
        }
        multiplicado = true;
        ConstruirVPF1( ob.AgruparTerminosSemejantes(ob.Reconstruir(resultadoFinal)));
        return Vs;
    }
}

