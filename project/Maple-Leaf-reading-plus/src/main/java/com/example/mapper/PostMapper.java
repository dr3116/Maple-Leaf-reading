package com.example.mapper;

import com.example.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface PostMapper {
    List<Post> findAllPosts();
    void insertPost(@Param("content") String content, @Param("photo") String photo, @Param("bookName") String bookName, @Param("numberOfLikes") int numberOfLikes, @Param("comments") int comments, @Param("userId") int userId, @Param("postTime") Date postTime);
    void updatePost(@Param("postId") int postId, @Param("content") String content, @Param("photo") String photo, @Param("bookName") String bookName, @Param("numberOfLikes") int numberOfLikes, @Param("comments") int comments, @Param("userId") int userId, @Param("postTime") Date postTime);
    void deletePost(@Param("postId") int postId);
    Post findPostById(@Param("postId") int postId);
    String findPostNumber();

}
