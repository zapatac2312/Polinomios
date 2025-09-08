package polinomios;


import java.util.Objects;

public class Forma2 {

    private int Du, VPF2[], VPF2Operado[];
    private String Vs[];
    public Forma2() {
        Du = 0;
    }

    public Forma2(int terminos) {  //Forma 2 usa la cantidad de terminos
        Du = terminos *2;
        VPF2 = new int[Du+1];
        VPF2[0]= terminos;
    }

    public int getDu() {
        return Du;
    }

    public void setDu(int du) {
        Du = du;
    }

    public int[] getVPF2() {
        return VPF2;
    }

    public void setVPF2(int[] VPF1) {
        this.VPF2 = VPF1;
    }

    public int getVPF2(int i) {
        return VPF2[i];
    }

    public void setVPF2(int d, int i) {
        this.VPF2[i] = d;
    }
    
    public void ConstruirVPF2(String []Vs){
        this.Vs = Vs;
        for (int i = 0; i < Vs.length; i++) {
                VPF2[i + 1] = Integer.parseInt(Vs[i]);
        }
    }

    public void imprimirVectorForma1_2() {
        if (VPF2 == null) {
            System.out.println("El vector está vacío (null).");
            return;
        }
        System.out.print("Vector: ");
        for (int numero : VPF2) {
            System.out.print("| " + numero + " |");
        }
        System.out.println();
    }

    public void imprimirVectorForma2Operqado() {
        if (Vs == null) {
            System.out.println("El vector está vacío (null).");
            return;
        }
        System.out.print("Vector: ");
        for (String numero : Vs) {
            System.out.print("| " + numero + " |");
        }
        System.out.println();
    }


    public String[] SumarPoli2(String[] Vs2) {
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
        this.Vs = new String[tamañoActual+1];
        int numeroDeTerminos=ob.ContarTerminos(resultadoTemp);
        Vs[0]= String.valueOf(numeroDeTerminos);
        resultadoTemp = ob.Reconstruir(resultadoTemp);

        for (int i = 1; i < tamañoActual+1; i++) {
            Vs[i] = resultadoTemp[i-1];
        }
        return Vs;
    }
}
