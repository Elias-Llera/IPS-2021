--Datos para carga inicial de la base de datos
delete
from carreras;

insert into carreras(idCarrera, nombre, tipo, descripcion, fecha,
                     plazas, distancia)
values (100, 'Barkley', 'Montaña', 'aaaaa', '2021-07-09', 100, 6),
       (101, 'Barkley2', 'Montaña', 'bbbb','2022-03-10', 90, 7),
       (102, 'Barkley3', 'Asfalto', 'ccccc', '2023-03-20', 80, 9),
       (103, 'Barkley4', 'Asfalto', 'ddddd', '2022-11-21', 70, 30),
       (104, 'Barkley5', 'Montaña', 'fffff', '2022-12-10', 60, 19),
       (105, 'ej no plazas', 'Montaña', 'fffff', '2020-12-10', 1, 19),
       (107, 'ej no plazos', 'Montaña', 'fffff', '2022-12-10', 50, 19),
       (106, 'ej todo ok', 'Montaña', 'fffff', '2022-12-10', 50, 19),
       (108, 'ej cronometraje', 'Montaña', 'fffff', '2021-11-10', 50, 19);

insert into PlazosDeInscripcion(idPlazosDeInscripcion, idCarrera, fechaInicio, fechaFin, precio)
values  (1, 105, '2020-10-01', '2022-11-11', 5),
        (3, 102, '2019-10-01', '2019-11-11', 5),
	(10, 102, '2019-11-12', '2022-11-14', 15),	
        (2, 106, '2020-10-01', '2022-11-11', 5),
        (4, 108, '2021-10-10', '2021-11-05', 5);

insert into atletas(emailAtleta, nombre, apellido, fechaNacimiento, sexo)
values ('ana@gmail.com', 'Ana', 'Perez', '2000-06-14', 'Mujer'),
       ('rosa@gmail.com', 'Rosa', 'Perez', '2000-03-24', 'Mujer'),
       ('juan@gmail.com', 'Juan', 'Perez', '2000-12-30', 'Hombre'),
       ('pedro@gmail.com', 'Pedro', 'Perez', '2000-01-14', 'Hombre'),
       ('ejemplo1@gmail.com', 'Ejemplo1', 'Ejemplo1', '2000-03-24', 'Mujer'),
       ('ejemplo2@gmail.com', 'Ejemplo2', 'Ejemplo2', '2000-12-30', 'Hombre'),
       ('ejemplo3@gmail.com', 'Ejemplo3', 'Ejemplo3', '2000-01-14', 'Hombre'),
       ('eugenio@hotmail.com', 'Eugenio', 'Perez', '1965-01-22', 'Hombre');

insert into categorias(idCategoria, idCarrera, edadInicio, edadFinal, sexo, nombre)
values (1, 102, 18, 999, 'Hombre', 'Barklay absoluta masculina'),
       (2, 102, 18, 999, 'Mujer', 'Barklay absoluta femenina'),
       (3, 105, 18, 999, 'Mujer', 'Ejemplo absoluta femenina'),
       (4, 106, 18, 999, 'Hombre', 'Ejemplo absoluta femenina'),
       (5, 108, 18, 999, 'Hombre', 'Ejemplo clasificacion masculino'),
       (6, 108, 18, 999, 'Mujer', 'Ejemplo clasificacion femenino');


insert into inscripciones(idCarrera, emailAtleta, estado, dorsal, idCategoria, idPlazosDeInscripcion, ultimaActualizacion, fecha)
values (102, 'ana@gmail.com', 'INSCRITO', 1, 2, 1, '2022-03-18', '2022-03-18'),
       (102, 'juan@gmail.com', 'INSCRITO', 2, 1, 1, '2022-03-15', '2022-03-18'),
       --test clasificacion
       (108, 'ejemplo1@gmail.com', 'INSCRITO', 1, 6, 4, '2021-10-15', '2021-10-15'),
       (108, 'ejemplo2@gmail.com', 'INSCRITO', 2, 5, 4, '2021-10-15', '2021-10-15'),
       (108, 'ejemplo3@gmail.com', 'INSCRITO', 3, 5, 4, '2021-10-15', '2021-10-15'),
       (108, 'rosa@gmail.com', 'INSCRITO', 4, 6, 4, '2021-10-15', '2021-10-15'),
       (108, 'eugenio@hotmail.com', 'INSCRITO', 5, 5, 4, '2021-10-15', '2021-10-15');

insert into PuntosDeControl(idCarrera, nombre)
values  (108, "punto1"),
        (108, "punto2");

insert into tiempos(idCarrera, emailAtleta, tiempo, nombre)
values (102, 'juan@gmail.com','02:25:35', 'FINAL'),
(102, 'ana@gmail.com','01:25:35', 'FINAL');



insert into limiteDorsales(idCarrera, numero, secuencial)
values (102, 5,'secuencial');


--insert into clasificaciones(idCarrera, idCategoria,nombreCategoria, nombreAtleta, genero, posicion, tiempo)
--values (102, 1, 'Barklay absoluta masculina', 'juan', 'masculino', 1, '01:30:54'),
--       (102, 1, 'Barklay absoluta masculina','pedro', 'masculino', 4, '---'),
--       (102, 1, 'Barklay absoluta masculina','alberto', 'masculino', 3, '01:30:54'),
--       (102, 1, 'Barklay absoluta masculina','manuel', 'masculino', 2, '01:30:54');
       

