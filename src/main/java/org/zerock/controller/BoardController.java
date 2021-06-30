package org.zerock.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board")
@Log
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/list")
//    public void list(Model model) {
//
//        log.info("list");
//        model.addAttribute("list", boardService.getList());
//    }

    @GetMapping("list")
    public void list(Criteria criteria, Model model) {
        log.info("list : " + criteria);

        model.addAttribute("list", boardService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(criteria, boardService.getTotal(criteria)));
    }

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(BoardVO boardVO, RedirectAttributes rttr) {

        log.info("register: "+boardVO);

        boardService.register(boardVO);

        rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, Model model, Criteria criteria) {

        log.info("/get or modify");
        model.addAttribute("cri", criteria);
        model.addAttribute("board", boardService.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO boardVO, RedirectAttributes rttr, @ModelAttribute("cri") Criteria criteria) {
        log.info("modify : "+boardVO);

        if (boardService.modify(boardVO)) {
            rttr.addFlashAttribute("result", "success");
        }

        rttr.addAttribute("pageNum", criteria.getPageNum());
        rttr.addAttribute("amount", criteria.getAmount());

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr, @ModelAttribute("cri") Criteria criteria) {

        log.info("remove.. " + bno);
        if (boardService.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        rttr.addAttribute("pageNum", criteria.getPageNum());
        rttr.addAttribute("amount", criteria.getAmount());

        return "redirect:/board/list";
    }
}
