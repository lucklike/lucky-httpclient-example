package io.github.lucklike.entity.request.gitee;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Stargazers {

    private long id;
    private String login;
    private String name;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String remark;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventsUrl;
    private String receivedEventsUrl;
    private String type;
    private Date starAt;

}
