package models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Infant {
    //Atributos de la clase de los infantes
    public int id_inf;
    public int age;
    public String name_inf;
    public String surnames;
    public String birth_day;
    public String dir;
    public String tel;
    public String reg_date;
    public String image_path;
    public String allergies;
    public String medical_service;
    public String num_service;

    
    //Este contructor crea un Infante con sus atrubutos vacios
    //menos el id_inf porque aun no se sabe si se guardara
    public Infant(){
        this.id_inf = 0;
        this.name_inf = "";
        this.surnames = "";
        this.age = 0;
        this.birth_day = "";
        this.dir = "";
        this.tel = "";
        this.reg_date = new SimpleDateFormat("dd-MMM-yyyy").
                           format(Calendar.getInstance().getTime());
        this.image_path = "C:/estancia_imagenes/default.png";
        this.allergies = "";
        this.medical_service = "";
        this.num_service = "";
    }
    //Este contructor recibe el nombre y lo busca en la
    //Base de datos para despues llenarlo
    //Se sabe de antemano que el nombre se saca ya de la base de datos
    public Infant(String name_inf){
        
    }
    //Este constructor nos servira para pasar el Infante entre las ventanas
    public Infant(int id_inf,int age,String name_inf,String surnames,
            String birth_day,String dir,String tel,String reg_date,
            String image_path,String allergies,String medicalService,
            String numService
    ){
        this.id_inf = id_inf;
        this.age = age;
        this.name_inf = name_inf;
        this.surnames = surnames;
        this.birth_day = new String (birth_day.substring(0, birth_day.length() - 11));
        this.dir = dir;
        this.tel = tel;
        this.reg_date = new String (reg_date.substring(0, reg_date.length() - 11));
        this.image_path = image_path;
        this.allergies = allergies;
        this.medical_service = medicalService;
        this.num_service = numService;
    }
}
