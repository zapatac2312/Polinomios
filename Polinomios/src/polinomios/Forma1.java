package polinomios;

public class Forma1 {
//atributos
    private int Du, VPF1[];
//metodos

    public Forma1() {
        Du = 0;
    }

    public Forma1(int grado) {
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
}

