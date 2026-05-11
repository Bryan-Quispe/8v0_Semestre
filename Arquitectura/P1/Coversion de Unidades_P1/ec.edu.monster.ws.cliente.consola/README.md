# Cliente Consola - Conversor de Unidades SOAP

## Descripción
Cliente de consola Java que consume el servicio web SOAP de conversión de unidades (`ec.edu.monster.ws`).

## Estructura (MVC)
```
ec.edu.monster.ws.cliente.consola/
├── src/main/java/ec/edu/monster/ws/cliente/consola/
│   ├── controladores/
│   │   └── MenuControlador.java          ← Controlador principal
│   ├── servicios/
│   │   └── SoapClienteServicio.java      ← Consume SOAP via HTTP
│   ├── modelos/
│   │   └── SesionUsuario.java            ← Modelo de sesión
│   ├── vistas/
│   │   └── ConsolaVista.java             ← Vista en terminal
│   └── AplicacionConsola.java            ← Punto de entrada
└── pom.xml
```

## Credenciales
- **Usuario:** monster
- **Contraseña:** monster9

## Ejecución
```bash
mvn compile exec:java -Dexec.mainClass="ec.edu.monster.ws.cliente.consola.AplicacionConsola"
```

## Requisitos
- Java 8+
- Maven
- Servidor SOAP desplegado en `http://localhost:8080/ec.edu.monster.ws/WS`
