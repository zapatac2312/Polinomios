package polinomios;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int opc =0;
        Forma1 F1;
        IngresarTermino(Reconstruir(Recortar(IngresarPoli())),"45","8");
    }

    public static String[] IngresarPoli(){
        //Scanner sc= new Scanner(System.in);

        String s="",pol = "5x^4+2x^6-10x+7+34x^8-23x^2+45x^3";
        int j=0;
        char Vc[] =pol.toCharArray();
        String Vs[] = new String[Vc.length];

        for (int i = 0; i < Vc.length; i++) {
            System.out.print("| "+Vc[i]+" |");
        }

        for (int i = 0; i < Vc.length; i++) {
            if (Vc[i]!='+'){
            //El primer condicional válida que el término no sea un +, pues no tenemos que concatenar el + en el Vs, asi que cuando haya un + el bucle simplemente avanzamos una posicion.
                if (Vc[i]=='-'|| Character.isDigit(Vc[i]))
                //Aquí verificamos si el caractér a evaluar es un - o un dígito, pues estos sí los tenemos que concatenar
                {
                    if (Character.isDigit(Vc[i])){
                        if( i + 1 == Vc.length || Vc[i+1] == '-'|| Vc[i+1] == '+' ){
                            //Luego en caso de que sea en un dígito, verificamos primero si alcanzamos el final del vector Vc, en caso de que sí o en caso de que el siguiente término sea un signo -, vamos a cortar la cadena y anexarla a Vs
                            s= s+Vc[i];
                            Vs[j]=s;
                            j++;
                            s="";
                            Vs[j]= "0";
                            j++;
                            //En caso de que el vector se acabe o que haya un signo -, eso quiere decir que el valor del exponente para ese coeficiente es 0, asi que lo guardamos en Vs y avanzamos J una iteracion
                        }else{
                            s= s+Vc[i];
                            //Si es un entero, pero no cumple con las condicion de que se acabe el vector en la siguiente operacion o que el siguiente término sea un -, simplemente lo guardamos en el string s
                        }
                    } else{
                        s= s+Vc[i];
                        //Si es un signo - o un entero en el que la posicion siguiente de ese entero no sea un - o el final del vector, vamos a guardar la cadena en s
                    }
                }else{
                    //Entramos a este else en caso de que el valor no sea uno que tengamos que concatenar y anexar, en este caso un - o un entero. Más abajo vamos a verificar el simbolo ^
                    if (Vc[i]=='x' || Vc[i]=='X' || Vc[i]=='+'){
                        if(!s.isBlank()){
                            //En caso de que sea una x el valor que estamos evaluando y que el string S no este vacio, lo anexamos a Vs
                            if (s.equals("-") && !Character.isDigit(Vc[i])){
                                Vs[j]=s+"1";
                                j++;
                                s="";
                                //Este condicional valida que el string s solamente tenga un - y el siguiente caracter no sea un entero, es ese caso significa que la x tiene 1 como coeficiente
                            }else if(s.isBlank()){
                                //Este condicional valida que la x esté sola, sin signo negativo ni un entero antes para expresar un coeficiente mayor a 1, en ese caso significa que la x tiene 1 como coeficiente
                                Vs[j]=s+"1";
                                j++;
                                s="";
                            }else{
                                Vs[j]=s;
                                j++;
                                s="";
                            }
                        }
                        if (Vc[i]=='x' || Vc[i]=='X'){
                            if (i + 1 == Vc.length || Vc[i+1]=='-'|| Vc[i+1]=='+'){
                                //En caso de que sea una x el valor que estamos evaluando, primero evaluamos que esa X sea el último valor del arreglo, luego evaluamos que el siguiente valor sea un signo que indique el inicio de otro termino, en caso de que alguna de esas condiciones se cumpla significa que el grado del término que estamos evaluando es 1 y lo guardamos en la siguiente posicion de Vs
                                Vs[j]="1";
                                j++;
                            }
                        }
                    }
                    if(Vc[i]=='^' && Character.isDigit(Vc[i+1])){
                        //En caso de que sea un ^ y el siguiente valor sea un dígito, anexamos el siguiente valor y avanzamos el bucle.
                        Vs[j]=Character.toString(Vc[i+1]);
                        j++;
                        i++;
                    }
                }
            }
        }
        System.out.println("\n");
        for (int i = 0; i < Vc.length; i++) {
            System.out.print("| "+Vs[i]+" |");
        }
        return Vs;
    }

    public static String[] Recortar(String[] VsInicial){

        int contador= 0;
        for (int i = 0; i < VsInicial.length; i++) {
            if (VsInicial[i] != null ){
                contador ++;
            }
        }
        System.out.println("\n");
        String[] VsFinal = new String[contador];
        for (int i = 0; i < contador; i++) {
           VsFinal[i]= VsInicial[i];
           System.out.print("| "+VsFinal[i]+" |");
        }
        return VsFinal;
    }

    public static String[] Reconstruir(String[] VsInicial){
        //int contador=0;
        String tempExp;
        for (int i = 0; i < VsInicial.length; i++) {
            for (int j = 1; j < VsInicial.length-2; j+=2) {
                if (Integer.parseInt(VsInicial[j]) < Integer.parseInt(VsInicial[j+2])){
                        tempExp = VsInicial[j+2];
                        VsInicial[j+2]= VsInicial[j];
                        VsInicial[j]= tempExp;

                        String tempCoe = VsInicial[j+1];
                        VsInicial[j+1]=VsInicial[j-1];
                        VsInicial[j-1]= tempCoe;
                }
            }
        }
        System.out.println("\n");
        for (String s : VsInicial) {
            System.out.print("| " + s + " |");
        }
        return VsInicial;
    }

    public static String[] IngresarTermino(String[] VsInicial, String coeficiente, String exponente){
        String[] VsFinal = null;
        boolean reconstruir = false;

        for (int i = 1; i < VsInicial.length; i+=2) {
            if(Objects.equals(VsInicial[i], exponente)){
                int numero1 = Integer.parseInt(VsInicial[i-1]);
                int numero2 = Integer.parseInt(coeficiente);
                int resultado = numero2+numero1;

                VsInicial[i-1] = String.valueOf(resultado);
                VsFinal = new String[VsInicial.length];
                for (int j = 0; j < VsInicial.length; j++) {
                    VsFinal[j]= VsInicial[j];
                }
                break;
            }else{
                reconstruir = true;
                VsFinal = new String[VsInicial.length+2];
                VsFinal[VsFinal.length-1]=exponente;
                VsFinal[VsFinal.length-2]=coeficiente;
                for (int j = 0; j < VsInicial.length; j++) {
                    VsFinal[j]= VsInicial[j];
                }
            }
        }
        if (reconstruir){
            Reconstruir(VsFinal);
        }
        System.out.println("\n");
        for (String s : VsFinal) {
            System.out.print("| " + s + " |");
        }
        return VsFinal;
    }
}

//Hacer un menu con switch para las operaciones del polinomio