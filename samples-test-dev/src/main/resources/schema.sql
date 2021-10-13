--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
DROP TABLE IF EXISTS carreras;
DROP TABLE IF EXISTS atletas;
DROP TABLE IF EXISTS inscripciones;

<<<<<<< HEAD
--Eliminar tablas anteriores
drop table Carreras;
drop table Inscritos;
drop table Atleta;
drop table Categoria;

--Crear nuevas tablas
create table Carrera (
    id int primary key not null, 
    nombre varchar not null, 
    tipo varchar(6) not null,
    descripcion varchar(32), 
    inicio_inscripcion date not null, 
    fin_inscripcion date not null, 
    precio_inscripcion float not null, 
    fecha date not null,
    plazas int,
    check(inicio<=fin), 
    check(fin<fecha)
=======
CREATE TABLE carreras(
	idCarrera INT PRIMARY KEY NOT NULL,
	nombre VARCHAR(32) NOT NULL,
	tipo VARCHAR(32) NOT NULL,
	descripcion VARCHAR(32) NOT NULL, 
	inicioInscripcion DATE NOT NULL, 
	finInscripcion DATE NOT NULL,
	precioInscripcion REAL NOT NULL,
	fecha DATE NOT NULL, 
	plazas INT NOT NULL, 
	distancia INT NOT NULL,
	CHECK(inicioInscripcion<=finInscripcion), 
	CHECK(finInscripcion<fecha)
);
CREATE TABLE atletas(
	email VARCHAR(32) PRIMARY KEY NOT NULL, 
	name VARCHAR(32) NOT NULL, 
	surname VARCHAR(32) NOT NULL, 
	birthDATE DATE NOT NULL, 
	isMale BOOLEAN NOT NULL
>>>>>>> refs/heads/main
);

<<<<<<< HEAD
create table Atleta (
    email varchar primary key not null, 
    nombre varchar, 
    apellido varchar, 
    nacimiento date not null,
    sexo varchar(6)
=======
CREATE table inscripciones(
	id_carrera INT NOT NULL,
	email_atleta VARCHAR(32) NOT NULL,
	estado VARCHAR(32) NOT NULL,
	dorsal INT NOT NULL,
	PRIMARY KEY(id_carrera, email_atleta)
>>>>>>> refs/heads/main
);

<<<<<<< HEAD
create table Inscritos(
    email varchar foreign key references Atleta(email), 
    id_carrera varchar foreign key references Carrera(id), 
    primary key (email, id_carrera), 
    estado varchar(15),
    idCategoria int varchar foreign key references Categoria(id),
    dorsal int
);
=======
>>>>>>> refs/heads/main

<<<<<<< HEAD
create table Categoria(
    id int primary key not null,
    id_carrera foreign key references Carrera(id),
    edad_inicio int,
    edad_final int,
    sexo varchar(6),
    nombre varchar
);
=======
>>>>>>> refs/heads/main
