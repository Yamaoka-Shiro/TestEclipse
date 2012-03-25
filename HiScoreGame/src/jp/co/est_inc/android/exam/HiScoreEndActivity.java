package jp.co.est_inc.android.exam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HiScoreEndActivity extends Activity implements OnClickListener {
	private Button mMainButton;
	private Button mEndButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hi_score_end);
		
		mMainButton = (Button)findViewById(R.id.btnMain);
		mEndButton = (Button)findViewById(R.id.btnEnd);
		
		ArrayList<Integer> scoreList = ReadHiScore();
		scoreList.add(Integer.valueOf(getIntent().getExtras().getString("INTENT_PARAM")));
		
		// è∏èáÇ≈É\Å[Ég
		Object[] objs = scoreList.toArray();
		Arrays.sort(objs);
		
		WriteHiScore(objs);
		
		mMainButton.setOnClickListener(this);
		mEndButton.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		Button wkButton = (Button)v;
		
		switch (wkButton.getId()) {
			case R.id.btnMain:
				Intent intent = new Intent(HiScoreEndActivity.this, HiScoreGameActivity.class);
				startActivity(intent);
				break;
				
			case R.id.btnEnd:
				android.os.Process.killProcess(android.os.Process.myPid());
				//finish();
				break;
				
			default:
				break;
				
		}
		
	}
	
	private ArrayList<Integer> ReadHiScore() {
		ArrayList<Integer> scoreList = new ArrayList<Integer>();

		try {
			InputStream in = openFileInput("HiScore.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String s;
			while ((s = reader.readLine())!= null) {
				scoreList.add(Integer.valueOf(s));
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

		return scoreList;
	}
	
	private void WriteHiScore(Object[] objs) {
		
        try {
            FileOutputStream fileOutputStream = openFileOutput("HiScore.txt", MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(fileOutputStream, "UTF-8"));
            for (int i = objs.length - 1; 0 <= i; i--) {
            	if (9 < i) {
            		continue;
            	}
            	//fileOutputStream.write(String.valueOf(objs[i]).getBytes());
            	writer.append(String.valueOf(objs[i]) + "\n");
            }
            
            writer.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
	}
}
