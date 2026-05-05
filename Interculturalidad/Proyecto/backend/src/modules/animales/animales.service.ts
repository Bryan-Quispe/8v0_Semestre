import { Injectable } from '@nestjs/common';
import { PrismaService } from '@/common/prisma/prisma.service';
import { CreateAnimalDto, UpdateAnimalDto } from './dto';

@Injectable()
export class AnimalesService {
  constructor(private prisma: PrismaService) {}

  async createAnimal(usuarioId: string, dto: CreateAnimalDto) {
    return this.prisma.animal.create({
      data: {
        ...dto,
        usuarioId,
        caracteristicas: {
          create: {},
        },
      },
      include: {
        caracteristicas: true,
        modelo: true,
      },
    });
  }

  async getAll() {
    return this.prisma.animal.findMany({
      include: {
        caracteristicas: true,
        modelo: true,
        usuario: {
          select: { id: true, name: true },
        },
      },
      orderBy: { createdAt: 'desc' },
    });
  }

  async getById(id: string) {
    return this.prisma.animal.findUnique({
      where: { id },
      include: {
        caracteristicas: true,
        modelo: true,
        usuario: {
          select: { id: true, name: true },
        },
      },
    });
  }

  async updateAnimal(id: string, dto: UpdateAnimalDto) {
    return this.prisma.animal.update({
      where: { id },
      data: dto,
      include: {
        caracteristicas: true,
        modelo: true,
      },
    });
  }

  async deleteAnimal(id: string) {
    return this.prisma.animal.delete({
      where: { id },
    });
  }

  async getByUsuario(usuarioId: string) {
    return this.prisma.animal.findMany({
      where: { usuarioId },
      include: {
        caracteristicas: true,
        modelo: true,
      },
    });
  }
}
