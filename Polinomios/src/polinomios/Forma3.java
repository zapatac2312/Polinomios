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

    public void InsertarAlFinal(int Coe, int Exp){
        
        Nodo p, x = new Nodo(Coe, Exp); // P no se instance como new Nodo, porque en caso de hacerlo se crearia todo el objeto, y ese objeto luego no se va usar, en cambio si no se inicilaiza, el P es = a null
                                        //al ser igual a null y luego asignarle un valor estamos ahorrando memoria
        if(punta==null ){
            punta = x; 
        }else{
            p=punta; //P que es el iterador de los Nodos, se le asigna el valor de la punta p para iterar desde el inicio y asignar el valor de memoria del siguiente nodo y enlazarlo
            while(p.getLiga()==null){
                //Este while se usa solo para avanzar hasta el ultimo nodo, nodo que tiene Liga = null
                p = p.getLiga();
            }
            //Cuando salimos del ciclo y estamos parado en el ultimo se cierra el ciclo y se liga con el nuevo,con X
            p.setLiga(x);
        }
    }
    
    public void Construir(String []Vs){
    
        int i=1; //la i se inicializa en 1 porque el vector tiene su primer Coeficiente en la posicion 0 y el parametro es Vs[i-1] que es igual a 0 y el exponente esta en la posicion 1 que es igual a Vs[i]
        
        while(Vs[i]!=null){
            InsertarAlFinal(Integer.parseInt(Vs[i-1]),Integer.parseInt(Vs[i]));
            i+=2;
            //El iterador salta de 2 en 2, el metodo construir llama a insertar al final, insertar al final inserte los 2 primeros datos del vector, i empieza en 1 e inserta la posicion 0 y 1, luego se le suma 2, i empieza en 3 e insera la posicion 2 y 3, y así sucesivamente.
        }
    }
}
