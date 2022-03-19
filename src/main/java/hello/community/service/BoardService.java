package hello.community.service;

import hello.community.domain.Board.Board;
import hello.community.domain.User.User;
import hello.community.dto.BoardWriteDto;
import hello.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void write(BoardWriteDto boardWriteDto, User user) {
        boardRepository.save(new Board(boardWriteDto.getTitle(), boardWriteDto.getContent(), user));
    }

    @Transactional(readOnly = true)
    public Page<Board> list(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board view(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
    }

    @Transactional
    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
