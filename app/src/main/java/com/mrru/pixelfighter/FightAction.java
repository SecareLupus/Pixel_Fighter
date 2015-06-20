package com.mrru.pixelfighter;

import com.google.gson.Gson;

import java.util.Vector;

/**
 * Created by desmond on 6/18/2015.
 */
public abstract class FightAction {
    private Vector<Target> req;
    private Vector<CombatTick> ticks;
    protected Vector<CombatEffects> fx = new Vector<>(ticks.size());
    protected CombatEffects onHit = new CombatEffects(0);
    protected CombatEffects onMiss = new CombatEffects(0);
    private int i = 0;

    public FightAction(Vector<CombatTick> map) {
        Gson gson = new Gson();
        ticks = gson.fromJson(gson.toJson(map), map.getClass());
        fx = new Vector<>(ticks.size());
    }

    public FightAction(Vector<CombatTick> map, Vector<CombatEffects> effects) {
        Gson gson = new Gson();
        ticks = gson.fromJson(gson.toJson(map), map.getClass());
        fx = new Vector<>(ticks.size());
        if (effects.size() <= fx.capacity()) {
            fx = gson.fromJson(gson.toJson(effects), effects.getClass());
        }
    }

    public FightAction copyMe(Fighter clone) {
        Gson gson = new Gson();
        FightAction tmp = gson.fromJson(gson.toJson(clone), FightAction.class);
        tmp.resetTickIndex();
        return tmp;
    }

    public FightAction copyMe(Fighter clone, boolean maintain_i) {
        Gson gson = new Gson();
        FightAction tmp = gson.fromJson(gson.toJson(clone), FightAction.class);
        if (!maintain_i) {
            tmp.resetTickIndex();
        }
        return tmp;
    }

    public abstract boolean hitCondition();
    public abstract boolean failCondition();

    public Vector<CombatTick> getTicks() {
        return ticks;
    }

    public Vector<CombatEffects> getEffects() {
        return fx;
    }

    public Vector<Target> getReqs() {
        return req;
    }

    public boolean isReq(Target t) {
        return req.contains(t);
    }

    public void addReq(Target t) {
        if (!isReq(t)) {
            req.add(t);
        }
    }

    public void revReq(Target t) {
        if(isReq(t)) {
            req.remove(t);
        }
    }

    public boolean setFX(Vector<CombatEffects> effects) {
        if (effects.size() <= ticks.size()) {
            Gson gson = new Gson();
            fx = gson.fromJson(gson.toJson(effects), effects.getClass());
            return true;
        }
        return false;
    }

    public boolean setSingleEffect(int index, SingleCombatEffect ce) {
        if (index <= ticks.size() && index <= fx.capacity()) {
            fx.get(index).add(ce);
            return true;
        }
        return false;
    }

    public int getCurrentTickIndex() {
        return i;
    }

    public CombatTick getCurrentTick() {
        return ticks.get(i);
    }

    public void resetTickIndex() {
        i = 0;
    }

    public boolean nextTickExists() {
        try {
            CombatTick nxtTck = ticks.get(i+1);
        } catch (ArrayIndexOutOfBoundsException err) {
            return false;
        }
        return true;
    }

    public CombatTick tick() {
        if (nextTickExists()) {
            i++;
            return getCurrentTick();
        }
        return null;
    }

    public boolean combatFxExists() {
        try {
            CombatEffects curFX = fx.get(i);
        } catch (ArrayIndexOutOfBoundsException err) {
            return false;
        }
        return true;
    }

    public CombatEffects getCombatFx() {
        if (combatFxExists()) {
            return fx.get(i);
        }
        return null;
    }

    protected void modTicks(int startIndex, CombatTick swapTo, boolean inclusive) {
        CombatTick swappedFrom = ticks.get(startIndex);
        if (!inclusive)
            startIndex++;
        for (int j = startIndex; j < ticks.size();j++) {
            if (ticks.get(j) != swappedFrom) {
                break;
            }
            ticks.set(j, swapTo);
        }
    }

    protected void modTicks(int startIndex, CombatTick swapTo, boolean inclusive, int length) {
        if (!inclusive)
            startIndex++;
        for (int j = 0; j < length && (startIndex + j < ticks.size()); j++) {
            ticks.set(startIndex + j, swapTo);
        }
    }

    protected void modRemainingTicks(int startIndex, CombatTick swapTo, boolean inclusive) {
        if (!inclusive)
            startIndex++;
        for (int j = startIndex; j < ticks.size(); j++) {
            ticks.set(j, swapTo);
        }
    }

    protected void trimRemainingTicks(int startIndex, int additional) {
    }
}
