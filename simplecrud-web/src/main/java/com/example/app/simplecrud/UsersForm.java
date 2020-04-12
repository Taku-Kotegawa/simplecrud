package com.example.app.simplecrud;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UsersForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ユーザID
     */
    @NotBlank
    private String uid;

    /**
     *   氏名
     */
    @NotBlank
    private String name;

    /**
     *   パスワード
     */
    @NotBlank
    private String pass;

    /**
     *   メール
     */
    @NotBlank
    private String mail;

    /**
     *   ステータス
     */
    private Boolean status;

    /**
     *   作成日時
     */
    private LocalDateTime createdAt;

    /**
     * 作成日時(表示用)
     */
    private String createdAtDisp;

    /**
     *   更新日時
     */
    private LocalDateTime changedAt;

    /**
     * 更新日時(表示用)
     */
    private String changedAtDisp;

    /**
     *   コメント
     */
    private String comment;
}
