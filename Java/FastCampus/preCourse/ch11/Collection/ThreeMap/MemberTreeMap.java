package com.company.ch11.Collection.ThreeMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class MemberTreeMap {

    private TreeMap<Integer, Member> treeMap;

    public MemberTreeMap(){
        treeMap = new TreeMap<>();
    }

    public void addMember(Member member){
        treeMap.put(member.getMemberID(),member);
    }

    public boolean removeMember(int memberID){
        if (treeMap.containsKey(memberID)){
            treeMap.remove(memberID);
            return true;
        }
        System.out.println("회원 번호가 없습니다");
        return false;
    }

    public void showAllMember(){
        Iterator<Integer> ir =treeMap.keySet().iterator(); //모든 키객체를 반환해준다. set타입으로 반환
        // iterator 객체가 key로 순환을 한다.
        while (ir.hasNext()){
            int key = ir.next();
            Member member = treeMap.get(key);
            System.out.println(member);
        }

        System.out.println();



        //hashMap.values().iterator();
        //애는 중복될 수 있어서 컬렉션(? 어레이가아니고?)으로 반환이된다.

    }
}
