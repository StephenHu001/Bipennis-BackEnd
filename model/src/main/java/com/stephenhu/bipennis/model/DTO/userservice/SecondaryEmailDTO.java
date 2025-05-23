package com.stephenhu.bipennis.model.DTO.userservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SecondaryEmailDTO {
    private String reId;
    private String reIsPublic;
    private String reEmail;
}
