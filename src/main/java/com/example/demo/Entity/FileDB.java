package com.example.demo.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_files")
public class FileDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String tipo;

    @Column
    @Lob
    private byte[] data;
}
