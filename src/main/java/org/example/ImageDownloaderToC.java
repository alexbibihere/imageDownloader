package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImageDownloaderToC {

    // 图片API的URL（需要替换为实际API地址）
    private static final String IMAGE_API_URL = "https://v2.api-m.com/api/heisi?return=302";
    // C盘保存目录
    private static final String SAVE_DIRECTORY = "C:\\downloaded_images";
    // 连接超时时间（毫秒）
    private static final int CONNECT_TIMEOUT = 10000;
    // 读取超时时间（毫秒）
    private static final int READ_TIMEOUT = 30000;

    public static void main(String[] args) {
        try {
            // 1. 创建保存目录
            createSaveDirectory();

            // 2. 从API获取图片URL列表
            List<String> imageUrls = fetchImageUrlsFromApi();

            if (imageUrls.isEmpty()) {
                System.out.println("API未返回任何图片URL");
                return;
            }

            System.out.println("找到 " + imageUrls.size() + " 张图片需要下载");

            // 3. 下载并保存每张图片
            downloadImages(imageUrls);

            System.out.println("\n所有图片下载完成！保存位置: " + SAVE_DIRECTORY);
        } catch (Exception e) {
            System.err.println("图片下载失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 在C盘创建保存图片的目录
     */
    private static void createSaveDirectory() throws IOException {
        Path path = Paths.get(SAVE_DIRECTORY);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("创建目录: " + SAVE_DIRECTORY);
        }

        // 检查目录是否可写
        if (!Files.isWritable(path)) {
            throw new IOException("目录不可写: " + SAVE_DIRECTORY +
                    " (可能需要管理员权限)");
        }
    }

    /**
     * 模拟从API获取图片URL列表
     * 实际应用中需要根据API返回的数据格式解析
     */
    private static List<String> fetchImageUrlsFromApi() {
        List<String> imageUrls = new ArrayList<>();

        // 这里应该是实际的API调用和解析逻辑
        // 示例中返回一个模拟的URL列表
        try {
            // 模拟API调用
            System.out.println("正在从API获取图片列表...");

            // 这里应该替换为实际的API调用代码
            // 访问1000次然后添加到imageUrls
            for(int i=0;i<9999;i++){
                imageUrls.add(IMAGE_API_URL);
            }


        } catch (Exception e) {
            System.err.println("获取图片URL列表失败: " + e.getMessage());
        }

        return imageUrls;
    }

    /**
     * 下载并保存所有图片
     */
    private static void downloadImages(List<String> imageUrls) {
        int total = imageUrls.size();
        int success = 0;
        int fail = 0;

        for (int i = 0; i < total; i++) {
            String imageUrl = imageUrls.get(i);
            try {
                // 生成文件名: image_序号.扩展名
                String fileName = "image_" + (i + 1) + getFileExtension(imageUrl);
                String savePath = SAVE_DIRECTORY + "\\" + fileName;

                System.out.printf("\n[%d/%d] 正在下载: %s", i+1, total, imageUrl);
                System.out.printf("\n保存到: %s", savePath);

                downloadSingleImage(imageUrl, savePath);
                success++;

                System.out.println(" ✔ 下载成功");
            } catch (Exception e) {
                fail++;
                System.err.println(" ✖ 下载失败: " + e.getMessage());
            }
        }

        System.out.printf("\n下载结果: 成功 %d, 失败 %d\n", success, fail);
    }

    /**
     * 下载单个图片并保存
     */
    private static void downloadSingleImage(String imageUrl, String savePath) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(CONNECT_TIMEOUT);
        connection.setReadTimeout(READ_TIMEOUT);

        // 检查响应码
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP请求失败，响应码: " + responseCode);
        }

        // 获取文件大小（用于显示进度）
        int fileSize = connection.getContentLength();

        try (InputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream out = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[8192];
            int bytesRead;
            long totalRead = 0;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalRead += bytesRead;

                // 显示下载进度
                if (fileSize > 0) {
                    int progress = (int) (totalRead * 100 / fileSize);
                    System.out.printf("\r进度: %d%%", progress);
                }
            }
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 从URL中提取文件扩展名
     */
    private static String getFileExtension(String url) {
        try {
            // 移除URL中的查询参数
            String cleanUrl = url.split("\\?")[0];

            // 获取最后一个点后面的部分
            int lastDotIndex = cleanUrl.lastIndexOf('.');
            if (lastDotIndex != -1) {
                String extension = cleanUrl.substring(lastDotIndex);

                // 只保留常见的图片扩展名
                if (extension.matches("(?i)\\.(jpg|jpeg|png|gif|bmp|webp)")) {
                    return extension.toLowerCase();
                }
            }
        } catch (Exception e) {
            System.err.println("无法从URL获取扩展名: " + url);
        }

        // 默认返回.jpg
        return ".jpg";
    }
}