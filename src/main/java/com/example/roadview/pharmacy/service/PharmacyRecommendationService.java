package com.example.roadview.pharmacy.service;

import com.example.roadview.api.dto.DocumentDto;
import com.example.roadview.api.dto.KakaoApiResponseDto;
import com.example.roadview.api.service.KakaoAddressSearchService;
import com.example.roadview.direction.dto.OutputDto;
import com.example.roadview.direction.entity.Direction;
import com.example.roadview.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PharmacyRecommendationService {
  
  private final KakaoAddressSearchService kakaoAddressSearchService;
  private final DirectionService directionService;
  
  public List<OutputDto> recommendationPharmacyList(String address) {
    KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);
    
    if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
      log.error("[PharmacyRecommendationService recommendationPharmacyList] Input address: {}", address);
      return Collections.emptyList();
    }
    
    DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);
    // List<Direction> directionList = directionService.buildDirectionList(documentDto);
    List<Direction> directionList = directionService.buildDirectionListByCategoryApi(documentDto);
    return directionService.saveAll(directionList)
            .stream()
            .map(this::convertToOutputDto)
            .collect(Collectors.toList());
  }
  
  private OutputDto convertToOutputDto(Direction direction) {
    return OutputDto.builder()
            .pharmacyName(direction.getTargetPharmacyName())
            .pharmacyAddress(direction.getTargetAddress())
            .directionUrl("todo") // todo
            .roadViewUrl("todo")
            .distance(String.format("%.2f km", direction.getDistance()))
            .build();
  }
  
}
