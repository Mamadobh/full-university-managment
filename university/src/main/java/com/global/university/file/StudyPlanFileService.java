package com.global.university.file;


import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class StudyPlanFileService {

    @Value("${application.file.upload.studyPlans}")
    private String studyPlanUploadPath;

    public String saveFile(@Nonnull MultipartFile sourceFile,
                           @Nonnull Integer levelId) {
        final String fileUploadPath = "levels" + File.separator + levelId;
        return uploadFile(sourceFile, fileUploadPath);
    }

    private String uploadFile(
            MultipartFile sourceFile,
            String fileUploadPath) {
        final String finalUploadPath = studyPlanUploadPath + File.separator + fileUploadPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            if (!folderCreated) {
                log.warn("failed to create the target folder");
                return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, sourceFile.getBytes());
            log.info("file saved to " + targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("file was not saved "+e.getMessage());
        }
        return null;
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return originalFilename.substring(lastDotIndex + 1).toLowerCase();
    }
    public void deleteFile(String filePath) {
        try {
            Path fileToDeletePath = Paths.get(filePath);

            if (Files.exists(fileToDeletePath)) {
                Files.delete(fileToDeletePath);
            } else {
                log.warn("File not found: " + filePath);
            }
        } catch (IOException e) {
            log.warn("Error while deleting file: " + filePath);
        }
    }

}
