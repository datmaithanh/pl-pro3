/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.dto.ThanhVienDTO;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThanhVienRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThanhVienUserService;
import java.util.Optional;
import java.util.List;
import java.security.SecureRandom;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lenovo
 */
@Service
public class ThanhVienUserServiceImpl implements ThanhVienUserService {
    private ThanhVienRepository thanhVienRepository;
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    public ThanhVienUserServiceImpl(ThanhVienRepository thanhVienRepository) {
        this.thanhVienRepository = thanhVienRepository;
    }

    @Override
    public Optional<ThanhVien> findById(Integer id) {
        return thanhVienRepository.findById(id);
    }

    @Override
    public List<ThanhVien> findAll() {
        return thanhVienRepository.findAll();
    }
    
    @Override
    public boolean kiemTraDangNhap(int maTV, String password) {
        ThanhVien thanhVien = thanhVienRepository.findByMaTVAndPassword(maTV, password);
        if (thanhVien != null)
            return true;
        return false;
    }
    
    @Override
    public List<ThanhVienDTO> getAllThanhVien() {
        List<ThanhVien> thanhViens = thanhVienRepository.findAll();
        return thanhViens.stream().map((thanhVien ->mapToThanhVienDTO(thanhVien))).collect(Collectors.toList());
    }
    private  ThanhVienDTO mapToThanhVienDTO(ThanhVien thanhVien){
        ThanhVienDTO thanhVienDTO = ThanhVienDTO.builder()
                .maTV(thanhVien.getMaTV())
                .hoTen(thanhVien.getHoTen())
                .khoa(thanhVien.getKhoa())
                .nganh(thanhVien.getNganh())
                .sdt(thanhVien.getSdt())
                .email(thanhVien.getEmail())
                .password(thanhVien.getPassword())
                .build();

        return thanhVienDTO;
    }

    
    
    @Override
    public ThanhVien AddThanhVien(ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = mapToThanhVien(thanhVienDTO);
        return thanhVienRepository.save(thanhVien);
    }
    
    private ThanhVien mapToThanhVien (ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = ThanhVien.builder()
                .maTV(thanhVienDTO.getMaTV())
                .hoTen(thanhVienDTO.getHoTen())
                .khoa(thanhVienDTO.getKhoa())
                .nganh(thanhVienDTO.getNganh())
                .sdt(thanhVienDTO.getSdt())
                .email(thanhVienDTO.getEmail())
                .password(thanhVienDTO.getPassword())
                .build();
        return thanhVien;
    }

   
    @Override
    public ThanhVienDTO getThanhVienDTOById(int thanhVienID) {
        ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).orElse(null);
        if (thanhVien != null) {
            return mapToThanhVienDTO(thanhVien);
        } else {
            return null;
        }
    }

    @Override
    public void updateThanhVien(ThanhVienDTO thanhVienDTO) {
        ThanhVien thanhVien = mapToThanhVien(thanhVienDTO);
        thanhVienRepository.save(thanhVien);
    }

    @Override
    public void deleteThanhVienByID(int thanhVienID) {
        thanhVienRepository.deleteById(thanhVienID);
    }

    @Override
    public ThanhVien getThanhVienById(int thanhVienID) {
        ThanhVien thanhVien = thanhVienRepository.findById(thanhVienID).orElse(null);
        if (thanhVien != null) {
            return thanhVien;
        } else {
            return null;
        }   
    }

    @Override
    public ThanhVien addOrUpdateThanhVien(ThanhVien thanhVien) {
        return thanhVienRepository.save(thanhVien);
    }

    @Override
    public void sendOtpToEmail(String email , String otpString) {
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otpString;

        sendEmail(email, subject, body);
    }
    
    
    private static final String charString = "0123456789";
    private static final int OTP_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();
    @Override
    public String generateOtp() {
        
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(charString.charAt(RANDOM.nextInt(charString.length())));
        }
        return otp.toString();
    }


    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    @Override
    public boolean emailExists(String email) {
        return thanhVienRepository.getThanhVienByEmail(email) != null;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        ThanhVienDTO thanhVienDTO = mapToThanhVienDTO(thanhVienRepository.getThanhVienByEmail(email));
        
        thanhVienDTO.setPassword(newPassword);
        updateThanhVien(thanhVienDTO);
        
        
    }


    
    
    
    
}
