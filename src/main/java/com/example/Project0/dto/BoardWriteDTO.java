package com.example.Project0.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardWriteDTO {

    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContent;
}
