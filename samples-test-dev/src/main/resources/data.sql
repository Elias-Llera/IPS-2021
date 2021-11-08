--Datos para carga inicial de la base de datos
delete
from carreras;

insert into carreras(idCarrera, nombre, tipo, descripcion, fecha,
                     plazas, distancia, precio)
values (100, 'Barkley', 'Montaña', 'aaaaa', '2021-07-09', 100, 6, 'No disponible'),
       (101, 'Barkley2', 'Montaña', 'bbbb','2022-03-10', 90, 7,'No disponible'),
       (102, 'Barkley3', 'Asfalto', 'ccccc', '2022-03-20', 80, 9,'No disponible'),
       (103, 'Barkley4', 'Asfalto', 'ddddd', '2022-11-21', 70, 30,'No disponible'),
       (104, 'Barkley5', 'Montaña', 'fffff', '2022-12-10', 60, 19,'No disponible'),
       (105, 'Barkley6', 'Asfalto', 'ccccc', '2021-03-20', 80, 9,'No disponible');

insert into atletas(emailAtleta, nombre, apellido, fechaNacimiento, sexo)
values ('ana@gmail.com', 'Ana', 'Perez', '2000-06-14', 'Mujer'),
       ('rosa@gmail.com', 'Rosa', 'Perez', '2000-03-24', 'Mujer'),
       ('juan@gmail.com', 'Juan', 'Perez', '2000-12-30', 'Hombre'),
       ('pedro@gmail.com', 'Pedro', 'Perez', '2000-01-14', 'Hombre'),
       ('eugenio@hotmail.com', 'Eugenio', 'Perez', '1965-01-22', 'Hombre');

insert into categorias(idCategoria, idCarrera, edadInicio, edadFinal, sexo, nombre)
values (1, 102, 18, 90, 'Hombre', 'Barklay absoluta masculina'),
       (2, 104, 18, 90, 'Mujer', 'Barklay absoluta femenina');

insert into inscripciones(idCarrera, emailAtleta, estado, dorsal, idCategoria)
values (102, 'ana@gmail.com', 'PRE-INSCRITO', 1, 2),
       (102, 'juan@gmail.com', 'PRE-INSCRITO', 2, 1);

insert into clasificaciones(idCarrera, idCategoria,nombreCategoria, nombreAtleta, genero, posicion, tiempo)
values (102, 1, 'Barklay absoluta masculina', 'juan', 'masculino', 1, '01:30:54'),
       (102, 1, 'Barklay absoluta masculina','pedro', 'masculino', 4, '---'),
       (102, 1, 'Barklay absoluta masculina','alberto', 'masculino', 3, '01:30:54'),
       (102, 1, 'Barklay absoluta masculina','manuel', 'masculino', 2, '01:30:54');
       

