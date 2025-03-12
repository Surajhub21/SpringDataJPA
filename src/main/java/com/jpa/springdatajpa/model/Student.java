package com.jpa.springdatajpa.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String tech;

    //For image
    private String imageName;
    private String imageType;

    @Column(name = "image_data", columnDefinition = "bytea")
    @Lob
    @JdbcTypeCode(SqlTypes.BINARY)
    //org.hibernate.annotations.Type(type = "org.hibernate.type.BinaryType")
    private byte[] imageData;
}
