package models;

public class ListaAsistencia {
    public int numInf;
    public Detalles detalles[];
    public int maxInf;
    public ListaAsistencia(int maxInf){
        this.numInf = 0;
        this.maxInf=maxInf;
        this.detalles = new Detalles[maxInf];
    }
}
