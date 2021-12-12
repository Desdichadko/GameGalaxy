package ru.pcs.web.gamegalaxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FilesController {

    @GetMapping("admin/files/upload")
    public String getFilesUploadPage() {
        return null;
    }

    @PostMapping("")
    public String uploadFile() {
        return null;
    }
}
