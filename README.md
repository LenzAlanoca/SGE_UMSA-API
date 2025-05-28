🎉 Gestión de Eventos UMSA
¡Bienvenido al proyecto de gestión de eventos para la UMSA! 🚀 Este backend está construido con Spring Boot y PostgreSQL, y la idea es que los eventos académicos y estudiantiles sean fáciles de organizar, registrar y administrar.

🌟 ¿Qué hace este proyecto?
🗓️ Permite crear, editar y eliminar eventos.

👥 Administra participantes y organizadores.

🔍 Filtra y busca eventos según tus necesidades.

🔐 Seguridad con autenticación y autorización.

📊 Genera reportes para llevar el control de asistencia.

🏗️ ¿Con qué está hecho?
Spring Boot (el cerebro del backend 🧠)

PostgreSQL (para que los datos tengan su casita 🏡)

Spring Security + JWT (para que todo esté seguro 🔒)

Hibernate (manejo elegante de la base de datos 🏗️)

Maven (que nos ayuda con dependencias 📦)

🚀 ¿Cómo lo pongo en marcha?
Clonar el repo:

sh
git clone https://github.com/tuUsuario/gestion-eventos-umsa.git
Configurar la base de datos en application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_eventos  
spring.datasource.username=tu_usuario  
spring.datasource.password=tu_contraseña  
Levantar la aplicación:

sh
mvn spring-boot:run
🔗 Endpoints útiles
Método	Endpoint	¿Para qué sirve?
GET	/eventos	Lista todos los eventos
POST	/eventos	Crea un nuevo evento
GET	/eventos/{id}	Muestra detalles de un evento
PUT	/eventos/{id}	Edita un evento existente
DELETE	/eventos/{id}	Borra un evento
🤝 ¿Quieres colaborar?
¡Claro que sí! Si tienes ideas o mejoras, haz un fork y manda un Pull Request. Toda contribución es bienvenida 🚀

📜 Licencia
Este proyecto está bajo la MIT License. Úsalo, mejóralo y compártelo.
