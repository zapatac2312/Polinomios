package polinomios;
import java.util.Objects;
import java.util.Scanner;

public class LogicaPolinomios {

    public String[] IngresarPoli(String pol) {

        String s = "";
        int j = 0;
        char Vc[] = pol.toCharArray();
        String Vs[] = new String[Vc.length+1];

        for (int i = 0; i < Vc.length; i++) {
            if (Vc[i] != '+') {
                //El primer condicional válida que el término no sea un +, pues no tenemos que concatenar el + en el Vs, asi que cuando haya un + el bucle simplemente avanzamos una posicion.
                if (Vc[i] == '-' || Character.isDigit(Vc[i]))
                //Aquí verificamos si el caractér a evaluar es un - o un dígito, pues estos sí los tenemos que concatenar
                {
                    if (Character.isDigit(Vc[i])) {
                        if (i + 1 == Vc.length || Vc[i + 1] == '-' || Vc[i + 1] == '+') {
                            //Luego en caso de que sea en un dígito, verificamos primero si alcanzamos el final del vector Vc, en caso de que sí o en caso de que el siguiente término sea un signo -, vamos a cortar la cadena y anexarla a Vs
                            s = s + Vc[i];
                            Vs[j] = s;
                            j++;
                            s = "";
                            Vs[j] = "0";
                            j++;
                            //En caso de que el vector se acabe o que haya un signo -, eso quiere decir que el valor del exponente para ese coeficiente es 0, asi que lo guardamos en Vs y avanzamos J una iteracion
                        } else {
                            s = s + Vc[i];
                            //Si es un entero, pero no cumple con las condicion de que se acabe el vector en la siguiente operacion o que el siguiente término sea un -, simplemente lo guardamos en el string s
                        }
                    } else {
                        s = s + Vc[i];
                        //Si es un signo - o un entero en el que la posicion siguiente de ese entero no sea un - o el final del vector, vamos a guardar la cadena en s
                    }
                } else {
                    //Entramos a este else en caso de que el valor no sea uno que tengamos que concatenar y anexar, en este caso un - o un entero. Más abajo vamos a verificar el simbolo ^
                    if (Vc[i] == 'x' || Vc[i] == 'X') {
                        if (!s.isBlank()) {
                            //En caso de que sea una x el valor que estamos evaluando y que el string S no este vacio, lo anexamos a Vs
                            if (s.equals("-") && !Character.isDigit(Vc[i])) {
                                Vs[j] = s + "1";
                                j++;
                                s = "";
                                //Este condicional valida que el string s solamente tenga un - y el siguiente caracter no sea un entero, es ese caso significa que la x tiene 1 como coeficiente
                            } else if( Vc[i + 1] == '-' || Vc[i + 1] == '+' ){
                                Vs[j] = s;
                                Vs[j+1] = "1";
                                j+=2;
                                s = "";
                            }else{
                                Vs[j] = s;
                                j++;
                                s = "";
                            }
                        }else {
                            //Este condicional valida que la x esté sola, sin signo negativo ni un entero antes para expresar un coeficiente mayor a 1, en ese caso significa que la x tiene 1 como coeficiente
                            if (i + 1 == Vc.length || Vc[i + 1] == '-' || Vc[i + 1] == '+' ){
                                Vs[j] = "1";
                                Vs[j+1]="1";
                                j+=2;
                            }else {
                                Vs[j] = s + "1";
                                j++;
                                s = "";
                            }
                        }
                    }
                    if (Vc[i] == '^' && Character.isDigit(Vc[i + 1])) {
                        //En caso de que sea un ^ y el siguiente valor sea un dígito, anexamos el siguiente valor y avanzamos el bucle.
                        Vs[j] = Character.toString(Vc[i + 1]);
                        j++;
                        i++;
                    }
                }
            }
        }
        return Reconstruir(Vs);
    }

    public String[] Recortar(String[] VsInicial) {

        int contador = 0;
        for (int i = 0; i < VsInicial.length; i++) {
            if (VsInicial[i] != null) {
                contador++;
            }
        }
        String[] VsFinal = new String[contador];
        for (int i = 0; i < contador; i++) {
            VsFinal[i] = VsInicial[i];
        }
        return VsFinal;
    }

    public String[] Reconstruir(String[] VsInicial) {
        String[] VsFinal = Recortar(VsInicial);
        String tempExp;
        for (int i = 0; i < VsFinal.length; i++) {
            for (int j = 1; j < VsFinal.length - 2; j += 2) {
                if (Integer.parseInt(VsFinal[j]) < Integer.parseInt(VsFinal[j + 2])) {
                    tempExp = VsFinal[j + 2];
                    VsFinal[j + 2] = VsFinal[j];
                    VsFinal[j] = tempExp;

                    String tempCoe = VsFinal[j + 1];
                    VsFinal[j + 1] = VsFinal[j - 1];
                    VsFinal[j - 1] = tempCoe;
                }
            }
        }
        return VsFinal;
    }

    public String[] IngresarTermino(String[] VsInicial, String coeficiente, String exponente) {
        String[] VsFinal = null;
        boolean reconstruir = false;

        for (int i = 1; i < VsInicial.length; i += 2) {
            if (Objects.equals(VsInicial[i], exponente)) {
                int numero1 = Integer.parseInt(VsInicial[i - 1]);
                int numero2 = Integer.parseInt(coeficiente);
                int resultado = numero2 + numero1;

                VsInicial[i - 1] = String.valueOf(resultado);
                VsFinal = new String[VsInicial.length];
                for (int j = 0; j < VsInicial.length; j++) {
                    VsFinal[j] = VsInicial[j];
                }
                break;
            } else {
                reconstruir = true;
                VsFinal = new String[VsInicial.length + 2];
                VsFinal[VsFinal.length - 1] = exponente;
                VsFinal[VsFinal.length - 2] = coeficiente;
                for (int j = 0; j < VsInicial.length; j++) {
                    VsFinal[j] = VsInicial[j];
                }
            }
        }
        if (reconstruir) {
            Reconstruir(VsFinal);
        }
        return VsFinal;
    }

    public int ObtenerGrado(String[] Vs) {
        int mayorExponente = Integer.parseInt(Vs[1]);

        for (int j = 1; j < Vs.length; j += 2) {
            int exponenteActual = Integer.parseInt(Vs[j]);
            if (exponenteActual > mayorExponente) {
                mayorExponente = exponenteActual;
            }
        }
        return mayorExponente;
    }

    public String[] EliminarTermino(String[] VsInicial, String exponente) {
        String[] VsFinal = null;
        boolean reconstruir = false;
        String temp = "";

        for (int i = 1; i < VsInicial.length; i += 2) {
            if (Objects.equals(VsInicial[i], exponente)) {

                VsFinal = new String[VsInicial.length];
                temp = VsInicial[VsInicial.length - 2];
                VsInicial[VsInicial.length - 2] = null;
                VsInicial[i - 1] = temp;

                temp = VsInicial[VsInicial.length - 1];
                VsInicial[VsInicial.length - 1] = null;
                VsInicial[i] = temp;

                reconstruir = true;
                break;
            }
        }
        for (int i = 0; i < VsInicial.length; i++) {
            VsFinal[i] = VsInicial[i];
        }
        if (reconstruir) {
            Reconstruir(VsFinal);
        } else {
            System.out.println("El termino a Eliminar no existe dentro del vector");
        }
        return VsFinal;
    }

    public int EvaluarPolinomio(String[] Vs, int valor) {
        int resultado = 0, num1 = 0, num2 = 0;

        for (int i = 1; i < Vs.length; i += 2) {
            num1 = Integer.parseInt(Vs[i - 1]);
            num2 = Integer.parseInt(Vs[i]);
            resultado += (int) (num1 * Math.pow(valor, num2));
        }

        return resultado;
    }

    public void imprimirVectorString(String[] vector) {
        if (vector == null) {
            System.out.println("El vector está vacío (null).");
            return;
        }
        System.out.print("Vector: ");
        for (String s : vector) {
            System.out.print("| " + s + " |");
        }
        System.out.println();
    }

    public int ContarTerminos(String[] Vs){
        if (Vs == null || Vs.length == 0) {
            return 0;
        }
        return Vs.length / 2;
    }

    public String[] AgruparTerminosSemejantes(String[] Vs) {
        if (Vs == null || Vs.length == 0) {
            return new String[0];
        }
        LogicaPolinomios ob = new LogicaPolinomios();

        String[] resultadoTemp = new String[Vs.length];
        int tamañoActual = 0;

        for (int i = 0; i < ob.ContarTerminos(Vs)*2; i += 2) {
            String coefActual = Vs[i];
            String expActual = Vs[i + 1];
            boolean exponenteEncontrado = false;

            for (int k = 1; k < tamañoActual; k += 2) {
                if (Objects.equals(resultadoTemp[k], expActual)) {
                    int coefExistente = Integer.parseInt(resultadoTemp[k - 1]);
                    int coefNuevo = Integer.parseInt(coefActual);

                    resultadoTemp[k - 1] = String.valueOf(coefExistente + coefNuevo);

                    exponenteEncontrado = true;
                    break;
                }
            }
            if (!exponenteEncontrado) {
                resultadoTemp[tamañoActual] = coefActual;
                resultadoTemp[tamañoActual + 1] = expActual;
                tamañoActual += 2;
            }
        }
        String[] resultadoFinal = new String[tamañoActual];
        for (int i = 0; i < tamañoActual; i++) {
            resultadoFinal[i] = resultadoTemp[i];
        }
        return resultadoFinal;
    }

    public String[] operarSegundoPoli(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el segundo polinomio que desea operar : ");
        String pol2 = scanner.nextLine();

        LogicaPolinomios ob= new LogicaPolinomios();
        return ob.Reconstruir(ob.Recortar(ob.IngresarPoli(pol2)));
    }

}
