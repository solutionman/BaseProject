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

    @Column(name = "record_name")
    String recordName;

    @Column(name = "short_name")
    String shortName;
}
