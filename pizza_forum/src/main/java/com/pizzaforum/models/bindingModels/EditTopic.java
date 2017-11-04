package com.pizzaforum.models.bindingModels;

import com.pizzaforum.staticData.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class EditTopic {

    private Long id;

    private String title;

    private String content;

    private Long category;

    public EditTopic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = Constants.TOPIC_TITLE_EMPTY_MESSAGE)
    @Length(min = Constants.TOPIC_TITLE_MIN_LENGTH,
            max = Constants.TOPIC_TITLE_MAX_LENGTH,
            message = Constants.TOPIC_TITLE_REQUIRED_LENGTH_MESSAGE)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = Constants.TOPIC_CONTENT_EMPTY_MESSAGE)
    @Length(min = Constants.TOPIC_CONTENT_MIN_LENGTH,
            max = Constants.TOPIC_CONTENT_MAX_LENGTH,
            message = Constants.TOPIC_CONTENT_REQUIRED_LENGTH_MESSAGE)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotNull
    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
