# 🦁 PLATAFORMA DE MASCOTAS 3D - PROYECTO SCRUM

![Versión](https://img.shields.io/badge/versión-1.0.0--mvp-blue.svg)
![Metodología](https://img.shields.io/badge/metodología-Scrum-orange.svg)
![Node](https://img.shields.io/badge/node-%3E%3D18.0.0-brightgreen.svg)
![TypeScript](https://img.shields.io/badge/lenguaje-TypeScript-blue.svg)
![Estado](https://img.shields.io/badge/estado-En%20Desarrollo-yellow.svg)

> **Plataforma web desarrollada con Scrum para visualizar y gestionar mascotas con modelos 3D de animales. MVP enfocado en autenticación, registro de mascotas y visualización de modelos 3D base.**

---

## 📋 TABLA DE CONTENIDOS

### 🎯 DOCUMENTACIÓN SCRUM (Principal)
1. [📌 Definición del Producto](#definición-del-producto-visión)
2. [🏃 Marco de Trabajo Scrum](#marco-de-trabajo-scrum)
3. [📚 Product Backlog](#product-backlog)
4. [⚡ Sprint Backlog (MVP)](#sprint-backlog-mvp)
5. [✅ Incremento del Producto](#incremento-del-producto)
6. [👥 Equipo Scrum](#equipo-scrum)
7. [📅 Ceremonia de Sprint](#ceremonia-de-sprint)

### 📖 DOCUMENTACIÓN TÉCNICA
- **[ARQUITECTURA_MVC.md](docs/ARQUITECTURA_MVC.md)** - Patrón MVC
- **[ESCALABILIDAD_Y_OPTIMIZACION.md](docs/ESCALABILIDAD_Y_OPTIMIZACION.md)** - Rendimiento
- **[INDICE_MODELOS.md](src/modelos/INDICE_MODELOS.md)** - Gestión de modelos 3D

### 🚀 REFERENCIA RÁPIDA
8. [📦 Stack Tecnológico](#stack-tecnológico)
9. [⚙️ Configuración e Instalación](#configuración-e-instalación)
10. [🔐 Sistema de Seguridad](#sistema-de-seguridad)

---

## 📖 REFERENCIAS SCRUM CITADAS

Este proyecto implementa el **Marco de Trabajo Scrum** según:

- 📘 **"Scrum Guide 2020"** - Ken Schwaber y Jeff Sutherland (Oficial)
  - Disponible en: https://www.scrum.org
  
- 📕 **"Scrum: A Pocket Guide"** - Günther Verheyen
  - Explicación concisa de roles, eventos y artefactos
  
- 📙 **"Agile Product Management with Scrum"** - Roman Pichler
  - Enfoque en vision de producto y priorización del backlog

---

## 🎯 DEFINICIÓN DEL PRODUCTO - VISIÓN

### Declaración de Visión

**"Una plataforma web que permite a los usuarios gestionar sus mascotas con modelos 3D interactivos, permitiendo registrar información de sus animales (nombre, edad, fotos) y visualizar representaciones 3D base para entender mejor a sus compañeros animales."**

### Objetivo del MVP (Mínimo Producto Viable)

**Alcance**: Sprint 1 y 2 (3-4 semanas)

El MVP debe incluir:

✅ **Sistema de Autenticación**
- Registro de nuevos usuarios
- Inicio de sesión con JWT
- Recuperación de contraseña (futuro)

✅ **Gestión de Mascotas**
- Crear perfil de mascota (nombre, edad, tipo)
- Subir fotos de mascota
- Listar mascotas del usuario
- Ver detalles de mascota

✅ **Visualización de Modelos 3D**
- Cargar modelos 3D base de animales (Gato, Perro, Jaguar, Águila, Serpiente)
- Renderizar en formato GLB/GLTF con Three.js
- Visualizar en canvas 3D interactivo
- Interacción básica (rotar, zoom)

❌ **NO Incluido en MVP** (Futuro - Post-MVP)
- Edición de modelos 3D
- Transformaciones personalizadas (escala, rotación)
- Panel administrativo avanzado
- Integración con Sketchfab
- Compartir modelos públicamente

### Objetivos de Negocio

1. **Validar demanda** de plataforma de mascotas 3D
2. **Obtener feedback** de usuarios reales
3. **Crear base sólida** para futuras expansiones
4. **Demostrar viabilidad técnica** de visualización 3D

### Criterios de Éxito

- ✅ Usuarios pueden registrarse y acceder
- ✅ Usuarios pueden crear 5+ mascotas
- ✅ Cargar modelo 3D en <2 segundos
- ✅ 0 errores de seguridad (JWT, SQL injection)
- ✅ Interfaz responsiva en móvil (80%+)
- ✅ Tests unitarios >80% cobertura

---

## 🏃 MARCO DE TRABAJO SCRUM

### ¿Qué es Scrum?

**Scrum** (según el Scrum Guide) es:

> *"Un marco ligero que ayuda a personas, equipos y organizaciones a generar valor mediante soluciones adaptativas para problemas complejos."*

### Pilares de Scrum

| Pilar | Descripción |
|-------|-------------|
| **Transparencia** | Todos ven el Product Backlog, Sprint Backlog e Incremento |
| **Inspección** | Se revisa progreso en Daily Standup y Sprint Review |
| **Adaptación** | Se ajusta plan basado en feedback en Sprint Retrospective |

### Estructura Scrum para este Proyecto

```
PRODUCTO
   │
   ├─ VISIÓN & ROADMAP
   │  └─ "Plataforma de mascotas 3D interactivas"
   │
   ├─ PRODUCT BACKLOG
   │  ├─ Product Backlog Items (PBIs) priorizados
   │  └─ Estimación en Story Points
   │
   ├─ SPRINT (1-2 semanas)
   │  │
   │  ├─ SPRINT PLANNING (2h)
   │  │  └─ Seleccionar PBIs y crear Sprint Backlog
   │  │
   │  ├─ SPRINT BACKLOG
   │  │  ├─ Tasks técnicas
   │  │  └─ Definition of Done (DoD)
   │  │
   │  ├─ DAILY STANDUP (15 min)
   │  │  ├─ ¿Qué hice ayer?
   │  │  ├─ ¿Qué haré hoy?
   │  │  └─ ¿Qué bloqueos tengo?
   │  │
   │  └─ SPRINT REVIEW + RETROSPECTIVE
   │     ├─ Demostración de Incremento
   │     ├─ Feedback de stakeholders
   │     └─ Mejoras para próximo sprint
   │
   └─ INCREMENTO (Producto potencialmente entregable)
```

---

## 📚 PRODUCT BACKLOG

**Definición (Scrum Guide):** *"Lista ordenada y dinámica de características, mejoras y correcciones que dan valor al cliente."*

### Estado Actual: PRODUCT BACKLOG PRIORIZADO

```
ÉPICA 1: AUTENTICACIÓN Y USUARIOS
├─ [ALTO] PBI-001: Registrar nuevo usuario
│  ├─ Descripción: Usuario puede crear cuenta con email y contraseña
│  ├─ Criterios de Aceptación:
│  │  ✓ Validación de email único
│  │  ✓ Contraseña hasheada con Bcrypt (10 rounds)
│  │  ✓ Mensajes de error claros
│  │  ✓ Respuesta REST exitosa
│  ├─ Story Points: 5
│  └─ Sprint: 1
│
├─ [ALTO] PBI-002: Iniciar sesión (Login)
│  ├─ Descripción: Usuario inicia sesión y recibe JWT válido
│  ├─ Criterios de Aceptación:
│  │  ✓ Validación de credenciales
│  │  ✓ JWT válido por 24h
│  │  ✓ Token guardable en cliente
│  │  ✓ Logout invalida token
│  ├─ Story Points: 3
│  └─ Sprint: 1
│
└─ [MEDIO] PBI-003: Editar perfil de usuario (Futuro Sprint 3+)
   ├─ Story Points: 3
   └─ Sprint: 3+

ÉPICA 2: GESTIÓN DE MASCOTAS
├─ [ALTO] PBI-005: Crear perfil de mascota
│  ├─ Descripción: Usuario crea mascota con nombre, edad, tipo
│  ├─ Criterios de Aceptación:
│  │  ✓ Validación de campos obligatorios
│  │  ✓ Relación a usuario propietario
│  │  ✓ Timestamp de creación
│  │  ✓ Respuesta REST clara
│  ├─ Story Points: 5
│  └─ Sprint: 1
│
├─ [ALTO] PBI-006: Subir fotos de mascota
│  ├─ Descripción: Usuario sube imagen JPG/PNG de su mascota
│  ├─ Criterios de Aceptación:
│  │  ✓ Validación de extensión (.jpg, .png)
│  │  ✓ Tamaño máximo 5MB
│  │  ✓ Compresión automática
│  │  ✓ Almacenamiento en servidor
│  ├─ Story Points: 5
│  └─ Sprint: 2
│
├─ [ALTO] PBI-007: Listar mascotas del usuario
│  ├─ Descripción: Ver todas tus mascotas en dashboard
│  ├─ Story Points: 3
│  └─ Sprint: 1
│
└─ [MEDIO] PBI-008: Ver detalles de mascota
   ├─ Descripción: Página individual de mascota con modelo 3D
   ├─ Story Points: 3
   └─ Sprint: 2

ÉPICA 3: VISUALIZACIÓN DE MODELOS 3D
├─ [ALTO] PBI-010: Cargar modelos 3D base de animales
│  ├─ Descripción: Sistema tiene modelos GLB/GLTF de 5+ animales base
│  ├─ Animales Base: Gato, Perro, Jaguar, Águila, Serpiente
│  ├─ Criterios de Aceptación:
│  │  ✓ Modelos optimizados (<2MB cada uno)
│  │  ✓ Almacenados en src/modelos/almacenamiento/glb/
│  │  ✓ Indexados en BD con metadata
│  │  ✓ Acceso vía REST API
│  │  ✓ Descargables en <2 segundos
│  ├─ Story Points: 8
│  └─ Sprint: 1-2
│
├─ [ALTO] PBI-011: Renderizar modelo 3D en canvas
│  ├─ Descripción: Three.js carga y muestra modelo en pantalla
│  ├─ Criterios de Aceptación:
│  │  ✓ Cargar en <2 segundos
│  │  ✓ Rotación con mouse
│  │  ✓ Zoom con rueda de scroll
│  │  ✓ Iluminación realista
│  │  ✓ Sin errores de renderizado
│  ├─ Story Points: 8
│  └─ Sprint: 2
│
└─ [FUTURO] PBI-012: Editar modelo 3D (POST-MVP)
   ├─ Descripción: Transformaciones personalizadas
   ├─ Alcance: Después de Sprint 2
   └─ Story Points: 13

ÉPICA 4: INFRAESTRUCTURA Y SEGURIDAD
├─ [ALTO] PBI-015: Setup BD PostgreSQL
│  ├─ Story Points: 3
│  └─ Sprint: 0 (Setup inicial)
│
├─ [ALTO] PBI-016: Configurar JWT y autenticación
│  ├─ Story Points: 5
│  └─ Sprint: 1
│
└─ [MEDIO] PBI-017: Rate limiting en rutas críticas
   ├─ Story Points: 3
   └─ Sprint: 2
```

### Resumen del Product Backlog

| Métrica | Valor |
|---------|-------|
| Total PBIs | 13 |
| Story Points Total | 65 |
| Épicas | 4 |
| MVP Items (Sprint 1-2) | 10 |
| MVP Story Points | ~45 |
| Post-MVP Items | 3 |

---

## ⚡ SPRINT BACKLOG (MVP)

**Definición (Scrum Guide):** *"Conjunto de Product Backlog Items seleccionados para el sprint más el plan para entregarlos."*

### SPRINT 1: AUTENTICACIÓN + MASCOTAS (Semana 1-2)

#### Sprint Goal
*"Implementar sistema de autenticación funcional y permitir a usuarios registrar sus mascotas con modelos 3D base."*

#### Sprint Backlog Estimado

| ID | Tarea | PBI | Duración Est. | Estado |
|----|----|-----|--------|--------|
| T001 | Setup BD PostgreSQL + Prisma | PBI-015 | 2h | Pendiente |
| T002 | Crear tabla `usuarios` en schema Prisma | PBI-001,002 | 2h | Pendiente |
| T003 | Endpoint POST /auth/register | PBI-001 | 3h | Pendiente |
| T004 | Endpoint POST /auth/login | PBI-002 | 2h | Pendiente |
| T005 | Guard JWT para rutas protegidas | PBI-016 | 2h | Pendiente |
| T006 | Página de Registro (Frontend Next.js) | PBI-001 | 3h | Pendiente |
| T007 | Página de Login (Frontend Next.js) | PBI-002 | 2h | Pendiente |
| T008 | Crear tabla `mascotas` en Prisma | PBI-005 | 2h | Pendiente |
| T009 | Endpoint POST /animales (crear mascota) | PBI-005 | 3h | Pendiente |
| T010 | Endpoint GET /animales (listar mascotas) | PBI-007 | 2h | Pendiente |
| T011 | Componente FormularioMascota (React) | PBI-005 | 3h | Pendiente |
| T012 | Página Dashboard (listado mascotas) | PBI-007 | 3h | Pendiente |
| T013 | Cargar modelos 3D base a servidor | PBI-010 | 4h | Pendiente |
| T014 | Crear tabla `modelos_3d` en BD | PBI-010 | 2h | Pendiente |
| T015 | Endpoint GET /modelos3d/:id | PBI-010 | 2h | Pendiente |

**Total Sprint 1: ~40 horas estimadas**

#### Definition of Done (DoD)

Cada tarea en Sprint 1 debe cumplir:

- ✅ Código revisado en Pull Request
- ✅ Tests unitarios con >80% cobertura
- ✅ Integración exitosa en rama `develop`
- ✅ API testeable en Postman/Thunder Client
- ✅ Documentación de endpoint en comentarios
- ✅ Sin warnings de TypeScript
- ✅ Error handling adecuado
- ✅ Validación de entrada en DTO
- ✅ Sin console.logs de debug

---

### SPRINT 2: VISUALIZACIÓN 3D + FOTOS (Semana 3-4)

#### Sprint Goal
*"Implementar renderización 3D completa y permitir subida de fotos de mascotas."*

#### Sprint Backlog Estimado

| ID | Tarea | PBI | Duración Est. | Estado |
|----|----|-----|--------|--------|
| T016 | Endpoint POST /animales/:id/foto | PBI-006 | 3h | Pendiente |
| T017 | Validación y compresión de imágenes | PBI-006 | 3h | Pendiente |
| T018 | Componente Canvas3DViewer (Three.js) | PBI-011 | 5h | Pendiente |
| T019 | GLTFLoader e implementación | PBI-011 | 3h | Pendiente |
| T020 | Controles de rotación y zoom | PBI-011 | 2h | Pendiente |
| T021 | Endpoint GET /animales/:id (detalles) | PBI-008 | 2h | Pendiente |
| T022 | Página de detalles de mascota | PBI-008 | 3h | Pendiente |
| T023 | Asociar mascota a modelo 3D base | PBI-010 | 2h | Pendiente |
| T024 | Mostrar modelo 3D en ficha mascota | PBI-012 | 2h | Pendiente |
| T025 | Rate limiting en upload de fotos | PBI-017 | 2h | Pendiente |
| T026 | Tests E2E: Flujo completo | - | 4h | Pendiente |
| T027 | Documentación de API REST | - | 2h | Pendiente |

**Total Sprint 2: ~35 horas estimadas**

---

## ✅ INCREMENTO DEL PRODUCTO

**Definición (Scrum Guide):** *"Suma de todos los Product Backlog Items completados durante el sprint + incrementos previos."*

### Incremento MVP Esperado (Al finalizar Sprint 2)

#### Backend Funcional

```
✅ API REST Nest.js con endpoints:
  ├─ POST   /api/auth/register        (Crear usuario)
  ├─ POST   /api/auth/login           (Obtener JWT)
  ├─ POST   /api/animales             (Crear mascota)
  ├─ GET    /api/animales             (Listar mascotas)
  ├─ GET    /api/animales/:id         (Detalles mascota)
  ├─ POST   /api/animales/:id/foto    (Subir foto)
  └─ GET    /api/modelos3d/:id        (Descargar modelo 3D)

✅ Base de Datos Funcional
  ├─ Tabla usuarios (con hash bcrypt 10 rounds)
  ├─ Tabla mascotas (relación a usuario)
  ├─ Tabla fotos_mascota
  └─ Tabla modelos_3d (con índices en queries frecuentes)

✅ Seguridad Implementada
  ├─ JWT con expiración 24h
  ├─ Guards de autenticación @UseGuards(JwtAuthGuard)
  ├─ Validación de entrada con class-validator
  └─ Rate limiting en rutas críticas

✅ Modelos 3D Cargados
  ├─ 5 animales base en formato GLB
  ├─ Indexados en BD con metadata
  ├─ Optimizados (<2MB c/u)
  └─ Servidos con headers correctos (Content-Type)
```

#### Frontend Funcional

```
✅ Aplicación Next.js con páginas:
  ├─ /auth/register              (Página de registro)
  ├─ /auth/login                 (Página de login)
  ├─ /dashboard                  (Listado de mascotas)
  ├─ /dashboard/mascota/:id      (Detalles + modelo 3D)
  └─ /dashboard/crear            (Formulario crear mascota)

✅ Componentes React
  ├─ FormularioRegistro (con validación)
  ├─ FormularioLogin
  ├─ FormularioMascota
  ├─ Canvas3DViewer (Three.js integrado)
  ├─ CardMascota
  └─ Navbar con logout

✅ Funcionalidad Completa
  ├─ Login/Registro funcionando
  ├─ Token guardado en localStorage
  ├─ Sesión persistente
  ├─ Listado de mascotas dinámico
  ├─ Modelos 3D renderizados interactivos
  └─ Interfaz responsiva (desktop + móvil)
```

### Criterios de Aceptación Globales (MVP)

```
✅ SEGURIDAD
   ├─ 0 contraseñas planas en BD
   ├─ 0 SQL injections
   ├─ 0 XSS vulnerabilities
   └─ CORS configurado correctamente

✅ RENDIMIENTO
   ├─ Carga página <3s
   ├─ Modelo 3D renderiza <2s
   ├─ API responde <200ms
   └─ Bundle <500KB (gzip)

✅ USABILIDAD
   ├─ Interfaz intuitiva
   ├─ Mensajes de error claros
   ├─ Responsive en móvil
   └─ Accesibilidad WCAG AA

✅ CALIDAD DE CÓDIGO
   ├─ TypeScript sin errores
   ├─ Tests unitarios >80%
   ├─ SIN comentarios "TODO"
   ├─ SIN console.logs
   └─ Linting pasado
```

---

## 👥 EQUIPO SCRUM

### Composición del Equipo

| Rol Scrum | Responsabilidades |
|-----------|-------------------|
| **Product Owner (PO)** | Priorizar backlog, validar incremento, feedback usuario |
| **Scrum Master** | Facilitar ceremonias, remover bloqueos, coach del equipo |
| **Desarrollador Frontend** | Next.js, React, Three.js, validación UI |
| **Desarrollador Backend** | Nest.js, API REST, BD, lógica de negocio |
| **QA/DevOps** | Tests, deployment, infraestructura |

### Capacidad del Equipo

- **Velocidad Inicial:** ~40 Story Points/Sprint
- **Duración Sprint:** 2 semanas
- **Reuniones Semanales:** 4-5 horas
- **Horas de Trabajo:** 40 horas/semana por persona

---

## 📅 CEREMONIA DE SPRINT

### 1️⃣ SPRINT PLANNING (2 horas)

**Cuándo:** Lunes 9:00 AM | **Participantes:** Todo equipo Scrum

**Agenda Detallada:**
```
0:00-0:15 PO presenta Product Backlog ordenado
0:15-0:45 Equipo estima PBIs usando Planning Poker
0:45-1:05 Seleccionar PBIs según velocidad histórica
1:05-1:35 Dividir PBIs en tasks técnicas granulares
1:35-1:50 Consenso en Sprint Goal claro y alcanzable
1:50-2:00 Confirmar Definition of Done con equipo
```

**Output:** Sprint Backlog comprometido, Sprint Goal en Jira, DoD confirmado

---

### 2️⃣ DAILY STANDUP (15 minutos)

**Cuándo:** Lunes-Viernes 9:30 AM | **Lugar:** Presencial o videollamada

**Formato:** Cada persona responde en <2 min:
```
1. ¿Qué completé ayer?
2. ¿Qué completaré hoy?
3. ¿Qué bloqueos/impedimentos tengo?
```

**Nota:** Si un bloqueador demora >30 min, se escala a Scrum Master para resolverlo offline

---

### 3️⃣ SPRINT REVIEW (1.5 horas)

**Cuándo:** Viernes 3:00 PM | **Participantes:** Equipo + Stakeholders/PO

**Agenda:**
```
0:00-0:45 Demo funcional del Incremento
          - Mostrar cada feature completada
          - Responder preguntas en tiempo real
          
0:45-1:15 Recolectar Feedback Stakeholders
          - ¿Qué funcionó bien?
          - ¿Qué cambios piden?
          - ¿Nuevas ideas?

1:15-1:30 Actualizar Product Backlog
          - Nuevos ítems = nuevos PBIs
          - Re-priorizar según feedback
```

**Output:** Feedback documentado, Product Backlog actualizado

---

### 4️⃣ SPRINT RETROSPECTIVE (1 hora)

**Cuándo:** Viernes 4:30 PM | **Participantes:** Solo equipo de desarrollo

**Formato: "What Went Well, What Didn't, What To Improve"**

```
0:00-0:15 QUÉ SALIÓ BIEN
          └─ Procesos, herramientas, comunicación positiva

0:15-0:35 QUÉ MEJORAR
          └─ Desafíos, cuellos de botella, problemas

0:35-1:00 ACCIONES CONCRETAS
          └─ 2-3 mejoras específicas para próximo sprint
          └─ Asignar propietario de cada acción
```

**Ejemplo de Mejora Implementada:**
- ❌ PROBLEMA: Code review tardaba 2-3 días
- ✅ SOLUCIÓN: Code review máximo 4 horas
- 📍 IMPLEMENTAR: Assign automático de reviewers en GitHub

---

## 📦 STACK TECNOLÓGICO

| Capa | Tecnología | Versión |
|------|-----------|---------|
| **Backend** | Nest.js | 10.2+ |
| **Frontend** | Next.js + React | 14+ / 18+ |
| **ORM** | Prisma | 5.7+ |
| **BD** | PostgreSQL | 15+ |
| **Auth** | JWT + Bcrypt | Latest |
| **3D** | Three.js | r160+ |
| **Styling** | Tailwind CSS | 3+ |
| **State** | Zustand | 4.4+ |
| **HTTP** | Axios | 1.6+ |
| **Runtime** | Node.js | 18+ LTS |
| **Package Manager** | npm | 9+ |

---

## ⚙️ CONFIGURACIÓN E INSTALACIÓN

### ✅ Requisitos Previos

```bash
node --version   # v18.0.0+
npm --version    # 9.0.0+
```

### 📥 Instalación Rápida (5 minutos)

**Terminal 1 - Backend:**
```bash
cd backend
npm install
cp .env.example .env
# Edita .env con tu DATABASE_URL de PostgreSQL
npx prisma migrate dev
npm run start:dev
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm install
npm run dev
```

Accede a: `http://localhost:3000`

---

## 🔐 SISTEMA DE SEGURIDAD

- ✅ **JWT Authentication** (24h expiration)
- ✅ **Bcrypt Hashing** (10 rounds)
- ✅ **Prisma ORM** (SQL injection prevention)
- ✅ **CORS Configurado**
- ✅ **Rate Limiting** (futuro implementación)
- ✅ **Class-validator** (Input validation)
- ✅ **Roles & Permissions** (USER/ADMIN)

---

## 📚 DOCUMENTACIÓN ADICIONAL

- [ARQUITECTURA_MVC.md](docs/ARQUITECTURA_MVC.md) - Patrón MVC completo
- [ESCALABILIDAD_Y_OPTIMIZACION.md](docs/ESCALABILIDAD_Y_OPTIMIZACION.md) - Rendimiento y optimización
- [INDICE_MODELOS.md](src/modelos/INDICE_MODELOS.md) - Gestión centralizada de modelos 3D

---

**Última actualización:** Mayo 2026 | **Estado:** 🟡 En Desarrollo Scrum | **Metodología:** Scrum Framework (Ken Schwaber & Jeff Sutherland)
