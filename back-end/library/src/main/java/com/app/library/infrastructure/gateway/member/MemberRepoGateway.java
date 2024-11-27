package com.app.library.infrastructure.gateway.member;

import com.app.library.application.gateways.member.MemberGateway;
import com.app.library.domain.entity.exception.DomainException;
import com.app.library.domain.entity.member.Member;
import com.app.library.infrastructure.mapper.member.MemberEntityMapper;
import com.app.library.infrastructure.persistence.member.JpaMemberRepo;

import java.util.List;
import java.util.UUID;

public class MemberRepoGateway implements MemberGateway {

    private final JpaMemberRepo jpaMemberRepo;
    private final MemberEntityMapper memberEntityMapper;

    public MemberRepoGateway(JpaMemberRepo jpaMemberRepo, MemberEntityMapper memberEntityMapper) {
        this.jpaMemberRepo = jpaMemberRepo;
        this.memberEntityMapper = memberEntityMapper;
    }

    @Override
    public List<Member> getAll() {
        return jpaMemberRepo.findAll().stream().map(memberEntityMapper::mapFromLibrarianEntity).toList();
    }

    @Override
    public Member get(UUID id) {
        return memberEntityMapper
            .mapFromLibrarianEntity(jpaMemberRepo.findById(id).orElseThrow(() -> new DomainException("Member not found")));
    }

    @Override
    public Member create(Member member) {
        return memberEntityMapper.mapFromLibrarianEntity(jpaMemberRepo.save(memberEntityMapper.mapToLibrarianEntity(member)));
    }

    @Override
    public Member update(Member member) {
        return memberEntityMapper.mapFromLibrarianEntity(jpaMemberRepo.save(memberEntityMapper.mapToLibrarianEntity(member)));
    }

    @Override
    public Member delete(UUID id) {
        Member member = memberEntityMapper
            .mapFromLibrarianEntity(jpaMemberRepo.findById(id).orElseThrow(() -> new DomainException("Member not found")));
        jpaMemberRepo.deleteById(id);
        return member;
    }
}
