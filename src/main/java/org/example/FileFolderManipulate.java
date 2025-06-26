package org.example;

import java.io.File;

/**
 * description: 用来修改视频文件名在上层的avi
 *
 * @author yandg
 * @date
 */
public class FileFolderManipulate {

    public static void main(String[] args) {
//        changeChildVideoName();
          findSameVideoName();
        }

    private static void findSameVideoName() {

    }

    private static void changeChildVideoName() {
        // 获取当前工作目录
        File currentDir = new File("E:\\12月 java学习视频\\面试题\\Java面试突击【第一季】");

        // 列出当前目录下的所有文件和子目录
        File[] files = currentDir.listFiles();

        if (files != null) {
            for (File file : files) {
                // 只处理子目录
                if (file.isDirectory()) {
                    String folderName = file.getName();
                    File videoFile = new File(file, "视频.avi");

                    // 检查目录中是否存在"视频.avi"文件
                    if (videoFile.exists()) {
                        File newFile = new File(file, folderName + ".avi");

                        // 重命名文件
                        if (videoFile.renameTo(newFile)) {
                            System.out.println("重命名成功: " + videoFile.getPath() + " -> " + newFile.getPath());
                        } else {
                            System.out.println("重命名失败: " + videoFile.getPath());
                        }
                    }
                }
            }
        }
    }
    }

