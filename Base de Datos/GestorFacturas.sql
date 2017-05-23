/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     23/5/2017 17:42:09                           */
/*==============================================================*/


drop table if exists CLIENTE;

drop table if exists FACTURA;

drop table if exists PROVEEDOR;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE
(
   IDCLIENTE            varchar(5) not null,
   NOMBRECLIENTE        varchar(50) not null,
   RUCCLIENTE           varchar(13) not null,
   primary key (IDCLIENTE)
);

/*==============================================================*/
/* Table: FACTURA                                               */
/*==============================================================*/
create table FACTURA
(
   IDFACTURA            varchar(5) not null,
   IDCLIENTE            varchar(5) not null,
   IDPROVEEDOR          varchar(5) not null,
   FECHAEMISION         date not null,
   TOTALALIMENTACION    float,
   TOTALEDUCACION       float,
   TOTALVIVIENDA        float,
   TOTALSALUD           float,
   TOTALVESTIMENTA      float,
   TOTALOTROS           float,
   SUBTOTALSINIVA       float not null,
   SUBTOTALIVA          float not null,
   DESCUENTO            float not null,
   TOTALFACTURA         float not null,
   primary key (IDFACTURA)
);

/*==============================================================*/
/* Table: PROVEEDOR                                             */
/*==============================================================*/
create table PROVEEDOR
(
   IDPROVEEDOR          varchar(5) not null,
   NOMBREPROVEEDOR      varchar(50) not null,
   RUCPROVEEDOR         varchar(13) not null,
   CIUDADPROVEEDOR      varchar(25) not null,
   DIRECCIONPROVEEDOR   varchar(100) not null,
   primary key (IDPROVEEDOR)
);

alter table FACTURA add constraint FK_CLIENTE_FACTURA foreign key (IDCLIENTE)
      references CLIENTE (IDCLIENTE) on delete restrict on update restrict;

alter table FACTURA add constraint FK_PROVEEDOR_FACTURA foreign key (IDPROVEEDOR)
      references PROVEEDOR (IDPROVEEDOR) on delete restrict on update restrict;

