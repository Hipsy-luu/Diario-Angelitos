
package models;

public class Tutor {
    
    public int id_tut;
    public String name_tut;
    public String surnames;
    public String email;
    public String work_place;
    public int age;
    public String dir;
    public String tel;
    
    public Tutor(){
        this.id_tut = 0;
        this.name_tut = "";
        this.surnames = "";
        this.email = "";
        this.work_place = "";
        this.age = 0;
        this.dir = "";
        this.tel = "";
    }
    
    
    public Tutor( int id_tut,String name_tut,String surnames,int age,String tel,String dir,String email,String work_place){
        this.id_tut = id_tut;
        this.name_tut = name_tut;
        this.surnames = surnames;
        this.email = email;
        this.work_place = work_place;
        this.age = age;
        this.dir = dir;
        this.tel = tel;
    }
}
