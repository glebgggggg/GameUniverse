package com.gameUniverse.GameUniverse.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostRequest {
    @NotBlank
    long id;
    @NotBlank
    String title;
    @NotBlank
    String content;
}
