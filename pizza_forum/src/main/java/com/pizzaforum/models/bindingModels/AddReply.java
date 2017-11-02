package com.pizzaforum.models.bindingModels;

import com.pizzaforum.staticData.Constants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AddReply {

    private String content;

    private String imageURL;

    public AddReply() {
    }

    @NotNull(message = Constants.REPLY_CONTENT_EMPTY_MESSAGE)
    @Length(min = Constants.REPLY_CONTENT_REQUIRED_LENGTH,
            message = Constants.REPLY_CONTENT_REQUIRED_LENGTH_MESSAGE)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
