package com.mrru.pixelfighter;

import java.util.Vector;

/**
 * Created by desmond on 6/18/2015.
 * CombatEffects is a subtype of Vector, specialized to hold objects of the SingleCombatEffect
 * type. Any given tick of a FightAction may have a CombatEffect associated with it.
 */
public class CombatEffects extends Vector<SingleCombatEffect> {
    public CombatEffects(SingleCombatEffect ce) {
        super(1);
        this.add(ce);
    }

    public CombatEffects(Vector<SingleCombatEffect> ce) {
        super();
        this.addAll(ce);
    }

    public CombatEffects(int cap) {
        super(cap);
    }
}
