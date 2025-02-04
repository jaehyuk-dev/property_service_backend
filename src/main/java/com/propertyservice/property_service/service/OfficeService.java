package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.office.Office;
import com.propertyservice.property_service.dto.office.OfficeRegisterRequest;
import com.propertyservice.property_service.repository.office.OfficeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfficeService {
    private final OfficeRepository officeRepository;

    /**
     * 회사 등록 및 회사 코드 발급
     * @param request
     * @return
     */
    @Transactional
    public String registerOffice(OfficeRegisterRequest request) {
        String officeCode = generateUniqueOfficeCode(request.getOfficeName(), request.getZoneCode());

        log.info("officeCode: {}", officeCode);

        Office office = Office.builder()
                .officeCode(officeCode) // 회사 코드 추가
                .officeName(request.getOfficeName())
                .zonecode(request.getZoneCode())
                .officeAddress(request.getOfficeAddress())
                .addressDetail(request.getAddressDetail())
                .presidentName(request.getPresidentName())
                .presidentEmail(request.getPresidentEmail())
                .mobileNumber(request.getMobileNumber())
                .phoneNumber(request.getPhoneNumber())
                .build();

        officeRepository.save(office);
        return officeCode;
    }

    /**
     * 회사 코드 생성 (중복 검사 포함)
     */
    private String generateUniqueOfficeCode(String officeName, String zoneCode) {
        String officeCode;

        do {
            officeCode = generateOfficeCode(officeName, zoneCode);
        } while (officeRepository.existsByOfficeCode(officeCode)); // 중복 체크

        return officeCode;
    }

    /**
     * 회사 코드 생성 (한글 초성 or 영문 + 우편번호 기반 난수)
     */
    private String generateOfficeCode(String officeName, String zoneCode) {
        String initials = extractInitials(officeName); // 한글 초성 or 영어 이니셜 변환
        int postalRandom = generatePostalRandom(zoneCode); // 우편번호 기반 난수 생성
        int randomNum = new SecureRandom().nextInt(9000) + 1000; // 1000~9999 랜덤 숫자

        return initials + "-" + postalRandom + "-" + randomNum; // ex: "PH-6123-4823"
    }

    /**
     * 한글 초성을 영문으로 변환하거나, 영문이면 그대로 유지하여 2자리 대문자로 반환
     */
    public String extractInitials(String name) {
        if (name == null || name.isEmpty()) return "XX"; // 기본값

        Map<Character, String> initialMap = getKoreanInitialMap();
        StringBuilder initials = new StringBuilder();

        for (char ch : name.toCharArray()) {
            if (isKoreanCharacter(ch)) { // 한글 음절이면 초성 변환
                initials.append(getKoreanInitial(ch));
            } else if (Character.isAlphabetic(ch)) { // 영문이면 그대로 사용
                initials.append(Character.toUpperCase(ch));
            }

            if (initials.length() >= 2) break; // 두 글자만 사용
        }

        // 2자리 미만이면 'X'로 채움
        while (initials.length() < 2) {
            initials.append("X");
        }

        return initials.substring(0, 2);
    }

    /**
     * 한글 초성을 대응하는 영문으로 매핑
     */
    private Map<Character, String> getKoreanInitialMap() {
        Map<Character, String> map = new HashMap<>();
        map.put('ㄱ', "G"); map.put('ㄲ', "GG"); map.put('ㄴ', "N");
        map.put('ㄷ', "D"); map.put('ㄸ', "DD"); map.put('ㄹ', "R");
        map.put('ㅁ', "M"); map.put('ㅂ', "B"); map.put('ㅃ', "BB");
        map.put('ㅅ', "S"); map.put('ㅆ', "SS"); map.put('ㅇ', "O");
        map.put('ㅈ', "J"); map.put('ㅉ', "JJ"); map.put('ㅊ', "CH");
        map.put('ㅋ', "K"); map.put('ㅌ', "T"); map.put('ㅍ', "P");
        map.put('ㅎ', "H");
        return map;
    }

    /**
     * 한글 초성 변환
     */
    private String getKoreanInitial(char ch) {
        String[] initials = {
                "G", "GG", "N", "D", "DD", "R", "M", "B", "BB",
                "S", "SS", "O", "J", "JJ", "CH", "K", "T", "P", "H"
        };

        if (ch >= '가' && ch <= '힣') { // 한글 음절이면 초성 추출
            int unicode = ch - 0xAC00;
            int initialIndex = unicode / (21 * 28);
            return initials[initialIndex]; // 초성을 영문으로 변환하여 반환
        }
        return ""; // 한글 음절이 아니면 빈 값 반환
    }

    /**
     * 한글 음절인지 확인
     */
    private boolean isKoreanCharacter(char ch) {
        return (ch >= '가' && ch <= '힣');
    }


    /**
     * 우편번호 기반 난수 생성 (우편번호 일부를 이용해 랜덤 숫자 생성)
     */
    private int generatePostalRandom(String zoneCode) {
        if (zoneCode == null || zoneCode.length() < 3) {
            return new SecureRandom().nextInt(9000) + 1000; // 기본 랜덤 처리
        }
        String numericPart = zoneCode.replaceAll("[^0-9]", ""); // 숫자만 추출
        int length = numericPart.length();

        // 길이가 충분하면 앞에서 4자리 사용
        if (length >= 4) {
            return Integer.parseInt(numericPart.substring(0, 4));
        }
        // 부족하면 전체를 사용하되, 랜덤값 추가
        return Integer.parseInt(numericPart) * 10 + new SecureRandom().nextInt(9);
    }
}
