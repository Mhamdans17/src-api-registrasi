package com.api.registrasi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ResponseDataMuridDTO {
    private String message;
    private List<MuridWaliMurid> data;

    public ResponseDataMuridDTO(String dataBerhasilDiambil, List<MuridWaliMurid> dataList) {
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MuridWaliMurid {
        private Long muridId;
        private String namaMurid;
        private String umur;
        private String namaWali;
        private String alamat;

        public MuridWaliMurid(Integer muridId, String namaMurid, String umur, String namaWali, String alamat) {
        }
    }
}
