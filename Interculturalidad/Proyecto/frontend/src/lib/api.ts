import axios, { AxiosInstance } from 'axios';

const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:3333/api';

class ApiClient {
  private client: AxiosInstance;

  constructor() {
    this.client = axios.create({
      baseURL: API_URL,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    // Agregar token JWT a cada petición
    this.client.interceptors.request.use((config) => {
      const token = typeof window !== 'undefined' ? localStorage.getItem('token') : null;
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    });
  }

  // Auth endpoints
  async register(email: string, password: string, name: string) {
    const res = await this.client.post('/auth/register', {
      email,
      password,
      name,
    });
    return res.data;
  }

  async login(email: string, password: string) {
    const res = await this.client.post('/auth/login', {
      email,
      password,
    });
    return res.data;
  }

  // Users endpoints
  async getUserProfile() {
    const res = await this.client.get('/users/profile');
    return res.data;
  }

  // Animales endpoints
  async getAnimals() {
    const res = await this.client.get('/animales');
    return res.data;
  }

  async getAnimal(id: string) {
    const res = await this.client.get(`/animales/${id}`);
    return res.data;
  }

  async createAnimal(data: any) {
    const res = await this.client.post('/animales', data);
    return res.data;
  }

  async updateAnimal(id: string, data: any) {
    const res = await this.client.put(`/animales/${id}`, data);
    return res.data;
  }

  async deleteAnimal(id: string) {
    const res = await this.client.delete(`/animales/${id}`);
    return res.data;
  }

  // Modelos 3D endpoints
  async getModelos() {
    const res = await this.client.get('/modelos3d');
    return res.data;
  }

  async getModelo(id: string) {
    const res = await this.client.get(`/modelos3d/${id}`);
    return res.data;
  }

  async uploadModelo(formData: FormData) {
    const res = await this.client.post('/modelos3d/upload', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return res.data;
  }

  async updateModeloTransformaciones(id: string, data: any) {
    const res = await this.client.put(`/modelos3d/${id}`, data);
    return res.data;
  }

  async deleteModelo(id: string) {
    const res = await this.client.delete(`/modelos3d/${id}`);
    return res.data;
  }
}

export const api = new ApiClient();
