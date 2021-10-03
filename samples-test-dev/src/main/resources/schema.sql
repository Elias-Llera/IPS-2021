--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
DROP TABLE IF EXISTS carreras;
DROP TABLE IF EXISTS atletas;
DROP TABLE IF EXISTS inscripciones;

CREATE TABLE carreras(
	id INT PRIMARY KEY NOT NULL, 
	inicio DATE NOT NULL, 
	fin DATE NOT NULL, 
	fecha DATE NOT NULL, 
	descr VARCHAR(32), 
	CHECK(inicio<=fin), 
	CHECK(fin<fecha)
);
CREATE TABLE atletas(
	email VARCHAR(32) PRIMARY KEY NOT NULL, 
	name VARCHAR(32) NOT NULL, 
	surname VARCHAR(32) NOT NULL, 
	birthDATE DATE NOT NULL, 
	isMale BOOLEAN NOT NULL
);

CREATE table inscripciones(
	id_carrera INT NOT NULL,
	email_atleta VARCHAR(32) NOT NULL,
	estado VARCHAR(32) NOT NULL,
	dorsal INT NOT NULL,
	PRIMARY KEY(id_carrera, email_atleta)
);


