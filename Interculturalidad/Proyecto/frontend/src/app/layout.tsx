import '@/app/globals.css';
import type { Metadata } from 'next';

export const metadata: Metadata = {
  title: 'Animales 3D - Explora y Edita Modelos',
  description: 'Plataforma para explorar y editar modelos 3D de animales con control total de transformaciones',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="es">
      <body className="bg-gradient-to-br from-primary to-secondary">
        <div className="min-h-screen">
          {children}
        </div>
      </body>
    </html>
  );
}
