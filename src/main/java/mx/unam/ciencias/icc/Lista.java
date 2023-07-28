package mx.unam.ciencias.icc;

import java.util.NoSuchElementException;

/**
 * <p>Clase para listas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas son iterables utilizando sus nodos. Las listas no aceptan a
 * <code>null</code> como elemento.</p>
 */
public class Lista {

    /**
     * Clase interna para nodos.
     */
    public class Nodo {

        /* El elemento del nodo. */
        private Object elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(Object elemento) {
            this.elemento = elemento; 
        }

        /**
         * Regresa el nodo anterior del nodo.
         * @return el nodo anterior del nodo.
         */
        public Nodo getAnterior() {
            return anterior; 
        }

        /**
         * Regresa el nodo siguiente del nodo.
         * @return el nodo siguiente del nodo.
         */
        public Nodo getSiguiente() {
            return siguiente; 
        }

        /**
         * Regresa el elemento del nodo.
         * @return el elemento del nodo.
         */
        public Object get() {
            return elemento; 
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud = 0;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        if(cabeza == null)
            return true; 

        return false; 
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaFinal(Object elemento) {

        if (elemento == null)
            throw new IllegalArgumentException();

        Nodo nodo = new Nodo(elemento);
        longitud++; 
        if(esVacia()){
            cabeza = nodo; 
            rabo = nodo;
        }
        else{
            rabo.siguiente = nodo; 
            nodo.anterior = rabo; 
            rabo = nodo; 
        }
        return; 
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar. El elemento se agrega únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void agregaInicio(Object elemento) {

        if (elemento == null)
            throw new IllegalArgumentException();

        Nodo nodo = new Nodo(elemento); 
        longitud++; 
        if(esVacia()){
            cabeza = nodo; 
            rabo = nodo;
        }
        else{
            cabeza.anterior = nodo; 
            nodo.siguiente = cabeza; 
            cabeza = nodo;
        }
        return; 
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar. El elemento se inserta únicamente
     *                 si es distinto de <code>null</code>.
     */
    public void inserta(int i, Object elemento) {
        
        if(elemento == null)
            throw new IllegalArgumentException();  

        if (i < 1){
            agregaInicio(elemento);
        }
        else if(i > longitud - 1){
            agregaFinal(elemento);
        }
        else{
            Nodo nodoAux;
            nodoAux = buscaNodo(i);

            if(nodoAux == null)
                return; 

            Nodo nodoE = new Nodo(elemento);

            longitud++; 

            Nodo nodoAuxAnterior = nodoAux.anterior; 

            nodoAux.anterior = nodoE; 
            nodoE.siguiente = nodoAux; 
            nodoAuxAnterior.siguiente = nodoE; 
            nodoE.anterior = nodoAuxAnterior;
        return;
        }
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(Object elemento) {

        if(elemento == null)
            return;
        if(esVacia())
            return; 

        Nodo nodo = buscaNodo(elemento);

        if(nodo == null)
            return;

        eliminaNodo(nodo);
        return; 
    }

    // metodo buscaNodo que recibe como parametro un entero
    private Nodo buscaNodo(int i){

        if(esVacia())
            return null; 
        
        if(i < 0 || i > longitud - 1)
            return null; 

        Nodo nodo; 
        int contador = 0; 
        nodo = cabeza; 
        while(contador < i){
            contador++;
            nodo = nodo.siguiente;
        }
        return nodo; 
    }

    // metodo buscaNodo que recibe como parametro un Estudiante 
    private Nodo buscaNodo(Object e){
        if(e == null)
            return null; 

        if(esVacia())
            return null;        

        Nodo aux;
        aux = cabeza;  

        while(aux != null && !aux.elemento.equals(e)){
            aux = aux.siguiente; 
        }
        return aux; 
    }

    private void eliminaNodo(Nodo nodo){

        longitud--; 

        if(cabeza == rabo){
            cabeza = null; 
            rabo = null; 
        }
        else if(nodo == cabeza){
            cabeza.siguiente.anterior = null; 
            cabeza = nodo.siguiente;  
        }
        else if(nodo == rabo){
            rabo.anterior.siguiente = null; 
            rabo = nodo.anterior;
        }
        else{
            nodo.siguiente.anterior = nodo.anterior; 
            nodo.anterior.siguiente = nodo.siguiente;    
        }
        return; 
    }


    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaPrimero() {
        if(esVacia())
            throw new NoSuchElementException();

        Object aux = cabeza.elemento; 
        eliminaNodo(cabeza);
        return aux; 
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo, o
     *         <code>null</code> si la lista es vacía.
     */
    public Object eliminaUltimo() {
        if(esVacia())
            throw new NoSuchElementException();

        Object aux = rabo.elemento;
        eliminaNodo(rabo);
        return aux; 
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(Object elemento) {
        return buscaNodo(elemento) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista reversa() {
        Lista listaReversa = new Lista();

        Nodo auxiliar = cabeza; 
        
        int contador = 0; 
        while(contador < longitud){
            listaReversa.agregaInicio(auxiliar.elemento);
            auxiliar = auxiliar.siguiente;
            contador++;
        } 

        return listaReversa;  
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista copia() {
        Lista listaCopia = new Lista();

        Nodo auxiliar = cabeza; 

        int contador = 0; 
        while(contador < longitud){
            listaCopia.agregaFinal(auxiliar.elemento);
            auxiliar = auxiliar.siguiente;
            contador++;
        } 
        return listaCopia; 
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        cabeza = null; 
        rabo = null; 
        longitud = 0;
        return;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getPrimero() {
        if(esVacia())
            throw new NoSuchElementException();

        return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el último elemento de la lista, o <code>null</code> si la lista
     *         es vacía.
     */
    public Object getUltimo() {
        if(esVacia())
            throw new NoSuchElementException();

        return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista, o <code>null</code> si
     *         <em>i</em> es menor que cero o mayor o igual que el número de
     *         elementos en la lista.
     */
    public Object get(int i) {
        if(esVacia())
            throw new NoSuchElementException(); 

        if(i >= longitud || i < 0)
            throw new ExcepcionIndiceInvalido(); 

        Nodo auxiliar = buscaNodo(i);
        if(auxiliar == null)
            return null; 
        return auxiliar.elemento; 
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(Object elemento) {
        if(esVacia())
            return -1;   
       
        Nodo auxiliar;
        auxiliar = cabeza; 

        int contador = 0; 
        while(contador < longitud){
            if(auxiliar.elemento.equals(elemento))
                return contador; 
            auxiliar = auxiliar.siguiente;
            contador++; 
        }
        return -1;  
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        if(esVacia()){
            return "[]";
        }

        Nodo auxiliar = cabeza;

        StringBuffer sb = new StringBuffer();

        sb.append("[").append(auxiliar.elemento);

        if(auxiliar == null)
            return "[]";

        while(auxiliar != rabo){
            sb.append(", ");
            auxiliar = auxiliar.siguiente;   
            if(auxiliar == null)
                break;
            sb.append(auxiliar.elemento);
        }
        sb.append("]");

        return sb.toString(); 
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {

        if(objeto == null)
            return false;

        if (!(objeto instanceof Lista))
            return false;

        Lista lista = (Lista)objeto;

        if (lista == null || longitud != lista.longitud)
            return false;

        Nodo n1 = cabeza;
        Nodo n2 = lista.cabeza;
        while (n1 != null && n2 != null) {
            if (!n1.elemento.equals(n2.elemento))
                return false;
            n1 = n1.siguiente;
            n2 = n2.siguiente;
        }
        return true;
    }

    /**
     * Regresa el nodo cabeza de la lista.
     * @return el nodo cabeza de la lista.
     */
    public Nodo getCabeza() {
        return cabeza; 
    }

    /**
     * Regresa el nodo rabo de la lista.
     * @return el nodo rabo de la lista.
     */
    public Nodo getRabo() {
        return rabo; 
    }
}
