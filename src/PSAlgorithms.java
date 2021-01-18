import acm.graphics.*;

import java.awt.*;

public class PSAlgorithms implements PSAlgorithmsInterface {

    /**
     * Method: 逆时针旋转图片
     *
     * @param source 要被逆时针旋转图片
     * @return
     */
    public GImage rotateCounterclockwise(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldWidth;                               // 新图片高度等于旧图片宽度
        int newWidth = oldHeight;                               // 新图片宽度等于旧图片高度
        int[][] newPixelArray = new int[newHeight][newWidth];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int yNew = 0; yNew < newHeight; yNew++) {
            for (int xNew = 0; xNew < newWidth; xNew++) {
                int yOld = xNew;
                int xOld = oldWidth - yNew - 1;
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
            }
        }
        return new GImage(newPixelArray);
    }

    /**
     * Method: 顺时针旋转图片
     *
     * @param source 要被顺时针旋转图片
     * @return
     */
    public GImage rotateClockwise(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldWidth;                               // 新图片高度等于旧图片宽度
        int newWidth = oldHeight;                               // 新图片宽度等于旧图片高度
        int[][] newPixelArray = new int[newHeight][newWidth];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int yNew = 0; yNew < newHeight; yNew++) {                          //新图片的高度大于新图片的y轴(yNew),也就是大于0，yNew加1；
            for (int xNew = 0; xNew < newWidth; xNew++) {                       //新图片的宽度大于新图片的x轴(xNew),也就是大于0，xNew加1；
                int yOld = oldHeight - xNew - 1;
                int xOld = yNew;
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
            }
        }
        return new GImage(newPixelArray);
    }

    /**
     * Method: 水平翻转图片
     *
     * @param source 要被水平翻转图片
     * @return
     */
    public GImage flipHorizontal(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldHeight;                               // 新图片高度等于旧图片宽度
        int newWidth = oldWidth;                               // 新图片宽度等于旧图片高度
        int[][] newPixelArray = new int[newHeight][newWidth];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int yNew = 0; yNew < newHeight; yNew++) {
            for (int xNew = 0; xNew < newWidth; xNew++) {
                int yOld = yNew;
                int xOld = oldWidth - xNew - 1;
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
            }
        }
        return new GImage(newPixelArray);
    }

    /**
     * Method: 图片反相
     *
     * @param source 要被反相的图片
     * @return
     */
    public GImage negative(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;
        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                int pixel = pixelArray[row][col];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int newPixel = GImage.createRGBPixel(255 - r, 255 - g, 255 - b);
                pixelArray[row][col] = newPixel;
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 绿屏扣图
     *
     * @param source
     * @return
     */
    public GImage greenScreen(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;
        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                int pixel = pixelArray[row][col];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int rbPixelMax = Math.max(r, b);
                if (g >= rbPixelMax * 2) {
                    int transparentPixel = GImage.createRGBPixel(0, 0, 0, 0);
                    pixelArray[row][col] = transparentPixel;
                }
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 图片卷积
     *
     * @param source
     * @return
     */
    public GImage convolution(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;
        int[][] newPixelArray = new int[pixelOfPhotoHeight][pixelOfPhotoWidth];
        for (int y = 0; y < pixelOfPhotoHeight; y++) {
            for (int x = 0; x < pixelOfPhotoWidth; x++) {
                newPixelArray[y][x] = getPixel(pixelArray, x, y);
            }
        }
        return new GImage(newPixelArray);
    }

    /**
     * Method: 获得图片卷积参数
     *
     * @param pixelArray
     * @param x
     * @param y
     * @return
     */
    private int getPixel(int[][] pixelArray, int x, int y) {
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;

        int rSum = 0;
        int bSum = 0;
        int gSum = 0;
        int pixelCount = 0;        // 计算卷积时，使用的像素点数

        int xMin = Math.max(x - CONVOLUTION_RADIUS, 0);
        int xMax = Math.min(x + CONVOLUTION_RADIUS, pixelOfPhotoWidth - 1);
        int yMin = Math.max(y - CONVOLUTION_RADIUS, 0);
        int yMax = Math.min(y + CONVOLUTION_RADIUS, pixelOfPhotoHeight - 1);

        for (int row = yMin; row <= yMax; row++) {
            for (int col = xMin; col <= xMax; col++) {
                int pixel = pixelArray[row][col];
                rSum += GImage.getRed(pixel);
                bSum += GImage.getBlue(pixel);
                gSum += GImage.getGreen(pixel);
                pixelCount++;
                System.out.println(row + " " + col + " " + pixelCount);
            }
        }
        return GImage.createRGBPixel(rSum / pixelCount, gSum / pixelCount, bSum / pixelCount);
    }

    /**
     * 裁剪图片，裁剪后仅保留选区内容，其他全部删掉
     *
     * @param source     要被裁剪的原始图片
     * @param cropX      选区左上角的x坐标
     * @param cropY      选区左上角的y坐标
     * @param cropWidth  选区的宽度
     * @param cropHeight 选区的高度
     * @return 裁剪后的图片
     */
    public GImage crop(GImage source, int cropX, int cropY, int cropWidth, int cropHeight) {
        int[][] oldPixelArray = source.getPixelArray();
        int[][] newPixelArray = new int[cropHeight][cropWidth];
        for (int xNew = 0; xNew < cropWidth; xNew++) {
            for (int yNew = 0; yNew < cropHeight; yNew++) {
                int xOld = cropX + xNew;
                int yOld = cropY + yNew;
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
            }
        }
        return new GImage(newPixelArray);
    }

    public GImage equalization(GImage source) {
        int[] luminosityStatus = getLuminosityStatus(source);
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;

        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                int pixel = pixelArray[row][col];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int luminosity = computeLuminosity(r, g, b);
                int totalNumOfPixel = totalPixels(luminosityStatus, 0, luminosity);
                int averageOfRGB = 255 * totalNumOfPixel / (pixelOfPhotoHeight * pixelOfPhotoWidth);
                int newImage = GImage.createRGBPixel(averageOfRGB, averageOfRGB, averageOfRGB);
                pixelArray[row][col] = newImage;
            }
        }
        return new GImage(pixelArray);
    }

    private int[] getLuminosityStatus(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;
        int[] luminosityStatus = new int[256];

        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                int pixel = pixelArray[row][col];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int luminosity = computeLuminosity(r, g, b);
                luminosityStatus[luminosity] += 1;
            }
        }
        return luminosityStatus;
    }

    private int totalPixels(int[] quantityOfPixel, int beg, int end) {
        int total = 0;

        for (int a = beg; a < end; a++) {
            total += quantityOfPixel[a];
        }
        return total;
    }
}
