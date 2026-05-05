'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';
import { useAuthStore } from '@/hooks/useAuth';

export default function Home() {
  const { isAuthenticated, user } = useAuthStore();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
    // Cargar datos del localStorage
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');
    if (token && userData) {
      useAuthStore.setState({
        token,
        user: JSON.parse(userData),
        isAuthenticated: true,
      });
    }
  }, []);

  if (!mounted) return null;

  return (
    <main className="min-h-screen bg-gradient-to-br from-primary via-purple-600 to-secondary">
      {/* Navbar */}
      <nav className="bg-black/30 backdrop-blur-md border-b border-white/10">
        <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
          <div className="flex items-center gap-2">
            <h1 className="text-3xl font-bold text-white">🦁 Animales 3D</h1>
          </div>
          <div className="flex gap-4">
            {isAuthenticated ? (
              <>
                <Link href="/dashboard">
                  <button className="btn-primary">Dashboard</button>
                </Link>
                <button
                  onClick={() => {
                    localStorage.clear();
                    useAuthStore.setState({
                      user: null,
                      token: null,
                      isAuthenticated: false,
                    });
                    window.location.href = '/';
                  }}
                  className="btn-danger"
                >
                  Salir
                </button>
              </>
            ) : (
              <>
                <Link href="/auth/login">
                  <button className="btn-primary">Inicia Sesión</button>
                </Link>
                <Link href="/auth/register">
                  <button className="btn-secondary">Regístrate</button>
                </Link>
              </>
            )}
          </div>
        </div>
      </nav>

      {/* Hero Section */}
      <section className="max-w-7xl mx-auto px-4 py-20 text-center text-white">
        <h2 className="text-5xl font-bold mb-6">Explora el Mundo Animal en 3D</h2>
        <p className="text-xl mb-8 text-gray-100">
          Visualiza, edita y personaliza modelos 3D de animales. Solo para usuarios registrados.
        </p>

        {!isAuthenticated && (
          <div className="flex gap-4 justify-center">
            <Link href="/auth/login">
              <button className="btn-primary text-lg px-8 py-4">Comenzar Ahora</button>
            </Link>
            <Link href="/auth/register">
              <button className="btn-secondary text-lg px-8 py-4">Crear Cuenta</button>
            </Link>
          </div>
        )}
      </section>

      {/* Features */}
      <section className="max-w-7xl mx-auto px-4 py-16">
        <div className="grid md:grid-cols-3 gap-8">
          <div className="card bg-white/95">
            <h3 className="text-2xl font-bold text-primary mb-4">🎨 Edita Transformaciones</h3>
            <p className="text-gray-700">
              Controla escala, rotación y posición en tiempo real. Personaliza cada detalle del modelo.
            </p>
          </div>
          <div className="card bg-white/95">
            <h3 className="text-2xl font-bold text-primary mb-4">👥 Multi-Usuario</h3>
            <p className="text-gray-700">
              Crea tu cuenta y gestiona tus propios animales. Los admin pueden subir nuevos modelos.
            </p>
          </div>
          <div className="card bg-white/95">
            <h3 className="text-2xl font-bold text-primary mb-4">🔒 Seguro y Escalable</h3>
            <p className="text-gray-700">
              Autenticación JWT, Prisma ORM, PostgreSQL. Arquitectura profesional para crecer.
            </p>
          </div>
        </div>
      </section>
    </main>
  );
}
