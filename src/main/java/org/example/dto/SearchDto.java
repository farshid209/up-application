package org.example.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchDto {
    private LocalDateTime from;
    private LocalDateTime to;
}
