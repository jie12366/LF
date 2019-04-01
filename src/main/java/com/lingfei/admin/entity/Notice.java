package com.lingfei.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 0:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "user", type = "msg",shards = 3)
public class Notice {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String account;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String number;

    @Field(type = FieldType.Text)
    private String stuClass;

    @Field(type = FieldType.Text)
    private String qq;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Text)
    private String phone;

    @Field(type = FieldType.Text)
    private String depart;

    @Field(type = FieldType.Double)
    private double balance;
}
