package ru.pcs.web.gamegalaxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.pcs.web.gamegalaxy.services.FilesService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FilesService filesService;

    @GetMapping("/files/{file:.+}")
    public void getFile(@PathVariable("file") String fileName, HttpServletResponse response) {
        filesService.addFileToResponse(fileName, response);
    }


    @PostMapping("admin/files/upload")
    public String uploadFile(@RequestParam("poster") MultipartFile multipartFile) {
        filesService.saveFile(multipartFile);
        return "redirect:/admin/files/upload";
    }
}
