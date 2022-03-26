package hello.community.service;

import hello.community.domain.Board;
import hello.community.domain.User;
import hello.community.dto.UpdateRequestBoardDto;
import hello.community.dto.WriteRequestBoardDto;
import hello.community.repository.DetailBoardRepository;
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
    private final DetailBoardRepository detailBoardRepository;

    @Transactional
    public void writeBoard(WriteRequestBoardDto writeRequestBoardDto, User user) {
        boardRepository.save(new Board(writeRequestBoardDto.getTitle(), writeRequestBoardDto.getContent(), user));
    }

    @Transactional(readOnly = true)
    public Page<Board> listBoards(Pageable pageable) {
        return boardRepository.findAllWithUser(pageable);
    }

    @Transactional(readOnly = true)
    public Board findBoardWithUserById(Long boardId) {
        return detailBoardRepository.findWithUserById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
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
    public void updateBoard(Long boardId, UpdateRequestBoardDto updateRequestBoardDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("존재하지 않는 글입니다.");
                });
        board.titleAndContentUpdate(updateRequestBoardDto.getTitle(), updateRequestBoardDto.getContent());
    }
}
