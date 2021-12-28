package com.gameUniverse.GameUniverse.response;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class PostInfoResponse {
    long id;
    String title;
    String content;
    String users_login;
    public PostInfoResponse(String title, String content, String users_login, long id){
        this.title = title;
        this.content = content;
        this.users_login = users_login;
        this.id = id;
    }
}
