export type Role = 'ADMIN' | 'USER';

export interface User {
  id: string;
  email: string;
  name: string;
  role: Role;
  avatar?: string;
  createdAt: string;
}

export interface AuthResponse {
  access_token: string;
  user: User;
}

export interface Animal {
  id: string;
  nombre: string;
  descripcion?: string;
  raza?: string;
  caracteristicas?: {
    id: string;
    tamaño?: string;
    color?: string;
    habitat?: string;
  };
  modeloId?: string;
  modelo?: Modelo3D;
  imagen?: string;
  slug: string;
  usuarioId: string;
  usuario: { id: string; name: string };
  createdAt: string;
  updatedAt: string;
}

export interface Transformaciones {
  id: string;
  escalaX: number;
  escalaY: number;
  escalaZ: number;
  rotacionX: number;
  rotacionY: number;
  rotacionZ: number;
  posicionX: number;
  posicionY: number;
  posicionZ: number;
}

export interface Modelo3D {
  id: string;
  nombre: string;
  raza?: string;
  descripcion?: string;
  archivo: {
    id: string;
    filename: string;
    path: string;
    mimetype: string;
    tamaño: number;
  };
  transformaciones: Transformaciones;
  color: string;
  isPublico: boolean;
  slug: string;
  descargas: number;
  usuarioId: string;
  usuario: { id: string; name: string };
  createdAt: string;
  updatedAt: string;
}
