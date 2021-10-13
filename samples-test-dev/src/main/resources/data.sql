--Datos para carga inicial de la base de datos
delete from carreras;

insert into carreras(idCarrera,nombre,tipo,descripcion,inicioInscripcion, finInscripcion,precioInscripcion,fecha,plazas,distancia) values 
	(100,'Barkley','Montaña','aaaaa','2016-10-05','2016-10-25',10,'2016-11-09',100, 6),
	(101,'Barkley2','Montaña','bbbb','2016-10-05','2016-10-25',12.4,'2016-11-10',90,7),
	(102,'Barkley3','Asfalto','ccccc','2016-11-05','2016-11-09',12,'2016-11-20',80,9),
	(103,'Barkley4','Asfalto','ddddd','2016-11-10','2016-11-15',16.9,'2016-11-21',70, 30),
	(104,'Barkley5','Montaña','fffff','2016-11-01','2016-11-07',17,'2016-11-10',60, 19);


