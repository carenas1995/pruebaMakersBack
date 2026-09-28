# 🏦 Sistema de Gestión de Préstamos (Loan Management System)

Sistema web **full-stack** para la solicitud, consulta y administración de préstamos bancarios. Cuenta con autenticación basada en JWT y control de acceso basado en roles (RBAC), con una arquitectura que separa las responsabilidades del backend y el frontend.

---

## 🛠️ Tecnologías utilizadas

### Backend

* **Java:** 17+
* **Spring Boot:** 3.x
* **Spring Security:** autenticación JWT y autorización por roles.
* **Spring Data JPA / Hibernate:** persistencia y gestión de datos.
* **Base de datos:** MySQL (Aiven Cloud o instancia local).
* **Gestor de dependencias:** Apache Maven.

### Frontend

* **Node.js:** 18.x LTS o 20.x LTS.
* **Angular:** 17.x.
* **RxJS / HttpClientModule:** comunicación reactiva con la API REST.
* **ngx-translate:** internacionalización (i18n).
* **Reactive Forms:** formularios reactivos y validaciones.

---

## ✨ Funcionalidades principales

### 👤 Usuario

* Iniciar sesión mediante autenticación JWT.
* Solicitar préstamos indicando el monto y el plazo.
* Consultar el historial de solicitudes personales.
* Consultar el estado de los préstamos.

### 🛡️ Administrador

* Consultar las solicitudes de préstamo del sistema.
* Aprobar o rechazar solicitudes.
* Gestionar el estado de los préstamos mediante endpoints protegidos.

---

## 🔐 Seguridad y autenticación

El sistema utiliza autenticación sin estado (*stateless*) mediante JSON Web Tokens (JWT) y control de acceso basado en roles (RBAC).

### Autenticación

El token JWT se envía en la cabecera HTTP `Authorization` mediante el esquema `Bearer`:

```http
Authorization: Bearer <token>
```

### Roles de usuario

| Rol     | Permisos                                                              |
| ------- | --------------------------------------------------------------------- |
| `USER`  | Solicitar préstamos y consultar su historial personal.                |
| `ADMIN` | Consultar los préstamos del sistema y aprobar o rechazar solicitudes. |

### Protección de rutas y solicitudes

* **Angular `JwtInterceptor`:** adjunta automáticamente el token JWT a las solicitudes HTTP protegidas.
* **Angular Guards:** controlan el acceso a las rutas según el rol del usuario.
* **Spring Security:** protege los endpoints de la API y aplica las reglas de autorización.
* **`JwtAuthenticationFilter`:** valida el token JWT e incorpora las autoridades del usuario al `SecurityContextHolder`.

### Endpoints principales

| Método | Endpoint                        | Descripción                                                          |
| ------ | ------------------------------- | -------------------------------------------------------------------- |
| GET    | `/api/prestamos/misprestamos`   | Consultar los préstamos del usuario autenticado.                     |
| GET    | `/api/prestamos`                | Consultar los préstamos del sistema, según los permisos del usuario. |
| PUT    | `/api/prestamos/{id}/aprobado`  | Aprobar una solicitud.                                               |
| PUT    | `/api/prestamos/{id}/rechazado` | Rechazar una solicitud.                                              |

---

## 🔑 Credenciales de prueba

El sistema contempla dos perfiles de acceso para probar los flujos principales.

| Perfil        | Correo electrónico | Contraseña |
| ------------- | ------------------ | ---------- |
| Usuario       | `usuario@test.com` | `123`      |
| Administrador | `admin@test.com`   | `123`      |

> **Nota:** Estas credenciales son exclusivamente para pruebas y desarrollo. No deben utilizarse en un entorno de producción.

---

## 🗄️ Esquema de base de datos

La aplicación utiliza una base de datos MySQL denominada `makers`.

### Tabla `users`

Almacena la información de los usuarios del sistema.

| Campo      | Descripción                                                |
| ---------- | ---------------------------------------------------------- |
| `id`       | Identificador del usuario.                                 |
| `email`    | Correo electrónico.                                        |
| `password` | Contraseña almacenada según la configuración de seguridad. |
| `role`     | Rol del usuario (`USER` o `ADMIN`).                        |

### Tabla `prestamos`

Almacena las solicitudes de préstamo y su información asociada.

| Campo         | Descripción                                          |
| ------------- | ---------------------------------------------------- |
| `id`          | Identificador del préstamo.                          |
| `user_id`     | Clave foránea que relaciona el préstamo con `users`. |
| `amount`      | Monto solicitado.                                    |
| `term_months` | Plazo del préstamo en meses.                         |
| `status`      | Estado de la solicitud.                              |
| `created_at`  | Fecha de creación de la solicitud.                   |

---

## 🚀 Configuración e instalación

### 1. Requisitos previos

Antes de iniciar, asegúrate de tener instalado:

* **JDK:** versión 17 o superior.
* **Apache Maven:** versión 3.8 o superior.
* **Node.js:** versión 18.x LTS o 20.x LTS.
* **Angular CLI:** versión 17.x.
* **MySQL:** instancia local o remota, como Aiven Cloud.
* **Git:** para clonar el repositorio.

Instalación de Angular CLI:

```bash
npm install -g @angular/cli@17
```

---

### 2. Configuración de la base de datos

Ejecuta el script SQL disponible en:

```text
backend/src/main/resources/script.sql
```

El script contiene la estructura inicial de la base de datos `makers`, las tablas `users` y `prestamos`, y los registros iniciales de prueba.

> Si el script se encuentra en otra ubicación dentro del repositorio, utiliza la ruta correspondiente.

---

### 3. Configuración y ejecución del backend

#### Paso A. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd <NOMBRE_DEL_REPOSITORIO>
```

#### Paso B. Ingresar a la carpeta del backend

```bash
cd backend
```

#### Paso C. Configurar la conexión a MySQL

Edita el archivo:

```text
src/main/resources/application.properties
```

Configura las siguientes propiedades con los datos de tu instancia:

```properties
spring.datasource.url=jdbc:mysql://<HOST>:<PUERTO>/makers?useSSL=false&serverTimezone=UTC
spring.datasource.username=<TU_USUARIO>
spring.datasource.password=<TU_CONTRASENA>

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**Importante:** reemplaza los valores de ejemplo por las credenciales de tu entorno. No publiques contraseñas reales en el repositorio.

#### Paso D. Compilar el proyecto

```bash
mvn clean install
```

#### Paso E. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

El backend estará disponible en:

```text
http://localhost:8080
```

---

### 4. Configuración y ejecución del frontend

Abre una nueva terminal.

#### Paso A. Ingresar a la carpeta del frontend

Desde la raíz del repositorio:

```bash
cd frontend
```

#### Paso B. Instalar las dependencias

```bash
npm install
```

#### Paso C. Iniciar el servidor de desarrollo

```bash
ng serve
```

#### Paso D. Acceder a la aplicación

Abre el navegador e ingresa a:

```text
http://localhost:4200
```

---

## 📁 Estructura del proyecto frontend

El frontend está organizado en capas para separar la infraestructura, la lógica de negocio y la presentación.

```text
src/
└── app/
    ├── datos/
    │   ├── http-servicios/    # Servicios REST (AuthService, PrestamoService)
    │   └── interceptor/       # JwtInterceptor para el token Bearer
    │
    ├── dominio/
    │   ├── caso-uso/          # Casos de uso de negocio
    │   ├── interface/         # Modelos e interfaces TypeScript
    │   └── repositorios/      # Contratos de los repositorios
    │
    └── presentacion/
        └── pages/
            └── home/          # HomeComponent: formulario y listado
```

### Responsabilidades de las capas

| Capa           | Responsabilidad                                                |
| -------------- | -------------------------------------------------------------- |
| `datos`        | Comunicación con la API REST e implementación de repositorios. |
| `dominio`      | Reglas de negocio, casos de uso, interfaces y contratos.       |
| `presentacion` | Componentes, formularios e interacción con el usuario.         |

---

## 🧪 Pruebas

El backend utiliza el ecosistema de pruebas de Spring Boot y JUnit 5 para verificar las operaciones principales.

Para ejecutar las pruebas del backend:

```bash
mvn test
```

---

## 🌐 Internacionalización

El frontend utiliza `ngx-translate` para facilitar la internacionalización de la interfaz.

Esta integración permite administrar los textos de la aplicación mediante archivos de traducción y mantener separada la presentación del contenido en cada idioma.

---

## 📌 Notas

* La aplicación está diseñada para ejecutarse en un entorno local de desarrollo.
* Las credenciales incluidas son únicamente para pruebas.
* La configuración de conexión a la base de datos debe ajustarse al entorno donde se despliegue.
* La autorización de las operaciones administrativas se valida en el backend mediante Spring Security.

---

## 👨‍💻 Autor

Desarrollado como parte de una prueba técnica para la construcción de un sistema de gestión de préstamos bancarios utilizando Spring Boot y Angular.
