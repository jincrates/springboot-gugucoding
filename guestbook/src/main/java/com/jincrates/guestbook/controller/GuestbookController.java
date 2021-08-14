package com.jincrates.guestbook.controller;

import com.jincrates.guestbook.dto.PageRequestDTO;
import com.jincrates.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2  //@Slf4j랑 뭐가 다를까?
@RequiredArgsConstructor  //자동 주입을 위한 Annotation
public class GuestbookController {

    private final GuestbookService service;  //final로 선언

    @GetMapping("/")
    public String index(){

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("list......................" + pageRequestDTO);

        model.addAttribute("result", service.getList(pageRequestDTO));
    }
}
