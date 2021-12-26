package ru.pcs.web.gamegalaxy.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalName;

    @Column(unique = true)
    private String storageName;

    private String mimeType;

    private Long size;

    private LocalDateTime uploadDateTime;
}
