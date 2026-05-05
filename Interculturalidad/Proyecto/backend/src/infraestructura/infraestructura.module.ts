import { Module } from '@nestjs/common';
import { CacheModule } from '@nestjs/cache-manager';
import { ThrottlerModule } from '@nestjs/throttler';
import { ServicioCacheMemoria } from './cache/servicio-cache-memoria';
import { ServicioLimitadorVelocidad } from './limitador/servicio-limitador-velocidad';

@Module({
  imports: [
    // Configurar caché en memoria (cambiar a Redis en producción)
    CacheModule.register({
      isGlobal: true,
      ttl: 3600, // 1 hora por defecto
      max: 1000, // máximo 1000 items en caché
    }),

    // Configurar limitador de velocidad
    ThrottlerModule.forRoot([
      {
        name: 'general',
        ttl: 60000, // 1 minuto
        limit: 100, // 100 peticiones por minuto
      },
      {
        name: 'autenticacion',
        ttl: 15 * 60 * 1000, // 15 minutos
        limit: 5, // 5 intentos por 15 minutos
      },
      {
        name: 'subir',
        ttl: 60 * 60 * 1000, // 1 hora
        limit: 3, // 3 cargas por hora
      },
    ]),
  ],
  providers: [ServicioCacheMemoria, ServicioLimitadorVelocidad],
  exports: [ServicioCacheMemoria, ServicioLimitadorVelocidad],
})
export class ModuloInfraestructura {}
