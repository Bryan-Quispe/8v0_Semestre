# 📍 REFERENCIA CENTRALIZADA DE MODELOS 3D

## Estructura de Almacenamiento

```
src/modelos/almacenamiento/
├── glb/                          # Modelos GLB (binarios)
│   ├── modelo_1698765432_jaguar.glb
│   ├── modelo_1698765500_aguila.glb
│   └── modelo_1698765600_serpiente.glb
│
├── sketchfab/                    # Referencias de Sketchfab
│   ├── referencias.json
│   └── README.md
│
└── INDICE_MODELOS.md            # Este archivo
```

## Configuración en Base de Datos

### Campo `rutaArchivo` en Tabla `archivos_modelos`

```sql
-- Estructura
archivos_modelos {
  id: UUID,
  nombre: VARCHAR,           -- Nombre original (jaguar.glb)
  nombreAlmacenado: VARCHAR, -- Nombre único (modelo_1698765432_jaguar.glb)
  ruta: VARCHAR,             -- Ruta relativa (/modelos/glb/modelo_1698765432_jaguar.glb)
  rutaCompleta: VARCHAR,     -- Ruta absoluta (src/modelos/almacenamiento/glb/...)
  tipo: ENUM('glb','sketchfab'),  -- Tipo de almacenamiento
  mimeType: VARCHAR,         -- model/gltf-binary
  tamaño: INT,              -- Bytes
  modeloId: UUID FOREIGN KEY,
  createdAt: TIMESTAMP,
  updatedAt: TIMESTAMP
}
```

## Referenciación en Aplicación

### En Backend (Nest.js)

```typescript
// ARCHIVO: backend/src/modelos/almacenamiento/configuracion-almacenamiento-modelos.ts

class ConfiguracionAlmacenamientoModelos {
  // ✅ Punto centralizado de referencia
  readonly RUTAS_BASE = {
    PRINCIPAL: '/src/modelos/almacenamiento',
    GLB: '/src/modelos/almacenamiento/glb',
    SKETCHFAB: '/src/modelos/almacenamiento/sketchfab',
  };

  // Métodos para obtener rutas
  obtenerRutaCompleta(nombreArchivo: string, tipo: 'glb' | 'sketchfab')
  obtenerRutaRelativa(nombreArchivo: string, tipo: 'glb' | 'sketchfab')
  validarExtension(nombreArchivo: string)
  validarTamaño(tamanioBytes: number)
}
```

### Cómo Usar en Servicios

```typescript
// backend/src/modulos/modelos3d/modelos3d.service.ts

@Injectable()
export class Modelos3dService {
  constructor(
    private almacenamiento: ServicioAlmacenamientoModelos,
    private configuracion: ConfiguracionAlmacenamientoModelos,
  ) {}

  async subirModelo(archivo: Express.Multer.File) {
    // Guardar usando servicio centralizado
    const { nombreArchivo, rutaRelativa, tamaño } =
      await this.almacenamiento.guardarModelo(archivo, 'glb');

    // Guardar en BD con referencia centralizada
    const archivoModelo = await this.prisma.archivoModelo.create({
      data: {
        nombre: archivo.originalname,
        nombreAlmacenado: nombreArchivo,
        ruta: rutaRelativa,                    // ✅ Referencia relativa
        rutaCompleta: this.configuracion
          .obtenerRutaCompleta(nombreArchivo), // ✅ Referencia absoluta
        tipo: 'glb',
        mimeType: archivo.mimetype,
        tamaño: tamaño,
        modeloId: modeloId,
      },
    });

    return archivoModelo;
  }

  async obtenerModelo(nombreArchivo: string) {
    // Obtener desde almacenamiento centralizado
    return this.almacenamiento.obtenerArchivo(nombreArchivo);
  }

  async eliminarModelo(nombreArchivo: string) {
    // Eliminar desde almacenamiento centralizado
    return this.almacenamiento.eliminarModelo(nombreArchivo);
  }
}
```

### En Frontend (Next.js)

```typescript
// frontend/src/servicios/cliente-api.ts

class ClienteAPI {
  async obtenerModelo(idModelo: string) {
    // Endpoint retorna URL completa
    const respuesta = await axios.get(
      `${this.baseURL}/modelos3d/${idModelo}`
    );

    // Datos retornados
    return {
      ...respuesta.data,
      archivo: {
        nombre: 'modelo_1698765432_jaguar.glb',
        ruta: '/modelos/glb/modelo_1698765432_jaguar.glb',      // ✅ Relativa
        rutaCompleta: '/api/modelos3d/descargar/modelo_1698765432_jaguar.glb' // ✅ Endpoint
      }
    };
  }
}

// frontend/src/componentes/VisualizadorCanvas3D.tsx

function VisualizadorCanvas3D({ modelo }) {
  useEffect(() => {
    // Cargar desde endpoint seguro
    const cargarModelo = async () => {
      const respuesta = await fetch(modelo.archivo.rutaCompleta);
      const arrayBuffer = await respuesta.arrayBuffer();

      // Cargar con Three.js GLTFLoader
      const loader = new GLTFLoader();
      loader.parse(arrayBuffer, '', (gltf) => {
        scene.add(gltf.scene);
      });
    };

    cargarModelo();
  }, [modelo]);

  return <canvas ref={refCanvas} />;
}
```

## Flujo Completo de Almacenamiento

```
1. USUARIO SUBE ARCHIVO (Admin)
   └─> form.glb (10MB)

2. BACKEND RECIBE
   └─> Valida: extension ✓, tamaño ✓, tipo MIME ✓

3. SERVICIO DE ALMACENAMIENTO
   └─> Genera nombre único: modelo_1698765432_jaguar.glb
   └─> Ruta completa: src/modelos/almacenamiento/glb/modelo_1698765432_jaguar.glb
   └─> Ruta relativa: /modelos/glb/modelo_1698765432_jaguar.glb

4. GUARDAR EN DISCO
   └─> fs.writeFileSync(rutaCompleta, buffer)

5. REGISTRAR EN BASE DE DATOS
   archivos_modelos {
     nombre: "jaguar.glb",                               // Original
     nombreAlmacenado: "modelo_1698765432_jaguar.glb",  // Único
     ruta: "/modelos/glb/modelo_1698765432_jaguar.glb", // Relativa ✅
     tipo: "glb",
     mimeType: "model/gltf-binary",
     tamaño: 10485760,
     modeloId: "uuid-del-modelo"
   }

6. RESPUESTA AL FRONTEND
   {
     id: "uuid",
     nombre: "Jaguar",
     archivo: {
       nombre: "modelo_1698765432_jaguar.glb",
       ruta: "/modelos/glb/modelo_1698765432_jaguar.glb",
       rutaCompleta: "/api/modelos3d/descargar/modelo_1698765432_jaguar.glb",
       tamaño: 10485760
     },
     transformaciones: { ... }
   }

7. FRONTEND CARGA MODELO
   └─> fetch("/api/modelos3d/descargar/modelo_1698765432_jaguar.glb")
   └─> Parsea con GLTFLoader
   └─> Renderiza en Canvas3D

8. USUARIO DESCARGA (opcional)
   └─> Endpoint: GET /api/modelos3d/:id/descargar
   └─> Retorna archivo con headers de descarga
```

## Migración a Producción (Nube)

### Opción 1: AWS S3

```typescript
// Cambiar punto de referencia
class ConfiguracionAlmacenamientoModelos {
  readonly RUTAS_BASE = {
    PRINCIPAL: 'https://mi-bucket-s3.amazonaws.com/modelos',
    GLB: 'https://mi-bucket-s3.amazonaws.com/modelos/glb',
    SKETCHFAB: 'https://mi-bucket-s3.amazonaws.com/modelos/sketchfab',
  };
}
```

### Opción 2: Cloudinary

```typescript
class ConfiguracionAlmacenamientoModelos {
  readonly RUTAS_BASE = {
    PRINCIPAL: 'https://res.cloudinary.com/tu-cuenta/image/upload/modelos',
    GLB: 'https://res.cloudinary.com/tu-cuenta/image/upload/modelos/glb',
  };
}
```

### Opción 3: Almacenamiento Local + CDN

```typescript
// Mantener estructura local
// Servir a través de CDN (Cloudflare, Akamai, etc.)
// CDN cachea archivos automáticamente
```

## Optimizaciones de Caché

### Para Modelos Frecuentes

```typescript
// backend/src/infraestructura/cache/servicio-cache-memoria.ts

@Injectable()
export class ServicioCacheMemoria {
  async obtenerModeloEnCache(idModelo: string) {
    // Clave: modelo:id-modelo
    const clave = `modelo:${idModelo}`;
    
    // Intentar obtener del caché
    const modeloEnCache = await this.obtener<Modelo3D>(clave);
    if (modeloEnCache) return modeloEnCache;

    // Si no está, obtener de BD
    const modeloEnBD = await this.prisma.modelo3D.findUnique({
      where: { id: idModelo },
      include: { archivo: true, transformaciones: true }
    });

    // Guardar en caché por 1 hora
    await this.guardar(clave, modeloEnBD, TIEMPOS_EXPIRACION.LARGO);
    
    return modeloEnBD;
  }
}
```

## Casos de Uso Prácticos

### Cargar un modelo en Canvas3D

```typescript
// 1. Obtener modelo desde API
const modelo = await ClienteAPI.obtenerModelo(idModelo);

// 2. Acceder a rutas
console.log(modelo.archivo.nombre);           // "modelo_1698765432_jaguar.glb"
console.log(modelo.archivo.ruta);             // "/modelos/glb/modelo_1698765432_jaguar.glb"
console.log(modelo.archivo.rutaCompleta);     // "/api/modelos3d/descargar/..."

// 3. Descargar archivo
const respuesta = await fetch(modelo.archivo.rutaCompleta);
const arrayBuffer = await respuesta.arrayBuffer();

// 4. Parsear con Three.js
const loader = new GLTFLoader();
loader.parse(arrayBuffer, '', (gltf) => {
  scene.add(gltf.scene);
});
```

## Archivos Relacionados

- `backend/src/infraestructura/` - Caché y limitador
- `backend/src/modelos/almacenamiento/` - Gestión de archivos
- `backend/src/modulos/modelos3d/` - Lógica de negocio
- `backend/prisma/schema.prisma` - Estructura de BD
- `frontend/src/servicios/cliente-api.ts` - Cliente HTTP
- `frontend/src/componentes/VisualizadorCanvas3D.tsx` - Renderizador 3D

---

**Última actualización:** Abril 2026
**Responsable:** Sistema de Almacenamiento Centralizado
