--Datos para carga inicial de la base de datos
delete from carreras;

insert into carreras(idCarrera,nombre,tipo,descripcion,inicioInscripcion, finInscripcion,precioInscripcion,fecha,plazas,distancia) values 
	(100,'Barkley','Montaña','aaaaa','2016-10-05','2016-10-25',10,'2016-11-09',100, 6),
	(101,'Barkley2','Montaña','bbbb','2016-10-05','2016-10-25',12.4,'2016-11-10',90,7),
	(102,'Barkley3','Asfalto','ccccc','2016-11-05','2016-11-09',12,'2016-11-20',80,9),
	(103,'Barkley4','Asfalto','ddddd','2016-11-10','2016-11-15',16.9,'2016-11-21',70, 30),
	(104,'Barkley5','Montaña','fffff','2016-11-01','2016-11-07',17,'2016-11-10',60, 19);

insert into atletas(emailAtleta, nombre, apellido, fechaNacimiento, sexo) values
	('ana@gmail.com', 'Ana', 'Perez', '2000-06-14', 'Mujer'),
	('rosa@gmail.com', 'Rosa', 'Perez', '2000-03-24', 'Mujer'),
	('juan@gmail.com', 'Juan', 'Perez', '2000-12-30', 'Hombre'),
	('pedro@gmail.com', 'Pedro', 'Perez', '2000-01-14', 'Hombre'),
	('eugenio@hotmail.com', 'Eugenio', 'Perez', '1965-01-22', 'Hombre');

insert into categorias(idCategoria, idCarrera, edadInicio, edadFinal, sexo, nombre) values
	(1, 100, 18, 90, 'Hombre', 'Barklay absoluta masculina'),
	(2, 100, 18, 90, 'Mujer', 'Barklay absoluta femenina'),

insert into inscripciones(idCarrera, emailAtleta, estado, dorsal, idCategoria) values
	(100, 'ana@gmail.com', 'PRE-INSCRITO', 1 , 2),
	(100, 'juan@gmail.com', 'PRE-INSCRITO', 2, 1);
