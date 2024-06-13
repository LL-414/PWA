package com.example.pwa.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "Unexpected Error";

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == 404) {
                errorMessage = "Page Not Found";
            } else if (statusCode == 500) {
                errorMessage = "Internal Server Error";
            } else {
                errorMessage = "Error Code: " + statusCode;
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

}
