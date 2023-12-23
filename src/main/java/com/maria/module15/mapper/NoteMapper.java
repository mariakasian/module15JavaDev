package com.maria.module15.mapper;

import com.maria.module15.dto.NoteDto;
import com.maria.module15.entity.Note;
import com.maria.module15.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public NoteDto toNoteDto(Note entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }

    public Note toNoteEntity(NoteDto dto) {
        Note entity = new Note();
        entity.setId(dto.getId());
        entity.setUser(new User(dto.getUsername()));
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }
    public NoteDto toNoteDto(String title, String content) {
        NoteDto dto = new NoteDto();
        dto.setTitle(title);
        dto.setContent(content);
        return dto;
    }
}
