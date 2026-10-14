package com.mycompany.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFileChooser selector = new JFileChooser();
                selector.setDialogTitle("Selecciona una imagen para Transformaciones");
                selector.setFileFilter(new FileNameExtensionFilter("Imagenes", "png", "jpg", "jpeg"));
                if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;
                
                BufferedImage imgOriginal = ImageIO.read(selector.getSelectedFile());
                int ancho = imgOriginal.getWidth();
                int alto = imgOriginal.getHeight();

                int moverX = 100, moverY = 50;
                BufferedImage imgTraslacion = new BufferedImage(ancho + moverX, alto + moverY, BufferedImage.TYPE_INT_RGB);
                Graphics2D gT = imgTraslacion.createGraphics();
                gT.setColor(Color.WHITE);
                gT.fillRect(0, 0, imgTraslacion.getWidth(), imgTraslacion.getHeight());
                gT.drawImage(imgOriginal, AffineTransform.getTranslateInstance(moverX, moverY), null);
                gT.dispose();

                BufferedImage imgRotacion = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                Graphics2D gR = imgRotacion.createGraphics();
                gR.setColor(Color.WHITE);
                gR.fillRect(0, 0, ancho, alto);
                gR.drawImage(imgOriginal, AffineTransform.getRotateInstance(Math.toRadians(45.0), ancho / 2.0, alto / 2.0), null);
                gR.dispose();

                double escala = 1.5;
                BufferedImage imgEscala = new BufferedImage((int)(ancho * escala), (int)(alto * escala), BufferedImage.TYPE_INT_RGB);
                Graphics2D gE = imgEscala.createGraphics();
                gE.drawImage(imgOriginal, AffineTransform.getScaleInstance(escala, escala), null);
                gE.dispose();

                new JFrameImagen(imgOriginal).setVisible(true);
                new JFrameImagen(imgTraslacion).setVisible(true);
                new JFrameImagen(imgRotacion).setVisible(true);
                new JFrameImagen(imgEscala).setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        });
    }
}
