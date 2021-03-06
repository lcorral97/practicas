Alter table Departamentos drop constraint fk_emp;
Alter table Empleados drop constraint fk_jefe;
Alter table Empleados drop constraint fk_dpto;
Alter table Departamentos drop constraint pk_dpto;
Alter table Empleados drop constraint pk_emp;

Drop Table departamentos;
Drop Table empleados;

Create table departamentos(
codDepto varchar(4) constraint pk_dpto primary key,
nombreDepto varchar(20),
ciudad varchar(15));

Create table empleados(
nDIEmp varchar(12) constraint pk_emp primary key ,
nomEmp varchar(30),
sexEmp char(1),
fecNac Date,
fecIncorporacion Date,
salEmp float,
cargoE varchar(15),
jefeId varchar(12) constraint fk_jefe references empleados(nDIEmp),
codDepto varchar(4) constraint fk_dpto references departamentos(codDepto),
comisionE float,
password varchar(50),
ciudad varchar(15),
coord varchar(30));

Alter Table departamentos
Add codDirector varchar(12) constraint fk_emp references empleados(nDIEmp);

Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, ciudad, password, coord) values ('AA0001','Juan Garcia', 'V', '1985-02-07', '1999-11-17', 35000, 'Director', 1500, 'Madrid', 'JGAA', '40.4378698,-3.8196207');
Insert into departamentos values ('1000', 'Direccion General', 'Madrid', 'AA0001');
Update empleados set codDepto = '1000' Where nDIEmp = 'AA0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE,ciudad,password, coord) values ('IN0001','Alvaro Calvo', 'V', '1987-05-22', '2002-08-13', 32400, 'Jefe Inf.', 1000, 'Madrid', 'ACIN', '40.4378698,-3.8196207');
Insert into departamentos values ('2000', 'Informatica', 'Madrid', 'IN0001');
Update empleados set codDepto = '2000' Where nDIEmp = 'IN0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('IN0002','Cintia Balaguer', 'M', '1990-06-01', '2003-02-07', 25000, 'Trabajador', 500, '2000','Madrid', 'CBIN', '40.4378698,-3.8196207');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('IN0003','Marta Marquez', 'M', '1977-12-12', '1999-11-30', 27500, 'Trabajador', 700, '2000','Madrid', 'MMIN', '40.4378698,-3.8196207');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('IN0004','Jose Carlos Alcantara', 'V', '1998-01-11', '2000-12-12', 26000, 'Trabajador', 500, '2000','Leganes', 'JCAIN', '40.328117,-3.7837087');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('IN0005','Sara Ferres', 'M', '1989-02-10', '2012-05-27', 23000, 'Secretaria', 800, '2000','Fuenlabrada', 'SFIN', '40.29736,-3.8470579');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, ciudad, password, coord) values ('MK0001','Ainhoa Liebana', 'M', '1985-10-05', '2000-10-14', 30750, 'Jefe Mark.', 950, 'Barcelona', 'ALMK', '41.3948976,2.0787273');
Insert into departamentos values ('3000', 'Marketing', 'Barcelona', 'MK0001');
Update empleados set codDepto = '3000' Where nDIEmp = 'MK0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('MK0002','Josep Clavell', 'V', '1966-12-02', '2005-07-18', 29000, 'Trabajador', 1000, '3000', 'Barcelona', 'JCMK', '41.3948976,2.0787273');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('MK0003','Carlos Escarol', 'V', '1991-03-15', '2017-01-15', 26600, 'Trabajador', 450, '3000', 'Cornella', 'CEMK', '41.3577787,2.0615397');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('MK0004','Juan Jose Sabater', 'V', '1966-01-14', '1999-12-23', 27000, 'Secretaria', 650, '3000', 'Barcelona', 'JJSMK', '41.3948976,2.0787273');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('MK0005','Anais Valtiere', 'M', '1996-08-15', '2009-09-14', 24500, 'Secretaria', 700, '3000', 'Tarragona', 'AVMK', '41.1258642,1.2035638');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, ciudad, password, coord) values ('RH0001','Ariadna Sevilla', 'M', '1972-02-29', '2003-08-19', 30900, 'Jefe RRHH.', 550, 'Valencia', 'ASRH', '39.4079665,-0.5015981');
Insert into departamentos values ('4000', 'RRHH', 'Valencia', 'RH0001');
Update empleados set codDepto = '4000' Where nDIEmp = 'RH0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('RH0002','Maria de la Rosa', 'M', '1994-11-05', '2000-10-11', 24550, 'Trabajador', 880, '4000', 'Valencia', 'MdlRRH', '39.4079665,-0.5015981');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto, ciudad, password, coord) values ('RH0003','Sergio Calvia', 'V', '1998-03-11', '2010-10-20', 25500, 'Secretaria', 650, '4000', 'Valencia', 'SCRH', '39.4079665,-0.5015981');
Update empleados set jefeId='IN0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '2000');
Update empleados set jefeId='MK0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '3000');
Update empleados set jefeId='RH0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '4000');
Update empleados set jefeId='AA0001' Where nDIEmp In (Select nDIEmp From empleados Where Lower(CargoE) Like 'jefe%');