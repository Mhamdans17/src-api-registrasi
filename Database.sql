CREATE DATABASE api_registrasi;

USE api_registrasi;

CREATE TABLE data_resgistrasi(
                                 murid_id VARCHAR(255) not null ,
                                 nama_murid VARCHAR(255) not null ,
                                 nama_wali varchar(255) not null ,
                                 umur varchar(10),
                                 primary key (murid_id)
);

ALTER TABLE data_resgistrasi
    ADD COLUMN hubungan_wali VARCHAR(15) NOT NULL;