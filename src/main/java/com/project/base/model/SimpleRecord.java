package com.project.base.model;

import javax.persistence.*;

@Entity
public class SimpleRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "recordName")
    String recordName;

    @Column(name = "shortName")
    String shortName;
}
