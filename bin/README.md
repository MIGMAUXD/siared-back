# SIREDSE-BE

Este es el backend hecho en base a microservicios con springboot, JWT y postgreSQL del aplicativo web para gestionar los semilleros de la UFPS semillero de invetigacion.

## registro

`POST localhost:8080/auth/signup`

`POST localhost:8080/auth/signin`

```json
{
    "name": "jonnyjaimes",
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023",
    "role": "USER",
    "codigoUniversidad": "1151743",
    "semestreActual":10,
    "edad": 23,
    "direccionResidencia":"cucuta calle 2",
    "celular":"3133713137"
}
{
    "name": "jonnyadmin",
    "email": "admin@ufps.co",
    "password": "ufps2023",
    "role": "ADMIN",
    "codigoUniversidad": "1151743",
    "semestreActual":10,
    "edad": 23,
    "direccionResidencia":"cucuta calle 2",
    "celular":"3133713137"
}
```

```json
{
    "email": "jonnyjaimes@ufps.co",
    "password": "ufps2023"
}
```

# Postman Importe
[Descarga el archivo aquí](https://github.com/JulianRivers/SIREDSE-BE/blob/dev/SIADSE.postman_collection.json)

https://github.com/JulianRivers/SIREDSE-BE/blob/dev/SIADSE.postman_collection.json

## Normatividad

### Crear Semillero

URL: `POST /public/seedbed`

Descripción: Este endpoint se utiliza para crear un nuevo semillero de investigación.

Body:

```json
{
  "nombre": "Semillero de Investigación en Inteligencia Artificial",
  "descripcion": "Este semillero se enfoca en la investigación de técnicas de inteligencia artificial para resolver problemas complejos."
}
```

#### Respuesta Exitosa (Código 200)

```json
{
    "id": 1,
    "nombre": "Semillero de Investigación en Inteligencia Artificial",
    "descripcion": "Este semillero se enfoca en la investigación de técnicas de inteligencia artificial para resolver problemas complejos.",
    "fechaCreacion": "2024-04-07T23:07:14.950+00:00",
    "fechaActualizacion": "2024-04-07T23:07:14.950+00:00"
}
```

### Subir Normatividad

**URL:** `POST /public/regulation`

**Descripción**: Este endpoint se utiliza para subir un archivo de normatividad a un semillero específico.

**Parámetros de la Petición:**

file: El archivo de normatividad a subir.
semilleroId: El ID del semillero al que se asociará la normatividad.

#### Respuesta Exitosa (Código 200)

```json
{
  "id": 1,
  "titulo": "Normativa AI",
  "fechaSubida": "2024-04-07T12:00:00Z",
  "url": "https://s3.amazonaws.com/bucket/normativas/Normativa_AI.pdf",
  "keyFile": "Normativa_AI.pdf",
  "formato": "pdf",
  "idSemillero": 1
}
```
