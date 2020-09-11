package com.company.ch11.Collection.Set;

public class Member {

    private int memberID;
    private String memberName;

    public Member(){}
    public Member(int memberID, String memberName){
        this.memberID = memberID;
        this.memberName = memberName;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String toString(){
        return memberName + "회원님의 아이디는"+ memberID + "입니다";
    }

    @Override
    public int hashCode() {
        // 여기서 쓴 것들 중에 integer을 제너레이트
        return memberID;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member)obj;
           return (this.memberID == member.memberID);
        }

        return false;
    }
}
