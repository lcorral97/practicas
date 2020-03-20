Drop Table departamentos;
Drop Table empleados;

Create table departamentos(
codDepto varchar(4) primary key,
nombreDepto varchar(20),
ciudad varchar(15)/*,
codDirector varchar(12)*/);

Create table empleados(
nDIEmp varchar(12) primary key,
nomEmp varchar(30),
sexEmp char(1),
fecNac Date,
fecIncorporacion Date,
salEmp float,
cargoE varchar(15),
jefeId varchar(12) references empleados(nDIEmp),
codDepto varchar(4) references departamentos(codDepto),
comisionE float);

Alter Table departamentos
Add codDirector varchar(12) references empleados(nDIEmp);

Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE) values ('AA0001','Juan García', 'V', '1985-02-07', '1999-11-17', 35000, 'Director', 1500);
Insert into departamentos values ('1000', 'Dirección General', 'Madrid', 'AA0001');
Update empleados set codDepto = '1000' Where nDIEmp = 'AA0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE) values ('IN0001','Álvaro Calvo', 'V', '1987-05-22', '2002-08-13', 32400, 'Jefe Inf.', 1000);
Insert into departamentos values ('2000', 'Informática', 'Madrid', 'IN0001');
Update empleados set codDepto = '2000' Where nDIEmp = 'IN0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('IN0002','Cintia Balaguer', 'M', '1990-06-01', '2003-02-07', 25000, 'Trabajador', 500, '2000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('IN0003','Marta Márquez', 'M', '1977-12-12', '1999-11-30', 27500, 'Trabajador', 700, '2000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('IN0004','José Carlos Alcántara', 'V', '1998-01-11', '2000-12-12', 26000, 'Trabajador', 500, '2000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('IN0005','Sara Ferrés', 'M', '1989-02-10', '2012-05-27', 23000, 'Secretaria', 800, '2000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE) values ('MK0001','Ainhoa Liébana', 'M', '1985-10-05', '2000-10-14', 30750, 'Jefe Mark.', 950);
Insert into departamentos values ('3000', 'Marketing', 'Barcelona', 'MK0001');
Update empleados set codDepto = '3000' Where nDIEmp = 'MK0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('MK0002','Josep Clavell', 'V', '1966-12-02', '2005-07-18', 29000, 'Trabajador', 1000, '3000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('MK0003','Carlos Escarol', 'V', '1991-03-15', '2017-01-15', 26600, 'Trabajador', 450, '3000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('MK0004','Juan José Sabater', 'V', '1966-01-14', '1999-12-23', 27000, 'Secretaria', 650, '3000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('MK0005','Anaïs Valtière', 'M', '1996-08-15', '2009-09-14', 24500, 'Secretaria', 700, '3000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE) values ('RH0001','Ariadna Sevilla', 'M', '1972-02-29', '2003-08-19', 30900, 'Jefe RRHH.', 550);
Insert into departamentos values ('4000', 'RRHH', 'Valencia', 'RH0001');
Update empleados set codDepto = '4000' Where nDIEmp = 'RH0001';
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('RH0002','María de la Rosa', 'M', '1994-11-05', '2000-10-11', 24550, 'Trabajador', 880, '4000');
Insert into empleados(nDIEmp, nomEmp, sexEmp, fecNac, fecIncorporacion, salEmp, cargoE, comisionE, codDepto) values ('RH0003','Sergio Calvià', 'V', '1998-03-11', '2010-10-20', 25500, 'Secretaria', 650, '4000');
Update empleados set jefeId='IN0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '2000');
Update empleados set jefeId='MK0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '3000');
Update empleados set jefeId='RH0001' Where nDIEmp In (Select nDIEmp From empleados Where codDepto = '4000');
Update empleados set jefeId='AA0001' Where nDIEmp In (Select nDIEmp From empleados Where Lower(CargoE) Like 'jefe%');

Select * From Empleados;
Select * From Departamentos;

/*Alter table -tabla- drop constraint -contraint; tabla sys.constraints-;

SELECT c.constraintname, t.tablename
    FROM sys.sysconstraints c join sys.systables t on c.tableid = t.tableid;*/
    
//Select * From Departamentos;
//Select * From Empleados;
/*Delete from sys.sysconstraints where tablename="DEPARTAMENTOS" Or tablename="EMPLEADOS";*/

//1.
//Select * From Empleados;

//2.
//Select * From Departamentos;

//3.
//Select * From Empleados Where lower(cargoE)="secretaria";

//4.
//Select nomEmp,salEmp From Empleados;

//5.
//Select * From Empleados Order By nomEmp;

//6.
//Select nombreDepto From Departamentos;

//7.
//Select nomEmp,cargoE From Empleados Order By salEmp;

//8.
//Select salEmp,comisionE From Empleados Where codDepto="2000";

//9.
//Select comisionE From Empleados;

//10.
//Select ? From Empleados Where codDepto="3000" Order By nomEmp;