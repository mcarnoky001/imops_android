package com.capps.smartbus;

import android.app.Activity;
import android.lib.recaptcha.ReCaptcha;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import AssyncTasks.EmailVerify;
import AssyncTasks.Send;
import AssyncTasks.VerifyName;

public class Register extends Activity implements View.OnClickListener,
		ReCaptcha.OnShowChallengeListener, ReCaptcha.OnVerifyAnswerListener {
	private static final String PUBLIC_KEY = "6LemPQMTAAAAAHs4o7CPxzZOHtVJTBnlGzER050p";
	private static final String PRIVATE_KEY = "6LemPQMTAAAAAFBl3EXFg_2hGm76QI_-619xp2fn";

	private ReCaptcha reCaptcha;
	private ProgressBar progress;
	private EditText answer;
	private EditText email;
	private EditText name;
	private EditText pass;
	private VerifyName obj;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_register);

		this.reCaptcha = (ReCaptcha) this.findViewById(R.id.recaptcha);
		this.progress = (ProgressBar) this.findViewById(R.id.progress);
		this.answer = (EditText) this.findViewById(R.id.answer);
		this.name = (EditText) this.findViewById(R.id.editText1);
		this.pass = (EditText) this.findViewById(R.id.editText2);
		this.email = (EditText) this.findViewById(R.id.editText3);

		this.findViewById(R.id.verify1).setOnClickListener(this);
		this.findViewById(R.id.reload1).setOnClickListener(this);

		this.showChallenge();
	}

	@Override
	public void onClick(final View view) {
		switch (view.getId()) {
		case R.id.verify1:
			try {
				this.VerifyInput();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case R.id.reload1:
			this.showChallenge();

			break;
		}
	}

	@Override
	public void onChallengeShown(final boolean shown) {
		this.progress.setVisibility(View.GONE);

		if (shown) {
			// If a CAPTCHA is shown successfully, displays it for the user to
			// enter the words
			this.reCaptcha.setVisibility(View.VISIBLE);
		} else {
			Toast.makeText(this, R.string.show_error, Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public void onAnswerVerified(final boolean success) {
		if (success) {
			try {
				Toast.makeText(this, "Name is not used", Toast.LENGTH_SHORT)
						.show();
				if (new Send(this).execute(name.getText().toString(),
						pass.getText().toString(), email.getText().toString())
						.get()) {
					Toast.makeText(this, "Data inserted", Toast.LENGTH_LONG)
							.show();
					onBackPressed();
				}

			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, R.string.verification_failed,
					Toast.LENGTH_SHORT).show();
			this.showChallenge();
		}

	}

	private void VerifyInput() throws InterruptedException, ExecutionException {
		String sprava = email.getText().toString();
		boolean ok = true;
		Pattern pattern = Patterns.EMAIL_ADDRESS;
		if (!pattern.matcher(sprava).matches()) {
			Toast.makeText(this, "Bad email adress form", Toast.LENGTH_SHORT)
					.show();
			this.showChallenge();
			email.setText("");
			pass.setText("");
			ok = false;
		}
		if (name.getText().toString().length() < 5) {
			Toast.makeText(this, "Login name must has at least 5 characters",
					Toast.LENGTH_SHORT).show();
			this.showChallenge();
			name.setText("");
			pass.setText("");
			ok = false;

		}
		if (new EmailVerify(Register.this).execute(email.getText().toString())
				.get()) {
			ok = false;
			Toast.makeText(this, "Email still belongs to registered account",
					Toast.LENGTH_SHORT).show();
		}
		Log.e("name", name.getText().toString());
		if (new VerifyName(this).execute(name.getText().toString()).get()) {
		} else {
			ok = false;
			Toast.makeText(this, "Name is still used", Toast.LENGTH_SHORT)
					.show();
		}
		if (pass.getText().toString().length() < 8) {
			Toast.makeText(this, "Password must has at least 8 characters",
					Toast.LENGTH_SHORT).show();
			this.showChallenge();
			pass.setText("");
			ok = false;
		}
		if (ok == true) {
			verifyAnswer();
		} else {
			ok = true;
			showChallenge();
		}
	}

	private void showChallenge() {
		// Displays a progress bar while downloading CAPTCHA
		this.progress.setVisibility(View.VISIBLE);
		this.reCaptcha.setVisibility(View.GONE);

		this.reCaptcha.showChallengeAsync(Register.PUBLIC_KEY, this);
	}

	private void verifyAnswer() {
		if (TextUtils.isEmpty(this.answer.getText())) {
			Toast.makeText(this, R.string.instruction, Toast.LENGTH_SHORT)
					.show();
		} else {
			// Displays a progress bar while submitting the answer for
			// verification
			this.progress.setVisibility(View.VISIBLE);
			this.reCaptcha.verifyAnswerAsync(Register.PRIVATE_KEY, this.answer
					.getText().toString(), this);
		}
	}
}
