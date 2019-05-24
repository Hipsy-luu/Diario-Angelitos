package models;

public class ListaHermanosInfante {
    public Infant[] hermanosNiño;
    public int numHermanos;
    public String id_inf;
    
    public ListaHermanosInfante() {
        this.hermanosNiño = new Infant[30];
        this.numHermanos = 0;
        this.id_inf = "0";
    }
}
