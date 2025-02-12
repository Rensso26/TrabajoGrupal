package uce.grupal.user.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FacialRecognitionService {
  public String compareFaces(MultipartFile image1, MultipartFile image2) {
        try {
            BufferedImage img1 = ImageIO.read(image1.getInputStream());
            BufferedImage img2 = ImageIO.read(image2.getInputStream());

            if (Objects.isNull(img1) || Objects.isNull(img2)) {
                return "Error: Una de las imágenes no se pudo cargar.";
            }

            // Compara el tamaño de las imágenes
            if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
                return "Denegado: Las imágenes tienen diferentes tamaños.";
            }

            // Compara píxel por píxel
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                        return "Denegado";
                    }
                }
            }

            return "Confirmado";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al procesar las imágenes.";
        }
    }
}