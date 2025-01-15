package com.api.registrasi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RequestRegistrasiDTO {

    @NotBlank(message = "Nama Murid Tidak Boleh Kosong")
    private String namaMurid;

    @NotBlank(message = "Nama Wali Murid Tidak Boleh Kosong")
    private String namaWali;

    @NotBlank(message = "Tanggal Lahir Murid Tidak Boleh Kosong")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Tanggal lahir harus dalam format yyyy-MM-dd")
    private String tanggalLahir;

    @NotBlank(message = "Alamat Murid Tidak Boleh Kosong")
    private String alamat;

    @NotBlank(message = "Asal Sekolah Tidak Boleh Kosong")
    private String asalSekolah;

    @NotBlank(message = "Hubungan Wali Murid Tidak Boleh Kosong")
    private String hubunganWali;

    @NotBlank(message = "No Telepon Wali Tidak Boleh Kosong")
    @Pattern(regexp = "\\d+", message = "No Telepon hanya boleh berisi angka")
    private String noTeleponWali;

}