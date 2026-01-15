package com.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有 RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(
            RuntimeException e,
            RedirectAttributes redirectAttributes) {

        // 把错误信息带到重定向页面
        redirectAttributes.addFlashAttribute("error", e.getMessage());

        // 统一跳回文章列表页
        return "redirect:/article/list";
    }
}
