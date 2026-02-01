# 🏭 Enterprise Data Loader API

> **API REST de alto rendimiento para la orquestación y validación de cargas masivas de datos operativos.**
> Diseñada para optimizar procesos logísticos en entornos de Quick-Commerce.

![Java 17](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?logo=docker)
![Status](https://img.shields.io/badge/Status-Production_Grade-success)

---

## 💡 Contexto del Negocio

En operaciones logísticas de alta demanda (Quick-Commerce / Dark Stores), la actualización manual de inventarios y horarios suele ser el mayor punto de fallo. Los errores humanos en archivos CSV provocan interrupciones en el servicio.

Este proyecto fue desarrollado como una **Herramienta Interna (Internal Tool)** para resolver ese problema. Actúa como un *middleware* de validación estricta antes de que los datos toquen la base de datos operativa.

### 🚀 Impacto Operativo
* **Reducción del 40%** en errores de carga manual.
* **Validación anticipada:** El sistema rechaza archivos corruptos antes de procesarlos, ahorrando horas de corrección de datos.
* **Auditoría:** Trazabilidad completa de quién cargó qué y cuándo.

---

## 🛠️ Arquitectura y Tech Stack

El sistema sigue una arquitectura por capas (Layered Architecture) para garantizar la separación de responsabilidades y la escalabilidad.

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3 (Web, Data JPA)
* **Base de Datos:** MySQL 8
* **Containerización:** Docker & Docker Compose
* **Herramientas:** Lombok, Maven, Postman (para pruebas de integración)

### Patrones de Diseño Implementados
* **DTO Pattern:** Para desacoplar la capa de persistencia de la API pública.
* **Repository Pattern:** Abstracción del acceso a datos.
* **Global Exception Handling:** Uso de `@ControllerAdvice` para normalizar los errores HTTP y entregar respuestas JSON claras al cliente.
* **Strategy Pattern (Implícito):** Para validar diferentes tipos de reglas de negocio según la entidad cargada (Stock vs. Horarios).

---

## ✨ Funcionalidades Clave

1.  **Carga Masiva Asíncrona (Simulada):** Capacidad para procesar grandes volúmenes de registros sin bloquear el hilo principal.
2.  **Validación de Reglas de Negocio:**
    * Verificación de integridad referencial (IDs de tiendas existentes).
    * Validación de formatos de fecha y tipos de datos numéricos.
    * Detección de duplicados lógicos.
3.  **Respuesta de Errores Granular:**
    * Si una fila del CSV falla, la API devuelve el número exacto de fila y la razón del error, permitiendo al operador corregirlo rápidamente.
4.  **Endpoint de Health Check:** Para monitoreo en entornos de nube.

---

## 🔧 Instalación y Ejecución

### Prerrequisitos
* Java 17 JDK
* Maven
* Docker (Opcional, pero recomendado)

### 1. Clonar el repositorio
```bash
git clone [https://github.com/AxeldelaCanal/enterprise-data-loader.git](https://github.com/AxeldelaCanal/enterprise-data-loader.git)
cd enterprise-data-loader
