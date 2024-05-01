package com.PL_Pro3_WebwithSpringBoot.Pro3.dto;


import com.PL_Pro3_WebwithSpringBoot.Pro3.models.ThanhVien;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class XuLyDTO {
    
    @NotNull(message = "MaXL không thể bỏ trống")
    private Integer maXL;  
    private ThanhVien thanhVien;
    
    @NotEmpty(message = "Hình thức XL không thể bỏ trống")
    private String hinhThucXL;  
    
   
    private Integer soTien;
    
    private LocalDateTime ngayXL;
    
    @NotNull(message = "Trạng thái XL không thể bỏ trống")
    private Boolean trangThaiXL; 
}
