package hello.community.service;

import hello.community.domain.Board.Board;
import hello.community.domain.User.User;
import hello.community.dto.BoardWriteDto;
import hello.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public List<Board> list() {
        return boardRepository.findAll();
    }
}
