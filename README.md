# Gestión de Bases de Datos Relacionales con Java JDBC

Este repositorio contiene una serie de proyectos desarrollados durante el primer curso de DAM (CPIFP Los Enlaces) enfocados en la integración de aplicaciones Java con servidores MariaDB a través de XAMPP.

## Objetivos Técnicos
El proyecto documenta el aprendizaje y uso de la API JDBC para la manipulación de datos, priorizando la seguridad y la organización del código.

### Tecnologías y Herramientas
- Lenguaje: Java (Programación Orientada a Objetos).
- Base de Datos: MariaDB / MySQL (vía XAMPP).
- Conector: JDBC (Java Database Connectivity).

### Implementaciones Realizadas
- Conectividad: Gestión de sesiones mediante DriverManager y Connection.
- Consultas Dinámicas: Uso de Statement y PreparedStatement para prevenir inyección SQL.
- Manipulación de Datos: Ejecución de comandos DML mediante executeUpdate() y recuperación de registros con ResultSet.
- Interfaz Gráfica: Desarrollo de entornos visuales para la interacción directa con el usuario.

## Arquitectura de Software
Como reto personal fuera del currículo académico, los proyectos están estructurados bajo el patrón de diseño MVC (Modelo-Vista-Controlador). 

Este enfoque permite separar la lógica de negocio y el acceso a datos de la interfaz de usuario, facilitando el mantenimiento y la escalabilidad del código.
