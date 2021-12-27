package ru.pcs.web.gamegalaxy.services;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FilesService {

    void addFileToResponse(String fileName, HttpServletResponse response);

    String saveFile(MultipartFile multipartFile);

    void deleteFile(String fileName);
}
