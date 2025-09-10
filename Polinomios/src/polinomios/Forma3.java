package polinomios;

import java.util.Objects;

public class Forma3 {

    Nodo punta;
    private String[] Vs;

    public String[] getVs() {
        return Vs;
    }

    public void setVs(String[] vs) {
        Vs = vs;
    }

    public Forma3() {
        this.punta = null;
    }

    public Nodo getPunta() {
        return punta;
    }

    public void setPunta(Nodo punta) {
        this.punta = punta;
    }

    public void InsertarAlFinal(int Coe, int Exp) {

        Nodo x = new Nodo(Coe, Exp);

        if (punta == null) {
            punta = x;
        } else {
            Nodo p = punta;
            while (p.getLiga() != null) {
                p = p.getLiga();
            }
            p.setLiga(x);
        }
    }

    public void Construir(String[] Vs) {
           this.Vs=Vs;

        for (int i = 0; i < Vs.length; i += 2) {

            int coeficiente = Integer.parseInt(Vs[i]);
            int exponente = Integer.parseInt(Vs[i + 1]);

            InsertarAlFinal(coeficiente, exponente);
        }
    }

    public void imprimirVectorForma3() {
        if (punta == null) {
            System.out.println("El polinomio está vacío.");
            return;
        }
        Nodo p = punta;

        System.out.print("Vector: ");
        while (p != null) {
            System.out.print("| " + p.getCoe() + " |");
            System.out.print(" " + p.getExp() + " |");
            p = p.getLiga();
        }
        System.out.println();
    }
    public void imprimirVectorForma3Operqado() {
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

    public String[] SumarPoli3(String[] Vs3) {
        LogicaPolinomios ob= new LogicaPolinomios();
        String[] resultadoTemp = new String[Vs.length + Vs3.length];

        for (int i = 0; i < Vs.length; i++) {
            resultadoTemp[i] = Vs[i];
        }

        int tamañoActual = Vs.length;

        for (int j = 0; j < Vs3.length; j += 2) {
            String coefVs2 = Vs3[j];
            String expVs2 = Vs3[j + 1];
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
        Vs = new String[tamañoActual];
        resultadoTemp = ob.Reconstruir(resultadoTemp);

        for (int i = 0; i < tamañoActual; i++) {
            Vs[i] = resultadoTemp[i];
        }
        return resultadoTemp;
    }

    public String[] MultiplicarPoli(String[] Vs3) {

        String[] resultadoTemp = new String[Vs.length * Vs3.length];
        LogicaPolinomios ob= new LogicaPolinomios();
        int tamañoActual = 0;

        for (int i = 0; i < Vs.length; i += 2) {
            int coef1 = Integer.parseInt(Vs[i]);
            int exp1 = Integer.parseInt(Vs[i + 1]);

            for (int j = 0; j < Vs3.length; j += 2) {
                int coef2 = Integer.parseInt(Vs3[j]);
                int exp2 = Integer.parseInt(Vs3[j + 1]);

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
        Vs = new String[tamañoActual];
        resultadoTemp = ob.Reconstruir(resultadoTemp);
        resultadoTemp = ob.AgruparTerminosSemejantes(resultadoTemp);

        for (int i = 0; i < tamañoActual; i++) {
            Vs[i] = resultadoTemp[i];
        }
        return resultadoTemp;
    }

}
