package com.example.springkadaiform.controller;

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
    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contactFormView";
    }

    // フォーム送信（確認画面へ）
    @PostMapping("/contact/confirm")
    public String confirm(
            @ModelAttribute("contactForm") @Valid ContactForm contactForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            // バリデーションエラー → 入力画面へ戻る
            return "contactFormView";
        }

        // バリデーションOK → 確認画面へ
        return "confirmView";
    }

    // 確認画面から戻る処理（任意）
    @PostMapping("/contact/back")
    public String back(@ModelAttribute("contactForm") ContactForm contactForm) {
        return "contactFormView";
    }
}
