package com.seu.pilipili.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetails {
    String username;
    File userProfile;
    Comment comment;
}
