package com.gameUniverse.GameUniverse.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAndFindRequest {
    @NotBlank
    private Long id;
}
