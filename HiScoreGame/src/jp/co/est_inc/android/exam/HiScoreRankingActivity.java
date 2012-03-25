package jp.co.est_inc.android.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class HiScoreRankingActivity extends Activity implements OnClickListener  {
	Button mMainButton;
	EditText mEditText;
	
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		
		// âÊñ ÇÃ xml ÇéwíËÇ∑ÇÈÅB
		setContentView(R.layout.hi_score_ranking);
		
		mMainButton = (Button)findViewById(R.id.rankMainButton);
		mMainButton.setOnClickListener(this);
		
		mEditText = (EditText)findViewById(R.id.editText1);
		
		DisplayRanking();
	}
	
	@Override
	public void onClick(View v) {
		Button wkButton = (Button)v;
		
		switch (v.getId()) {
			case R.id.rankMainButton:
				Intent intent = new Intent(HiScoreRankingActivity.this, HiScoreGameActivity.class);
				startActivity(intent);

				break;
		}
	}

	private void DisplayRanking() {

		ArrayList<String> scoreList = new ArrayList<String>();

		try {
			InputStream in = openFileInput("HiScore.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String s;
			while ((s = reader.readLine())!= null) {
				scoreList.add(s);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
		
		Object[] objs = scoreList.toArray();
		for (int i = 0; i < objs.length; i++) {
			mEditText.append("ëÊ" + String.valueOf(i + 1) + "à \t" + objs[i].toString() + "ì_\n");
		}
		
	}
}
