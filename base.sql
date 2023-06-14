drop index if exists IDX_CLIE_RAZSOC;

drop index if exists IDX_CLIE_TIP_IDEN;

drop index if exists IDX_CLIE_APE;

drop table if exists CLIENTE;

drop index if exists IDX_FAC_CLIE;

drop index if exists IDX_FAC_SERIE;

drop index if exists IDX_FAC_FECH;

drop table if exists FACTURA;

drop table if exists FACTURA_DETALLE;

drop table if exists FACTURA_DETALLE_IMPUESTO;

drop table if exists FACTURA_IMPUESTO;

drop table if exists IMPUESTO;

drop table if exists IMPUESTO_PORCENTAJE;

drop index if exists IDX_PROD_NOMBRE;

drop table if exists PRODUCTO;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
    COD_CLIENTE SERIAL not null,
    TIPO_IDENTIFICACION VARCHAR(3) not null constraint CKC_TIPO_IDENTIFICACI_CLIENTE check (TIPO_IDENTIFICACION in ('CED', 'RUC', 'PAS')),
    IDENTIFICACION VARCHAR(20) not null,
    APELLIDO VARCHAR(50) null,
    NOMBRE VARCHAR(50) null,
    RAZON_SOCIAL VARCHAR(100) null,
    DIRECCION VARCHAR(100) not null,
    TELEFONO VARCHAR(15) null,
    CORREO_ELECTRONICO VARCHAR(100) not null,
    constraint PK_CLIENTE primary key (COD_CLIENTE)
);

comment on table CLIENTE is 'Entidad que almacena los clientes del sistema de Facturación.';

comment on column CLIENTE.COD_CLIENTE is 'Código numérico que identifica a un cliente.';

comment on column CLIENTE.TIPO_IDENTIFICACION is 'Tipo de identificacion del cliente, puede ser: RUC, CED o PAS';

comment on column CLIENTE.IDENTIFICACION is 'Valor de la identificación del cliente';

comment on column CLIENTE.APELLIDO is 'Apellidos del cliente';

comment on column CLIENTE.NOMBRE is 'Nombres de cliente';

comment on column CLIENTE.RAZON_SOCIAL is 'Razon social del cliente, cuando la identificacion sea RUC';

comment on column CLIENTE.DIRECCION is 'Dirección del domicilio o matriz, segun corresponda del cliente.';

comment on column CLIENTE.TELEFONO is 'Telefono del cliente';

/*==============================================================*/
/* Index: IDX_CLIE_APE                                          */
/*==============================================================*/
create index IDX_CLIE_APE on CLIENTE (APELLIDO);

/*==============================================================*/
/* Index: IDX_CLIE_TIP_IDEN                                     */
/*==============================================================*/
create unique index IDX_CLIE_TIP_IDEN on CLIENTE (TIPO_IDENTIFICACION, IDENTIFICACION);

/*==============================================================*/
/* Index: IDX_CLIE_RAZSOC                                       */
/*==============================================================*/
create index IDX_CLIE_RAZSOC on CLIENTE (RAZON_SOCIAL);

/*==============================================================*/
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA (
    COD_FACTURA SERIAL not null,
    COD_CLIENTE INT4 not null,
    COD_ESTABLECIMIENTO VARCHAR(3) not null,
    PUNTO_EMISION VARCHAR(3) not null,
    SECUENCIAL NUMERIC(9, 0) not null,
    NUMERO_AUTORIZACION VARCHAR(40) null,
    FECHA DATE not null,
    SUBTOTAL NUMERIC(10, 2) not null,
    TOTAL NUMERIC(10, 2) not null,
    constraint PK_FACTURA primary key (COD_FACTURA)
);

comment on column FACTURA.COD_CLIENTE is 'Código numérico que identifica a un cliente.';

/*==============================================================*/
/* Index: IDX_FAC_FECH                                          */
/*==============================================================*/
create index IDX_FAC_FECH on FACTURA (FECHA);

/*==============================================================*/
/* Index: IDX_FAC_SERIE                                         */
/*==============================================================*/
create index IDX_FAC_SERIE on FACTURA (
    COD_ESTABLECIMIENTO,
    PUNTO_EMISION,
    SECUENCIAL
);

/*==============================================================*/
/* Index: IDX_FAC_CLIE                                          */
/*==============================================================*/
create index IDX_FAC_CLIE on FACTURA (COD_CLIENTE);

/*==============================================================*/
/* Table: FACTURA_DETALLE                                       */
/*==============================================================*/
create table FACTURA_DETALLE (
    COD_FACTURA_DETALLE SERIAL not null,
    COD_FACTURA INT4 not null,
    COD_PRODUCTO VARCHAR(20) not null,
    CANTIDAD NUMERIC(10, 2) not null,
    NOMBRE_PRODUCTO VARCHAR(100) not null,
    PRECIO_UNITARIO NUMERIC(10, 2) not null,
    SUBTOTAL NUMERIC(10, 2) not null,
    constraint PK_FACTURA_DETALLE primary key (COD_FACTURA_DETALLE)
);

/*==============================================================*/
/* Table: FACTURA_DETALLE_IMPUESTO                              */
/*==============================================================*/
create table FACTURA_DETALLE_IMPUESTO (
    COD_FACTURA_DETALLE_IMPUESTO SERIAL not null,
    COD_IMPUESTO VARCHAR(3) not null,
    PORCENTAJE NUMERIC(4, 1) not null,
    COD_FACTURA_DETALLE INT4 not null,
    VALOR NUMERIC(10, 2) not null,
    constraint PK_FACTURA_DETALLE_IMPUESTO primary key (COD_FACTURA_DETALLE_IMPUESTO)
);

/*==============================================================*/
/* Table: FACTURA_IMPUESTO                                      */
/*==============================================================*/
create table FACTURA_IMPUESTO (
    FACTURA_IMPUESTO_ID SERIAL not null,
    COD_FACTURA INT4 not null,
    COD_IMPUESTO VARCHAR(3) not null,
    PORCENTAJE NUMERIC(4, 1) not null,
    VALOR NUMERIC(10, 2) not null,
    constraint PK_FACTURA_IMPUESTO primary key (FACTURA_IMPUESTO_ID)
);

/*==============================================================*/
/* Table: IMPUESTO                                              */
/*==============================================================*/
create table IMPUESTO (
    COD_IMPUESTO VARCHAR(3) not null,
    NOMBRE VARCHAR(100) not null,
    SIGLAS VARCHAR(10) not null,
    constraint PK_IMPUESTO primary key (COD_IMPUESTO)
);

/*==============================================================*/
/* Table: IMPUESTO_PORCENTAJE                                   */
/*==============================================================*/
create table IMPUESTO_PORCENTAJE (
    COD_IMPUESTO VARCHAR(3) not null,
    PORCENTAJE NUMERIC(4, 1) not null,
    ESTADO VARCHAR(3) not null default 'ACT' constraint CKC_ESTADO_IMPUESTO check (ESTADO in ('ACT', 'INA')),
    FECHA_INICIO DATE not null,
    FECHA_FIN DATE null,
    constraint PK_IMPUESTO_PORCENTAJE primary key (COD_IMPUESTO, PORCENTAJE)
);

/*==============================================================*/
/* Table: PRODUCTO                                              */
/*==============================================================*/
create table PRODUCTO (
    COD_PRODUCTO VARCHAR(20) not null,
    NOMBRE VARCHAR(100) not null,
    DESCRIPCION VARCHAR(500) null,
    PRECIO NUMERIC(10, 2) not null,
    EXISTENCIA NUMERIC(10, 2) not null default 0,
    ESTADO VARCHAR(3) not null default 'ACT' constraint CKC_ESTADO_PRODUCTO check (ESTADO in ('ACT', 'INA')),
    IVA VARCHAR(1) not null constraint CKC_IVA_PRODUCTO check (IVA in ('S', 'N')),
    ICE VARCHAR(1) not null constraint CKC_ICE_PRODUCTO check (ICE in ('S', 'N')),
    constraint PK_PRODUCTO primary key (COD_PRODUCTO)
);

/*==============================================================*/
/* Index: IDX_PROD_NOMBRE                                       */
/*==============================================================*/
create index IDX_PROD_NOMBRE on PRODUCTO (NOMBRE);

alter table
    FACTURA
add
    constraint FK_FACTURA_CLIENTE foreign key (COD_CLIENTE) references CLIENTE (COD_CLIENTE) on delete restrict on update restrict;

alter table
    FACTURA_DETALLE
add
    constraint FK_FACDETALLE_FACTURA foreign key (COD_FACTURA) references FACTURA (COD_FACTURA) on delete restrict on update restrict;

alter table
    FACTURA_DETALLE
add
    constraint FK_FACDETALLE_PRODUCTO foreign key (COD_PRODUCTO) references PRODUCTO (COD_PRODUCTO) on delete restrict on update restrict;

alter table
    FACTURA_DETALLE_IMPUESTO
add
    constraint FK_FACDETIMP_FACDET foreign key (COD_FACTURA_DETALLE) references FACTURA_DETALLE (COD_FACTURA_DETALLE) on delete restrict on update restrict;

alter table
    FACTURA_DETALLE_IMPUESTO
add
    constraint FK_FACDETIMP_IMPPORCEN foreign key (COD_IMPUESTO, PORCENTAJE) references IMPUESTO_PORCENTAJE (COD_IMPUESTO, PORCENTAJE) on delete restrict on update restrict;

alter table
    FACTURA_IMPUESTO
add
    constraint FK_FACIMP_FACTURA foreign key (COD_FACTURA) references FACTURA (COD_FACTURA) on delete restrict on update restrict;

alter table
    FACTURA_IMPUESTO
add
    constraint FK_FACIMP_IMPPORCENTAJ foreign key (COD_IMPUESTO, PORCENTAJE) references IMPUESTO_PORCENTAJE (COD_IMPUESTO, PORCENTAJE) on delete restrict on update restrict;

alter table
    IMPUESTO_PORCENTAJE
add
    constraint FK_IMPUESTOPORCE_IMPUESTO foreign key (COD_IMPUESTO) references IMPUESTO (COD_IMPUESTO) on delete restrict on update restrict;