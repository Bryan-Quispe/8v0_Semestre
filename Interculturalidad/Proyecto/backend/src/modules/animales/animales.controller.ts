import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Param,
  Body,
  UseGuards,
} from '@nestjs/common';
import { AnimalesService } from './animales.service';
import { CreateAnimalDto, UpdateAnimalDto } from './dto';
import { JwtAuthGuard } from '@/common/guards/jwt-auth.guard';
import { CurrentUser } from '@/common/decorators/current-user.decorator';

@Controller('animales')
export class AnimalesController {
  constructor(private animalesService: AnimalesService) {}

  @Get()
  async getAll() {
    return this.animalesService.getAll();
  }

  @Get(':id')
  async getById(@Param('id') id: string) {
    return this.animalesService.getById(id);
  }

  @Post()
  @UseGuards(JwtAuthGuard)
  async create(
    @Body() dto: CreateAnimalDto,
    @CurrentUser() user: any,
  ) {
    return this.animalesService.createAnimal(user.id, dto);
  }

  @Put(':id')
  @UseGuards(JwtAuthGuard)
  async update(
    @Param('id') id: string,
    @Body() dto: UpdateAnimalDto,
  ) {
    return this.animalesService.updateAnimal(id, dto);
  }

  @Delete(':id')
  @UseGuards(JwtAuthGuard)
  async delete(@Param('id') id: string) {
    return this.animalesService.deleteAnimal(id);
  }

  @Get('usuario/:usuarioId')
  async getByUsuario(@Param('usuarioId') usuarioId: string) {
    return this.animalesService.getByUsuario(usuarioId);
  }
}
