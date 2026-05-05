import { IsString, IsOptional } from 'class-validator';

export class CreateAnimalDto {
  @IsString()
  nombre: string;

  @IsOptional()
  @IsString()
  descripcion?: string;

  @IsOptional()
  @IsString()
  raza?: string;

  @IsOptional()
  @IsString()
  modeloId?: string;

  @IsOptional()
  imagen?: string;
}

export class UpdateAnimalDto {
  @IsOptional()
  @IsString()
  nombre?: string;

  @IsOptional()
  @IsString()
  descripcion?: string;

  @IsOptional()
  @IsString()
  raza?: string;

  @IsOptional()
  @IsString()
  modeloId?: string;
}
