package hello.community.service;

import hello.community.dto.CommentWriteDto;
import hello.community.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void writeComment(CommentWriteDto commentWriteDto) {

    }
}
