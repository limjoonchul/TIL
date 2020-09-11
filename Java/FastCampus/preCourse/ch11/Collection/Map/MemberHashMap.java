package com.company.ch11.Collection.Map;

import java.util.HashMap;
import java.util.Iterator;

public class MemberHashMap {

    private HashMap<Integer,Member> hashMap;

    public MemberHashMap(){
        hashMap = new HashMap<>();
    }

    public void addMember(Member member){
        hashMap.put(member.getMemberID(),member);
    }

    public boolean removeMember(int memberID){
        if (hashMap.containsKey(memberID)){
            hashMap.remove(memberID);
            return true;
        }
        System.out.println("회원 번호가 없습니다");
        return false;
    }

    public void showAllMember(){
        Iterator<Integer> ir =hashMap.keySet().iterator(); //모든 키객체를 반환해준다. set타입으로 반환
        // iterator 객체가 key로 순환을 한다.
        while (ir.hasNext()){
            int key = ir.next();
            Member member = hashMap.get(key);
            System.out.println(member);
        }

        System.out.println();



        //hashMap.values().iterator();
        //애는 중복될 수 있어서 컬렉션(? 어레이가아니고?)으로 반환이된다.

    }
}
