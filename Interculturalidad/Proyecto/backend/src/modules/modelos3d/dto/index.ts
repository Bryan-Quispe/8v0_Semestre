import { IsString, IsOptional, IsNumber } from 'class-validator';

export class CreateModelo3DDto {
  @IsString()
  nombre: string;

  @IsOptional()
  @IsString()
  raza?: string;

  @IsOptional()
  @IsString()
  descripcion?: string;

  @IsOptional()
  @IsString()
  color?: string;
}

export class UpdateTransformacionesDto {
  @IsOptional()
  @IsNumber()
  escalaX?: number;

  @IsOptional()
  @IsNumber()
  escalaY?: number;

  @IsOptional()
  @IsNumber()
  escalaZ?: number;

  @IsOptional()
  @IsNumber()
  rotacionX?: number;

  @IsOptional()
  @IsNumber()
  rotacionY?: number;

  @IsOptional()
  @IsNumber()
  rotacionZ?: number;

  @IsOptional()
  @IsNumber()
  posicionX?: number;

  @IsOptional()
  @IsNumber()
  posicionY?: number;

  @IsOptional()
  @IsNumber()
  posicionZ?: number;

  @IsOptional()
  @IsString()
  color?: string;
}
