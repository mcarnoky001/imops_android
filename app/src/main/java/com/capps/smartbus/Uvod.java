package com.capps.smartbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Uvod extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uvod);
		runThread();
	}

	private void runThread() {

		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000);
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							animacia();
						}
					});

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void animacia() {
		final ImageView obrazok = (ImageView) findViewById(R.id.imageView1);
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(this,
				R.anim.fadein);
		final Animation myFadeOUTAnimation = AnimationUtils.loadAnimation(this,
				R.anim.fadeout);
		obrazok.startAnimation(myFadeInAnimation); // Set animation to your
													// ImageView
		myFadeInAnimation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				obrazok.startAnimation(myFadeOUTAnimation);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		});

		myFadeOUTAnimation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				Intent launchactivity = new Intent(Uvod.this,
						MainActivity.class);
				startActivity(launchactivity);
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		});

	}
}
