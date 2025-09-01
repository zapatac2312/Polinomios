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

    public int[] getVPF1() {
        return VPF2;
    }

    public void setVPF1(int[] VPF1) {
        this.VPF2 = VPF1;
    }

    public int getVPF1(int i) {
        return VPF2[i];
    }

    public void setVPF1(int d, int i) {
        this.VPF2[i] = d;
    }
    
    public void Construir(String []Vs){
    
        int i=1,k=2; //la i se inicializa en 1 porque el vector String tiene su primer Coeficiente en la posicion 0 y el parametro es Vs[i-1] que es igual a 0 y el exponente esta en la posicion 1 que es igual a Vs[i]
                     // La k inicia en 2 porque en el VPF2 el primer exponente esta en la posicion 2, la posicion 0 es la cantidad de terminos, la 1 es el ptimer coeficiente y 2 es el primer exponente, para luego evaluar 1 y 2
        while(Vs[i]!=null){
            VPF2[k-1]= Integer.parseInt(Vs[i-1]);//Coe
            VPF2[k]= Integer.parseInt(Vs[i]);//Exp
            k+=2; 
            i+=2;
            //El iterador salta de 2 en 2, el metodo construir llama a insertar al final, insertar al final inserte los 2 primeros datos del vector, i empieza en 1 e inserta la posicion 0 y 1, luego se le suma 2, i empieza en 3 e insera la posicion 2 y 3, y así sucesivamente.
        }
    }
}
