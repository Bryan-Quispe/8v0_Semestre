# Instrucciones para Usar el Informe en Overleaf

## Pasos para Subir a Overleaf

1. **Acceder a Overleaf**
   - Ve a www.overleaf.com
   - Inicia sesión con tu cuenta

2. **Crear Nuevo Proyecto**
   - Haz clic en "New Project"
   - Selecciona "Blank Project"
   - Dale un nombre significativo (ej: "Informe Realidad Aumentada - Bryan Quispe")

3. **Subir Archivos**
   - Haz clic en el ícono de carpeta (Upload)
   - Sube el archivo `main.tex` a la carpeta raíz
   - Crea una carpeta llamada `images` en Overleaf
   - Sube todas las imágenes dentro de esa carpeta:
     - `Logo_ESPE.png`
     - `imagen_referencia.png`
     - `modelado de zapatos.png`
     - `modelado de mochila.png`
     - `MODELO_FINAL SIN TEXTURA.png`
     - `Coloryrextura.png`
     - `qr animado.jpeg`

4. **Compilar el Documento**
   - Haz clic en "Recompile" o presiona Ctrl+Enter
   - El documento se generará automáticamente en PDF

## Contenido del Informe

El informe incluye las siguientes secciones:

✅ **Portada profesional** con:
   - Logo ESPE
   - Nombre del estudiante (Bryan Quispe)
   - Materia y NRC
   - Nombre del tutor

✅ **Introducción** - Contexto del proyecto

✅ **Inspiración y Concepto** - Explicación del meme humorístico de sistemas

✅ **Proceso de Modelado 3D** - Con descripciones de:
   - Desafíos del modelado de zapatos
   - Desafíos del modelado de mochila
   - Resolución de problemas

✅ **Resultado del Modelado Base** - Modelo sin textura

✅ **Texturización y Visualización Final** - Modelo coloreado y texturizado

✅ **Implementación de Realidad Aumentada** - Con código QR

✅ **Resultados y Logros**

✅ **Conclusiones**

✅ **Referencias**

## Características del Documento

- 📄 Formato profesional en español
- 🎨 Estructura clara con tabla de contenidos automática
- 📊 Numeración automática de figuras y referencias cruzadas
- 📱 Responde a estándares académicos
- 🖼️ Integración de todas tus imágenes
- 📑 Referencias bibliográficas

## Notas Importantes

⚠️ **Las imágenes no tienen el nombre del archivo visible** - Se muestran con descripciones significativas (ej: "Detalle del modelado del calzado" en lugar de "modelado de zapatos.png")

⚠️ **Los nombres en las imágenes de resultado** aparecen sin espacios en el código, por lo que debes asegurar que los nombres de tus archivos coincidan exactamente

## Personalización Adicional

Si deseas hacer cambios:

- **Color de títulos**: Modifica el valor RGB en `\definecolor{darkblue}{RGB}{0,51,102}`
- **Márgenes**: Ajusta en `\usepackage[margin=2.5cm]{geometry}`
- **Espaciado**: Modifica `\onehalfspacing` (0.5, 1.5, 2)
- **Fuente**: Cambia en `\documentclass[12pt,a4paper]{article}` el tamaño (10pt, 12pt, etc.)

## Soporte

Si tienes problemas al compilar:
1. Verifica que todos los archivos estén en la carpeta correcta
2. Asegúrate de que los nombres de imagen coincidan exactamente
3. Compila nuevamente

¡Tu informe está listo para usar!
