package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 合并当前目录下的指定后缀文件
 */
public class VideoMerger {

    public static void main(String[] args) {

//        YtDlpMerger.downloadAndMergeWithYtDlp(
//                "https://space.bilibili.com/33308939/upload/video",
//                "downloads"
//        );
        // 使用当前目录
        String currentDir ="C:\\B站视频";
        mergeAllVideosInDirectory(currentDir);
//
        // 或者指定目录
        // mergeAllVideosInDirectory("D:/downloads");
    }

    /**
     * 合并当前目录下的所有音视频文件
     */
    public static void mergeAllVideosInDirectory(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("目录不存在: " + directoryPath);
            return;
        }
        
        // 查找所有视频文件
        File[] videoFiles = dir.listFiles((d, name) -> 
            name.toLowerCase().endsWith(".mp4") || name.toLowerCase().endsWith(".mkv"));
        
        if (videoFiles == null || videoFiles.length == 0) {
            System.out.println("没有找到视频文件");
            return;
        }
        
        int successCount = 0;
        for (File videoFile : videoFiles) {
            String baseName = getBaseName(videoFile.getName());
            
            // 查找对应的音频文件
            File audioFile = findAudioFile(dir, baseName);
            if (audioFile != null) {
                if (mergeVideoAudio(videoFile, audioFile, dir.getPath() + "/merged_" + videoFile.getName())) {
                    successCount++;
                }
            }
        }
        
        System.out.println("合并完成！成功: " + successCount + " 个文件");
    }
    
    /**
     * 查找对应的音频文件
     */
    private static File findAudioFile(File directory, String baseName) {
        File[] audioFiles = directory.listFiles((d, name) -> 
            name.toLowerCase().startsWith(baseName) && 
            (name.toLowerCase().endsWith(".m4a") || 
             name.toLowerCase().endsWith(".mp3") || 
             name.toLowerCase().endsWith(".aac")));
        
        return (audioFiles != null && audioFiles.length > 0) ? audioFiles[0] : null;
    }
    
    /**
     * 使用ffmpeg合并音视频
     */
    public static boolean mergeVideoAudio(File videoFile, File audioFile, String outputPath) {
        try {
            System.out.println("正在合并: " + videoFile.getName() + " + " + audioFile.getName());
            String ffmpegPath="C:\\ffmpeg\\bin\\ffmpeg.exe";
            ProcessBuilder pb = new ProcessBuilder(
                    ffmpegPath,
                "-i", videoFile.getAbsolutePath(),
                "-i", audioFile.getAbsolutePath(),
                "-c", "copy",
                "-y", // 覆盖已存在文件
                outputPath
            );
            
            Process process = pb.start();
            
            // 读取输出信息
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            BufferedReader errorReader = new BufferedReader(
                new InputStreamReader(process.getErrorStream())
            );
            
            // 消耗输出流，避免进程阻塞
            while (reader.readLine() != null) {}
            while (errorReader.readLine() != null) {}
            
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✓ 合并成功: " + new File(outputPath).getName());
                return true;
            } else {
                System.out.println("✗ 合并失败: " + videoFile.getName());
                return false;
            }
            
        } catch (Exception e) {
            System.out.println("合并出错: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 获取文件名（不含扩展名）
     */
    private static String getBaseName(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
    

}