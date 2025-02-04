package com.propertyservice.property_service.service;

import com.propertyservice.property_service.domain.office.Office;
import com.propertyservice.property_service.domain.office.OfficeUser;
import com.propertyservice.property_service.domain.office.Role;
import com.propertyservice.property_service.dto.office.OfficeUserSignUpRequest;
import com.propertyservice.property_service.error.ErrorCode;
import com.propertyservice.property_service.error.exception.BusinessException;
import com.propertyservice.property_service.repository.office.OfficeRepository;
import com.propertyservice.property_service.repository.office.OfficeUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final OfficeRepository officeRepository;
    private final OfficeUserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OfficeUserRepository officeUserRepository;

    @Transactional
    public void signUpUser(OfficeUserSignUpRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        Office office = officeRepository.findByOfficeCode(request.getOfficeCode()).orElseThrow(
                () -> new BusinessException(ErrorCode.OFFICE_NOT_FOUND)
        );

        OfficeUser user = OfficeUser.builder()
                .office(office)
                .name(request.getName())
                .email(request.getEmail())
                .passwordHash(bCryptPasswordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(officeUserRepository.existsByOffice(office) ? Role.ADMIN : Role.USER) // 첫 가입이면 대표
                .build();

        officeUserRepository.save(user);
    }
}
