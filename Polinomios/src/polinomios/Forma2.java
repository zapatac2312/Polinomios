package polinomios;


public class Forma2 {

    private int Du, VPF2[];
    
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
    
    public void Construir(String []Vs){
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
}
