package polinomios;

public class Forma3 {

    Nodo punta;

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
        for (int i = 0; i < Vs.length; i += 2) {

            int coeficiente = Integer.parseInt(Vs[i]);
            int exponente = Integer.parseInt(Vs[i + 1]);

            InsertarAlFinal(coeficiente, exponente);
        }
    }

    public void imprimirVectorForma3() {
        if (punta == null) {
            System.out.println("El polinomio está vacío.");
            return; // Termina el método si no hay nodos que mostrar
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
}
