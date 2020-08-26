package com.company.ch13.Exception;

public class IDFormatTest {

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) throws IDFormatException {

        if(userID == null){
            throw new IDFormatException("아이디는 NULL 일 수 없습니다."); //실제로 예외를 발생시키는 것
        }else if(userID.length() <8 || userID.length() >20){
            throw new IDFormatException("아이디는 8자 이상 20자 이하로 쓰세요.");
        }
        this.userID = userID;
    }

    public static void main(String[] args) {
        IDFormatTest idtext = new IDFormatTest();

        String id = "123456";
        try {
            idtext.setUserID(id);
        } catch (IDFormatException e) {
            System.out.println(e);
        }
        
    }
}
