package com.project.base.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "simplerecords")
public class SimpleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "recordName")
    String recordName;

    @Column(name = "shortName")
    String shortName;
}
