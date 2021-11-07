--sqlite
--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
--Eliminar tablas anteriores
DROP TABLE IF EXISTS carreras;
DROP TABLE IF EXISTS atletas;
DROP TABLE IF EXISTS inscripciones;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS PlazosDeInscripcion;
DROP TABLE IF EXISTS Clasificaciones;


CREATE TABLE carreras(
	idCarrera INT PRIMARY KEY NOT NULL,
	nombre TEXT NOT NULL,
	tipo TEXT NOT NULL,
	descripcion TEXT NOT NULL, 
	fecha TEXT NOT NULL, 
	plazas INT NOT NULL, 
	distancia INT NOT NULL,
	precio TEXT NOT NULL

);

CREATE TABLE atletas(
	emailAtleta TEXT PRIMARY KEY NOT NULL, 
	nombre TEXT NOT NULL, 
	apellido TEXT NOT NULL, 
	fechaNacimiento DATE NOT NULL, 
	sexo TEXT NOT NULL
);

CREATE table inscripciones(
	idCarrera INT NOT NULL,
	emailAtleta TEXT NOT NULL,
	estado TEXT NOT NULL,
	dorsal INT NOT NULL,
    idCategoria INT NOT NULL,
	PRIMARY KEY(idCarrera, emailAtleta),
	FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera),
	FOREIGN KEY (emailAtleta) REFERENCES Atletas (emailAtleta),
	FOREIGN KEY (idCategoria) REFERENCES Atletas (idCategoria)
);

create table Categorias(
    idCategoria INT PRIMARY KEY NOT NULL,
    idCarrera INT NOT NULL,
    nombre TEXT,
    edadInicio INT,
    edadFinal INT,
    sexo TEXT,
    
	FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera)
);

create table PlazosDeInscripcion(
    idPlazosDeInscripcion INT PRIMARY KEY NOT NULL,
    idCarrera INT NOT NULL,
    fechaInicio TEXT,
    fechaFin TEXT,
    precio INT,

    
    FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera)
);

create table Clasificaciones(
    idCarrera INT NOT NULL,
    idCategoria INT NOT NULL,
    nombreAtleta STRING NOT NULL,
    genero TEXT NOT NULL,
    posicion INT,
    tiempo TEXT NOT NULL,

    
    FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera)
);

