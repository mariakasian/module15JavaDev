package com.maria.module15.controllers;

import com.maria.module15.auth.AuthService;
import com.maria.module15.dto.NoteDto;
import com.maria.module15.entity.Note;
import com.maria.module15.exceptions.NoteNotFoundException;
import com.maria.module15.mapper.NoteMapper;
import com.maria.module15.service.NoteService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Validated
@Controller
@RequestMapping("/note")
public class NoteController {
    private final AuthService authService;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private NoteService noteService;

    @GetMapping(value = "/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("allNotes");
        result.addObject("notes",  noteService.listAll());
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView createNote(
            @RequestParam(value="title") @Size(min = 3, max = 50) String title,
            @RequestParam(value="content") @Size(min = 3, max = 300) String content) {

        NoteDto noteDto = noteMapper.toNoteDto(title, content);
        noteDto.setUsername(authService.getUsername());
        Note entity = noteMapper.toNoteEntity(noteDto);
        noteService.add(entity);
        return noteList();
    }

    @GetMapping(value = "/edit")
    public ModelAndView editNote(
            @NotEmpty @RequestParam(value="id") String id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("updateNote");
        result.addObject("note", noteService.getById(Long.valueOf(id)));
        return result;
    }

    @PostMapping(value = "/edit")
    public ModelAndView updateNote(
            @NotEmpty @RequestParam(value="id") String id,
            @Size(min = 3, max = 50) @RequestParam(value="title") String title,
            @Size(min = 3, max = 300) @RequestParam(value="content") String content) throws NoteNotFoundException {

        noteService.update(Long.valueOf(id), title, content);
        return noteList();
    }

    @GetMapping(value = "/delete")
    @PostMapping(value = "/delete")
    public ModelAndView deleteNoteByPost(@NotEmpty @RequestParam(value="id") String id) throws NoteNotFoundException {
        noteService.deleteById(Long.valueOf(id));
        return noteList();
    }
}

