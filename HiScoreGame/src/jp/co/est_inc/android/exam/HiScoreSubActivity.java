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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HiScoreSubActivity extends Activity implements OnClickListener {
    //繰り返し間隔（ミリ秒）
    private final int REPEAT_INTERVAL = 100;
    private double mTimeLimit = 15.0;
    private int mScore = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

	private Button mButton1;
	private Button mButton2;
	private Button mButton3;
	
	private TextView mTextTime;
	private TextView mTextScore;
	
	@Override
	public void onCreate(Bundle saveInstanceState)
	{
		
		super.onCreate(saveInstanceState);
		
		// 画面の xml を指定する。
		setContentView(R.layout.hi_score_sub);

		// TextView 取得
		mTextScore = (TextView)findViewById(R.id.textScore);
		mTextTime = (TextView)findViewById(R.id.textTime);
		
		mButton1 = (Button)findViewById(R.id.button1);
		mButton2 = (Button)findViewById(R.id.button2);
		mButton3 = (Button)findViewById(R.id.button3);
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
		mButton3.setOnClickListener(this);
		
		mRunnable = new Runnable() {
			@Override
			public void run() {

				// 繰り返し処理
				CountDown();
				
				mTimeLimit -= 0.1;
				if (0 <= mTimeLimit) {
					// 次回処理をセット
					mHandler.postDelayed(this, REPEAT_INTERVAL);
				} else {
					ArrayList<Integer> scoreList = ReadHiScore();
					if (scoreList.get(2).intValue() < mScore) {
						
					}
					Intent intent = new Intent(HiScoreSubActivity.this, HiScoreEndActivity.class);
					intent.putExtra( "INTENT_PARAM", String.valueOf(mScore));
					startActivity(intent);
				}
			}
		};
		
		// 初回起動
		mHandler.postDelayed(mRunnable, REPEAT_INTERVAL);
	}
	
	public void onClick(View v) {
		Button wkButton = (Button)v;
		
		switch (wkButton.getId()) {
			case R.id.button1:
				mScore += 10;
				break;
				
			case R.id.button2:
				mScore += 5;
				break;
				
			case R.id.button3:
				mScore += 1;
				break;
				
			default:
				break;
				
		}
		
		mTextScore.setText(String.valueOf(mScore));
	}
	
	private void CountDown() {
		//mTextTime.setText(String.valueOf(mTimeLimit));
		mTextTime.setText(String.format("%.2f", mTimeLimit));
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

}