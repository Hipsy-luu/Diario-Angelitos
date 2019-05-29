package models;

public class Detalles {
    public int i_id_inf ;
    public String i_name ;
    public String i_surnames ;
    public int i_age ;
    public String i_tel ;
    public String i_allergies ;
    public String i_medical_service;
    public String i_num_service ;
    public String i_reg_date ;
    public String i_image_path ;
    public String de_obs ;
    public String t_id_tut ;
    public String t_name_tut ;
    public String t_surnames ;
    public String t_tel;
    public String id_rela_tut_inf;
    public String id_ent_sal;
    
    public Detalles(){
        this.i_id_inf=0;
        this.i_name="";
        this.i_surnames="";
        this.i_age=0;
        this.i_tel="";
        this.i_allergies="";
        this.i_medical_service="";
        this.i_num_service="";
        this.i_reg_date="";
        this.i_image_path="";
        this.de_obs=""; 
        this.t_id_tut="";
        this.t_name_tut="";
        this.t_surnames="";
        this.t_tel="";
        this.id_rela_tut_inf = "";
        this.id_ent_sal = "";
    }
    public Detalles(
            int i_id_inf,String i_name,String i_surnames,int i_age,String i_tel,
            String i_allergies ,String i_medical_service,String i_num_service ,
            String i_reg_date ,String i_image_path ,String de_obs , 
            String t_id_tut ,String t_name_tut ,String t_surnames ,String t_tel,
            String id_rela_tut_inf , String id_ent_sal
    ){
        this.i_id_inf=i_id_inf;
        this.i_name=i_name;
        this.i_surnames=i_surnames;
        this.i_age=i_age;
        this.i_tel=i_tel;
        this.i_allergies=i_allergies;
        this.i_medical_service=i_medical_service;
        this.i_num_service=i_num_service;
        this.i_reg_date=i_reg_date;
        this.i_image_path=i_image_path;
        this.de_obs=de_obs; 
        this.t_id_tut=t_id_tut;
        this.t_name_tut=t_name_tut;
        this.t_surnames=t_surnames;
        this.t_tel=t_tel;
        this.id_rela_tut_inf = id_rela_tut_inf;
        this.id_ent_sal = id_ent_sal;
    }
    
}
