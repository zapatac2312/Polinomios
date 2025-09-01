
package polinomios;

public class Main {
    public static void main(String[] args) {

        int opc =0;
        Forma1 F1;

        IngresarPoli();
    }

    public static String[] IngresarPoli(){
        //Scanner sc= new Scanner(System.in);

        String s="",pol = "-9x^6+12x^4-x^2+1";
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
                        if( i + 1 == Vc.length || Vc[i+1] == '-' ){
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
                                //Este condicional valida que el string s solamente tenga un -, en el caso de que
                            }else if(s.isBlank()){
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
}

//Hacer un menu con switch para las operaciones del polinomio