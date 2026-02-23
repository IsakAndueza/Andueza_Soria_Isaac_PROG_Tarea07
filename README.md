# 📚 Gestión de Biblioteca IES Al-Ándalus (Tarea 07)

Evolución del sistema de gestión bibliotecaria migrando de almacenamiento estático (Arrays) a estructuras de datos dinámicas (**ArrayList**). Este proyecto se centra en la flexibilidad, la eficiencia en la gestión de memoria y la organización avanzada de la información.

## 🚀 Mejoras Técnicas Principales

### 1. Estructuras de Datos Dinámicas
* **Eliminación de restricciones:** Se ha suprimido la constante `CAPACIDAD` y la gestión manual de índices.
* **Escalabilidad:** El uso de `ArrayList` permite que las colecciones de Libros, Usuarios y Préstamos crezcan de forma indefinida.
* **Autores ilimitados:** La clase `Libro` ahora soporta un número variable de autores, eliminando el límite previo de tres.

### 2. Lógica de Ordenación Avanzada
Se han implementado comparadores específicos para mejorar la visualización de datos:
* **Usuarios y Libros:** Ordenación alfabética ascendente (A-Z) por nombre y título respectivamente.
* **Préstamos:** Ordenación cronológica descendente (más recientes primero). En caso de coincidencia de fecha, se aplica una segunda ordenación por nombre de usuario.

### 3. Optimización de UX y "Fail-Fast"
* **Validación temprana:** El sistema verifica la existencia de datos y stock antes de solicitar entradas adicionales al usuario.
* **Feedback robusto:** Mejora en los mensajes de error durante la captura de datos (email, DNI, ISBN) para guiar al usuario en tiempo real.

## 🏗️ Estructura del Proyecto (MVC)

El proyecto mantiene una separación estricta de responsabilidades:
* `biblioteca.modelo`: Gestión de datos y lógica de negocio dinámica.
* `biblioteca.vista`: Interfaz de consola con validación de entrada avanzada.
* `biblioteca.controlador`: Orquestador entre la vista y el modelo.

## 🛠️ Tecnologías y Herramientas
* **Lenguaje:** Java 17+
* **Gestor de proyectos:** Gradle
* **Librerías externas:** `iesalandalus.Entrada` para gestión de teclado.

---

### 💡 Outside the Box: De la Programación Estructurada a la Dinámica
El paso de Arrays a `ArrayList` marca un cambio de paradigma. Mientras que con Arrays gestionamos la memoria de forma "manual" (cuidando índices y tamaños), con las colecciones de Java delegamos esa complejidad al lenguaje. Esto nos permite centrar nuestros esfuerzos en las **reglas de negocio** (como la ordenación compleja de préstamos) en lugar de en la infraestructura de los datos.

---
**Autor:** Isak  
**Asignatura:** Programación - Grado Superior DAM
