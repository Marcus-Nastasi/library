package com.app.library.infrastructure.configuration.member;

import com.app.library.adapters.mapper.member.MemberDtoMapper;
import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.infrastructure.gateway.member.MemberRepoGateway;
import com.app.library.infrastructure.mappers.member.MemberEntityMapper;
import com.app.library.infrastructure.persistence.member.JpaMemberRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberBeans {

    @Bean
    public MemberUseCase memberUseCase(MemberGateway memberRepoGateway) {
        return new MemberUseCase(memberRepoGateway);
    }

    @Bean
    public MemberGateway memberGateway(JpaMemberRepo jpaMemberRepo, MemberEntityMapper memberEntityMapper) {
        return new MemberRepoGateway(jpaMemberRepo, memberEntityMapper);
    }

    @Bean
    public MemberDtoMapper memberDtoMapper() {
        return new MemberDtoMapper();
    }

    @Bean
    public MemberEntityMapper memberEntityMapper() {
        return new MemberEntityMapper();
    }
}
