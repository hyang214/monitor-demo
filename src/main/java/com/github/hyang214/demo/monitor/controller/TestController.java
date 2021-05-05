package com.github.hyang214.demo.monitor.controller;

import com.github.hyang214.demo.monitor.resources.SomeResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * title:
 *
 * @author Hao YANG
 * @since 2021.05.0
 */
@RestController
@ResponseBody
@RequestMapping(value = "/test")
public class TestController {

    private Random random = new Random();

    private SomeResource someResource;

    public TestController(SomeResource someResource) {
        this.someResource = someResource;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String word) throws InterruptedException {
        someResource.borrow();
        Thread.sleep(random.nextInt(1000));
        someResource.ret();
        return word;
    }

}
