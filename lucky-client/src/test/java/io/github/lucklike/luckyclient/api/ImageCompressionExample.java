package io.github.lucklike.luckyclient.api;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageCompressionExample {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\18143\\Desktop\\财人汇\\浙商\\人像比对测试数据20241220(1)\\人像比对测试数据20241220\\女3_金阳.jpg"); // 输入图片文件
            File outputFile = new File("C:\\Users\\18143\\Desktop\\财人汇\\浙商\\人像比对测试数据20241220(1)\\人像比对测试数据20241220\\女3_金阳-4.jpg"); // 输出压缩后的图片文件
            int targetSize = 1024 * 5; // 目标压缩后的大小（单位：字节）
// 读取输入图片
            BufferedImage image = ImageIO.read(inputFile);
// 创建一个临时BufferedImage对象，用于压缩图片
            BufferedImage tempImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = tempImage.createGraphics();
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();
// 循环压缩图片，直到达到目标大小
            float quality = 1.0f; // 初始压缩质量
            ImageWriter writer = null;
            ImageOutputStream ios = null;
            do {
                writer = ImageIO.getImageWritersByFormatName("jpg").next();
                ios = ImageIO.createImageOutputStream(outputFile);
                writer.setOutput(ios);
                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);
                writer.write(null, new IIOImage(tempImage, null, null), param);
                quality -= 0.1f; // 每次压缩质量降低0.1
                ios.flush();
                writer.dispose();
                ios.close();
            } while (outputFile.length() > targetSize);
            System.out.println("压缩完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}