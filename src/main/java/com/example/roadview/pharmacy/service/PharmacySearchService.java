package com.example.roadview.pharmacy.service;

import com.example.roadview.pharmacy.dto.PharmacyDto;
import com.example.roadview.pharmacy.entity.Pharmacy;
import com.example.roadview.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacySearchService {
  
  private final PharmacyRepositoryService pharmacyRepositoryService;
  
  public List<PharmacyDto> searchPharmacyDtoList() {
    return pharmacyRepositoryService.findAll()
            .stream()
            .map(this::convertToPharmacyDto)
            .collect(Collectors.toList());
  }
  
  private PharmacyDto convertToPharmacyDto(Pharmacy entity) {
    return PharmacyDto.builder()
            .id(entity.getId())
            .pharmacyAddress(entity.getPharmacyAddress())
            .pharmacyName(entity.getPharmacyName())
            .latitude(entity.getLatitude())
            .longitude(entity.getLongitude())
            .build();
  }
  
  
}
