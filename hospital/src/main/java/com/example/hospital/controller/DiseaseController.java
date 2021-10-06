package com.example.hospital.controller;

import com.example.hospital.baseResponse.BaseResponse;
import com.example.hospital.dto.DiseaseDto;
import com.example.hospital.entity.Disease;
import com.example.hospital.serviece.DiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/disease")
@RestController
public class DiseaseController {

    @Autowired
    private DiseaseInterface diseaseInterface;


    @PostMapping("/create")
    public BaseResponse<Disease> addDiseaseInfo(@RequestBody DiseaseDto diseaseDTO) {
        BaseResponse<Disease> baseResponse=null;
        baseResponse = BaseResponse.<Disease>builder().Data(diseaseInterface.AddDiseaseInfo(diseaseDTO)).build();
        return baseResponse;

    }

    @GetMapping("/getAll")
    public BaseResponse<Disease> list(){
        BaseResponse<Disease> baseResponse= null;
        baseResponse = BaseResponse.<Disease>builder().Data((Disease) diseaseInterface.listAlldisease()).build();
        return baseResponse;
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Disease> delete(@PathVariable int id) {
        BaseResponse<Disease> baseResponse= null;
        baseResponse = BaseResponse.<Disease>builder().Data(diseaseInterface.deleteById(id)).build();
        return baseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<Optional<Disease>> updateDiseaseById(@RequestBody DiseaseDto diseaseDTO){
        BaseResponse<Optional<Disease>>  baseResponse=null;
        baseResponse = BaseResponse.<Optional<Disease>> builder().Data(diseaseInterface.UpdateDiseaseById(diseaseDTO)).build();
        return baseResponse;
    }
}
