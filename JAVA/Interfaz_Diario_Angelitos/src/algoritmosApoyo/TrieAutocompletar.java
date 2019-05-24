package algoritmosApoyo;


public class TrieAutocompletar {
    //Contiene los nombres de los niños en la lista en forma de trie
    public Letra diccionario;
    
    //Lo usamos para contar cuantas coincidencias encuentra y ponemos en cero cada ves que lo usamos
    //para que cada vez que encuentre una con ese numero guardarlo en el arreglo string coincidencias[MAXTAM]
    public int cont=0;
    //Aqui guardamos las palabras que encontramos en las coincidencias (maximo 500)
    public Coincidencia coincidencias[];
    //Se guarda el diccionario actual con su indice a cada palabra
    String diccionarioActual[] = new String[500];

    public TrieAutocompletar() {
        this.diccionario = new Letra();
        this.coincidencias = new Coincidencia[500];
    }
    
    public Letra nuevaLetra(int indice){
        Letra letraNueva = new Letra();
        letraNueva.marcarUsado(indice);
        return letraNueva;
    }
    //Solo se le debe pasar el nombre y su indice de la tabla
    //para poder consultarlo rapido
    public void insertWord( String nombre , int indice ) {
        //Crea el index de cada letra dentro del trie mientras te mueves a el y al
        //cuando termina de crear cada index de la palabra en el trie la pone como fin de palabra
        Letra aux = this.diccionario;
        for (int i = 0; i < ( nombre.length() ) ; i++) 
        { 
            //Se recore el numero asci para que se comience en la casilla 0
            int index = nombre.charAt(i); 
            if ( aux.ABC[index] == null ) 
                 aux.ABC[index] = nuevaLetra(-1);
            aux = aux.ABC[index]; 
        } 
        //Se marca que este nuevo nodo es un final de palabra
        aux.finalPalabra = true;
        //Se guarda el indice que tiene en el arreglo
        //Para poderlo consultar rapido desde el arreglo
        aux.indice = indice;
    }
    
    public Letra regresarUltimoNodo(Letra root, String palabra){
	//aux me sirve como un pivote para moverme por la palabra hasta el nultimo nodo 
	Letra aux = root; int index;
        //Buscamos el ultimo caracter
        for (int i = 0; i < palabra.length() ; i++) { 
            //El index representa la casilla del arreglo de 0-26
            index = palabra.charAt(i); 
            aux = aux.ABC[index]; 
        } 
        //(antes se debe verificar que existe toda la palabra)
        //Regresamos la direccion de la penultima letra de la palabra   
        return aux ; 
    }
    
    public Letra regresarPenultimoNodo(Letra root, String palabra){
        //aux me sirve como un pivote para moverme por la palabra hasta el penultimo nodo 
	Letra aux = root; 
	int index;
        //Buscamos el penultimo caracter
         //El index representa la casilla del arreglo de 0-26
        for (int i = 0; i < (palabra.length()-1) ; i++) { 
            index = palabra.charAt(i);
            aux = aux.ABC[index]; 
        } 
        //(antes se debe verificar que existe toda la palabra)
        //Regresamos la direccion de la penultima letra de la palabra      
        return aux ; 
    }
    
    void delteLetras( String palabra ){
	Letra padre;
	Letra ultimoCaracter;
	int index;

	padre = regresarPenultimoNodo( this.diccionario , palabra );//es lo mismo que regresar el padre del ultimo caracter
	ultimoCaracter = regresarUltimoNodo( this.diccionario , palabra );//lo mismo pero regresa el ultimo caracter
	
	if( sinHijo(ultimoCaracter) ){ //buscamos que solo tenga un hijo
            index = palabra.charAt(palabra.length()-1 ); 
            //Este es el detalle de nuestro programa porque si ponemos el ultimoCaracter como NULL no nos lo borra
            //pero si lo hacemos null desde el penultimo caracter si lo hace
            //Borramos el apuntador del padre a la ultima letra 
            padre.ABC[index] = null;
            //Borramos el ultimo caracter de la cadena
            if(palabra.length()==1){
                palabra="";
            }else{
                palabra = palabra.substring( 0 , palabra.length() - 1 );
            }
            //es lo mismo que regresar el padre del ultimo caracter
            padre = regresarPenultimoNodo( this.diccionario , palabra );
            ultimoCaracter = regresarUltimoNodo( this.diccionario , palabra );
            while( ultimoCaracter.finalPalabra==false && sinHijo( ultimoCaracter ) == true && palabra.length()>0 ){
                //Buscamos el ultimo caracter y lo convertimos a valor del arreglo
                index = palabra.charAt( palabra.length()-1 ); 
                padre.ABC[index] = null;
                //Borramos el ultimo caracter de la cadena
                if(palabra.length()==1){
                    palabra="";
                }else{
                    palabra = palabra.substring( 0 , palabra.length() - 1 );
                }
                padre = regresarPenultimoNodo( this.diccionario , palabra );
                ultimoCaracter = regresarUltimoNodo( this.diccionario , palabra );
            }
	}else{
		ultimoCaracter.finalPalabra=false;
	}
    }
    
    //Si es final de palabra y coincide que la cadena palabraAutocompletar
    //se encuentra en la palabra encontrada imprime el String que llevamos acarreando
    public void buscarCoincidencias( String palabraAutocompletar ){
        //Se reinicia el arreglo con coincidencias
        this.coincidencias = new Coincidencia[500];
        this.cont=0;//Este contador nos indica cuantas coincidencias encontramos
	//Se le debe pasar un espacio vacio la primera vez
        buscarCoincidencias( this.diccionario  , palabraAutocompletar , "" );
        //System.out.println("\nCoincidencias : "+cont);
    }
    
    public void buscarCoincidencias(Letra nodo, String palabraAutocompletar , String palabra ){
        if( nodo.finalPalabra == true){
            //Si encuentra la palabraAutocompletar en la
            // lista  de palabra1  la funcion strstr() devuelve la posicion y si no NULL
            if( palabra.contains( palabraAutocompletar) ){
                //Se crea una nueva instancia del objeto para llenar el array
                Coincidencia aux = new Coincidencia(palabra,nodo.indice);
                this.coincidencias[cont] = aux;
                System.out.println("\t"+cont+"- "+palabra);
                cont++;
            }
            if( sinHijo(nodo) )
                    palabra="";
	}
	//Buscamos si existe un caracter y lueno nos movemos a el hasta que recorre todo el diccionario
	for(int index=0 ; index<300 ; index++){
            if ( nodo.ABC[index] != null ) {
                char caracter = (char)( index ) ;
                palabra = palabra + caracter ;
                buscarCoincidencias( nodo.ABC[index] , palabraAutocompletar , palabra );
                if(palabra.length()==1){
                    palabra="";
                }else{
                    palabra = palabra.substring( 0 , palabra.length() - 1 );
                }
            }
        }
    }
    
    boolean sinHijo(Letra root){
	int numCarac=0;

	for(int x=0;x<26;x++){
            if ( root.ABC[x] != null )
                numCarac++; 
	}
	if(numCarac==0){
            return true;
	}else{
            return false;
	}
    }
    
    public boolean searchWord(Letra root, String palabra) {
        //Si la palabra COMPLETA ya fue añadida regresa un true
        Letra aux = root; 

        for (int i = 0; i < palabra.length(); i++){ 
            int index = palabra.charAt(i); 
                    
            if ( aux.ABC[index] == null ) //si no existe el nodo (caracter) 
                return false; 

            aux = aux.ABC[index]; 
        } 
        return ( aux != null && aux.finalPalabra ); 
    }
    //Se le pasa un espacio vacio la primera vez
    public void printDic(Letra root, String palabra){
        //Si es final de palabra imprime el String que llevamos acarreando
        if( root.finalPalabra == true){
                System.out.println(palabra + " " +Integer.toString(root.indice));
                this.diccionarioActual[root.indice]=palabra + " " + Integer.toString(root.indice) ;
                if( !sinHijo(root) )
                        palabra="";
        }
        //Buscamos si existe un caracter y lueno nos movemos a el hasta que recorre todo el diccionario
        for(int index=0 ; index<26 ; index++){
            if ( root.ABC[index] != null ) {
                char caracter = (char)( index ) ;
                palabra = palabra + caracter ;
                printDic( root.ABC[index] , palabra);
                
                if(palabra.length()==1){
                    palabra="";
                }else{
                    palabra = palabra.substring( 0 , palabra.length() - 1 );
                }
            }
        }
    }
    
    public void test(){
        this.insertWord("luismi", 0);
        this.insertWord("flor", 1);
        this.insertWord("juan", 2);
        this.insertWord("pedro", 3);
        this.insertWord("putos", 4);
        this.insertWord("andrea", 4);
        this.printDic( this.diccionario , "");
        
        buscarCoincidencias( "lu" );
        for(int x=0; x< this.cont; x++){
            System.out.println( this.coincidencias[x].nombre );
        }
    }
    
}
