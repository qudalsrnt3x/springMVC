package org.zerock.service;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO boardVO);

    public BoardVO get(Long bno);

    //public List<BoardVO> getList();

    public List<BoardVO> getList(Criteria criteria);

    public boolean modify(BoardVO boardVO);

    public boolean remove(Long bno);
}
