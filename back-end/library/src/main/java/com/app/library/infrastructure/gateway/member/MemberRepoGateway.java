package com.app.library.infrastructure.gateway.member;

import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;
import com.app.library.infrastructure.entity.member.MemberEntity;
import com.app.library.infrastructure.mapper.member.MemberEntityMapper;
import com.app.library.infrastructure.persistence.member.JpaMemberRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public class MemberRepoGateway implements MemberGateway {

    private final JpaMemberRepo jpaMemberRepo;
    private final MemberEntityMapper memberEntityMapper;

    public MemberRepoGateway(JpaMemberRepo jpaMemberRepo, MemberEntityMapper memberEntityMapper) {
        this.jpaMemberRepo = jpaMemberRepo;
        this.memberEntityMapper = memberEntityMapper;
    }

    @Override
    public MemberPaginated getAll(int page, int size) {
        Page<MemberEntity> memberEntities = jpaMemberRepo.findAll(PageRequest.of(page, size));
        return new MemberPaginated(
            memberEntities.getNumber(),
            memberEntities.getSize(),
            memberEntities.getTotalPages(),
            memberEntities.map(memberEntityMapper::mapFromMemberEntity).toList()
        );
    }

    @Override
    public Member get(UUID id) {
        return memberEntityMapper.mapFromMemberEntity(jpaMemberRepo.findById(id).orElseThrow(() -> new DomainException("Member not found")));
    }

    @Override
    public Member create(Member member) {
        return memberEntityMapper.mapFromMemberEntity(jpaMemberRepo.save(memberEntityMapper.mapToMemberEntity(member)));
    }

    @Override
    public Member update(Member member) {
        return memberEntityMapper.mapFromMemberEntity(jpaMemberRepo.save(memberEntityMapper.mapToMemberEntity(member)));
    }

    @Override
    public Member delete(UUID id) {
        Member member = memberEntityMapper.mapFromMemberEntity(jpaMemberRepo.findById(id).orElseThrow(() -> new DomainException("Member not found")));
        jpaMemberRepo.deleteById(id);
        return member;
    }

    @Override
    public Member getByCpf(String cpf) {
        return memberEntityMapper.mapFromMemberEntity(jpaMemberRepo.findByCpf(cpf));
    }
}
