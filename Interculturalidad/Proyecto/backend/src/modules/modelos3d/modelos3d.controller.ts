import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
  UseGuards,
  UseInterceptors,
  UploadedFile,
  BadRequestException,
} from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
import { diskStorage } from 'multer';
import { Modelos3DService } from './modelos3d.service';
import { CreateModelo3DDto, UpdateTransformacionesDto } from './dto';
import { JwtAuthGuard } from '@/common/guards/jwt-auth.guard';
import { RolesGuard } from '@/common/guards/roles.guard';
import { Roles } from '@/common/decorators/roles.decorator';
import { CurrentUser } from '@/common/decorators/current-user.decorator';
import { v4 as uuid } from 'uuid';
import * as path from 'path';

@Controller('modelos3d')
export class Modelos3DController {
  constructor(private modelos3dService: Modelos3DService) {}

  @Get()
  async getAll() {
    return this.modelos3dService.getAll();
  }

  @Get(':id')
  async getById(@Param('id') id: string) {
    return this.modelos3dService.getById(id);
  }

  @Post('upload')
  @UseGuards(JwtAuthGuard, RolesGuard)
  @Roles('ADMIN')
  @UseInterceptors(
    FileInterceptor('file', {
      storage: diskStorage({
        destination: './uploads',
        filename: (req, file, cb) => {
          const randomName = uuid();
          const ext = path.extname(file.originalname);
          cb(null, `${randomName}${ext}`);
        },
      }),
      fileFilter: (req, file, cb) => {
        const allowedMimes = [
          'model/gltf+json',
          'model/gltf-binary',
          'application/octet-stream',
          'text/plain',
        ];
        if (
          allowedMimes.includes(file.mimetype) ||
          file.originalname.endsWith('.glb') ||
          file.originalname.endsWith('.gltf') ||
          file.originalname.endsWith('.obj')
        ) {
          cb(null, true);
        } else {
          cb(
            new BadRequestException(
              'Solo se permiten archivos .glb, .gltf, .obj',
            ),
            false,
          );
        }
      },
    }),
  )
  async upload(
    @UploadedFile() file: Express.Multer.File,
    @Body() dto: CreateModelo3DDto,
    @CurrentUser() user: any,
  ) {
    return this.modelos3dService.uploadModelo(user.id, dto, file);
  }

  @Put(':id')
  @UseGuards(JwtAuthGuard)
  async updateTransformaciones(
    @Param('id') id: string,
    @Body() dto: UpdateTransformacionesDto,
  ) {
    return this.modelos3dService.updateTransformaciones(id, dto);
  }

  @Delete(':id')
  @UseGuards(JwtAuthGuard, RolesGuard)
  @Roles('ADMIN')
  async delete(@Param('id') id: string) {
    return this.modelos3dService.deleteModelo(id);
  }

  @Get('usuario/:usuarioId')
  async getByUsuario(@Param('usuarioId') usuarioId: string) {
    return this.modelos3dService.getByUsuario(usuarioId);
  }
}
