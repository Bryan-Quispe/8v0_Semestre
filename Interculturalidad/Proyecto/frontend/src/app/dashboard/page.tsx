'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { useAuthStore } from '@/hooks/useAuth';
import { api } from '@/lib/api';
import { Animal, Modelo3D } from '@/types';
import Canvas3DViewer from '@/components/Canvas3DViewer';

export default function DashboardPage() {
  const { isAuthenticated, user } = useAuthStore();
  const [animales, setAnimales] = useState<Animal[]>([]);
  const [modelos, setModelos] = useState<Modelo3D[]>([]);
  const [selectedAnimal, setSelectedAnimal] = useState<Animal | null>(null);
  const [selectedModelo, setSelectedModelo] = useState<Modelo3D | null>(null);
  const [loading, setLoading] = useState(true);
  const [showUploadForm, setShowUploadForm] = useState(false);
  const [showAnimalForm, setShowAnimalForm] = useState(false);
  const router = useRouter();

  useEffect(() => {
    const token = localStorage.getItem('token');
    const userData = localStorage.getItem('user');
    if (!token || !userData) {
      router.push('/auth/login');
      return;
    }

    useAuthStore.setState({
      token,
      user: JSON.parse(userData),
      isAuthenticated: true,
    });

    loadData();
  }, [router]);

  const loadData = async () => {
    try {
      setLoading(true);
      const [animalesData, modelosData] = await Promise.all([
        api.getAnimals(),
        api.getModelos(),
      ]);
      setAnimales(animalesData);
      setModelos(modelosData);
    } catch (error) {
      console.error('Error loading data:', error);
    } finally {
      setLoading(false);
    }
  };

  if (!isAuthenticated) {
    return <div className="text-center py-20 text-white">Redirigiendo...</div>;
  }

  return (
    <div className="min-h-screen bg-gray-900">
      {/* Navbar */}
      <nav className="bg-black/50 backdrop-blur-md border-b border-white/10">
        <div className="max-w-7xl mx-auto px-4 py-4 flex justify-between items-center">
          <h1 className="text-2xl font-bold text-white">🦁 Dashboard</h1>
          <div className="flex gap-4 items-center">
            <span className="text-white">{user?.name}</span>
            {user?.role === 'ADMIN' && (
              <span className="bg-red-600 text-white px-3 py-1 rounded-full text-sm">
                ADMIN
              </span>
            )}
            <button
              onClick={() => {
                localStorage.clear();
                useAuthStore.setState({
                  user: null,
                  token: null,
                  isAuthenticated: false,
                });
                router.push('/');
              }}
              className="btn-danger"
            >
              Salir
            </button>
          </div>
        </div>
      </nav>

      <main className="max-w-7xl mx-auto px-4 py-8">
        {selectedAnimal && selectedModelo ? (
          // Vista 3D del animal
          <div className="space-y-4">
            <button
              onClick={() => {
                setSelectedAnimal(null);
                setSelectedModelo(null);
              }}
              className="btn-primary"
            >
              ← Volver a Lista
            </button>

            <div className="grid lg:grid-cols-3 gap-6">
              <div className="lg:col-span-2">
                <Canvas3DViewer modelo={selectedModelo} />
              </div>

              <div className="card bg-white">
                <h3 className="text-xl font-bold mb-4 text-primary">
                  {selectedAnimal.nombre}
                </h3>
                {selectedAnimal.caracteristicas && (
                  <div className="space-y-2 text-gray-700">
                    {selectedAnimal.caracteristicas.tamaño && (
                      <p><strong>Tamaño:</strong> {selectedAnimal.caracteristicas.tamaño}</p>
                    )}
                    {selectedAnimal.caracteristicas.color && (
                      <p><strong>Color:</strong> {selectedAnimal.caracteristicas.color}</p>
                    )}
                    {selectedAnimal.caracteristicas.habitat && (
                      <p><strong>Hábitat:</strong> {selectedAnimal.caracteristicas.habitat}</p>
                    )}
                  </div>
                )}
              </div>
            </div>
          </div>
        ) : (
          // Lista de animales
          <div className="space-y-6">
            <div className="flex justify-between items-center">
              <h2 className="text-3xl font-bold text-white">Mis Animales</h2>
              <button
                onClick={() => setShowAnimalForm(true)}
                className="btn-secondary"
              >
                ➕ Nuevo Animal
              </button>
            </div>

            {loading ? (
              <div className="text-center text-white">Cargando...</div>
            ) : animales.length === 0 ? (
              <div className="card text-center bg-gray-800 text-white py-12">
                <p className="text-lg">No hay animales aún</p>
              </div>
            ) : (
              <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
                {animales.map((animal) => (
                  <div key={animal.id} className="card bg-gray-800 hover:bg-gray-700">
                    <h3 className="text-xl font-bold text-white mb-2">{animal.nombre}</h3>
                    {animal.raza && (
                      <p className="text-gray-400 mb-4">{animal.raza}</p>
                    )}
                    {animal.modelo ? (
                      <button
                        onClick={() => {
                          setSelectedAnimal(animal);
                          setSelectedModelo(animal.modelo!);
                        }}
                        className="btn-primary w-full"
                      >
                        Ver en 3D
                      </button>
                    ) : (
                      <p className="text-gray-500 text-sm">Sin modelo 3D asignado</p>
                    )}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}
      </main>

      {/* Admin Section */}
      {user?.role === 'ADMIN' && (
        <section className="max-w-7xl mx-auto px-4 py-8 border-t border-gray-700 mt-8">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-3xl font-bold text-white">🔧 Panel Admin - Modelos 3D</h2>
            <button
              onClick={() => setShowUploadForm(true)}
              className="btn-secondary"
            >
              ⬆️ Subir Modelo
            </button>
          </div>

          {modelos.length === 0 ? (
            <div className="card text-center bg-gray-800 text-white py-12">
              <p className="text-lg">No hay modelos 3D cargados</p>
            </div>
          ) : (
            <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
              {modelos.map((modelo) => (
                <div key={modelo.id} className="card bg-gray-800">
                  <h3 className="text-xl font-bold text-white mb-2">{modelo.nombre}</h3>
                  <p className="text-gray-400 mb-4">Tamaño: {(modelo.archivo.tamaño / 1024 / 1024).toFixed(2)} MB</p>
                  <button
                    onClick={() => api.deleteModelo(modelo.id).then(() => loadData())}
                    className="btn-danger w-full"
                  >
                    🗑️ Eliminar
                  </button>
                </div>
              ))}
            </div>
          )}
        </section>
      )}
    </div>
  );
}
