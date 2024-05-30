DROP TABLE IF EXISTS cambio_estado_radicado CASCADE;
DROP TABLE IF EXISTS pqrs CASCADE;
DROP TABLE IF EXISTS estados_pqrs CASCADE;
DROP TABLE IF EXISTS tipos_pqrs CASCADE;


CREATE TABLE IF NOT EXISTS public.roles (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS estados_pqrs (
                                            id SERIAL PRIMARY KEY,
                                            estado VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tipos_pqrs (
                                          id SERIAL PRIMARY KEY,
                                          tipo VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cambio_estado_radicado(
                                                     id SERIAL PRIMARY KEY,
                                                     fecha_cambio DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS pqrs (
                                    id SERIAL PRIMARY KEY,
                                    anonimo BOOLEAN NOT NULL,
                                    fecha_radicado DATE,
                                    apellido VARCHAR(255),
                                    nombre VARCHAR(255),
                                    cedula VARCHAR(255),
                                    correo VARCHAR(255) NOT NULL,
                                    codigo_radicado VARCHAR(255) NOT NULL,
                                    titulo VARCHAR(255) NOT NULL,
                                    descripcion TEXT NOT NULL
);

ALTER TABLE pqrs
    ADD COLUMN id_estados INT NOT NULL,
    ADD CONSTRAINT fk_estados_id FOREIGN KEY (id_estados) REFERENCES estados_pqrs (id),
    ADD COLUMN id_tipos_pqrs INT NOT NULL,
    ADD CONSTRAINT fk_tipospqrs_id FOREIGN KEY (id_tipos_pqrs) REFERENCES tipos_pqrs (id);

ALTER TABLE cambio_estado_radicado
    ADD COLUMN id_estados INT NOT NULL,
    ADD CONSTRAINT fk_estados_id FOREIGN KEY (id_estados) REFERENCES estados_pqrs (id),
    ADD COLUMN id_pqrs INT NOT NULL,
    ADD CONSTRAINT fk_pqrs_id FOREIGN KEY (id_pqrs) REFERENCES pqrs (id);


INSERT INTO estados_pqrs (estado)
VALUES
    ('PENDIENTE'),
    ('REVISION'),
    ('RESUELTO');

INSERT INTO tipos_pqrs (tipo)
VALUES
    ('PETICION'),
    ('QUEJA'),
    ('RECLAMO'),
    ('SOLICITUD');


INSERT INTO public.roles (id, name) VALUES (1, 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO public.roles (id, name) VALUES (2, 'DIRECTOR') ON CONFLICT DO NOTHING;
INSERT INTO public.roles (id, name) VALUES (3, 'ESTUDIANTE') ON CONFLICT DO NOTHING;