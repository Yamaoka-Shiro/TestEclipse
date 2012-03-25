package jp.co.est_inc.android.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HiScoreGameActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        findViewById(R.id.buttonStart).setOnClickListener(this);
        findViewById(R.id.buttonRanking).setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
    	Button wkButton = (Button)v;
    	
    	switch (wkButton.getId()) {
	    	case R.id.buttonStart:
	    		Intent intent = new Intent(HiScoreGameActivity.this, HiScoreSubActivity.class);
	    		startActivity(intent);
	    		
	    		break;
	    	case R.id.buttonRanking:
	    		Intent intent2 = new Intent(HiScoreGameActivity.this, HiScoreRankingActivity.class);
	    		startActivity(intent2);
	    		break;
    		default:
    			break;
    	}
    }
    
}