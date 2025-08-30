-- =====================================
-- V2__insert_data.sql - Datos de prueba
-- =====================================

-- Rubros
INSERT INTO rubros (nombre_rubro) VALUES ('Tecnología');
INSERT INTO rubros (nombre_rubro) VALUES ('Finanzas');
INSERT INTO rubros (nombre_rubro) VALUES ('Construcción');
INSERT INTO rubros (nombre_rubro) VALUES ('Salud');
INSERT INTO rubros (nombre_rubro) VALUES ('Educación');

-- Tipos de Contrato
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Indefinido');
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Plazo Fijo');
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Por Obra');
INSERT INTO tipos_contrato (nombre_tipo) VALUES ('Honorarios');

-- Empresas
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('Tech Solutions', 1);
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('FinCorp', 2);
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('Constructora del Sur', 3);
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('Hospital San Juan', 4);
INSERT INTO empresas (nombre_empresa, id_rubro) VALUES ('Colegio Los Andes', 5);

-- Estado de Estudio
INSERT INTO estado_estudio (descripcion) VALUES ('En curso');
INSERT INTO estado_estudio (descripcion) VALUES ('Finalizado');
INSERT INTO estado_estudio (descripcion) VALUES ('Egresado');
INSERT INTO estado_estudio (descripcion) VALUES ('Titulado');

-- Carreras
INSERT INTO carreras (nombre_carrera) VALUES ('Ingeniería en Informática');
INSERT INTO carreras (nombre_carrera) VALUES ('Contabilidad');
INSERT INTO carreras (nombre_carrera) VALUES ('Construcción Civil');
INSERT INTO carreras (nombre_carrera) VALUES ('Enfermería');
INSERT INTO carreras (nombre_carrera) VALUES ('Pedagogía en Matemáticas');

-- Instituciones
INSERT INTO instituciones (nombre_institucion) VALUES ('Universidad de Chile');
INSERT INTO instituciones (nombre_institucion) VALUES ('Pontificia Universidad Católica');
INSERT INTO instituciones (nombre_institucion) VALUES ('CFT INACAP');
INSERT INTO instituciones (nombre_institucion) VALUES ('Instituto Profesional AIEP');
INSERT INTO instituciones (nombre_institucion) VALUES ('Universidad de Santiago');

-- Datos Personales
INSERT INTO datos_personales (nombres, apellidos, rut, fecha_nacimiento, sexo, nacionalidad, estado_civil, telefono, correo, direccion, discapacidad)
VALUES ('Juan Carlos', 'Pérez González', '12345678-9', '1990-05-15', 'Masculino', 'Chilena', 'Soltero', '912345678', 'juan.perez@mail.com', 'Calle Falsa 123', FALSE);

INSERT INTO datos_personales (nombres, apellidos, rut, fecha_nacimiento, sexo, nacionalidad, estado_civil, telefono, correo, direccion, discapacidad)
VALUES ('María José', 'González López', '98765432-1', '1992-08-20', 'Femenino', 'Chilena', 'Casada', '987654321', 'maria.gonzalez@mail.com', 'Avenida Siempre Viva 742', FALSE);

INSERT INTO datos_personales (nombres, apellidos, rut, fecha_nacimiento, sexo, nacionalidad, estado_civil, telefono, correo, direccion, discapacidad)
VALUES ('Pedro Antonio', 'Silva Morales', '11222333-4', '1988-12-03', 'Masculino', 'Chilena', 'Divorciado', '911222333', 'pedro.silva@mail.com', 'Pasaje Los Aromos 456', FALSE);

-- Antecedentes Laborales
INSERT INTO antecedentes_laborales (cargo, fecha_inicio, fecha_fin, sueldo, motivo, descripcion, id_datos_personales, id_empresa, id_tipo_contrato)
VALUES ('Desarrollador Full Stack', '2020-01-15', '2023-12-31', 1500000, 'Mejor oportunidad', 'Desarrollo de aplicaciones web', 1, 1, 1);

INSERT INTO antecedentes_laborales (cargo, fecha_inicio, fecha_fin, sueldo, motivo, descripcion, id_datos_personales, id_empresa, id_tipo_contrato)
VALUES ('Analista Contable', '2016-03-01', '2020-06-30', 900000, 'Cambio de área', 'Gestión contable', 2, 2, 2);

INSERT INTO antecedentes_laborales (cargo, fecha_inicio, fecha_fin, sueldo, motivo, descripcion, id_datos_personales, id_empresa, id_tipo_contrato)
VALUES ('Jefe de Obra', '2015-01-10', '2020-11-20', 1800000, 'Reducción de personal', 'Supervisión de obras', 3, 3, 1);

-- Información Estudios
INSERT INTO informacion_estudios (anio_inicio, anio_fin, id_datos_personales, id_institucion, id_carrera, id_estado_estudio)
VALUES (2008, 2012, 1, 1, 1, 4); -- Juan, Ingeniería en Informática, Universidad de Chile, Titulado

INSERT INTO informacion_estudios (anio_inicio, anio_fin, id_datos_personales, id_institucion, id_carrera, id_estado_estudio)
VALUES (2010, 2014, 2, 2, 2, 4); -- María, Contabilidad, Pontificia Universidad Católica, Titulado

INSERT INTO informacion_estudios (anio_inicio, anio_fin, id_datos_personales, id_institucion, id_carrera, id_estado_estudio)
VALUES (2007, 2009, 3, 3, 3, 4); -- Pedro, Construcción Civil, CFT INACAP, Titulado