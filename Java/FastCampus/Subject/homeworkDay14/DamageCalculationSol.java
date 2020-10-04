package com.company.Homework12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntUnaryOperator;

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
enum Weapon{
    BARE_HANDS(5),DAGGER(40),LONG_SWORD(100),DRAGON_SLAYER(250);

    private int damage;
    Weapon(int damage){
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
enum Item{
    BLACK_POTION(v->(int)(v *1.1),2),WHITE_POTION(v->v+200,3),MUSHROOM(v->v+20,1);
    private IntUnaryOperator op;
    private int priority;
    Item(IntUnaryOperator op, int priority){
        this.op = op;
        this.priority = priority;
    }

    public IntUnaryOperator getOp() {
        return op;
    }

    public int getPriority() {
        return priority;
    }
}
class Player{
    Weapon currentWeapon = Weapon.BARE_HANDS;
    List<Item> items = new ArrayList<>();

    private int totalDamage;
    public void changeWeapon(Weapon weapon){
        currentWeapon = weapon;
    }
    public void useItem(Item item){
        items.add(item);
    }
    public void finishItemEffect(Item item){
        items.remove(item);
    }

    public int getTotalDamage() {
        items.sort(Comparator.comparingInt(Item::getPriority));
        // MUSHROOM이 먼저 공격력이 다 더해지고 그다음에 BLACK 포션으로 전체 공격력의 10퍼센트를 증가시켜준다음에 계산이 끝난 후에
        // WHITE포션으로 공격력 +200을 해주는 식이다.

        IntUnaryOperator op = v -> v;
        // 람다식으로 값이 들어왔을 때 그값을 그대로 리턴해줌.
        // op의 applyAsInt메소드가 어떤 동작을 하는지 정의

        for (Item item : items){
            op = op.andThen(item.getOp());
        }
        return op.applyAsInt(currentWeapon.getDamage());
    }
}


public class DamageCalculationSol {
    public static void main(String[] args) {
        Player player = new Player();
        player.changeWeapon(Weapon.DAGGER);
        System.out.println(player.getTotalDamage());

        player.changeWeapon(Weapon.DRAGON_SLAYER);
        System.out.println(player.getTotalDamage());

        player.useItem(Item.BLACK_POTION);
        player.useItem(Item.WHITE_POTION);
        player.useItem(Item.WHITE_POTION);
        System.out.println(player.getTotalDamage());

        player.finishItemEffect(Item.WHITE_POTION);
        System.out.println(player.getTotalDamage());

        player.useItem(Item.BLACK_POTION);
        player.useItem(Item.BLACK_POTION);
        player.useItem(Item.BLACK_POTION);
        player.useItem(Item.BLACK_POTION);
        System.out.println(player.getTotalDamage());

        player.useItem(Item.MUSHROOM);
        System.out.println(player.getTotalDamage());

        player.changeWeapon(Weapon.LONG_SWORD);
        System.out.println(player.getTotalDamage());
    }
}
