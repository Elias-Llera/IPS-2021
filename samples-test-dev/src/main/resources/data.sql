--Datos para carga inicial de la base de datos
delete from carreras;
delete from atletas;
delete from carreras;
insert into carreras(id,inicio,fin,fecha,descr) values 
	(100,'2016-10-05','2016-10-25','2016-11-09','finalizada'),
	(101,'2016-10-05','2016-10-25','2016-11-10','en fase 3'),
	(102,'2016-11-05','2016-11-09','2016-11-20','en fase 2'),
	(103,'2016-11-10','2016-11-15','2016-11-21','en fase 1'),
	(104,'2016-11-11','2016-11-15','2016-11-22','antes inscripcion');
insert into atletas(email,name,surname,birthDate,isMale) values 
	('a@gmail.com','Alvaro','Lopez','2016-11-09',true),
	('b@gmail.com','Elias','Llera','2016-11-10',true),
	('c@gmail.com','Erick','Unkown','2016-11-20',true),
	('d@gmail.com','Ana','Perez','2016-10-5',false);

insert into carreras(idCarrera,emailAtleta,estado,dorsal) values 
	(100,'a@gmail.com','INSCRITO',1),
	(101,'b@gmail.com','INSCRITO',2),
	(102,'c@gmail.com','INSCRITO',3),
	(103,'d@gmail.com','PREINSCRITO',4);

