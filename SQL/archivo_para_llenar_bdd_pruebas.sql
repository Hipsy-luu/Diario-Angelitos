--SELECT * FROM V$VERSION;

drop table DAILY_DEPARTURES;
drop table DAILY_ENTRIES;
drop table INF_INF;
drop table INF_TUT;
drop table TUTORS;
drop table INFANT;

drop SEQUENCE inf_id_seq;

drop SEQUENCE tut_id_seq;

drop SEQUENCE nin_tut_id_seq;

drop SEQUENCE nin_nin_id_seq;

drop SEQUENCE daly_ent_id_seq;

drop SEQUENCE daly_dep_id_seq;

--Este comando se usa para reescribir las tablas en caso de que se modifique la estructura del archivo
--Este archivo esta en esta ruta pero tambien dentro del rpyecto del git asi que si se cambia hay que reescribirlo en el git

@ C:/bdd/estancia_infantil.sql;

--Secuencia para llevar el control del id de los niños
CREATE SEQUENCE inf_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;

--Secuencia para llevar el control del id de los tutores
CREATE SEQUENCE tut_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;

--Secuencia para llevar el control del id de las relaciones Niños Tutores
CREATE SEQUENCE nin_tut_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;

--Secuencia para llevar el control del id de las relaciones Niños Niños
CREATE SEQUENCE nin_nin_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;

--Secuencia para llevar el control del id de las entradas
CREATE SEQUENCE daly_ent_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;

--Secuencia para llevar el control del id de las salidas 
CREATE SEQUENCE daly_dep_id_seq START WITH 0 INCREMENT BY 1 MINVALUE 0 NOMAXVALUE;



--SELECT inf_id_seq.nextval FROM dual;

--Se insertan unos cuantos infantes
INSERT INTO INFANT 
VALUES( inf_id_seq.nextval ,  'Flor' , 'Legarda Araujo' , 25 , TO_DATE('21-MAY-1999', 'dd-MON-yyyy') , 'Avenida Las Flores #1521, Col Cafetales' , '614-566-25-12' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'C:/estancia_imagenes/default.png' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( inf_id_seq.nextval ,  'Luismiguel' , 'Ortiz Alvarez' , 25 , TO_DATE('25-JUL-1996', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'C:/estancia_imagenes/default.png' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( inf_id_seq.nextval ,  'Celia' , 'Rice Alvarez' , 25 , TO_DATE('21-MAR-1976', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'C:/estancia_imagenes/default.png' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( inf_id_seq.nextval ,  'Carlos' , 'Herrera Dominguez' , 25 , TO_DATE('12-DEC-1978', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'C:/estancia_imagenes/default.png' , 'Ninguna' ,	'IMSS' , '90159215452' );

--UPDATE INFANT SET name = 'test'  WHERE id_inf = 6;

--Se insertan unos tutores
INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Carlos' , 'Lorep Ipsum' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Felipe' , 'Dolores Dominguez' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Uriel' , 'Ortiz Alvarez' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Fatima' , 'Legarda Araujo' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Lucero' , 'Cardenas Treviño' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

INSERT INTO TUTORS ( id_tut , name_tut , surnames ,age , tel , dir , email , work_place )
VALUES( tut_id_seq.nextval , 'Marta' , 'Neart Nelson' , 25 ,  '614-125-25-57' , 'Calle Villa de Lira #9925, Col Campo Bello' , 'hchotmail@gmail.com' , 'UACH');

--Se Insertan algunas relaciones entre los tutores y el tutor
INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 0 , 4 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 0 , 2 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 0 , 1 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 0 , 5 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 1 , 4 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 1 , 5 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 2 , 1 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 2 , 3 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 2 , 2 );

INSERT INTO INF_TUT ( id_rela_tut_inf , id_inf , id_tut )
VALUES( nin_tut_id_seq.nextval , 3 , 4 );

--Se insertan algunas relaciones entre hermanos
INSERT INTO INF_INF ( id_rela_bro , id_inf_a , id_inf_b )
VALUES( nin_nin_id_seq.nextval , 0 , 1 );

INSERT INTO INF_INF ( id_rela_bro , id_inf_a , id_inf_b )
VALUES( nin_nin_id_seq.nextval , 2 , 0 );

INSERT INTO INF_INF ( id_rela_bro , id_inf_a , id_inf_b )
VALUES( nin_nin_id_seq.nextval , 0 , 3 );

INSERT INTO DAILY_DEPARTURES ( id_dep , id_rela_tut_inf , date_dep , obs ) 
VALUES ( daly_dep_id_seq.nextval , 5 ,  TO_DATE('27/05/2019 20:49:40', 'DD/MM/YYYY HH24:MI:SS') , ' ');

commit;

--Consulta para obtener las entradas del dia '27/05/2019'
--Consulta para obtener los ni�os que aun no regstran salida del dia actual
SELECT i.id_inf , i.name , i.surnames , i.age , i.tel , i.allergies , i.medical_service
    , i.num_service , i.reg_date , i.image_path , de.obs , t.id_tut , t.name_tut , t.surnames , t.tel
FROM DAILY_ENTRIES de
JOIN INF_TUT it
ON(de.id_rela_tut_inf=it.id_rela_tut_inf)
JOIN INFANT i
ON(it.id_inf=i.id_inf)
JOIN TUTORS t
ON(it.id_tut=t.id_tut)
WHERE to_char(DE.date_ent,'dd/MM/yyyy') = '27/05/2019' ;
--Consulta para obtener los ni�os que aun no regstran salida del dia actual
SELECT i.id_inf , i.name , i.surnames , i.age , i.tel , i.allergies , i.medical_service
    , i.num_service , i.reg_date , i.image_path , de.obs , t.id_tut , t.name_tut , t.surnames , t.tel
FROM DAILY_ENTRIES de
JOIN INF_TUT it
ON(de.id_rela_tut_inf=it.id_rela_tut_inf)
JOIN INFANT i
ON(it.id_inf=i.id_inf)
JOIN TUTORS t
ON(it.id_tut=t.id_tut)
WHERE to_char(DE.date_ent,'dd/MM/yyyy') = (
    SELECT TO_CHAR(CURRENT_DATE, 'dd/MM/yyyy') 
    FROM dual
)
and 0 = (
    SELECT COUNT(*) 
    FROM DAILY_DEPARTURES
    WHERE id_rela_tut_inf IN (
        SELECT id_rela_tut_inf 
        FROM INF_TUT 
        WHERE id_inf = i.id_inf 
        and to_char(date_dep,'dd/MM/yyyy') = (
            SELECT TO_CHAR(CURRENT_DATE, 'dd/MM/yyyy') 
            FROM dual
        )
    )
);

SELECT TO_CHAR(CURRENT_DATE, 'dd/MM/yyyy') 
    FROM dual;

SELECT COUNT(*) 
FROM DAILY_ENTRIES 
WHERE  id_rela_tut_inf IN (
    SELECT id_rela_tut_inf 
    FROM INF_TUT 
    WHERE id_inf = 0
) and to_char(date_ent,'dd/MM/yyyy') = '27/05/2019' ;

SELECT COUNT(*) 
FROM DAILY_ENTRIES 
WHERE  to_char(date_ent,'dd/MM/yyyy') = '27/05/2019' ;
    
SELECT COUNT(*) 
FROM DAILY_ENTRIES 
WHERE  id_rela_tut_inf IN (
    SELECT id_rela_tut_inf 
    FROM INF_TUT 
    WHERE id_inf = 3) 
and date_ent = TO_DATE('27/05/2019 21:49:42','dd/MM/yyyy') ;

--Busca todos los hermanos de un ni�o
SELECT p.id_inf , p.name , p.surnames , p.age ,	p.birth_day  , p.dir  ,	p.tel , p.reg_date , p.image_path,	p.allergies, p.medical_service, p.num_service
    from INFANT p	
    WHERE p.id_inf IN (
        SELECT a.id_inf
        from INFANT a	
        join INF_INF b
        on(b.id_inf_a = a.id_inf )
        where b.id_inf_b = 0
    ) or p.id_inf IN (
        SELECT c.id_inf
        from INFANT c	
        join INF_INF d
        on(d.id_inf_b = c.id_inf )
        where d.id_inf_a = 0
    );

--Busca los tutores del ni�o con id 2
SELECT t.id_tut , t.name_tut , t.surnames ,t.age , t.tel , t.dir , t.email , t.work_place
    from INF_TUT i
	join TUTORS t
    on(i.id_tut = t.id_tut)
    where i.id_inf = 2 ;

UPDATE TUTORS SET name_tut='Marta' , surnames='Neart Nelson' , age=25 , tel='614-125-25-57' , 
	dir='Calle Villa de Lira #9925, Col Campo Bello' , email='hchotmail@gmail.com' , work_place='UACH'  WHERE id_tut = 3;

select tut_id_seq.currval from DUAL;

--Buscamos si ya existe la relacion del ni�o
SELECT COUNT(*) FROM INF_INF WHERE id_inf_a = 2 and id_inf_b = 0 or id_inf_a = 0 and id_inf_b = 2;

--
SELECT COUNT(*) FROM INF_TUT WHERE  id_inf = '1' and id_tut = '1' ;
--UPDATE INFANT 
--SET name = 'loco' ,surnames= 'Herrera Dominguez' ,
--age=12,birth_day=TO_DATE('30-JUL-1987', 'dd-MON-yyyy'),
--dir='Calle Villa de Lira #9925, Col Campo Bello',
--tel='614-125-25-57',reg_date=TO_DATE(TO_CHAR('30-JUL-2019', 'DD-MON-YYYY')),
--image_path='C:/estancia_imagenes/default.png',allergies='Ninguna',
--medical_service='IMSS',num_service='90159215452'  WHERE id_inf = 6;


commit;