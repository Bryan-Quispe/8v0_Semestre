import { Injectable, BadRequestException } from '@nestjs/common';
import { PrismaService } from '@/common/prisma/prisma.service';
import { CreateModelo3DDto, UpdateTransformacionesDto } from './dto';
import * as fs from 'fs';
import * as path from 'path';

@Injectable()
export class Modelos3DService {
  private uploadDir = process.env.UPLOAD_DIR || './uploads';

  constructor(private prisma: PrismaService) {
    // Crear directorio si no existe
    if (!fs.existsSync(this.uploadDir)) {
      fs.mkdirSync(this.uploadDir, { recursive: true });
    }
  }

  async uploadModelo(
    usuarioId: string,
    dto: CreateModelo3DDto,
    file: Express.Multer.File,
  ) {
    if (!file) {
      throw new BadRequestException('Debes subir un archivo');
    }

    // Validar extensión
    const allowedExtensions = ['.glb', '.gltf', '.obj'];
    const fileExt = path.extname(file.originalname).toLowerCase();
    if (!allowedExtensions.includes(fileExt)) {
      fs.unlinkSync(file.path);
      throw new BadRequestException('Formato de archivo no permitido');
    }

    try {
      const modelo = await this.prisma.modelo3D.create({
        data: {
          nombre: dto.nombre,
          raza: dto.raza,
          descripcion: dto.descripcion,
          color: dto.color || '#3498db',
          usuarioId,
          archivo: {
            create: {
              filename: file.filename,
              path: `/uploads/${file.filename}`,
              mimetype: file.mimetype,
              tamaño: file.size,
            },
          },
          transformaciones: {
            create: {
              escalaX: 1,
              escalaY: 1,
              escalaZ: 1,
            },
          },
        },
        include: {
          archivo: true,
          transformaciones: true,
        },
      });

      return modelo;
    } catch (error) {
      // Eliminar archivo si falla la creación
      fs.unlinkSync(file.path);
      throw error;
    }
  }

  async getAll() {
    return this.prisma.modelo3D.findMany({
      include: {
        archivo: true,
        transformaciones: true,
        usuario: {
          select: { id: true, name: true },
        },
      },
      orderBy: { createdAt: 'desc' },
    });
  }

  async getById(id: string) {
    return this.prisma.modelo3D.findUnique({
      where: { id },
      include: {
        archivo: true,
        transformaciones: true,
        usuario: {
          select: { id: true, name: true },
        },
      },
    });
  }

  async updateTransformaciones(id: string, dto: UpdateTransformacionesDto) {
    const transformaciones: any = {};

    if (dto.escalaX !== undefined) transformaciones.escalaX = dto.escalaX;
    if (dto.escalaY !== undefined) transformaciones.escalaY = dto.escalaY;
    if (dto.escalaZ !== undefined) transformaciones.escalaZ = dto.escalaZ;
    if (dto.rotacionX !== undefined) transformaciones.rotacionX = dto.rotacionX;
    if (dto.rotacionY !== undefined) transformaciones.rotacionY = dto.rotacionY;
    if (dto.rotacionZ !== undefined) transformaciones.rotacionZ = dto.rotacionZ;
    if (dto.posicionX !== undefined) transformaciones.posicionX = dto.posicionX;
    if (dto.posicionY !== undefined) transformaciones.posicionY = dto.posicionY;
    if (dto.posicionZ !== undefined) transformaciones.posicionZ = dto.posicionZ;

    const modelo = await this.prisma.modelo3D.findUnique({
      where: { id },
    });

    if (!modelo) {
      throw new BadRequestException('Modelo no encontrado');
    }

    const updated = await this.prisma.modelo3D.update({
      where: { id },
      data: {
        color: dto.color,
        transformaciones: {
          update: transformaciones,
        },
      },
      include: {
        archivo: true,
        transformaciones: true,
      },
    });

    return updated;
  }

  async deleteModelo(id: string) {
    const modelo = await this.prisma.modelo3D.findUnique({
      where: { id },
      include: { archivo: true },
    });

    if (!modelo) {
      throw new BadRequestException('Modelo no encontrado');
    }

    // Eliminar archivo físico
    if (modelo.archivo) {
      const filePath = path.join(this.uploadDir, modelo.archivo.filename);
      if (fs.existsSync(filePath)) {
        fs.unlinkSync(filePath);
      }
    }

    // Eliminar de base de datos (cascade)
    return this.prisma.modelo3D.delete({
      where: { id },
    });
  }

  async getByUsuario(usuarioId: string) {
    return this.prisma.modelo3D.findMany({
      where: { usuarioId },
      include: {
        archivo: true,
        transformaciones: true,
      },
    });
  }
}
