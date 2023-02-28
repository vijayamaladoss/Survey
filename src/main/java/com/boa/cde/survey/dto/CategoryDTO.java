package com.boa.cde.survey.dto;
import com.boa.cde.survey.entity.Category;
import com.boa.cde.survey.entity.Question;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoryDTO {

    private Long id;
    private String name;
    private List<QuestionDTO> questions;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }


}
