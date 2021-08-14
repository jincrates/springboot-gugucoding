package com.jincrates.guestbook.service;

import com.jincrates.guestbook.dto.GuestbookDTO;
import com.jincrates.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {

        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }
}
