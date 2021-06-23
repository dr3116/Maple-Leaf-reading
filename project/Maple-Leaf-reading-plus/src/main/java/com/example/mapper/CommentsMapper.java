package com.example.mapper;

import com.example.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yxmia
 */
@Mapper
public interface CommentsMapper {
    void insertComments(@Param("postId") int postId, @Param("content") String content, @Param("commenterId") int commenterId);
    void deleteComments(@Param("commentsId") int commentsId);
    Comments findCommentsById(@Param("commentsId")int commentsId);
    String getCommentsNumber();
    List<Comments> findCommentsByPostId(@Param("postId")int postId);
}
