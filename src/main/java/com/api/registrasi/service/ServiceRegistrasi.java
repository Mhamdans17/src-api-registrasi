package com.api.registrasi.service;

import com.api.registrasi.entity.RegistrasiDataEntity;
import com.api.registrasi.model.RequestRegistrasiDTO;
import com.api.registrasi.model.ResponseResgistrasiDTO;
import com.api.registrasi.repository.RegistrasiRepository;
import com.api.registrasi.util.DateUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return new ResponseResgistrasiDTO("Murid dan wali murid berhasil terdaftar", data);
    }
}
