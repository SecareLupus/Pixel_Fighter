package com.mrru.pixelfighter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TabHost;

import java.util.Vector;


public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        TabHost tabs = (TabHost)findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec sometab = tabs.newTabSpec("attack");
        sometab.setContent(R.id.attack);
        sometab.setIndicator("Attack");
        tabs.addTab(sometab);
        sometab = tabs.newTabSpec("feint");
        sometab.setContent(R.id.feint);
        sometab.setIndicator("Feint");
        tabs.addTab(sometab);
        sometab = tabs.newTabSpec("defend");
        sometab.setContent(R.id.defend);
        sometab.setIndicator("Defend");
        tabs.addTab(sometab);
        game();
    }

    private void game() {
        //Two Fighters
        //Two Combat Logs
        while(true) {
            /**
             * Steps to take each game loop:
             * * Check gamestate
             *   - Check life totals.
             *   - Check body damage.
             *   - Check fatigue totals.
             * * Check each fighter for a "nextTick"
             *   - if so, call both act methods.
             *   - else, request input from each player who must give it then continue;.
             * * Compare current ticks, and alter remaining action ticks if necessary
             * * render()
             *   - Call the render function of each fighter separately, as the methods are swappable.
             */
            int x = 0;
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void checkAttackButton(View v) {
        RadioGroup aim = (RadioGroup)findViewById(R.id.attackAimRadioGroup);
        if (aim.getCheckedRadioButtonId() == -1) {
            return;
        }

        RadioGroup action = (RadioGroup)findViewById(R.id.attackActionRadioGroup);
        if (action.getCheckedRadioButtonId() == -1) {
            return;
        }

        Button fire = (Button)findViewById(R.id.attackButton);
        fire.setEnabled(true);
    }

    public void checkFeintButton(View v) {
        RadioGroup aim = (RadioGroup)findViewById(R.id.feintAimRadioGroup);
        if (aim.getCheckedRadioButtonId() == -1) {
            return;
        }

        RadioGroup action = (RadioGroup)findViewById(R.id.feintActionRadioGroup);
        if (action.getCheckedRadioButtonId() == -1) {
            return;
        }

        Button fire = (Button)findViewById(R.id.feintButton);
        fire.setEnabled(true);
    }

    public void checkDefendButton(View v) {
        RadioGroup aim = (RadioGroup)findViewById(R.id.defendAimRadioGroup);
        if (aim.getCheckedRadioButtonId() == -1) {
            return;
        }

        RadioGroup action = (RadioGroup)findViewById(R.id.defendActionRadioGroup);
        if (action.getCheckedRadioButtonId() == -1) {
            return;
        }

        Button fire = (Button)findViewById(R.id.defendButton);
        fire.setEnabled(true);
    }
}
