package org.zerock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Override
    public void register(BoardVO boardVO) {
        boardMapper.insertSelectKey(boardVO);
    }

    @Override
    public BoardVO get(Long bno) {
        return boardMapper.read(bno);
    }

//    @Override
//    public List<BoardVO> getList() {
//        return boardMapper.getList();
//    }

    @Override
    public List<BoardVO> getList(Criteria criteria) {
        return boardMapper.getListWithPaging(criteria);
    }

    @Override
    public boolean modify(BoardVO boardVO) {
        int update = boardMapper.update(boardVO);

        return update == 1;
    }

    @Override
    public boolean remove(Long bno) {

        return boardMapper.delete(bno) == 1;
    }
}
