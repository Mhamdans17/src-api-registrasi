package com.api.registrasi.service;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.model.RequestRegistrasiDTO;
import com.api.registrasi.model.ResponseDataMuridDTO;
import com.api.registrasi.model.ResponseResgistrasiDTO;
import com.api.registrasi.repository.RegistrasiRepository;
import com.api.registrasi.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.registrasi.constants.Constants.RESPONSE_SUCCESS_ADD_DATA;
import static com.api.registrasi.constants.Constants.SUCCESS_REGISTRATION_MESSAGE;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ServiceRegistrasi {

    @Autowired
    RegistrasiRepository registrasiRepository;

    @Transactional
    public ResponseResgistrasiDTO resResgistrasi(RequestRegistrasiDTO requestRegistrasiDTO) {
        RegistrasiDataEntity registrasiData = new RegistrasiDataEntity();
        registrasiData.setNamaMurid(requestRegistrasiDTO.getNamaMurid());
        registrasiData.setNamaWali(requestRegistrasiDTO.getNamaWali());
        registrasiData.setTanggalLahir(requestRegistrasiDTO.getTanggalLahir());
        registrasiData.setAlamat(requestRegistrasiDTO.getAlamat());
        registrasiData.setAsalSekolah(requestRegistrasiDTO.getAsalSekolah());
        int umur = DateUtil.calculateAge(requestRegistrasiDTO.getTanggalLahir());
        registrasiData.setUmur(String.valueOf(umur));
        registrasiData.setHubunganWali(requestRegistrasiDTO.getHubunganWali());
        registrasiData.setNoTelponWali(requestRegistrasiDTO.getNoTeleponWali());

        registrasiData = registrasiRepository.save(registrasiData);

        ResponseResgistrasiDTO.MuridWaliMurid data = new ResponseResgistrasiDTO.MuridWaliMurid(
                registrasiData.getMuridId(),
                registrasiData.getNamaMurid(),
                registrasiData.getUmur(),
                registrasiData.getNamaWali(),
                registrasiData.getHubunganWali(),
                registrasiData.getAlamat()
        );
        log.info(RESPONSE_SUCCESS_ADD_DATA, data);

        return new ResponseResgistrasiDTO(SUCCESS_REGISTRATION_MESSAGE, data);
    }

    public ResponseEntity<List<RegistrasiDataEntity>> getDataMurid(String namaMurid) {
        List<RegistrasiDataEntity> registrasiList = registrasiRepository.findAllByNamaMurid(namaMurid);

        if (registrasiList.isEmpty()) {
            throw new IllegalArgumentException("Data murid dengan nama " + namaMurid + " tidak ditemukan");
        }

        log.info("Data ditemukan untuk nama murid {}: {}", namaMurid, registrasiList);

        return ResponseEntity.ok(registrasiList);
    }
}
