# Aplicación de adopción de animales - Simbad

**Trabajo de Fin del Grado Superior (TFG)**

**Desarrollo de Aplicaciones de Multiplataformas (DAM)**

**María Moragriega Ruano**

---------------------------------

## Índice:
1. [Introducción](#introducción)
2. [Funcionalidades del proyecto y tecnologías utilizadas](#funcionalidades)
4. [Guía de instalación](#instalación)
5. [Guía de uso](#uso)
6. [Enlace a la documentación](#documentación)
7. [Enlace a figma de la interfaz](#figma)
8. [Conclusión](#conclusión)
9. [Agradecimientos](#agradecimientos)
10. [Contacto](#contacto)

---------------------------------

## Introducción

El Trabajo Fin de Grado (TFG) en Desarrollo de Aplicaciones Multiplataforma (DAM) titulado "Simbad" presenta el desarrollo de una aplicación Android que consume una API de adopciones de animales. La aplicación tiene como objetivo principal facilitar el proceso de adopción de animales a los usuarios interesados, permitiéndoles visualizar la información de los animales disponibles para adopción, así como realizar consultas y filtrar los resultados según diferentes criterios.

La aplicación se ha desarrollado siguiendo las buenas prácticas de diseño y desarrollo de aplicaciones Android, utilizando la arquitectura Model-View-ViewModel (MVVM) y el patrón de diseño Repository. Además, se ha utilizado la librería Retrofit para la gestión de peticiones HTTP y la librería Glide para la carga de imágenes.

La API consumida por la aplicación proporciona la información de los animales disponibles para adopción, así como los datos de contacto de los refugios responsables de cada animal. La aplicación permite a los usuarios visualizar esta información de forma clara y organizada

En resumen, el TFG presenta el desarrollo de una aplicación móvil para la adopción de animales que consume una API y permite a los usuarios visualizar y filtrar la información de los animales disponibles para adopción de forma sencilla e intuitiva. La aplicación ha sido desarrollada siguiendo las buenas prácticas de diseño y desarrollo de aplicaciones Android, y se ha utilizado una arquitectura y patrones de diseño modernos y escalables.

---------------------------------

## Funcionalidades

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

**Registro y Autenticación de Usuario y Refugio:** Los usuarios pueden registrarse e iniciar sesión en la aplicación como "Usuario" o "Refugio".

**Perfil de Usuario y Refugio:** Cada usuario y refugio tiene un perfil donde puede ver y editar su información personal.

**Adopción de Animales:** Los usuarios normales pueden navegar por la lista de animales disponibles para adopción, ver detalles sobre cada animal y solicitar la adopción de uno o más animales.

**Administración de Animales por parte del Refugio:** Los refugios pueden agregar nuevos animales a la lista de adopción, editar la información de los animales existentes y marcarlos como adoptados una vez que han encontrado un hogar.

**Gestión de Solicitudes de Adopción:** Los refugios pueden revisar y gestionar las solicitudes de adopción recibidas, aceptando o rechazando las solicitudes según corresponda.

## Tecnologías Utilizadas:

**Java:** Lenguaje de programación fundamental para el desarrollo tanto de la aplicación Android como del backend.

**Spring Boot:** Framework de Java utilizado para crear el backend de la aplicación, simplificando el desarrollo de servicios RESTful y la gestión de la lógica empresarial.

**MySQL:** Sistema de gestión de bases de datos empleado para almacenar la información de usuarios, animales disponibles para adopción y solicitudes de adopción.

## Herramientas Utilizadas

**Figma:** Plataforma colaborativa de diseño, empleada para la creación y compartición de prototipos de interfaces de usuario.

**Generador de Paletas de Colores:** Herramienta web utilizada para generar paletas de colores, esenciales para el diseño de la interfaz de usuario.

**Canvas:** Plataforma de aprendizaje en línea que permite la creación de contenido interactivo y actividades educativas.

**IntelliJ IDEA:** Entorno de desarrollo integrado (IDE) utilizado para escribir y depurar el código Java del backend.

**Android Studio:** IDE especializado en el desarrollo de aplicaciones Android, que facilita el diseño de interfaces de usuario y la implementación de la lógica de la aplicación.

**Docker:** Plataforma de contenedores que simplifica el despliegue y gestión de la base de datos MySQL de manera eficiente y portátil.

**DBeaver:** Herramienta de gestión de bases de datos utilizada para interactuar con MySQL, facilitando el diseño de esquemas y la ejecución de consultas SQL.

**Postman:** Herramienta para probar y depurar servicios RESTful del backend, permitiendo realizar solicitudes HTTP y analizar las respuestas.

**GitHub:** Plataforma de desarrollo colaborativo empleada para almacenar y gestionar el código fuente del proyecto, facilitando la colaboración entre los miembros del equipo y el control de versiones del código.

---------------------------------

## Diseño de la base de datos

![Captura de pantalla 2024-06-10 205734](https://github.com/mmruano/TFGFrontend_Android/assets/146729468/de1a4623-60ac-4f8d-beb3-3080f541907e)

Relaciones entre Tablas

User y Adoption: Un usuario puede tener múltiples adopciones. La relación se representa a través del atributo userId en la tabla Adoption, que es una clave foránea que apunta a la tabla User.

Animal y Adoption: Un animal puede estar asociado con una adopción. La relación se representa a través del atributo animalId en la tabla Adoption, que es una clave foránea que apunta a la tabla Animal.

Refuge y Animal: Un refugio puede tener múltiples animales. La relación se representa a través del atributo refugeId en la tabla Animal, que es una clave foránea que apunta a la tabla Refuge.

Refuge y Adoption: Un refugio puede estar involucrado en múltiples adopciones. La relación se representa a través del atributo refugeId en la tabla Adoption, que es una clave foránea que apunta a la tabla Refuge.

---------------------------------

## Instalación

El siguiente enlace te llevará a una pagina de MEGA para poder descargarte el archivo APK de la aplicación.

Link de descarga de la APK: https://mega.nz/file/xEZlWIba#b1TW9MnHF4LbOgLoADoWRjReup9KUGJYg0MGNfW-_A4

1. Darle al botón de descarga.

2. Una vez descargado, es posible que veas una notificación indicando que el archivo podría dañar tu dispositivo. Para continuar con la instalación, sigue estos pasos:

    Ve a Configuración en tu dispositivo.
    Busca y selecciona Aplicaciones.
    Accede a Acceso especial de aplicaciones.
    Busca y selecciona el navegador que utilizaste para descargar el archivo.
    Activa la opción Instalar aplicaciones desconocidas.
    Asegúrate de desactivar esta opción una vez que hayas completado la instalación.

3. Una vez completados los pasos anteriores, vuelve al archivo descargado y haz clic en él para iniciar la instalación. Es posible que aparezca una advertencia indicando que la aplicación no pudo ser escaneada. Para continuar con la instalación, sigue estos pasos:

    Haz clic en Más detalles.
    Selecciona Instalar sin escanear.

---------------------------------

## Uso

1. Registro como usuario o refugio:

Cuando descargues y abras la aplicación, serás recibido por la pantalla de inicio de sesión. Aquí, tendrás la opción de ingresar con tus credenciales o de registrarte si eres nuevo en la plataforma. Al hacer clic en el botón de registro, serás dirigido a una nueva pantalla donde se te presentarán dos opciones: Registrarse como Usuario o Registrarse como Refugio. Dependiendo de tu rol, podrás elegir la opción que mejor se adapte a ti. Una vez seleccionada, serás conducido a un formulario donde podrás proporcionar tus datos personales. Una vez completado el registro, serás redirigido nuevamente a la pantalla de inicio de sesión para ingresar con tus nuevas credenciales.

2. Loguearse:

Para iniciar sesión en la aplicación, simplemente introduce tu correo electrónico y contraseña en los campos correspondientes. Si los datos ingresados son incorrectos, recibirás un mensaje de error indicando que la información proporcionada no coincide con nuestros registros.

3. Buscar animales por filtro como usuario:

La pantalla principal de la aplicación mostrará una lista completa de todos los animales disponibles para adopción. Si deseas encontrar un animal específico, puedes utilizar la función de filtrado. Al hacer clic en el botón de "Filtrar", se abrirá un cuadro de diálogo donde podrás ingresar tus criterios de búsqueda. Una vez que hayas completado los campos relevantes, simplemente haz clic en el botón de búsqueda y la lista se actualizará para mostrar solo los animales que cumplan con tus criterios.

4. Ver información del animal:

Al seleccionar un animal de la lista, podrás acceder a una pantalla dedicada donde encontrarás toda la información relevante sobre ese animal en particular. Desde detalles sobre su raza, edad y salud, hasta fotos y videos que te ayudarán a conocerlo mejor.

5. Mandar una petición para adoptar a un animal:

Si estás interesado en adoptar un animal en particular, puedes enviar una solicitud de adopción directamente desde la pantalla de información del animal. Simplemente haz clic en el botón de "Adoptar" y se abrirá un formulario donde podrás proporcionar tus detalles de contacto. Esta información será enviada al refugio correspondiente para que puedan ponerse en contacto contigo y discutir los detalles de la adopción.

6. Ver adopciones:

Desde el menú principal, tendrás la opción de acceder a tus solicitudes de adopción enviadas. Aquí podrás ver el estado de cada solicitud y cualquier actualización relacionada con tu petición.

7. Ver información sobre la adopcion como usuario:

Al seleccionar una solicitud de adopción específica, tendrás acceso a una pantalla donde podrás ver más detalles sobre la solicitud y la información de contacto del refugio correspondiente.

8. Editar información del usuario:

Si necesitas actualizar o corregir la información de tu perfil, puedes hacerlo fácilmente desde la sección de configuración de la aplicación. Aquí encontrarás un formulario donde podrás realizar los cambios necesarios y guardar la información actualizada.

9. Añadir animales a la lista:

Como refugio, tendrás la capacidad de agregar nuevos animales a la lista de adopción. Simplemente haz clic en el botón de "Agregar" y serás llevado a un formulario donde podrás ingresar los detalles del animal, incluyendo su nombre, edad, género y cualquier información relevante adicional.

10. Editar animal de la lista:

Si necesitas realizar cambios en la información de un animal existente, puedes hacerlo fácilmente desde la lista de animales en adopción. Haz clic en el icono de edición junto al animal correspondiente y podrás actualizar los detalles según sea necesario.

11. Eliminar un animal:

En caso de que un animal ya no esté disponible para adopción, tendrás la opción de eliminarlo de la lista. Simplemente haz clic en el icono de eliminar y confirma tu decisión cuando se te solicite.

12. Mirar las peticiones de un refugio mandadas por un usuario:

Desde el menú principal, podrás acceder a una lista completa de todas las solicitudes de adopción enviadas al refugio. Aquí podrás revisar cada solicitud y tomar las acciones apropiadas según corresponda.

13. Mirar la información de una petición:

Al seleccionar una solicitud de adopción específica, podrás acceder a una pantalla donde encontrarás información detallada sobre la solicitud y el usuario que la envió.

14. Dar de alta una petición de adopción:

Si deseas proceder con una solicitud de adopción específica, puedes hacerlo fácilmente desde la pantalla de información de la solicitud. Haz clic en el botón correspondiente para confirmar la adopción y completar el proceso de adopción.

15. Negar la petición de adopción de un usuario:

Si decides no proceder con una solicitud de adopción, puedes rechazarla desde la pantalla de información de la solicitud. Simplemente haz clic en el botón correspondiente para cancelar la solicitud y notificar al usuario correspondiente.

16. Editar información del refugio:

Como refugio, tendrás la capacidad de editar la información de tu perfil desde la sección de configuración de la aplicación. Aquí podrás actualizar los detalles del refugio, incluyendo la dirección, el número de contacto y cualquier otra información relevante.

17. Salir de la cuenta:

Cuando hayas terminado de usar la aplicación, puedes cerrar sesión fácilmente desde el menú principal. Simplemente haz clic en la opción correspondiente y serás desconectado de tu cuenta, volviendo a la pantalla de inicio de sesión para futuros accesos.

---------------------------------

## Documentación

https://mega.nz/file/9E5AGAZQ#vqr_C9U5TAoYrMcNNwNmR48qGveNFkAUxeCNX_A4oyE

---------------------------------

## Figma

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

---------------------------------

## Contacto

Gmail: maria.moragriega@a.vedrunasevillasj.es
