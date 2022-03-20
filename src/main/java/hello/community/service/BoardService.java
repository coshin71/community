package hello.community.service;

import hello.community.domain.Board;
import hello.community.domain.User;
import hello.community.dto.BoardUpdateDto;
import hello.community.dto.BoardWriteDto;
import hello.community.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void writeBoard(BoardWriteDto boardWriteDto, User user) {
        boardRepository.save(new Board(boardWriteDto.getTitle(), boardWriteDto.getContent(), user));
    }

//    @Transactional(readOnly = true)
//    public Page<Board> listBoard(Pageable pageable) {
//        return boardRepository.findAll(pageable);
//    }

    @Transactional(readOnly = true)
    public Page<Board> listBoard(Pageable pageable) {
        return boardRepository.findAllWithUser(pageable);
    }

    @Transactional(readOnly = true)
    public Board findBoardById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
        board.setTitle(boardUpdateDto.getTitle());
        board.setContent(boardUpdateDto.getContent());
    }
}
