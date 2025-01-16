package com.api.registrasi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_resgistrasi")
public class RegistrasiDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "murid_id")
    private Integer muridId;

    @Column(name = "nama_murid")
    private String namaMurid;

    @Column(name = "nama_wali")
    private String namaWali;

    @Column(name = "tanggallahir")
    private String tanggalLahir;

    @Column(name = "umur")
    private String umur;

    @Column(name = "no_telpon_wali")
    private String noTelponWali;

    @Column(name = "hubungan_wali")
    private String hubunganWali;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "asal_sekolah")
    private String asalSekolah;

    @Column(name = "tanggal_daftar")
    private String tanggalDaftar;
}
