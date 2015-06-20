package com.mrru.pixelfighter;

/**
 * Created by desmond on 6/19/2015.
 */
public class ContinuousEffect {
    String name = "";
    String desc = "";

    private int maxHP_limit = -1;
    private int maxHP_mod = 0;
    private int maxFP_limit = -1;
    private int maxFP_mod = 0;

    public ContinuousEffect(String newname) {
        name = newname;
    }
    public ContinuousEffect(String newname, String newdesc) {
        name = newname;
        desc = newdesc;
    }

    public int getMaxHP_limit() {
        return maxHP_limit;
    }

    public void setMaxHP_limit(int maxHP_limit) {
        this.maxHP_limit = maxHP_limit;
    }

    public int getMaxHP_mod() {
        return maxHP_mod;
    }

    public void setMaxHP_mod(int maxHP_mod) {
        this.maxHP_mod = maxHP_mod;
    }

    public int getMaxFP_limit() {
        return maxFP_limit;
    }

    public void setMaxFP_limit(int maxFP_limit) {
        this.maxFP_limit = maxFP_limit;
    }

    public int getMaxFP_mod() {
        return maxFP_mod;
    }

    public void setMaxFP_mod(int maxFP_mod) {
        this.maxFP_mod = maxFP_mod;
    }

    public class CustomPointsBar extends ContinuousEffect {
        int points;
        int max_points;

        public CustomPointsBar(String newname) {
            super(newname);
        }

        public CustomPointsBar(String newname, String newdesc) {
            super(newname, newdesc);
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
            normalizePoints();
        }

        public void modPoints(int mod) {
            this.points += mod;
            normalizePoints();
        }

        public int getMax_points() {
            return max_points;
        }

        public void setMax_points(int max_points) {
            this.max_points = max_points;
            normalizePoints();
        }

        private void normalizePoints() {
            if (this.points > this.max_points) {
                this.points = this.max_points;
            }
            if (this.points < 0) {
                this.points = 0;
            }
        }
    }
}
