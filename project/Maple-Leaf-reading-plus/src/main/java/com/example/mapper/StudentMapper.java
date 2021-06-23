package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface StudentMapper {
    List<Student> findAllStudents();
    List<Student> findAllStudentsBySignNumDesc();
    void insertStudent(@Param("userId") int userId, @Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userPhoto") String userPhoto,@Param("num") int num,@Param("userSex")String userSex,@Param("userStyleText")String userStyleText);
    int updateStudent(@Param("userId") int userId, @Param("userName") String userName, @Param("userPhoto") String userPhoto,@Param("num") int num,@Param("userSex")String userSex,@Param("userStyleText")String userStyleText);
    void deleteStudent(@Param("userId") int userId);
    List<Student> findStudentByName(@Param("userName") String userName);
    String findStudentNumber();
    String findStudentPasswordByAccount(@Param("userName") String userName);
    String findStudentIdByAccount(@Param("userName") String userName);
    Student findStudentInfoByAccount(@Param("userName") String userName);
    Student findStudentInfoById(@Param("userId")int userId);

}
