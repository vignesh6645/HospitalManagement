package com.example.hospital.servieceImplements;

import com.example.hospital.dto.DiseaseDto;
import com.example.hospital.entity.Disease;
import com.example.hospital.repository.DiseaseRepository;
import com.example.hospital.serviece.DiseaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiseaseServieceImplements implements DiseaseInterface {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Disease AddDiseaseInfo(DiseaseDto diseaseDTO) {
        Disease disease = new Disease();
        disease.setDiseaseId(diseaseDTO.getDiseaseId());
        disease.setDiseaseName(diseaseDTO.getDiseaseName());
        diseaseRepository.save(disease);
        return disease;
    }

    @Override
    public List<Disease> listAlldisease() {
        List<Disease> obj=diseaseRepository.findAll();
        return obj;
    }

    @Override
    public Disease deleteById(int id) {
        Disease disease = new Disease();
        diseaseRepository.deleteById(id);
        return disease;
    }

    @Override
    public Optional<Disease> UpdateDiseaseById(DiseaseDto diseaseDTO) {
        Optional<Disease> existDisease = diseaseRepository.findById(diseaseDTO.getDiseaseId());
        if (existDisease.isPresent())
        {
            existDisease.get().setDiseaseName(diseaseDTO.getDiseaseName());
        }
        else
        {

        }
        diseaseRepository.save(existDisease.get());
        return existDisease;
    }

}
