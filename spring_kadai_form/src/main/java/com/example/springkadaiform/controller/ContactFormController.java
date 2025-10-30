package com.example.springkadaiform.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    // お問い合わせフォーム表示
    @GetMapping("/form")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contactFormView";
    }

    @PostMapping("/form")
    public String submitForm(
            @ModelAttribute("contactForm") @Valid ContactForm contactForm,
            BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "contactFormView"; // エラー時はそのまま
        }
        session.setAttribute("contactForm", contactForm); // 入力値をセッションに保存
        return "redirect:/confirm"; // /confirmへリダイレクト
    }
    
    @GetMapping("/confirm")
    public String confirm(Model model, HttpSession session) {
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        if (contactForm == null) {
            // 直接アクセスした時などは/formに戻す
            return "redirect:/form";
        }
        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
    
    

    // 確認画面から戻る処理（任意）
    @PostMapping("/contact/back")
    public String back(@ModelAttribute("contactForm") ContactForm contactForm) {
        return "contactFormView";
    }
}
