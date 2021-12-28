package ru.pcs.web.gamegalaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.pcs.web.gamegalaxy.entities.FileInfo;
import ru.pcs.web.gamegalaxy.repositories.FilesInfoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class FilesServiceImpl implements FilesService {

//    @Value("${files.storage.path}")
    private String storageFolder = "Storage/images/";

    private final FilesInfoRepository filesInfoRepository;

    @Autowired
    public FilesServiceImpl(FilesInfoRepository filesInfoRepository) {
        this.filesInfoRepository = filesInfoRepository;
    }

    /** Save the file and return file name as String
     *
     * @param multipartFile file to be saved
     * @return new file name generated with .randomUUID
     */
    @Override
    public String saveFile(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.indexOf("."));
        String fileName = UUID.randomUUID() + extension;

        FileInfo fileInfo = FileInfo.builder()
                .originalName(multipartFile.getOriginalFilename())
                .mimeType(multipartFile.getContentType())
                .uploadDateTime(LocalDateTime.now())
                .storageName(fileName)
                .size(multipartFile.getSize())
                .build();

        filesInfoRepository.save(fileInfo);

        try {
            Files.copy(multipartFile.getInputStream(), Paths.get(storageFolder, fileName));
        } catch (IOException ex) {
            throw new IllegalArgumentException();
        }
        return fileName;
    }

    @Override
    public void deleteFile(String storageFileName) {
        try {
            Files.delete(Paths.get(storageFolder, storageFileName));
            filesInfoRepository.delete(filesInfoRepository.findByStorageName(storageFileName));
        } catch (IOException ignore) {};
    }

    @Override
    public void addFileToResponse(String storageFileName, HttpServletResponse response) {
        FileInfo fileInfo = filesInfoRepository.findByStorageName(storageFileName);
        response.setContentType(fileInfo.getMimeType());
        response.setContentLength(fileInfo.getSize().intValue());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalName() + "\"");
        try {
            Files.copy(Paths.get(storageFolder, fileInfo.getStorageName()), response.getOutputStream());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }




}
