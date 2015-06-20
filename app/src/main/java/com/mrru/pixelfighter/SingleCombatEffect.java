package com.mrru.pixelfighter;

/**
 * Created by desmond on 6/18/2015.
 */
public class SingleCombatEffect {
    private Fighter effectiveAgainst;
    private Target targetted = Target.MID;
    private int FP_mod = 0;
    private int HP_mod = 0;
    private int TH_mod = 0;
    private boolean effectLocked = false;

    public SingleCombatEffect(Fighter usedAgainst) {
        this.effectiveAgainst = usedAgainst;
    }

    public SingleCombatEffect(Fighter usedAgainst, Target targetted) {
        this.effectiveAgainst = usedAgainst;
        this.targetted = targetted;
    }

    public Target getTarget() {
        return targetted;
    }

    public void setTarget(Target targetted) {
        if (!effectLocked)
            this.targetted = targetted;
    }

    public int getFP_mod() {
        return FP_mod;
    }

    public void setFP_mod(int FP_mod) {
        if (!effectLocked)
            this.FP_mod = FP_mod;
    }

    public int getHP_mod() {
        return HP_mod;
    }

    public void setHP_mod(int HP_mod) {
        if (!effectLocked)
            this.HP_mod = HP_mod;
    }

    public int getTH_mod() {
        return TH_mod;
    }

    public void setTH_mod(int TH_mod) {
        if (!effectLocked)
            this.TH_mod = TH_mod;
    }

    public boolean isEffectLocked() {
        return effectLocked;
    }

    public void lockEffect() {
        effectLocked = true;
    }
}
