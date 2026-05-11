# Cliente Web Thymeleaf - Conversor de Unidades SOAP

## Descripción
Cliente web con Spring Boot + Thymeleaf que consume el servicio web SOAP de conversión de unidades.

## Estructura (MVC)
```
ec.edu.monster.ws.cliente.web/
├── src/main/java/ec/edu/monster/ws/cliente/web/
│   ├── controladores/
│   │   ├── LoginControlador.java
│   │   └── ConversionControlador.java
│   ├── servicios/
│   │   └── SoapClienteServicio.java
│   ├── modelos/
│   │   ├── LoginFormulario.java
│   │   └── ConversionFormulario.java
│   └── ClienteWebApplication.java
├── src/main/resources/
│   ├── templates/
│   │   ├── login.html
│   │   └── conversion.html
│   ├── static/css/estilos.css
│   └── application.properties
└── pom.xml
```

## Credenciales
- **Usuario:** monster
- **Contraseña:** monster9

## Ejecución
```bash
mvn spring-boot:run
```
Acceder a: `http://localhost:9090`

## Requisitos
- Java 8+
- Maven
- Servidor SOAP desplegado en `http://localhost:8080/ec.edu.monster.ws/WS`
