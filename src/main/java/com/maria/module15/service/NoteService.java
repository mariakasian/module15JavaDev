package com.maria.module15.service;

import com.maria.module15.entity.Note;
import com.maria.module15.exceptions.NoteNotFoundException;
import com.maria.module15.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Transactional
    public Note add(Note note) {

        return noteRepository.save(note);
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note getById(Long id) throws NoteNotFoundException{
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    @Transactional
    public void update(Long id, String newTitle, String newContent) throws NoteNotFoundException {
        Note updatingNote = getById(id);
        if (updatingNote == null){
            throw new NoteNotFoundException(id);
        }

        updatingNote.setTitle(newTitle);
        updatingNote.setContent(newContent);
        noteRepository.save(updatingNote);
    }

    @Transactional
    public void deleteById(Long id) throws NoteNotFoundException {
        if (getById(id) == null){
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
}
