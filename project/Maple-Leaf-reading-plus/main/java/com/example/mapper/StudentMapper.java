package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StudentMapper {
    List<Student> findAllStudents();
    Student insertStudent(@Param("userId") int userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userPhoto") String userPhoto,@Param("num") int num);
    Student updateStudent(@Param("userId") int userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userPhoto") String userPhoto,@Param("num") int num);
    void deleteStudent(@Param("userId") int userId);

}
