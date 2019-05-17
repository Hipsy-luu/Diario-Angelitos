
package algoritmosApoyo;


public class Letra {
    int indice = -1;
    boolean finalPalabra = false;
    boolean usado = false;
    Letra ABC[];
    
    Letra(){
       this.ABC = null ;
    }
    
    public void marcarUsado(int indice){
        this.ABC = new Letra[26];
        this.usado = true;
        this.indice = indice;
    }
    
    public void desmarcarUsado(){
        this.ABC = null ;
        this.usado = false;
        this.finalPalabra = false;
        this.indice = -1;
    }
}
