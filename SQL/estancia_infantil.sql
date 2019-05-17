
CREATE TABLE TUTORS
(
	id_tut               CHAR(18) NOT NULL ,
	name_tut             CHAR(18) NULL ,
	age                  CHAR(18) NULL ,
	dir                  CHAR(18) NULL ,
	tel                  CHAR(18) NULL ,
CONSTRAINT  XPKTUTORES PRIMARY KEY (id_tut)
);



CREATE TABLE INFANT
(
	id_inf               VARCHAR2(10) NOT NULL ,
	name                 VARCHAR2(30) NOT NULL ,
	surnames             VARCHAR2(30) NOT NULL ,
	age                  NUMBER(3) NOT NULL ,
	birth_day            DATE NOT NULL ,
	dir                  VARCHAR2(50) NOT NULL ,
	tel                  VARCHAR2(40) NOT NULL ,
	reg_date             DATE NOT NULL ,
	image_path           VARCHAR2(200) NOT NULL ,
	allergies            VARCHAR2(30) NOT NULL ,
	medical_service      VARCHAR2(20) NOT NULL ,
	num_service          VARCHAR2(20) NOT NULL ,
CONSTRAINT  XPKINFANTES PRIMARY KEY (id_inf)
);



CREATE TABLE INF_TUT
(
	id_rela_tut_inf      CHAR(18) NOT NULL ,
	id_tut               CHAR(18) NOT NULL ,
	id_inf               VARCHAR2(10) NOT NULL ,
CONSTRAINT  XPKINF_TUT PRIMARY KEY (id_rela_tut_inf),
CONSTRAINT R_5 FOREIGN KEY (id_tut) REFERENCES TUTORS (id_tut),
CONSTRAINT R_6 FOREIGN KEY (id_inf) REFERENCES INFANT (id_inf)
);



CREATE TABLE DAILY_DEPARTURES
(
	id_dep               CHAR(18) NOT NULL ,
	id_rela_tut_inf      CHAR(18) NOT NULL ,
	date_dep             CHAR(18) NULL ,
	hour                 CHAR(18) NULL ,
	obs                  CHAR(18) NULL ,
CONSTRAINT  XPKDAILY_DEPARTURES PRIMARY KEY (id_dep),
CONSTRAINT R_8 FOREIGN KEY (id_rela_tut_inf) REFERENCES INF_TUT (id_rela_tut_inf)
);



CREATE TABLE DAILY_ENTRIES
(
	id_ent               CHAR(18) NOT NULL ,
	id_rela_tut_inf      CHAR(18) NOT NULL ,
	date_ent             CHAR(18) NULL ,
	hour                 CHAR(18) NULL ,
	obs                  CHAR(18) NULL ,
CONSTRAINT  XPKDAILY_ENTRIES PRIMARY KEY (id_ent),
CONSTRAINT R_7 FOREIGN KEY (id_rela_tut_inf) REFERENCES INF_TUT (id_rela_tut_inf)
);



CREATE TABLE INF_INF
(
	id_rela_bro          CHAR(18) NOT NULL ,
	id_inf_a             VARCHAR2(10) NOT NULL ,
	id_inf_b             VARCHAR2(10) NOT NULL ,
CONSTRAINT  XPKINF_INF PRIMARY KEY (id_rela_bro),
CONSTRAINT R_3 FOREIGN KEY (id_inf_a) REFERENCES INFANT (id_inf),
CONSTRAINT R_4 FOREIGN KEY (id_inf_b) REFERENCES INFANT (id_inf)
);


