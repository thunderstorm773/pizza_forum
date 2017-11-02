package com.pizzaforum.models.viewModels;

import java.util.Date;
import java.util.Set;

public class TopicDetailsView {

    private Long id;

    private String title;

    private String authorUsername;

    private Date publishDate;

    private String content;

    private Set<ReplyView> replies;

    public TopicDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<ReplyView> getReplies() {
        return replies;
    }

    public void setReplies(Set<ReplyView> replies) {
        this.replies = replies;
    }
}
