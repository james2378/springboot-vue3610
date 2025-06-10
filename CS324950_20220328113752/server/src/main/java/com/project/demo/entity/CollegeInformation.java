package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学院信息：(CollegeInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CollegeInformation")
public class CollegeInformation implements Serializable {

    //CollegeInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_information_id")
    private Integer college_information_id;
   // 学院名称
   @Basic
    private String college_name;
   // 学院地址
   @Basic
    private String college_address;
   // 联系电话
   @Basic
    private String contact_number;
   // 学院图片
   @Basic
    private String college_pictures;
   // 学院介绍
   @Basic
    private String college_introduction;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
