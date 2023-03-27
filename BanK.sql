CREATE TABLE ESTADO_CLIENTE
(
    ID   int         NOT NULL AUTO_INCREMENT,
    TIPO varchar(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE CLIENTE
(
    ID                int      NOT NULL AUTO_INCREMENT,
    FECHA_INICIO      datetime NOT NULL,
    ESTADO_CLIENTE_ID int      NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ESTADO_CLIENTE_ID) REFERENCES ESTADO_CLIENTE (ID)
);

CREATE TABLE ESTADO_CUENTA
(
    ID   int         NOT NULL AUTO_INCREMENT,
    TIPO varchar(20) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ENTIDAD_BANCARIA
(
    ID     int         NOT NULL,
    NOMBRE varchar(45) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE CUENTA_BANCO
(
    ID                  int         NOT NULL AUTO_INCREMENT,
    MONEDA              varchar(10) NOT NULL,
    IBAN_CUENTA         varchar(24) NOT NULL UNIQUE,
    SALDO               double      NOT NULL,
    SWIFT               varchar(11) NOT NULL,
    PAIS                varchar(45) NOT NULL,
    FECHA_APERTURA      datetime    NOT NULL,
    FECHA_CIERRE        datetime    NOT NULL,
    TITULAR_ID          int         NOT NULL,
    ENTIDAD_BANCARIA_ID int         NOT NULL,
    ESTADO_CUENTA_ID    int         NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (TITULAR_ID) REFERENCES CLIENTE (ID),
    FOREIGN KEY (ENTIDAD_BANCARIA_ID) REFERENCES ENTIDAD_BANCARIA (ID),
    FOREIGN KEY (ESTADO_CUENTA_ID) REFERENCES ESTADO_CUENTA (ID)
);

CREATE TABLE TIPO_DIRECCION
(
    ID   int NOT NULL AUTO_INCREMENT,
    TIPO varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE DIRECCION
(
    ID                    int         NOT NULL AUTO_INCREMENT,
    CALLE                 varchar(45) NOT NULL,
    NUMERO                int         NOT NULL,
    PLANTA_PUERTA_OFICINA varchar(45) DEFAULT NULL,
    CIUDAD                varchar(45) NOT NULL,
    REGION                varchar(45) NOT NULL,
    CODIGO_POSTAL         int         NOT NULL,
    PAIS                  varchar(45) NOT NULL,
    VALIDA                tinyint     DEFAULT NULL,
    CLIENTE_ID            int         NOT NULL,
    TIPO_DIRECCION_ID     int         NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID),
    FOREIGN KEY (TIPO_DIRECCION_ID) REFERENCES TIPO_DIRECCION (ID)
);

CREATE TABLE TRANSACCION
(
    ID                int      NOT NULL AUTO_INCREMENT,
    FECHA_INSTRUCCION datetime NOT NULL,
    FECHA_EJECUCION   datetime NOT NULL,
    CANTIDAD          double   NOT NULL,
    CUENTA_ORIGEN     int      NOT NULL,
    CUENTA_DESTINO    int      NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CUENTA_ORIGEN) REFERENCES CUENTA_BANCO (ID),
    FOREIGN KEY (CUENTA_DESTINO) REFERENCES CUENTA_BANCO (ID)
);

CREATE TABLE DIVISA
(
    ID           varchar(3)  NOT NULL,
    NOMBRE       varchar(40) NOT NULL,
    EQUIVALENCIA double      NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE PERSONA
(
    ID               int         NOT NULL,
    NOMBRE           varchar(45) NOT NULL,
    APELLIDO1        varchar(45) NOT NULL,
    APELLIDO2        varchar(45),
    FECHA_NACIMIENTO datetime    NOT NULL,
    DNI              varchar(9)  NOT NULL UNIQUE,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES CLIENTE (ID)
);

CREATE TABLE EMPRESA
(
    ID             int         NOT NULL,
    NOMBRE         varchar(45) NOT NULL,
    FECHA_CREACION datetime    NOT NULL,
    CIF            varchar(9)  NOT NULL UNIQUE,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES CLIENTE (ID)
);

CREATE TABLE TIPO_USUARIO
(
    ID   int NOT NULL AUTO_INCREMENT,
    TIPO varchar(20),
    PRIMARY KEY (ID)
);

CREATE TABLE USUARIO
(
    ID           int         NOT NULL,
    NIF          varchar(9)  NOT NULL,
    CONTRASENA   varchar(45) NOT NULL,
    TIPO_USUARIO int         NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (ID) REFERENCES CLIENTE (ID),
    FOREIGN KEY (TIPO_USUARIO) REFERENCES TIPO_USUARIO (ID)
);

CREATE TABLE CONVERSACION
(
    ID        int     NOT NULL,
    EMISOR    int     NOT NULL,
    RECEPTOR  int     NOT NULL,
    TERMINADA tinyint NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT EMISOR FOREIGN KEY (EMISOR) REFERENCES USUARIO (ID),
    CONSTRAINT RECEPTOR FOREIGN KEY (RECEPTOR) REFERENCES USUARIO (ID)
);

CREATE TABLE MENSAJE
(
    ID           int          NOT NULL,
    CONVERSACION int          NOT NULL,
    CONTENIDO    varchar(500) NOT NULL,
    FECHA        datetime     NOT NULL,
    EMISOR       int          NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CONVERSACION) REFERENCES CONVERSACION (ID),
    FOREIGN KEY (EMISOR) REFERENCES USUARIO (ID)
);

