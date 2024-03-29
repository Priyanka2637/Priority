CREATE TABLE AREA(
    AREA_ID NUMBER(5) NOT NULL,
	AREA_NAME VARCHAR2(50) NOT NULL,
	PRIMARY KEY (AREA_ID)
);

CREATE SEQUENCE AREA_ID_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
CREATE TABLE USERS(
    USER_ID NUMBER(5) NOT NULL,
	USER_NAME VARCHAR2(50) NOT NULL,
    USER_ROLE VARCHAR2(50) NOT NULL,
    EMAIL VARCHAR2(50) NOT NULL,
    PASSWORD VARCHAR2(10) NOT NULL,
	PRIMARY KEY (USER_ID)
); 
 
CREATE SEQUENCE USER_ID_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;
 
 CREATE TABLE RATING(
    USER_ID NUMBER(5) NOT NULL,
    AREA_ID NUMBER(5) NOT NULL,
	PRIORITY VARCHAR2(50) NOT NULL,
    SATISFATION VARCHAR2(50) NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID),
    FOREIGN KEY (AREA_ID) REFERENCES AREA(AREA_ID)
); 

COMMIT;
