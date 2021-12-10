package ru.pcs.web.gamegalaxy.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public enum Status {
    NEW("N"),
    PROCESSING("P"),
    COMPLETED("C"),
    DEFERRED("D");

    @Getter
    private final String code;
}

