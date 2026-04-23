import os
import shutil
import subprocess
from pathlib import Path

import cv2
import numpy as np


# Solo unir estas 3 imagenes, en secuencia, sin efectos ni transformaciones.
IMAGE_FILES = ["image1.png", "image2.png", "image3.png"]
MUSIC_CANDIDATES = ["musica.mp3", "music.mp3", "audio.mp3"]

FPS = 30
SECONDS_PER_IMAGE = 11

TEMP_VIDEO = "video_temp.mp4"
SILENT_VIDEO = "video_sin_audio.mp4"
FINAL_VIDEO = "video_con_audio.mp4"


def load_images_unchanged(image_paths: list[str]) -> list:
    """Carga imagenes sin cambiar su contenido (sin resize ni filtros)."""
    images = []
    for path in image_paths:
        image = cv2.imread(path, cv2.IMREAD_COLOR)
        if image is None:
            raise FileNotFoundError(f"No se encontro la imagen: {path}")
        images.append(image)
    return images


def write_video_sequential(images: list, output_path: str) -> None:
    """Genera video secuencial: imagen1 -> imagen2 -> imagen3."""
    max_height = max(image.shape[0] for image in images)
    max_width = max(image.shape[1] for image in images)

    # H.264 con yuv420p requiere dimensiones pares para maxima compatibilidad.
    if max_height % 2 != 0:
        max_height += 1
    if max_width % 2 != 0:
        max_width += 1

    # Mantiene cada imagen intacta: sin escalar y sin recortar, solo centrada en fondo negro.
    def image_on_canvas(image):
        canvas = np.zeros((max_height, max_width, 3), dtype=np.uint8)
        h, w = image.shape[:2]
        y = (max_height - h) // 2
        x = (max_width - w) // 2
        canvas[y : y + h, x : x + w] = image
        return canvas

    fourcc = cv2.VideoWriter_fourcc(*"mp4v")
    writer = cv2.VideoWriter(output_path, fourcc, FPS, (max_width, max_height))

    frames_per_image = int(SECONDS_PER_IMAGE * FPS)

    for image in images:
        frame = image_on_canvas(image)
        for _ in range(frames_per_image):
            writer.write(frame)

    writer.release()


def find_ffmpeg_binary() -> str | None:
    """Busca ffmpeg en PATH y en rutas comunes de Windows/WinGet."""
    ffmpeg_in_path = shutil.which("ffmpeg")
    if ffmpeg_in_path:
        return ffmpeg_in_path

    local_app_data = os.environ.get("LOCALAPPDATA", "")
    program_files = os.environ.get("ProgramFiles", "")
    user_profile = os.environ.get("USERPROFILE", "")

    direct_candidates = [
        Path(local_app_data) / "Microsoft" / "WinGet" / "Links" / "ffmpeg.exe",
        Path(program_files) / "ffmpeg" / "bin" / "ffmpeg.exe",
        Path(user_profile) / "scoop" / "shims" / "ffmpeg.exe",
    ]

    for candidate in direct_candidates:
        if candidate.exists():
            return str(candidate)

    winget_packages_dir = Path(local_app_data) / "Microsoft" / "WinGet" / "Packages"
    if winget_packages_dir.exists():
        matches = list(winget_packages_dir.glob("**/ffmpeg.exe"))
        if matches:
            matches.sort(key=lambda p: p.stat().st_mtime, reverse=True)
            return str(matches[0])

    return None


def export_phone_compatible_video(
    video_path: str, output_path: str, music_path: str | None = None
) -> bool:
    """Exporta MP4 compatible con telefono (H.264 + yuv420p + AAC opcional)."""
    ffmpeg_bin = find_ffmpeg_binary()
    if ffmpeg_bin is None:
        return False

    if music_path:
        cmd = [
            ffmpeg_bin,
            "-y",
            "-i",
            video_path,
            "-i",
            music_path,
            "-map",
            "0:v:0",
            "-map",
            "1:a:0",
            "-c:v",
            "libx264",
            "-pix_fmt",
            "yuv420p",
            "-movflags",
            "+faststart",
            "-c:a",
            "aac",
            "-b:a",
            "192k",
            "-shortest",
            output_path,
        ]
    else:
        cmd = [
            ffmpeg_bin,
            "-y",
            "-i",
            video_path,
            "-c:v",
            "libx264",
            "-pix_fmt",
            "yuv420p",
            "-movflags",
            "+faststart",
            "-an",
            output_path,
        ]

    completed = subprocess.run(cmd, capture_output=True, text=True)
    if completed.returncode != 0:
        print("ffmpeg devolvio error al exportar video compatible:")
        print(completed.stderr[-1000:])
    return completed.returncode == 0


def find_music_file() -> str | None:
    for candidate in MUSIC_CANDIDATES:
        if os.path.exists(candidate):
            return candidate
    return None


def main() -> None:
    missing_images = [image for image in IMAGE_FILES if not os.path.exists(image)]
    if missing_images:
        print("Faltan imagenes para crear el video:")
        for image in missing_images:
            print(f" - {image}")
        return

    print("Cargando imagenes sin alterar...")
    images = load_images_unchanged(IMAGE_FILES)

    print("Generando video base temporal...")
    write_video_sequential(images, TEMP_VIDEO)

    music_file = find_music_file()
    if music_file is None:
        print("No se encontro pista de audio (.mp3).")
        print("Exportando solo video compatible para telefono...")
        if export_phone_compatible_video(TEMP_VIDEO, SILENT_VIDEO):
            print(f"Video sin audio (compatible): {SILENT_VIDEO}")
        else:
            print("No se pudo exportar el formato compatible.")
            print("Verifica que ffmpeg este instalado.")
        if os.path.exists(TEMP_VIDEO):
            os.remove(TEMP_VIDEO)
        return

    print(f"Agregando audio: {music_file}")
    if export_phone_compatible_video(TEMP_VIDEO, FINAL_VIDEO, music_file):
        print(f"Video final compatible con audio: {FINAL_VIDEO}")
    else:
        print("No se pudo agregar audio automaticamente.")
        print("Verifica que ffmpeg este instalado y disponible en PATH.")
        print("Intentando generar al menos version sin audio...")
        if export_phone_compatible_video(TEMP_VIDEO, SILENT_VIDEO):
            print(f"Video sin audio (compatible): {SILENT_VIDEO}")

    if os.path.exists(TEMP_VIDEO):
        os.remove(TEMP_VIDEO)


if __name__ == "__main__":
    main()