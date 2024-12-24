package io.github.lucklike.luckyclient.api;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageCompressor {

    // 压缩图片到指定大小范围（单位：字节），包括上限和下限
    public static void compressImage(File inputImageFile, File outputImageFile, long minSize, long maxSize) throws IOException {
        BufferedImage inputImage = ImageIO.read(inputImageFile);

        // 获取图片的原始宽度和高度
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        // 计算图片缩放比例，首先根据最大文件大小来压缩
        float scale = calculateScale(inputImageFile, maxSize);

        // 计算新的宽度和高度
        int newWidth = (int) (width * scale);
        int newHeight = (int) (height * scale);

        // 创建新的缩放后的图片
        Image scaledImage = inputImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // 将缩放后的图片转换为BufferedImage
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        // 保存压缩后的图片
        ImageIO.write(outputImage, "jpg", outputImageFile);

        // 如果文件仍然大于最大大小，进一步压缩
        if (outputImageFile.length() > maxSize) {
            adjustCompressionQuality(outputImageFile, maxSize);
        }

        // 如果文件小于最小大小，逐步提高质量，直到达到最小值
        if (outputImageFile.length() < minSize) {
            adjustCompressionQuality(outputImageFile, minSize, true);
        }
    }

    // 计算合适的压缩比例
    private static float calculateScale(File inputImageFile, long targetSize) throws IOException {
        long originalSize = inputImageFile.length();
        if (originalSize <= targetSize) {
            return 1.0f; // 如果原始图片小于目标大小，直接返回1，表示不需要压缩
        }

        // 根据文件大小计算比例
        return (float) targetSize / originalSize;
    }

    // 调整图片的压缩质量 (根据目标大小来调整质量)
    private static void adjustCompressionQuality(File outputImageFile, long targetSize) throws IOException {
        adjustCompressionQuality(outputImageFile, targetSize, false);
    }

    // 调整图片的压缩质量（是否要增加质量调整的策略）
    private static void adjustCompressionQuality(File outputImageFile, long targetSize, boolean increaseQuality) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(outputImageFile);

        // 设置JPEG压缩质量 (0.0 - 1.0)
        float quality = increaseQuality ? 0.9f : 0.8f;  // 如果增加质量，则初始质量设置为90%

        // 逐步调整质量，直到压缩到目标大小
        while (outputImageFile.length() > targetSize && quality > 0.1) {
            File tempFile = new File(outputImageFile.getParent(), "temp.jpg");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                // 创建JPEG压缩器
                javax.imageio.plugins.jpeg.JPEGImageWriteParam jpegWriteParam = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
                jpegWriteParam.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
                jpegWriteParam.setCompressionQuality(quality); // 设置压缩质量

                javax.imageio.ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                writer.setOutput(ImageIO.createImageOutputStream(fos));
                writer.write(null, new javax.imageio.IIOImage(bufferedImage, null, null), jpegWriteParam);
            }

            // 如果压缩后文件小于目标大小，停止调整
            if (tempFile.length() <= targetSize) {
                tempFile.renameTo(outputImageFile);
                break;
            }

            // 减小压缩质量
            quality -= 0.05f;
        }

        // 如果文件仍然小于目标大小（增加质量策略），逐步提高质量
        if (outputImageFile.length() < targetSize && !increaseQuality) {
            while (outputImageFile.length() < targetSize && quality < 1.0) {
                File tempFile = new File(outputImageFile.getParent(), "temp_high_quality.jpg");
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    // 增加压缩质量
                    javax.imageio.plugins.jpeg.JPEGImageWriteParam jpegWriteParam = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
                    jpegWriteParam.setCompressionMode(javax.imageio.ImageWriteParam.MODE_EXPLICIT);
                    jpegWriteParam.setCompressionQuality(quality); // 设置压缩质量

                    javax.imageio.ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                    writer.setOutput(ImageIO.createImageOutputStream(fos));
                    writer.write(null, new javax.imageio.IIOImage(bufferedImage, null, null), jpegWriteParam);
                }

                // 如果压缩后文件大于目标大小，停止调整
                if (tempFile.length() >= targetSize) {
                    tempFile.renameTo(outputImageFile);
                    break;
                }

                // 提高质量
                quality += 0.05f;
            }
        }
    }

    public static void main(String[] args) {
        try {
            File inputImage = new File("C:\\Users\\18143\\Desktop\\财人汇\\浙商\\人像比对测试数据20241220(1)\\人像比对测试数据20241220\\男1_苏壹.jpg");
            File outputImage = new File("C:\\Users\\18143\\Desktop\\财人汇\\浙商\\人像比对测试数据20241220(1)\\人像比对测试数据20241220\\男1_苏壹-1.jpg");
            long minSize = 5*1024; // 最小文件大小，单位为字节（例如: 200 KB）
            long maxSize = 20*1024; // 最大文件大小，单位为字节（例如: 500 KB）

            compressImage(inputImage, outputImage, minSize, maxSize);
            System.out.println("Image compressed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
