# Cliente Escritorio JavaFX - Conversor de Unidades SOAP

## Descripción
Cliente de escritorio con JavaFX que consume el servicio web SOAP de conversión de unidades.

## Estructura (MVC)
```
ec.edu.monster.ws.cliente.escritorio/
├── src/main/java/ec/edu/monster/ws/cliente/escritorio/
│   ├── controladores/
│   │   ├── LoginControlador.java
│   │   └── ConversionControlador.java
│   ├── servicios/
│   │   └── SoapClienteServicio.java
│   ├── modelos/
│   │   ├── LoginModelo.java
│   │   └── ConversionModelo.java
│   └── AplicacionEscritorio.java
├── src/main/resources/
│   ├── vistas/
│   │   ├── login.fxml
│   │   └── conversion.fxml
│   └── css/estilos.css
└── pom.xml
```

## Credenciales
- **Usuario:** monster
- **Contraseña:** monster9

## Ejecución
```bash
mvn javafx:run
```

## Requisitos
- Java 11+
- Maven
- Servidor SOAP desplegado en `http://localhost:8080/ec.edu.monster.ws/WS`
