# Sistema de Gestión de Préstamos (Loan Management System)

Sistema web full-stack para la solicitud, consulta y administración de préstamos con autenticación basada en JWT y control de acceso basado en roles (RBAC).

---

## 🛠️ Tecnologías Utilizadas

### **Backend**
* **Java 17+** / **Spring Boot 3+**
* **Spring Security** (Autenticación JWT y Roles)
* **Spring Data JPA** / **Hibernate**
* **MySQL** (Instancia en Aiven Cloud / Local)
* **Maven**

### **Frontend**
* **Angular** (Módulos, Componentes, Servicios)
* **RxJS** / **HttpClientModule**
* **ngx-translate** (Soporte i18n para internacionalización)
* **Reactive Forms**

---

## 🔐 Seguridad y Autenticación

* **Autenticación Stateless**: Uso de JSON Web Tokens (JWT) pasados en la cabecera `Authorization: Bearer <token>`.
* **Roles de Usuario**:
  * `USER`: Permite solicitar préstamos y consultar el historial personal (`/api/prestamos/misprestamos`).
  * `ADMIN`: Acceso total para listar todos los préstamos del sistema y cambiar su estado (`/api/prestamos/{id}/aprobado`, `/api/prestamos/{id}/rechazado`).
* **Interceptors & Guards**:
  * Angular `JwtInterceptor` adjunta automáticamente el token Bearer a las solicitudes HTTP.
  * Backend valida tokens con `JwtAuthenticationFilter` e inyecta autoridades en el `SecurityContextHolder`.

---

## 🚀 Configuración e Instalación

### **1. Backend (Spring Boot)**

1. Clona el repositorio e ingresa a la carpeta del backend:
   ```bash
   cd backend
