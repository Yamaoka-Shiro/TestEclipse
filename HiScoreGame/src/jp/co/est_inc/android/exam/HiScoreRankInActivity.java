package jp.co.est_inc.android.exam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HiScoreRankInActivity extends Activity implements OnClickListener {

	Button mButton1;
	Button mButton2;
	
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		
		// âÊñ ÇÃ xml ÇéwíËÇ∑ÇÈÅB
		setContentView(R.layout.hi_score_rank_in);
		
		mButton1 = (Button)findViewById(R.id.buttonRegist);
		mButton2 = (Button)findViewById(R.id.buttonCancel);
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);


	}
	
	public void onClick(View v) {
		Button wkButton = (Button)v;
		
		switch (wkButton.getId()) {
			case R.id.buttonRegist:
				break;
			
			case R.id.buttonCancel:
				break;
			
		}
	}
}
