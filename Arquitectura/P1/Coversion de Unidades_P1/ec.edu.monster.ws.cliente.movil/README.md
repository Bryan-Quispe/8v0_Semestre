# Cliente Móvil Kotlin/Android - Conversor de Unidades SOAP

## Descripción
Cliente móvil Android con Kotlin que consume el servicio web SOAP de conversión de unidades.

## Estructura (MVC)
```
ec.edu.monster.ws.cliente.movil/
├── app/src/main/java/ec/edu/monster/ws/cliente/movil/
│   ├── actividades/
│   │   ├── LoginActividad.kt
│   │   └── ConversionActividad.kt
│   ├── servicios/
│   │   └── SoapClienteServicio.kt
│   ├── modelos/
│   │   ├── Usuario.kt
│   │   └── ResultadoConversion.kt
│   └── utilidades/
│       └── ConstantesApp.kt
├── app/src/main/res/
│   ├── layout/
│   │   ├── actividad_login.xml
│   │   └── actividad_conversion.xml
│   └── values/
│       ├── strings.xml, colors.xml, themes.xml
├── app/build.gradle.kts
├── build.gradle.kts
└── settings.gradle.kts
```

## Credenciales
- **Usuario:** monster
- **Contraseña:** monster9

## Ejecución
Abrir el proyecto en Android Studio y ejecutar en emulador o dispositivo físico.

**Nota:** En emulador Android, `10.0.2.2` apunta al `localhost` de la máquina host.

## Requisitos
- Android Studio
- Android SDK 34
- Servidor SOAP desplegado en el host
