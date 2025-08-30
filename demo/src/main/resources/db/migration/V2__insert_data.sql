
-- =====================================
-- V2__insert_data.sql - Datos de prueba
-- =====================================

-- Rubros
INSERT INTO rubros (nombre_rubro) VALUES ('Tecnología');
INSERT INTO rubros (nombre_rubro) VALUES ('Finanzas');

-- Tipos de Contrato
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Indefinido');
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Plazo Fijo');

-- Empresas
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('Tech Solutions', 1);
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('FinCorp', 2);

-- Estado de Estudio
INSERT INTO estado_estudio (descripcion) VALUES ('En curso');
INSERT INTO estado_estudio (descripcion) VALUES ('Finalizado');

-- Carreras
INSERT INTO carreras (nombre_carrera) VALUES ('Ingeniería en Informática');
INSERT INTO carreras (nombre_carrera) VALUES ('Contabilidad');

-- Instituciones
INSERT INTO instituciones (nombre_institucion) VALUES ('Universidad de Chile');
INSERT INTO instituciones (nombre_institucion) VALUES ('Pontificia Universidad Católica');

-- Datos Personales
INSERT INTO datos_personales (nombres, apellidos, rut, fecha_nacimiento, sexo, nacionalidad, estado_civil, telefono, correo, direccion, discapacidad)
VALUES ('Juan', 'Pérez', '12345678-9', '1990-05-15', 'Masculino', 'Chilena', 'Soltero', '912345678', 'juan.perez@mail.com', 'Calle Falsa 123', FALSE);

INSERT INTO datos_personales (nombres, apellidos, rut, fecha_nacimiento, sexo, nacionalidad, estado_civil, telefono, correo, direccion, discapacidad)
VALUES ('María', 'González', '98765432-1', '1992-08-20', 'Femenino', 'Chilena', 'Casada', '987654321', 'maria.gonzalez@mail.com', 'Avenida Siempre Viva 742', FALSE);

-- Antecedentes Laborales
INSERT INTO antecedentes_laborales (cargo, fecha_inicio, fecha_fin, sueldo, motivo, descripcion, id_datos_personales, id_empresa, id_tipo_contrato)
VALUES ('Desarrollador', '2015-01-01', '2018-12-31', 1200000, 'Mejor oportunidad', 'Desarrollo de software', 1, 1, 1);

INSERT INTO antecedentes_laborales (cargo, fecha_inicio, fecha_fin, sueldo, motivo, descripcion, id_datos_personales, id_empresa, id_tipo_contrato)
VALUES ('Analista Contable', '2016-03-01', '2020-06-30', 1000000, 'Cambio de área', 'Gestión contable', 2, 2, 2);

-- Informacion Estudios
INSERT INTO informacion_estudios (anio_inicio, anio_fin, id_datos_personales, id_institucion, id_carrera, id_estado_estudio)
VALUES (2008, 2012, 1, 1, 1, 2); -- Juan, Ingeniería en Informática, Universidad de Chile, Finalizado

INSERT INTO informacion_estudios (anio_inicio, anio_fin, id_datos_personales, id_institucion, id_carrera, id_estado_estudio)
VALUES (2010, 2014, 2, 2, 2, 2); -- María, Contabilidad, Pontificia Universidad Católica, Finalizado
