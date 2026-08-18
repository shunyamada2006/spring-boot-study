package com.example.spring_tutorial.controller;

import java.util.List;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_tutorial.entity.User;
import com.example.spring_tutorial.form.UserRegisterForm;
import com.example.spring_tutorial.service.UserService;
import org.springframework.security.core.Authentication;

@Controller
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminuser")
    public String adminUser(Model model, Authentication authentication) {
        // 最新のユーザーリストを取得
        List<User> users = userService.getAllUsers();

        // ビューにユーザーリストを渡す
        model.addAttribute("users", users);

        if(!model.containsAttribute("userRegisterForm")){
            model.addAttribute("userRegisterForm",new UserRegisterForm());
        }
        return "adminUserView";
    }

    @PostMapping("/register")
    public String registerUser(RedirectAttributes redirectAttributes,
              @Validated UserRegisterForm form,BindingResult result){
//            UserRegisterForm form){
//                               @RequestParam("user_name") String userName,
//                               @RequestParam("password") String password,
//                               @RequestParam("role_id") int roleId) {

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterForm",form);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX +Conventions.getVariableName(form),result);
            return "redirect:/adminuser";
        }

        try {
            userService.createUser(form.getUserName(), form.getPassword(), form.getRoleId());
//            userService.createUser(userName, password, roleId);

            // 登録成功時は完了メッセージをビューに受け渡す
            redirectAttributes.addFlashAttribute("successMessage", "ユーザー登録が完了しました。");

        } catch (IllegalArgumentException e) {
            // 登録失敗時はエラーメッセージをビューに受け渡す
            redirectAttributes.addFlashAttribute("failureMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("userRegisterForm",form);

//            redirectAttributes.addFlashAttribute("userName", userName);
//            redirectAttributes.addFlashAttribute("roleId", roleId);
        }

        // adminuserにリダイレクトしてリストを再表示
        return "redirect:/adminuser";
    }
}