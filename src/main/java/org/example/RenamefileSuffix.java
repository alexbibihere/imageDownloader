package org.example;

import java.io.File;

public class RenamefileSuffix {

    private static final String[] TARGET_EXTENSIONS = {".mp4", ".m4a"};
    private static final int CHARS_TO_REMOVE = 7;

    // 处理模式：BEFORE_EXTENSION（扩展名前）或 AFTER_EXTENSION（扩展名后）
    private static final boolean REMOVE_BEFORE_EXTENSION = true;

    public static void main(String[] args) {
        File rootDir = new File("C:\\B站视频");
        System.out.println("开始处理目录: " + rootDir.getAbsolutePath());

        int totalProcessed = processDirectoryWithDetails(rootDir);

        System.out.println("处理完成！共处理了 " + totalProcessed + " 个文件");
    }

    private static int processDirectoryWithDetails(File dir) {
        int count = 0;
        File[] files = dir.listFiles();
        if (files == null) return 0;

        for (File file : files) {
            if (file.isDirectory()) {
                count += processDirectoryWithDetails(file);
            } else if (isTargetFile(file)) {
                if (processFileAdvanced(file)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean processFileAdvanced(File file) {
        String oldName = file.getName();
        String newName;

        if (REMOVE_BEFORE_EXTENSION) {
            newName = removeCharsBeforeExtension(oldName);
        } else {
            newName = removeCharsAfterExtension(oldName);
        }

        if (!newName.equals(oldName)) {
            File newFile = new File(file.getParent(), newName);

            if (newFile.exists()) {
                System.out.println("⚠️  文件已存在: " + newName);
                return false;
            }

            boolean success = file.renameTo(newFile);
            if (success) {
                System.out.println("✅ " + oldName + " → " + newName);
                return true;
            } else {
                System.out.println("❌ 重命名失败: " + oldName);
                return false;
            }
        }
        return false;
    }

    /**
     * 删除扩展名前的字符
     */
    private static String removeCharsBeforeExtension(String fileName) {
        String extension = getExtension(fileName);
        String baseName = getBaseName(fileName);

        if (baseName.length() <= CHARS_TO_REMOVE) {
            System.out.println("⏭️  跳过（名称太短）: " + fileName);
            return fileName;
        }

        return baseName.substring(0, baseName.length() - CHARS_TO_REMOVE) + extension;
    }

    /**
     * 删除扩展名后的字符（如果文件名有额外后缀）
     */
    private static String removeCharsAfterExtension(String fileName) {
        String extension = getExtension(fileName);
        String fullBaseName = getBaseName(fileName);

        // 如果文件名在扩展名后还有内容
        if (fileName.length() > fullBaseName.length() + extension.length()) {
            String extraSuffix = fileName.substring(fullBaseName.length() + extension.length());
            if (extraSuffix.length() >= CHARS_TO_REMOVE) {
                return fullBaseName + extension + extraSuffix.substring(CHARS_TO_REMOVE);
            }
        }

        return fileName;
    }

    private static boolean isTargetFile(File file) {
        String name = file.getName().toLowerCase();
        for (String ext : TARGET_EXTENSIONS) {
            if (name.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    private static String getBaseName(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot > 0) ? fileName.substring(0, lastDot) : fileName;
    }

    private static String getExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return (lastDot > 0) ? fileName.substring(lastDot) : "";
    }
}