import { NestFactory } from '@nestjs/core';
import { ValidationPipe, BadRequestException } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  const configService = app.get(ConfigService);

  // CORS
  app.enableCors({
    origin: configService.get('CORS_ORIGIN'),
    credentials: true,
  });

  // Validación global
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true,
      forbidNonWhitelisted: true,
      transform: true,
      transformOptions: {
        enableImplicitConversion: true,
      },
      exceptionFactory: (errors) => {
        const messages = errors.map((error) => ({
          field: error.property,
          messages: Object.values(error.constraints || {}),
        }));
        return new BadRequestException({
          statusCode: 400,
          message: 'Validation error',
          errors: messages,
        });
      },
    }),
  );

  // Prefix global
  app.setGlobalPrefix('api');

  const port = configService.get('PORT') || 3333;
  await app.listen(port);

  console.log(`✓ API ejecutándose en puerto ${port}`);
  console.log(`✓ Documentación en http://localhost:${port}/api`);
}

bootstrap().catch((err) => {
  console.error('✗ Error al iniciar la aplicación:', err);
  process.exit(1);
});
