package com.texo.filmproducer.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MovieRequest {
    @NotNull(message = "year is mandatory.")
    @Max(value = 2022, message = "year must not be in the future.")
    @Min(value = 1888, message = "First movie was released in 1888.")
    @ApiModelProperty("The movie release date.")
    private Integer year;

    @NotNull(message = "title is mandatory.")
    @NotBlank(message = "title cannot be blank.")
    @ApiModelProperty("The movie title.")
    private String title;

    @NotNull(message = "studios is mandatory.")
    @NotBlank(message = "studios cannot be blank.")
    @ApiModelProperty("The name of the studios who produced the movie.")
    private String studios;

    @NotNull(message = "producers is mandatory.")
    @NotBlank(message = "producers cannot be blank.")
    @ApiModelProperty("The name of the movie producers.")
    private String producers;

    @ApiModelProperty("Boolean indicating if the movie won the academy prize that year.")
    private boolean winner;
}
