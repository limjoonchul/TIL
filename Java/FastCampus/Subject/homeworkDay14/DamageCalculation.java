package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 열거형 타입과 함수형 프로그래밍을 이용하여 플레이어의 공격력을 계산하는 알고리즘을 구현하시오.
 *
 * 플레이어 공격력을 계산하는 과정은 아래와 같다.
 * 1. 플레이어의 무기에 따라 공격력이 변화한다. 무기는 최근에 장착한 무기의 공격력 만으로 계산된다.
 *   1-1. BARE_HANDS - 공격력 5
 *   1-2. DAGGER - 공격력 40
 *   1-3. LONG_SWORD - 공격력 100
 *   1-4. DRAGON_SLAYER -  공격력 250
 * 2. 플레이어의 공격력에 영향을 주는 아이템에 따라 공격력 증가 방식이 다르며, 아이템은 중복 적용된다.
 *   2-1. BLACK_POTION - 공격력 10% 증가
 *   2-2. WHITE_POTION - 모든 공격력 계산이 끝난 후에 공격력 + 200
 *   2-3. MUSHROOM - 무기 공격력 + 20
 *
 */
enum Weapon {
    // 무기 구현
    BARE_HANDS(5), DAGGER(40),LONG_SWORD(100),DRAGON_SLAYER(250);
    private int attackPower;
    Weapon(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}

enum Item {
    // 소비 아이템 구현
    BLACK_POSITION,WHITE_POSITION,MUSHROOM;

}

class Player {
    Weapon currentWeapon;
    List<Item> items = new ArrayList<>();

    // TODO: Player에 필요한 메소드 구현
    // 무기 교체, 아이템 사용, 아이템 효과 종료 메소드 구현
    double attackPower;
    public void weaponChange(Weapon weapon){
        currentWeapon = weapon;
        if (items.isEmpty()){
            attackPower = currentWeapon.getAttackPower();
        }else if(items.get(items.size()-1).equals(Item.BLACK_POSITION)){
            attackPower = currentWeapon.getAttackPower() + (currentWeapon.getAttackPower() * 0.1);
        }else if(items.get(items.size()-1).equals(Item.WHITE_POSITION)){
            attackPower = currentWeapon.getAttackPower() + 200;
        }else if(items.get(items.size()-1).equals(Item.MUSHROOM)){
            attackPower = currentWeapon.getAttackPower() + 20;
        }
        System.out.println("현재 무기 :"+currentWeapon+ " 공격력 :"+attackPower);
//        return (int)attackPower;
    }
    public void useItem(Item item){
        items.add(item);
        double itemPower=0;
        if (item.equals(item.BLACK_POSITION)){
            attackPower = attackPower + (attackPower *0.1);
             itemPower = 0.1;
        }else if(item.equals(item.WHITE_POSITION)){
            attackPower += 200;
            itemPower = 200;
        }else if(item.equals(item.MUSHROOM)){
            attackPower += 20;
            itemPower = 20;
        }




        System.out.println("현재 무기 : "+currentWeapon+", 아이템 : "+item+ ", 공격력 :"+attackPower);
//       return itemPower;
    }
    public void finishItemEffect(Item item){
        int cnt=0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(Item.BLACK_POSITION)){
                attackPower = attackPower - (attackPower *0.1);
                cnt++;
            }else if(items.get(i).equals(Item.WHITE_POSITION)){
                attackPower = attackPower - 200;
                cnt++;
            }else if(items.get(i).equals(Item.MUSHROOM)){
                attackPower = attackPower - 20;
                cnt++;
            }

            if (cnt>0){
                break;
            }
        }
        System.out.println("현재 무기 :"+currentWeapon+ " 공격력 :"+attackPower);
    }
}

public class DamageCalculation {

    public static void main(String[] args) {
        // 무기 및 아이템 장착/사용 시나리오 및 플레이어 공격력 출력
        Player player = new Player();
        player.weaponChange(Weapon.BARE_HANDS);
        player.useItem(Item.BLACK_POSITION);
        player.useItem(Item.BLACK_POSITION);
        player.useItem(Item.WHITE_POSITION);
        player.finishItemEffect(Item.BLACK_POSITION);

    }
}
