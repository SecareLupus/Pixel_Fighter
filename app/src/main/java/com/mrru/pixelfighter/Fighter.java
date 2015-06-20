package com.mrru.pixelfighter;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Vector;

/**
 * Created by desmond on 6/18/2015.
 */
public abstract class Fighter {
    protected int hp;
    protected int max_hp;
    protected int fp;
    protected int max_fp;
    protected FightAction curAct;

    protected Map<Target, Integer> body_hp;
    public Vector<ContinuousEffect> statusFx;

    public Vector<FightAction> availableActions;

    public Fighter(int max_hp, int max_fp) {
        this.max_hp = max_hp;
        this.max_fp = max_fp;
        this.hp = max_hp;
        this.fp = max_fp;

        normalizePoints();
    }

    public Fighter(int hp, int max_hp, int fp, int max_fp) {
        this.hp = hp;
        this.max_hp = max_hp;
        this.fp = fp;
        this.max_fp = max_fp;

        normalizePoints();
    }

    public Fighter copyMe(Fighter clone) {
        Gson gson = new Gson();
        String json = gson.toJson(clone);
        Fighter tmp = gson.fromJson(json, Fighter.class);
        return tmp;
    }

    public boolean act() {
        if (curAct != null && curAct.nextTickExists()) {
            CombatTick curTick = curAct.tick();
        } else {
            curAct = null;
            return false;
        }

        return true;
    }

    public abstract Type render();

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
        normalizePoints();
    }

    public void modHp(int hp) {
        this.hp += hp;
        normalizePoints();
    }

    public int getFp() {
        return fp;
    }

    public void setFp(int fp) {
        this.fp = fp;
        normalizePoints();
    }

    public int getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(int max_hp) {
        this.max_hp = max_hp;
        normalizePoints();
    }

    public int getMax_fp() {
        return max_fp;
    }

    public void setMax_fp(int max_fp) {
        this.max_fp = max_fp;
        normalizePoints();
    }

    public FightAction getCurAct() {
        return curAct;
    }

    public CombatTick getCurTick() {
        return curAct.getCurrentTick();
    }

    public int getCurTickIndex() {
        return curAct.getCurrentTickIndex();
    }

    public void setCurAct(int index) {
        FightAction tmp;
        try {
            tmp = availableActions.get(index);
        } catch (ArrayIndexOutOfBoundsException err) {
            return;
        }
        this.curAct = tmp;
        this.curAct.resetTickIndex();
    }

    public Map<Target, Integer> getBody_hp() {
        return body_hp;
    }

    public void setBody_hp(Map<Target, Integer> body_hp) {
        this.body_hp = body_hp;
        normalizePoints();
    }

    public Vector<ContinuousEffect> getStatusFx() {
        return statusFx;
    }

    public void addStatusFx(ContinuousEffect statusFx) {
        this.statusFx.add(statusFx);
    }

    public Vector<FightAction> getAvailableActions() {
        return availableActions;
    }

    public void addAvailableAction(FightAction addAction) {
        this.availableActions.add(addAction);
    }

    private void normalizePoints() {
        if (max_hp <= 0) {
            max_hp = 1;
        }
        if (max_fp <= 0) {
            max_fp = 1;
        }
        if (hp > max_hp) {
            hp = max_hp;
        }
        if (hp < 0) {
            hp = 0;
        }
        if (fp > max_fp) {
            fp = max_fp;
        }
        if (fp < 0) {
            fp = 0;
        }
        for (Map.Entry<Target, Integer> entry : body_hp.entrySet()) {
            if (entry.getValue() > max_hp) {
                body_hp.put(entry.getKey(), max_hp);
            }
        }
    }
}
