package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *课程信息：(CourseInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourseInformation")
public class CourseInformation implements Serializable {

    //CourseInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_information_id")
    private Integer course_information_id;
   // 课程名称
   @Basic
    private String course_name;
   // 专业
   @Basic
    private String major;
   // 教师
   @Basic
    private Integer teacher;
   // 备注
   @Basic
    private String remarks;
   // 课程封面
   @Basic
    private String course_cover;
   // 课程视频
   @Basic
    private String course_video;
   // 课程附件
   @Basic
    private String course_attachment;
   // 课程介绍
   @Basic
    private String course_introduction;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
