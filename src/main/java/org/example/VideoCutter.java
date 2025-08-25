package org.example;

import java.io.File;

/**
 * description:去除视频文件名中包含指定前缀和后缀的部分，并重命名文件
 *
 * @author yandg
 * @date
 */
public class VideoCutter {
    // 支持的视频文件扩展名
    private static final String[] VIDEO_EXTENSIONS = {
            ".mp4", ".avi", ".mkv", ".mov", ".flv", ".wmv",
            ".mpg", ".mpeg", ".rmvb", ".3gp", ".webm"
    };

    public static void main(String[] args) {
        // 默认从当前目录开始
        File rootDir = new File("C:\\B站视频");
        System.out.println("开始处理目录: " + rootDir.getAbsolutePath());

        processDirectory(rootDir);

        System.out.println("处理完成！");
    }

    private static void processDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                // 递归处理子目录
                processDirectory(file);
            } else if (isVideoFile(file)) {
                // 处理视频文件
                processVideoFile(file);
            }
        }
    }

    private static boolean isVideoFile(File file) {
        String name = file.getName().toLowerCase();
        for (String ext : VIDEO_EXTENSIONS) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private static void processVideoFile(File file) {
        String oldName = file.getName();
//        String prefix = ".f30280";
        String suffix = ".f30080";

        // 检查是否同时包含前缀和后缀
        if ( oldName.contains(suffix)) {
            // 获取前缀之前的内容
//            String beforePrefix = oldName.substring(0, oldName.indexOf(prefix)) +.mp4;

            // 获取后缀之后的内容

            // 构建新文件名
            String newName = oldName.substring(oldName.lastIndexOf(suffix) + suffix.length());

            // 处理可能出现的连续空格
            newName = newName.replaceAll("\\s+", " ").trim();

            // 确保扩展名仍然存在
            String ext = "";
            for (String videoExt : VIDEO_EXTENSIONS) {
                if (oldName.toLowerCase().endsWith(videoExt)) {
                    ext = videoExt;
                    break;
                }
            }

            // 如果没有找到扩展名，保留原扩展名
            if (ext.isEmpty()) {
                int lastDot = oldName.lastIndexOf('.');
                if (lastDot > 0) {
                    ext = oldName.substring(lastDot);
                }
            }

            // 确保新文件名有扩展名
            if (!newName.endsWith(ext)) {
                newName += ext;
            }

            // 检查新文件名是否有效
            if (newName.trim().isEmpty()) {
                System.out.println("生成的文件名无效，跳过: " + oldName);
                return;
            }

            File newFile = new File(file.getParent(), newName);

            // 检查文件是否已存在
            if (newFile.exists()) {
                System.out.println("文件已存在，跳过: " + newName);
            } else {
                // 重命名文件
                if (file.renameTo(newFile)) {
                    System.out.println("重命名成功: " + oldName + " → " + newName);
                } else {
                    System.out.println("重命名失败: " + oldName);
                }
            }
        }
    }
}