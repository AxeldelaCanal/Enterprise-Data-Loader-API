# ⚡ Enterprise Data Loader API (Excel-to-Rest Adapter)

> **Microservicio de transformación y orquestación de datos operativos.**
> Ingesta configuraciones complejas en Excel (.xlsx) y las expone como una API REST de alta velocidad (In-Memory Access) para sistemas de Quick-Commerce.

![Java 21](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.x-green?logo=springboot)
![Apache POI](https://img.shields.io/badge/Excel_Processing-Apache_POI-blue)
![Security](https://img.shields.io/badge/Spring_Security-Configured-red)

---

## 💡 El Problema de Negocio
En operaciones de Quick-Commerce, los equipos comerciales gestionan horarios y configuraciones de tiendas ("Dark Stores") utilizando hojas de cálculo masivas (Excel).
Sincronizar estos Excels con las plataformas tecnológicas suele ser un proceso manual propenso a errores.

## 🛠️ La Solución Técnica
Este servicio actúa como un **Middleware de Adaptación**:
1.  **Ingesta Automática:** Lee y procesa archivos `.xlsx` complejos al iniciar el servicio.
2.  **Normalización Dinámica:** Un algoritmo inteligente detecta columnas independientemente de variaciones en el nombre (ej: "Store ID" vs "store_id").
3.  **High-Performance Serving:** Almacena los datos procesados en memoria (Heap), permitiendo tiempos de respuesta de **<10ms** para consultas de operación en tiempo real.

---

## 🏗️ Arquitectura del Sistema

### Tech Stack
* **Core:** Java 21 (LTS) & Spring Boot 3.
* **Data Processing:** Apache POI 5.x (Para parsing avanzado de Office Open XML).
* **API Layer:** Spring Web MVC.
* **Security:** Spring Security (CSRF disabled para APIs internas).
* **Tools:** Lombok (Boilerplate reduction), Maven.

### Patrones de Diseño Detectados
* **Singleton Service:** `BaseFileService` mantiene el estado único de los datos en memoria.
* **Strategy / Normalizer:** Lógica de mapeo de columnas flexible en `mapColumns()` para tolerar errores de entrada humana en los Excels.
* **Eager Loading:** Uso de `@PostConstruct` para garantizar que los datos estén validados y listos antes de aceptar la primera petición HTTP.

---

## 🚀 Instalación y Ejecución

### Prerrequisitos
* JDK 21 instalado.
* Maven 3.8+.

### 1. Clonar

```bash
git clone https://github.com/AxeldelaCanal/Enterprise-Data-Loader-API.git
cd Enterprise-Data-Loader-API/Enterprise\ Data\ Loader\ API/Enterprise\ Data\ Loader\ API
```

### 2. Configurar credenciales

La API usa **HTTP Basic Auth**. Seteá las variables de entorno antes de correr:

```bash
export APP_USER=admin
export APP_PASSWORD=tu_password
```

> En Windows (PowerShell): `$env:APP_USER="admin"` / `$env:APP_PASSWORD="tu_password"`

### 3. Ejecutar

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.  
El dashboard en `http://localhost:8080/` no requiere autenticación.  
Las rutas `/api/**` requieren Basic Auth.
