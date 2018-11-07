package com.anber.concurrency.example.threadLocal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anber
 * @date 2018/11/7
 **/
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    public Long test() {
        return RequestHolder.getId();
    }
}
