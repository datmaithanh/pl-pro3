package com.PL_Pro3_WebwithSpringBoot.Pro3.service.impluser;

import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThongTinSD;
import com.PL_Pro3_WebwithSpringBoot.Pro3.repository.ThongTinSDRepository;
import com.PL_Pro3_WebwithSpringBoot.Pro3.service.serviceuser.ThongTinSDUserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThongTinSDUserServiceImpl implements ThongTinSDUserService {
    @Autowired
    ThongTinSDRepository repository;

    @Override
    public Optional<ThongTinSD> findByThanhVien(ThanhVien thanhVien) {
        return repository.findByThanhVien(thanhVien);
    }
}
