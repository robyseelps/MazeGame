package sk.tuke.kpi.kp.mazegame.Actors;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private List<FatigueEffect> effectList;
    private int maxHp;
    private boolean dead;
    private int hp;
    public Health(int startHp,int maxHp){
        hp = startHp;
        this.maxHp = maxHp;
        effectList = new ArrayList<>();
        dead = false;
    }
    public Health(int maxHp){
        hp = maxHp;
        this.maxHp = maxHp;
        effectList = new ArrayList<>();
        dead = false;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void refill(int amount){
        hp += amount;
        if(hp > maxHp)
            hp = maxHp;
    }

    public void restore(){
        hp = maxHp;
    }
    public void drain(int amount) {
        hp -= amount;
        if(hp <= 0 ) {
            hp = 0;
            if (!dead) {
                hp = 0;
                dead = true;
                effectList.forEach(FatigueEffect::apply);
            }
        }
    }
    public void kill(){
        hp = 0;
        if (!dead){
            dead = true;
            effectList.forEach(FatigueEffect::apply);
        }
    }
    @FunctionalInterface
    public interface FatigueEffect {
        void apply();
    }
    public void onFatigued(FatigueEffect effect){
        effectList.add(effect);
    }
}
