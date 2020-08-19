package com.company.ch05.StaticEx;
class CardCompany{
    private static CardCompany instance = new CardCompany();

//    private static int cardNum = 10000;
//    public CardCompany(){
//
//    }

    public Card createCard(){
//        cardNum++;
//       return new Card(cardNum);
        Card card = new Card();
        return card;
    }

    public static CardCompany getInstance(){
        if(instance == null){
            instance = new CardCompany();
        }
        return instance;
    }

}
//extends CardCompany
class Card {
    private static int serialNum = 10000;
    private int cardNum;
//    public int cardNum=0;
//    public Card(int cardNum){
//        this.cardNum = cardNum;
//
//    }

    public Card() {
        serialNum++;
        cardNum = serialNum;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
public class DoItCode {
    public static void main(String[] args) {
        CardCompany company = CardCompany.getInstance();

        Card myCard = company.createCard();
        Card youCard = company.createCard();

        System.out.println(myCard.getCardNum());
        System.out.println(youCard.getCardNum());


    }
}
