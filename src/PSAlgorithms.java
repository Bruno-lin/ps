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

    /**
     * Method: 均衡化
     *
     * @param source
     * @return
     */
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
                int totalNumOfPixel = totalPixels(luminosityStatus, 0, luminosity + 1);
                int averageOfRGB = 255 * totalNumOfPixel / (pixelOfPhotoHeight * pixelOfPhotoWidth);
                int newImage = GImage.createRGBPixel(averageOfRGB, averageOfRGB, averageOfRGB);
                pixelArray[row][col] = newImage;
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 获取当前像素点的亮度
     *
     * @param source
     * @return
     */
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

    /**
     * Method: 所有像素点
     *
     * @param quantityOfPixel
     * @param beg
     * @param end
     * @return
     */
    private int totalPixels(int[] quantityOfPixel, int beg, int end) {
        int total = 0;

        for (int a = beg; a < end; a++) {
            total += quantityOfPixel[a];
        }
        return total;
    }

    /**
     * Method: 复古滤镜
     *
     * @param source
     * @return
     */
    public GImage vintage(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;

        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                Color color = new Color(pixelArray[row][col]);
                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();

                int[] rgb = new int[3];
                rgb[0] = (int) (0.393 * red + 0.769 * green + 0.189 * blue);
                rgb[1] = (int) (0.349 * red + 0.686 * green + 0.168 * blue);
                rgb[2] = (int) (0.272 * red + 0.534 * green + 0.131 * blue);

                for (int i = 0; i < 3; i++) {
                    if (rgb[i] > 255)
                        rgb[i] = 255;
                }
                int newImage = GImage.createRGBPixel(rgb[0], rgb[1], rgb[2]);
                pixelArray[row][col] = newImage;
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 二值化
     *
     * @param source
     * @return
     */
    public GImage binarization(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;

        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                Color color = new Color(pixelArray[row][col]);

                int red = color.getRed();
                int blue = color.getBlue();
                int green = color.getGreen();
                int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
                if (gray > 170)
                    gray = 255;
                else
                    gray = 0;
                int newImage = GImage.createRGBPixel(gray, gray, gray);
                pixelArray[row][col] = newImage;
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 图片去色
     *
     * @param source
     * @return
     */
    public GImage desaturate(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int pixelOfPhotoHeight = pixelArray.length;
        int pixelOfPhotoWidth = pixelArray[0].length;

        for (int row = 0; row < pixelOfPhotoHeight; row++) {
            for (int col = 0; col < pixelOfPhotoWidth; col++) {
                Color color = new Color(pixelArray[row][col]);
                int r = color.getRed();
                int b = color.getBlue();
                int g = color.getGreen();
                int avg = (Math.max(Math.max(r, b), g) + Math.max(Math.min(r, b), g)) / 2;
                int newImage = GImage.createRGBPixel(avg, avg, avg);
                pixelArray[row][col] = newImage;
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 马赛克
     *
     * @param source
     * @param mosaicX
     * @param mosaicY
     * @param mosaicWidth
     * @param mosaicHeight
     * @return
     */
    public GImage mosaic(GImage source, int mosaicX, int mosaicY, int mosaicWidth, int mosaicHeight) {
        int[][] PixelArray = source.getPixelArray();     // 旧图片数组

        int yMosaicMin = mosaicY, xMosaicMin = mosaicX;
        int yMosaicMax = mosaicY + mosaicHeight, xMosaicMax = mosaicX + mosaicWidth;

        for (int yOld = yMosaicMin; yOld < yMosaicMax; yOld++) {     //遍历选框内的每一个点
            for (int xOld = xMosaicMin; xOld < xMosaicMax; xOld++) {
                if (yOld % 7 == 0 && xOld % 7 == 0) {                //每隔七个点进行一次马赛克，所以半径大小为3
                    for (int y = yOld - 3; y < yOld + 4; y++) {
                        for (int x = xOld - 3; x < xOld + 4; x++) {
                            if (y > yMosaicMin && y < yMosaicMax + 1 && x < xMosaicMax + 1 && x > xMosaicMin) {
                                PixelArray[y][x] = PixelArray[yOld][xOld];
                            }
                        }
                    }
                }
            }
        }
        return new GImage(PixelArray);
    }

    /**
     * Method: 放大镜
     * @param source
     * @param cenX
     * @param cenY
     * @return
     */
    public GImage zoom(GImage source, int cenX, int cenY) {
        int[][] pixelArray = source.getPixelArray();
        int[][] newImg = new int[pixelArray.length][pixelArray[0].length];

        int distance;
        int radius = 50;
        int m = 2;
        for (int row = 0; row < pixelArray.length; row++) {
            for (int col = 0; col < pixelArray[row].length; col++) {
                distance = (int) Math.sqrt(Math.pow(row - cenX, 2) + Math.pow(col - cenY, 2));
                if (distance < radius)
                    newImg[row][col] = pixelArray[(row + cenX) / m][(col + cenY) / m];
                else newImg[row][col] = pixelArray[row][col];
            }
        }
        for (int row = 0; row < newImg.length; row++) {
            for (int col = 0; col < newImg[row].length; col++) {
                pixelArray[row][col] = newImg[row][col];
            }
        }
        return new GImage(pixelArray);
    }

    /**
     * Method: 浮雕
     * @param source
     * @return
     */
    public GImage emboss(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        int finalRed = 0;
        int finalBlue = 0;
        int finalGreen = 0;

        Color[][] colors = new Color[pixelArray.length][pixelArray[0].length];
        int[][] red = new int[pixelArray.length][pixelArray[0].length];
        int[][] green = new int[pixelArray.length][pixelArray[0].length];
        int[][] blue = new int[pixelArray.length][pixelArray[0].length];

        for (int row = 1; row < pixelArray.length - 1; row++) {
            for (int col = 1; col < pixelArray[row].length - 1; col++) {

                colors[row - 1][col - 1] = new Color(pixelArray[row - 1][col - 1]);
                colors[row - 1][col] = new Color(pixelArray[row - 1][col]);
                colors[row - 1][col + 1] = new Color(pixelArray[row - 1][col + 1]);
                colors[row][col - 1] = new Color(pixelArray[row][col - 1]);
                colors[row][col] = new Color(pixelArray[row][col]);
                colors[row][col + 1] = new Color(pixelArray[row][col + 1]);
                colors[row + 1][col - 1] = new Color(pixelArray[row + 1][col - 1]);
                colors[row + 1][col] = new Color(pixelArray[row + 1][col]);
                colors[row + 1][col + 1] = new Color(pixelArray[row + 1][col + 1]);


                for (int m = row - 1; m < row + 2; m++) {
                    for (int n = col - 1; n < col + 2; n++) {
                        red[m][n] = colors[m][n].getRed();
                        green[m][n] = colors[m][n].getGreen();
                        blue[m][n] = colors[m][n].getBlue();
                    }
                }

                //浮雕矩阵
                int[][] matrix = {{-1, -1, 0}, {-1, 0, 1}, {0, 1, 1}};

                for (int k = row - 1; k < row + 2; k++) {
                    for (int l = col - 1; l < col + 2; l++) {
                        finalRed += red[k][l] * matrix[k - (row - 1)][l - (col - 1)];
                        finalBlue += blue[k][l] * matrix[k - (row - 1)][l - (col - 1)];
                        finalGreen += green[k][l] * matrix[k - (row - 1)][l - (col - 1)];
                    }
                }

                if (finalRed < 0) finalRed = 0;
                if (finalRed > 255) finalRed = 255;

                if (finalBlue < 0) finalBlue = 0;
                if (finalBlue > 255) finalBlue = 255;

                if (finalGreen < 0) finalGreen = 0;
                if (finalGreen > 255) finalGreen = 255;
                int newImg = GImage.createRGBPixel(finalRed, finalGreen, finalBlue);
                pixelArray[row][col] = newImg;
            }
        }
        return new GImage(pixelArray);
    }
}