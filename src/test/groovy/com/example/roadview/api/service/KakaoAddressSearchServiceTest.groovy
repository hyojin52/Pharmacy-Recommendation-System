package com.example.roadview.api.service

import com.example.roadview.AbstractIntegrationContainerBaseTest
import com.example.roadview.api.dto.KakaoApiResponseDto
import org.springframework.beans.factory.annotation.Autowired

class KakaoAddressSearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private KakaoAddressSearchService kakaoAddressSearchService

    def "address 파라미터의 값이 null이면, null을 리턴한다."() {
        given:
        String address = null

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result == null
    }

    def "주소값이 valid 하다면, 정상적으로 document를 반환한다."() {
        given:
        def address = "서울 성북구 종암로 10길"

        when:
        def result = kakaoAddressSearchService.requestAddressSearch(address)

        then:
        result.metaDto.totalCount > 0
        result.documentList.size() > 0
        result.documentList.get(0).addressName != null
    }
}
