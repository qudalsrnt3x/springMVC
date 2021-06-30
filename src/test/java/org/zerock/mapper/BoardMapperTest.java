package org.zerock.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {

    @Setter(onMethod_ = {@Autowired})
    private BoardMapper boardMapper;

    @Test
    public void testGetList() {

        boardMapper.getList().forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testInsert() {

        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성 글");
        boardVO.setContent("새로 작성 내용");
        boardVO.setWriter("user00");

        boardMapper.insert(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성 글");
        boardVO.setContent("새로 작성 내용");
        boardVO.setWriter("user00");

        boardMapper.insertSelectKey(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testSelect() {

        BoardVO boardVO = boardMapper.read(5L);

        log.info(boardVO);

    }

    @Test
    public void testDelete() {

        int count = boardMapper.delete(1L);

        log.info(count);
    }

    @Test
    public void update() {

        BoardVO boardVO = new BoardVO();

        boardVO.setBno(5L);
        boardVO.setTitle("수정 타이틀");
        boardVO.setContent("수정 내용");
        boardVO.setWriter("user11");

        int count = boardMapper.update(boardVO);

        log.info("UPDATE COUNT : " + count);
    }

    @Test
    public void testPaging() {
        Criteria criteria = new Criteria();

        // 10개씩 3페이지
        criteria.setPageNum(3);
        criteria.setAmount(10);


        List<BoardVO> list = boardMapper.getListWithPaging(criteria);

        list.forEach(boardVO -> log.info(boardVO));
    }


}