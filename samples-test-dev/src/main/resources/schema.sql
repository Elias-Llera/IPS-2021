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
DROP TABLE IF EXISTS devoluciones;

CREATE TABLE carreras(
	idCarrera INT PRIMARY KEY NOT NULL,
	nombre TEXT NOT NULL,
	tipo TEXT NOT NULL,
	descripcion TEXT NOT NULL, 
	fecha TEXT NOT NULL, 
	plazas INT NOT NULL, 
	distancia INT NOT NULL
);

CREATE TABLE atletas(
	emailAtleta TEXT PRIMARY KEY NOT NULL, 
	nombre TEXT NOT NULL, 
	apellido TEXT NOT NULL, 
	fechaNacimiento DATE NOT NULL, 
	sexo TEXT NOT NULL
);

CREATE TABLE inscripciones(
	idCarrera INT NOT NULL,
	emailAtleta TEXT NOT NULL,
	estado TEXT NOT NULL,
	dorsal INT NOT NULL,
    idCategoria INT NOT NULL,
	fecha TEXT,
	idPlazosDeInscripcion NUMBER NOT NULL,
	PRIMARY KEY(idCarrera, emailAtleta),
	FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera),
	FOREIGN KEY (emailAtleta) REFERENCES Atletas (emailAtleta),
	FOREIGN KEY (idPlazosDeInscripcion) REFERENCES PlazosDeInscripcion (idPlazosDeInscripcion),
	FOREIGN KEY (idCategoria) REFERENCES Categorias (idCategoria)
);

CREATE TABLE Categorias(
    idCategoria INT PRIMARY KEY NOT NULL,
    idCarrera INT NOT NULL,
    nombre TEXT,
    edadInicio INT,
    edadFinal INT,
    sexo TEXT,
    
	FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera)
);

CREATE TABLE PlazosDeInscripcion(
    idPlazosDeInscripcion INT PRIMARY KEY NOT NULL,
    idCarrera INT NOT NULL,
    fechaInicio TEXT,
    fechaFin TEXT,
    precio INT,

    FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera)
);

CREATE TABLE devoluciones(
    emailAtleta TEXT,
    idCarrera INT,
    cantidad NUMBER,

    FOREIGN KEY (idCarrera) REFERENCES Carreras (idCarrera),
	FOREIGN KEY (emailAtleta) REFERENCES Atletas (emailAtleta),
	PRIMARY KEY (emailAtleta, idCarrera)
);