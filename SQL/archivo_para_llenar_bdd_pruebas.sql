

drop table DAILY_DEPARTURES;
drop table DAILY_ENTRIES;
drop table INF_INF;
drop table INF_TUT;
drop table TUTORS;
drop table INFANT;



--Este comando se usa para reescribir las tablas en caso de que se modifique la estructura del archivo
--Este archivo esta en esta ruta pero tambien dentro del rpyecto del git asi que si se cambia hay que reescribirlo en el git

@ C:\bdd\estancia_infantil.sql;

INSERT INTO INFANT 
VALUES( '0' ,  'Flor' , 'Legarda Araujo' , 25 , TO_DATE('21-MAY-1999', 'dd-MON-yyyy') , 'Avenida Las Flores #1521, Col Cafetales' , '614-566-25-12' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\0.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( '1' ,  'Luismiguel' , 'Ortiz Alvarez' , 25 , TO_DATE('25-JUL-1996', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\1.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( '2' ,  'Celia' , 'Rice Alvarez' , 25 , TO_DATE('21-MAR-1976', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\2.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( '3' ,  'Carlos' , 'Herrera Dominguez' , 25 , TO_DATE('12-DEC-1978', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\3.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( '4' ,  'Jose' , 'Jurado Guadalupe' , 25 , TO_DATE('24-OCT-1982', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\4.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT
VALUES( '5' ,  'Rubi' , 'Benavides Rosa' , 25 , TO_DATE('15-JUN-1984', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\5.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

INSERT INTO INFANT 
VALUES( '6' ,  'Lucero' , 'Piña Saenz' , 25 , TO_DATE('30-JUL-1987', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\6.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );
    
INSERT INTO INFANT 
VALUES( '7' ,  'Omar' , 'Lopez Yawey' , 25 , TO_DATE('30-JUL-1987', 'dd-MON-yyyy') , 'Calle Villa de Lira #9925, Col Campo Bello' , '614-125-25-57' 
	, TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MON-YYYY')), 'c:\imagenes\guarderia\intantes\6.jpg' , 'Ninguna' ,	'IMSS' , '90159215452' );

commit;