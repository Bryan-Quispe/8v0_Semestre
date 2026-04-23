# 📱 Configuración de la Página Web de Realidad Aumentada

## ✅ Archivos Listos

He creado dos archivos HTML para ti:

1. **ar-viewer.html** ← ESTA ES LA NUEVA (Recomendada)
2. index.html (anterior - puedes eliminar o reemplazar)

---

## 🚀 Cómo Usar la Página Web

### Opción 1: En tu laptop/PC (para pruebas)
1. Abre el archivo `ar-viewer.html` en tu navegador
2. Verás la página de bienvenida
3. Haz clic en **"Iniciar Realidad Aumentada"**
4. Permitir acceso a la cámara cuando se solicite

### Opción 2: En un servidor web (Recomendado)

Si deseas compartir la página en internet:

**Con GitHub Pages:**
1. Crea un repositorio en GitHub
2. Sube todos los archivos:
   - `ar-viewer.html`
   - `saludogaymer.glb`
   - `images/` (carpeta con tus fotos)
3. Activa GitHub Pages en el repositorio
4. Accede por: `https://tuusuario.github.io/nombre-repo/ar-viewer.html`

**Con Netlify (Gratis):**
1. Ve a netlify.com
2. Arrastra y suelta la carpeta con tus archivos
3. Automáticamente obtendrás una URL pública

**Con tu propio servidor:**
- Copia todos los archivos a tu servidor web
- Accede por: `tudominio.com/ar-viewer.html`

---

## 📋 Archivos Necesarios

Verifica que tengas estos archivos en la carpeta "Realidad Aumentada":

✅ `ar-viewer.html` (la nueva página)
✅ `saludogaymer.glb` (tu modelo 3D)
✅ Carpeta `images/` (si quieres mostrar fotos)
✅ `main.tex` (tu informe LaTeX)

---

## 🎯 Características de la Página

### 📖 Página de Inicio
- Descripción del proyecto
- 4 características destacadas
- Instrucciones de uso claras
- Botón para iniciar AR
- Código QR generado automáticamente

### 🎮 Experiencia de Realidad Aumentada
- Carga el modelo 3D (saludogaymer.glb)
- Visualización en tiempo real
- Botón para salir y volver

### 📲 Código QR
- Se genera automáticamente
- Apunta a la URL actual
- Permite compartir la experiencia

---

## 🔧 Personalizaciones Posibles

Si deseas cambiar algo, edita `ar-viewer.html`:

### Cambiar título:
```
<h1>🎓 Mascota 3D</h1>
```

### Cambiar descripción:
```
Experimenta la mascota de la carrera de Ingeniería de Software...
```

### Cambiar colores:
- Azul actual: `#667eea` y `#764ba2`
- Oscuro: `#003366`

### Cambiar modelo 3D:
Si tienes otro modelo GLB, reemplaza en esta línea:
```
gltf-model="url(./saludogaymer.glb)"
```

---

## ✨ Flujo de Uso

```
Usuario abre ar-viewer.html
    ↓
Ve página de bienvenida
    ↓
Presiona "Iniciar AR"
    ↓
Permite cámara
    ↓
Ve modelo 3D en realidad aumentada
    ↓
Puede rotar y mover
    ↓
Presiona "SALIR" para volver
```

---

## 📊 Recomendaciones

- 💻 Prueba primero en tu PC
- 📱 Luego en tu teléfono (Android o iOS)
- 🌐 Sube a un servidor para mejor experiencia
- 🔗 Comparte el URL del Código QR
- ⚡ Asegúrate que el navegador permita acceso a cámara

---

## 🐛 Solución de Problemas

**"No carga el modelo"**
- Verifica que `saludogaymer.glb` esté en la misma carpeta
- Recarga la página (Ctrl+R o Cmd+R)

**"No funciona la cámara"**
- Usa un navegador moderno (Chrome, Firefox, Safari, Edge)
- Permite acceso a cámara cuando se solicite
- Usa HTTPS si está en la nube

**"El modelo se ve pequeño"**
- Cambia `scale="1.5 1.5 1.5"` en el HTML
- Aumenta el número para verlo más grande

---

## 📞 Integración con tu Informe

1. En el informe LaTeX (main.tex), el Código QR debe apuntar a:
   ```
   https://tudominio.com/ar-viewer.html
   ```

2. Cuando alguien escanee el código con teléfono:
   - Se abrirá la página AR
   - Verá el modelo 3D
   - Podrá interactuar libremente

---

## 🎓 Para tu Proyecto

El flujo completo sería:
1. **Informe en PDF** (con Código QR)
2. **Página Web** (esta: ar-viewer.html)
3. **Modelo 3D** (saludogaymer.glb)

Cuando alguien escanee el QR del informe:
→ Se abre la página web
→ Ven el modelo en AR
→ Pueden interactuar

¡Listo para presentar! 🚀
