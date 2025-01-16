package com.api.registrasi.service;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.model.RequestRegistrasiDTO;
import com.api.registrasi.model.ResponseResgistrasiDTO;
import com.api.registrasi.repository.RegistrasiRepository;
import com.api.registrasi.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.api.registrasi.constants.Constants.*;

@Slf4j
@Service
public class ServiceRegistrasi {

    @Autowired
    private RegistrasiRepository registrasiRepository;

    @Transactional
    public ResponseResgistrasiDTO resResgistrasi(RequestRegistrasiDTO requestRegistrasiDTO) {
        // Membuat objek RegistrasiDataEntity dan mengisinya dengan data dari DTO
        RegistrasiDataEntity registrasiData = new RegistrasiDataEntity();
        registrasiData.setNamaMurid(requestRegistrasiDTO.getNamaMurid());
        registrasiData.setNamaWali(requestRegistrasiDTO.getNamaWali());
        registrasiData.setTanggalLahir(requestRegistrasiDTO.getTanggalLahir());
        registrasiData.setAlamat(requestRegistrasiDTO.getAlamat());
        registrasiData.setAsalSekolah(requestRegistrasiDTO.getAsalSekolah());
        registrasiData.setHubunganWali(requestRegistrasiDTO.getHubunganWali());
        registrasiData.setNoTelponWali(requestRegistrasiDTO.getNoTeleponWali());

        // Menghitung umur berdasarkan tanggal lahir
        int umur = DateUtil.calculateAge(requestRegistrasiDTO.getTanggalLahir());
        registrasiData.setUmur(String.valueOf(umur));

        // Menetapkan tanggal daftar dengan tanggal saat ini
        registrasiData.setTanggalDaftar(String.valueOf(LocalDate.now()));

        // Menyimpan data registrasi
        registrasiData = registrasiRepository.save(registrasiData);

        // Membuat Response DTO untuk data yang baru disimpan
        ResponseResgistrasiDTO.MuridWaliMurid data = new ResponseResgistrasiDTO.MuridWaliMurid(
                registrasiData.getMuridId(),
                registrasiData.getNamaMurid(),
                registrasiData.getUmur(),
                registrasiData.getNamaWali(),
                registrasiData.getHubunganWali(),
                registrasiData.getAlamat(),
                registrasiData.getTanggalDaftar()
        );

        // Log data yang berhasil ditambahkan
        log.info(RESPONSE_SUCCESS_ADD_DATA, data);

        // Mengembalikan response dengan pesan sukses dan data yang baru disimpan
        return new ResponseResgistrasiDTO(SUCCESS_REGISTRATION_MESSAGE, data);
    }

    public ResponseEntity<List<RegistrasiDataEntity>> getDataMurid(String namaMurid, String umur) {
        // Mencari data registrasi berdasarkan nama murid dan umur
        List<RegistrasiDataEntity> registrasiList = registrasiRepository.findAllByNamaMuridAndUmur(namaMurid, umur);

        // Jika tidak ada data ditemukan, lempar exception
        if (registrasiList.isEmpty()) {
            throw new IllegalArgumentException(NAMA_MURID + namaMurid + " dengan umur " + umur + EMPTY);
        }

        // Log data yang ditemukan
        log.info("Data ditemukan untuk nama murid {}: {}", namaMurid, registrasiList);

        // Mengembalikan data dalam bentuk ResponseEntity
        return ResponseEntity.ok(registrasiList);
    }
}
