# Aplicación de adopción de animales - Simbad

**Trabajo de Fin del Grado Superior (TFG)**

**Desarrollo de Aplicaciones de Multiplataformas (DAM)**

**María Moragriega Ruano**

---------------------------------

## Índice:
1. [Introducción](#introducción)
2. [Funcionalidades del proyecto y tecnologías utilizadas](#funcionalidades_del_proyecto_y_tecnologías_utilizadas)
4. [Guía de instalación](#guía_de_instalación)
5. [Guía de uso](#guía_de_uso)
6. [Enlace a la documentación](#enlace_a_la_documentación)
7. [Enlace a la documentación](#enlace_a_la_documentación)
8. [Enlace a figma de la interfaz](#enlace_a_figma_de_la_interfaz)
9. [Agradecimientos](#agradecimientos)
10. [Licencias](#licencias)
11. [Contacto](#contacto)

---------------------------------

## Introducción

El Trabajo Fin de Grado (TFG) en Desarrollo de Aplicaciones Multiplataforma (DAM) titulado "Simbad" presenta el desarrollo de una aplicación Android que consume una API de adopciones de animales. La aplicación tiene como objetivo principal facilitar el proceso de adopción de animales a los usuarios interesados, permitiéndoles visualizar la información de los animales disponibles para adopción, así como realizar consultas y filtrar los resultados según diferentes criterios.

La aplicación se ha desarrollado siguiendo las buenas prácticas de diseño y desarrollo de aplicaciones Android, utilizando la arquitectura Model-View-ViewModel (MVVM) y el patrón de diseño Repository. Además, se ha utilizado la librería Retrofit para la gestión de peticiones HTTP y la librería Glide para la carga de imágenes.

La API consumida por la aplicación proporciona la información de los animales disponibles para adopción, así como los datos de contacto de los refugios responsables de cada animal. La aplicación permite a los usuarios visualizar esta información de forma clara y organizada

En resumen, el TFG presenta el desarrollo de una aplicación móvil para la adopción de animales que consume una API y permite a los usuarios visualizar y filtrar la información de los animales disponibles para adopción de forma sencilla e intuitiva. La aplicación ha sido desarrollada siguiendo las buenas prácticas de diseño y desarrollo de aplicaciones Android, y se ha utilizado una arquitectura y patrones de diseño modernos y escalables.

---------------------------------

## Funcionalidades del proyecto y tecnologías utilizadas

El proyecto consiste en una aplicación Android dedicada a la adopción de animales, diseñada para ofrecer una plataforma intuitiva y funcional tanto para usuarios normales como para refugios de animales. Entre las funcionalidades principales se incluyen la capacidad de los usuarios para buscar y solicitar adopciones de animales, así como la administración por parte de los refugios de los animales disponibles y las solicitudes de adopción recibidas.

Para lograr estas funcionalidades, se han empleado diversas herramientas y tecnologías. El desarrollo de la aplicación Android se ha llevado a cabo utilizando Android Studio, permitiendo la creación de interfaces de usuario atractivas y la implementación de la lógica de la aplicación. Por otro lado, el backend de la aplicación se ha construido utilizando Spring Boot, lo que facilita la creación de servicios RESTful y la gestión de la lógica de negocio.

## Arquitectura de la Aplicación

La aplicación de adopción de animales, al consumir una API y utilizar una base de datos, se beneficia de la combinación de las arquitecturas de capas y ViewModel por las siguientes razones:

**Arquitectura de Capas:** Un Diseño Modular y Escalable

La arquitectura de capas divide la aplicación en secciones independientes, cada una con una responsabilidad específica. Esto crea una estructura modular que facilita el desarrollo, la depuración y el mantenimiento.

**Capa de Presentación (UI):** Se encarga de mostrar la información a los usuarios y de recibir sus interacciones.

**Capa de Lógica de Negocio (Business Logic):** Se encarga de la lógica interna de la aplicación.

**Capa de Acceso a Datos (Data Access):** Se encarga de la comunicación con la API y la base de datos.

**Capa de Datos:** Contiene la base de datos local y la API.

**ViewModel:** Gestión de la Lógica de Negocio y el Estado de la UI

El patrón ViewModel se encarga de preparar los datos para la UI y de gestionar las acciones del usuario. Actúa como un intermediario entre la capa de presentación y la capa de lógica de negocio.

En conjunto, la combinación de la arquitectura de capas y el patrón ViewModel permite desarrollar una aplicación de adopción de animales robusta, escalable, fácil de mantener y con una excelente experiencia de usuario.

Para lograr estas funcionalidades y asegurar la eficiencia del proyecto, se han empleado diversas herramientas y tecnologías.

## Funcionalidades del Proyecto

**Registro y Autenticación de Usuarios:** Los usuarios pueden registrarse e iniciar sesión en la aplicación como "Usuario Normal" o "Refugio".

**Perfil de Usuario:** Cada usuario tiene un perfil donde puede ver y editar su información personal.

**Adopción de Animales:** Los usuarios normales pueden navegar por la lista de animales disponibles para adopción, ver detalles sobre cada animal y solicitar la adopción de uno o más animales.

**Administración de Animales por parte del Refugio:** Los refugios pueden agregar nuevos animales a la lista de adopción, editar la información de los animales existentes y marcarlos como adoptados una vez que han encontrado un hogar.

**Gestión de Solicitudes de Adopción:** Los refugios pueden revisar y gestionar las solicitudes de adopción recibidas, aceptando o rechazando las solicitudes según corresponda.

## Tecnologías Utilizadas:

**Java:** Lenguaje de programación utilizado para el desarrollo tanto de la aplicación Android como del backend.

**Spring Boot:** Framework de Java utilizado para desarrollar el backend de la aplicación, facilitando la creación de servicios RESTful y la gestión de la lógica de negocio.

**IntelliJ IDEA:** Entorno de desarrollo integrado (IDE) utilizado para escribir y depurar el código Java del backend.

**Android Studio:** Entorno de desarrollo utilizado para crear la aplicación Android, permitiendo el diseño de interfaces de usuario y la implementación de la lógica de la aplicación.

**Docker:** Plataforma de contenedores utilizada para desplegar y gestionar la base de datos MySQL de manera eficiente y portátil.

**MySQL:** Sistema de gestión de bases de datos utilizado para almacenar la información de los usuarios, los animales disponibles para adopción y las solicitudes de adopción.

**DBeaver:** Herramienta de gestión de bases de datos utilizada para interactuar con la base de datos MySQL, facilitando el diseño de esquemas de bases de datos y la ejecución de consultas SQL.

**Postman:** Herramienta utilizada para probar y depurar los servicios RESTful del backend, permitiendo realizar solicitudes HTTP y analizar las respuestas.

**GitHub:** Plataforma de desarrollo colaborativo utilizada para almacenar y gestionar el código fuente del proyecto, facilitando la colaboración entre los miembros del equipo y el control de versiones del código.

## Herramientas Utilizadas

**Figma:** Herramienta de diseño colaborativo utilizada para crear y compartir prototipos de interfaz de usuario.

**Generador de Paletas de Colores:** Página web utilizada para generar paletas de colores para el diseño de la interfaz de usuario.

**Canvas:** Plataforma de aprendizaje en línea utilizada para crear contenido interactivo y actividades.

---------------------------------

## Enlace a figma de la interfaz

https://www.figma.com/design/dJrNwrjCegRQ6uRQK4F03E/Simbad?node-id=1-2&t=tFolHPhYNd93cXw1-1

---------------------------------

## Conclusión

En resumen, el desarrollo de este Trabajo de Fin de Grado ha sido una experiencia de aprendizaje invaluable para mí. A través de la aplicación práctica de los conceptos teóricos adquiridos durante mi formación académica, he logrado comprender en profundidad diversos aspectos relacionados con el diseño, desarrollo y despliegue de aplicaciones móviles.

Particularmente, el proceso de implementación de una aplicación móvil basada en una API de adopción de animales me ha proporcionado una valiosa perspectiva sobre la importancia de la planificación, la organización y la resolución de problemas en el desarrollo de software. Además, he adquirido habilidades técnicas específicas relacionadas con el manejo de bases de datos, consumo de APIs y diseño de interfaces de usuario.

A lo largo de este proyecto, también he tenido la oportunidad de enfrentarme a desafíos técnicos y tomar decisiones informadas para superarlos, lo que ha contribuido a mi crecimiento profesional. La capacidad de adaptarme a situaciones cambiantes y de aprender de los errores ha sido fundamental para alcanzar los objetivos propuestos.

En conclusión, este Trabajo de Fin de Grado ha sido una etapa significativa en mi proceso de formación, donde he consolidado mi comprensión teórica a través de la aplicación práctica y he desarrollado habilidades técnicas y profesionales relevantes para mi futura carrera en el campo de la informática.

---------------------------------

## Agradecimientos

Quiero expresar mi sincero agradecimiento a mi familia, amigos y compañeros de clase por su constante apoyo y aliento durante la realización de este Trabajo de Fin de Grado.

Su ánimo y motivación fueron fundamentales en cada etapa de este proyecto, y estoy profundamente agradecido por su presencia y confianza en mí.
