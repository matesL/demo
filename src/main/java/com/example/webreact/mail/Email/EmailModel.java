/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.webreact.mail.Email;
public class EmailModel {

        public String title;  //邮件标题
        public String message; //邮件内容
        public String emailReciver; //邮件收件人
        public EmailModel(String title, String message, String emailReciver){
            this.title = title;
            this.message = message;
            this.emailReciver = emailReciver;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getEmailReciver() {
            return emailReciver;
        }
        public void setEmailReciver(String emailReciver) {
            this.emailReciver = emailReciver;
        }
}

