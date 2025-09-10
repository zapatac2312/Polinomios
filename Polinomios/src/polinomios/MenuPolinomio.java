package polinomios;
import java.util.Scanner;

public class MenuPolinomio {

    public void MostrarMenu(){
        LogicaPolinomios ob = new LogicaPolinomios();

        Scanner scanner = new Scanner(System.in);
        String[] polinomioVector = null;
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ DE OPERACIONES CON POLINOMIOS ---");
            System.out.println("1. Ingresar Polinomio (desde código)");
            System.out.println("2. Ingresar/Sumar un término");
            System.out.println("3. Eliminar un término");
            System.out.println("4. Evaluar el polinomio para un valor de X");
            System.out.println("5. Obtener el grado del polinomio");
            System.out.println("6. Reconstruir (Ordenar) el polinomio");
            System.out.println("7. Contar términos del polinomio");
            System.out.println("8. Mostrar Polinomio en Diferentes Formas");
            System.out.println("9. Salir");
            System.out.print("--> Digite una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el polinomio que desea operar: ");
                    String pol = scanner.nextLine();
                    polinomioVector = ob.IngresarPoli(pol);
                    polinomioVector = ob.Recortar(polinomioVector);
                    System.out.println("Polinomio inicializado y procesado:");
                    ob.imprimirVectorString(polinomioVector);
                    break;

                case 2:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        System.out.print("Ingrese el coeficiente del nuevo término: ");
                        String coef = scanner.nextLine();
                        System.out.print("Ingrese el exponente del nuevo término: ");
                        String exp = scanner.nextLine();
                        polinomioVector = ob.IngresarTermino(polinomioVector, coef, exp);
                        System.out.println("Polinomio actualizado:");
                        ob.imprimirVectorString(polinomioVector);
                    }
                    break;

                case 3:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        System.out.print("Ingrese el exponente del término a eliminar: ");
                        String expEliminar = scanner.nextLine();
                        polinomioVector = ob.EliminarTermino(polinomioVector, expEliminar);
                        System.out.println("Polinomio después de eliminar:");
                        ob.imprimirVectorString(polinomioVector);
                    }
                    break;

                case 4:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        System.out.print("Ingrese el valor de X para evaluar el polinomio: ");
                        int valorX = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                        int resultado = ob.EvaluarPolinomio(polinomioVector, valorX);
                        System.out.println("El resultado del polinomio para x = " + valorX + " es: " + resultado);
                    }
                    break;

                case 5:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        int grado = ob.ObtenerGrado(polinomioVector);
                        System.out.println("El grado del polinomio es: " + grado);
                    }
                    break;

                case 6:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        polinomioVector = ob.Reconstruir(polinomioVector);
                        System.out.println("Polinomio ordenado:");
                        ob.imprimirVectorString(polinomioVector);
                    }
                    break;

                case 7:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        int numeroDeTerminos = ob.ContarTerminos(polinomioVector);
                        System.out.println("El polinomio tiene: " + numeroDeTerminos + " término(s).");
                    }
                    break;

                case 8:
                    if (polinomioVector == null) {
                        System.out.println("Error: Primero debe ingresar un polinomio (Opción 1).");
                    } else {
                        int opcionSubMenu = 0;
                        do {
                            System.out.println("\n--- SUBMENÚ: ELEGIR FORMA DE VISUALIZACIÓN ---");
                            System.out.println("1. Mostrar en Forma 1");
                            System.out.println("2. Mostrar en Forma 2");
                            System.out.println("3. Mostrar en Forma 3");
                            System.out.println("4. Volver al menú principal");
                            System.out.print("--> Digite una opción: ");

                            opcionSubMenu = scanner.nextInt();
                            scanner.nextLine();

                            switch(opcionSubMenu) {
                                case 1:
                                    System.out.println("\n--- Representación en Forma 1 ---");
                                    Forma1 Vp1= new Forma1(ob.ObtenerGrado(polinomioVector));
                                    Vp1.ConstruirVPF1(polinomioVector);
                                    Vp1.imprimirVectorForma1_2();
                                    int opcionSubMenuForma1 = 0;
                                    do {
                                        System.out.println("\n--- SUBMENÚ-FORMA 1: ELEGIR FORMA DE OPERACIÓN ---");
                                        System.out.println("1. Sumar forma 1");
                                        System.out.println("2. Multiplicar forma 1");
                                        System.out.print("--> Digite una opción: ");

                                        opcionSubMenuForma1 = scanner.nextInt();
                                        scanner.nextLine();

                                        switch(opcionSubMenuForma1) {
                                            case 1:
                                                System.out.println("\n--- Suma en Forma 1 ---");
                                                Vp1.SumarPoli(ob.operarSegundoPoli());
                                                Vp1.imprimirVectorForma1Operqado();
                                                break;
                                            case 2:
                                                System.out.println("\n--- Multiplicacion en Forma 1 ---");
                                                Vp1.MultiplicarPoli(ob.operarSegundoPoli());
                                                Vp1.imprimirVectorForma1Operqado();
                                                break;
                                            default:
                                                System.out.println("Opción no válida.");
                                                break;
                                        }
                                    } while (opcionSubMenuForma1 != 2);

                                    break;
                                case 2:
                                    System.out.println("\n--- Representación en Forma 2 ---");
                                    Forma2 Vp2= new Forma2(ob.ContarTerminos(polinomioVector));
                                    Vp2.ConstruirVPF2(polinomioVector);
                                    Vp2.imprimirVectorForma1_2();

                                    int opcionSubMenuForma2 = 0;
                                    do {
                                        System.out.println("\n--- SUBMENÚ-FORMA 2: ELEGIR FORMA DE OPERACIÓN ---");
                                        System.out.println("1. Sumar forma 2");
                                        System.out.println("2. Multiplicar forma 2");
                                        System.out.print("--> Digite una opción: ");

                                        opcionSubMenuForma2 = scanner.nextInt();
                                        scanner.nextLine();

                                        switch(opcionSubMenuForma2) {
                                            case 1:
                                                System.out.println("\n--- Suma en Forma 2 ---");
                                                Vp2.SumarPoli2(ob.operarSegundoPoli());
                                                Vp2.imprimirVectorForma2Operqado();
                                                break;
                                            case 2:
                                                System.out.println("\n--- Multiplicacion en Forma 2 ---");
                                                Vp2.MultiplicarPoli(ob.operarSegundoPoli());
                                                Vp2.imprimirVectorForma2Operqado();
                                                break;
                                            default:
                                                System.out.println("Opción no válida.");
                                                break;
                                        }
                                    } while (opcionSubMenuForma2 != 2);

                                    break;
                                case 3:
                                    System.out.println("\n--- Representación en Forma 3 ---");
                                    Forma3 Vp3 = new Forma3();
                                    Vp3.Construir(polinomioVector);
                                    Vp3.imprimirVectorForma3();

                                    int opcionSubMenuForma3 = 0;
                                    do {
                                        System.out.println("\n--- SUBMENÚ-FORMA 3: ELEGIR FORMA DE OPERACIÓN ---");
                                        System.out.println("1. Sumar forma 3");
                                        System.out.println("2. Multiplicar forma 3");
                                        System.out.print("--> Digite una opción: ");

                                        opcionSubMenuForma3 = scanner.nextInt();
                                        scanner.nextLine();

                                        switch(opcionSubMenuForma3) {
                                            case 1:
                                                System.out.println("\n--- Suma en Forma 3 ---");
                                                Vp3.SumarPoli3(ob.operarSegundoPoli());
                                                Vp3.setPunta(null);
                                                Vp3.Construir(Vp3.getVs());
                                                Vp3.imprimirVectorForma3();
                                                break;
                                            case 2:
                                                System.out.println("\n--- Multiplicacion en Forma 3 ---");
                                                Vp3.MultiplicarPoli(ob.operarSegundoPoli());
                                                Vp3.setPunta(null);
                                                Vp3.Construir(Vp3.getVs());
                                                Vp3.imprimirVectorForma3();
                                                break;
                                            default:
                                                System.out.println("Opción no válida.");
                                                break;
                                        }
                                    } while (opcionSubMenuForma3 != 2);

                                    break;
                                case 4:
                                    System.out.println("Volviendo al menú principal...");
                                    break;
                                default:
                                    System.out.println("Opción no válida.");
                                    break;
                            }
                        } while (opcionSubMenu != 4);
                    }
                    break;

                case 9:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        } while (opcion != 9);

        scanner.close();
    }
}
