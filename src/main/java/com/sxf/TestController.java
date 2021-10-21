package com.sxf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


  @GetMapping("/")
  public String getString(@RequestParam("number") int number) {
    if (number < 0) {
      return "you query number is less than zero";
    } else if (number > 0) {
      return "you query number is greater than zero";
    } else {
      return "you query number is equal zero";
    }
  }
}
