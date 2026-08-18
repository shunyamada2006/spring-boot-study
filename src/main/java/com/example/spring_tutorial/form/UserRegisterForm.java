package com.example.spring_tutorial.form;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class UserRegisterForm {
    @NotBlank(message="ユーザー名を入力してください")
    private String userName;
    @NotBlank(message="パスワードを入力してください")
    @Size(min = 8, message="パスワードは少なくとも８文字は必要です")
    private String password;
    @NotNull(message="ロールを正しく取得できませんでした")
    private Integer roleId;
}
