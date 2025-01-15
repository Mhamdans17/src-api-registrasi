package com.api.registrasi.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseResgistrasiDTO {

    private String message;
    private MuridWaliMurid data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MuridWaliMurid {
        private int muridId;
        private String namaMurid;
        private String umur;
        private String namaWali;
        private String hubunganWali;
        private String alamat;

        public MuridWaliMurid(String namaMurid, String umur, String namaWali, String hubunganWali, String alamat) {
        }
    }
}
