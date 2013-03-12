package com.example.lostandfound;

import java.io.IOException;
import java.util.ArrayList;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.util.Account;
import com.example.lostandfound.util.Admin;
import com.example.lostandfound.util.Item;
import com.example.lostandfound.util.ItemDatabaseUtility;
import com.example.lostandfound.util.UserDatabaseUtility;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.lostandfound.LoginActivity.MESSAGE";

	/**
	 * A dummy authentication store containing known user names and passwords.
	 * TODO: remove after connecting to a real authentication system.
	 */
	//private static String[] DUMMY_CREDENTIALS = new String[] {
		//	"foo@example.com:hello", "bar@example.com:world" };

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	private Activity thisActivity;

	public static ArrayList<Account> accounts;
	public static ArrayList<Item> items;
	public static Account curAcc;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		//TODO change the code below to use accounts arraylist instead of Dummy_credentials
		setContentView(R.layout.activity_login);
		
		//----------
/*		Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
		toast.show();
		if(message!=null){
			String[] newCreds = new String[DUMMY_CREDENTIALS.length+1];
			int i;
			for(i = 0;i<DUMMY_CREDENTIALS.length;i++){
				newCreds[i] = DUMMY_CREDENTIALS[i];
			}
			newCreds[DUMMY_CREDENTIALS.length]=message;
			DUMMY_CREDENTIALS=newCreds;
		}
		//
		toast = Toast.makeText(this, Arrays.toString(DUMMY_CREDENTIALS), Toast.LENGTH_LONG);
		toast.show();*/
		if(MainActivity.firstTime){
		/*	UserDatabaseUtility udu= new UserDatabaseUtility();
			try {
				accounts = udu.readFromUserDB();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ItemDatabaseUtility idu= new ItemDatabaseUtility();
			try {
				items = idu.readFromUserDB();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			MainActivity.firstTime=false;
			accounts = new ArrayList<Account>();
			Admin defaultAdmin = new Admin();
			defaultAdmin.setUsername("admin@admin.com");
			defaultAdmin.setPassword("admin");
			accounts.add(defaultAdmin);
			items = new ArrayList<Item>();
			
		}
		
		
		
		
		
		thisActivity = this;
		// Set up the login form.
		mEmail = getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.email);
		mEmailView.setText(mEmail);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordView.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordView;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailView.setError(getString(R.string.error_invalid_email));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
			showProgress(true);
			mAuthTask = new UserLoginTask();
			mAuthTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			try {
				// Simulate network access.
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				return false;
			}

			for (Account credential : accounts) {
				if (credential.getUsername().equals(mEmail)) {
					// Account exists, return true if the password matches.
					//Toast t = Toast.makeText(thisActivity, credential.getPassword()+":"+mPassword, Toast.LENGTH_LONG);
					//t.show();
					return credential.getPassword().equals(mPassword)&&!credential.isLocked;
				}
			}
 
			// TODO: register the new account here.
			Intent intent = new Intent(thisActivity, RegistrationActivity.class);
			//EditText editText = (EditText) findViewById(R.id.edit_message);
			//String message = editText.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, mEmail+":"+mPassword);
			startActivity(intent);
			return false;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			showProgress(false);

			if (success) {
				for (Account credential : accounts) {
					if (credential.getUsername().equals(mEmail)) {
						curAcc = credential; 
					}
				}
				if(curAcc.getAccountType().equals("User")&&!curAcc.isLocked){
					Intent intent = new Intent(thisActivity, HomeActivity.class);
					//EditText editText = (EditText) findViewById(R.id.edit_message);
					//String message = editText.getText().toString();
					//intent.putExtra(EXTRA_MESSAGE, mEmail+":"+mPassword);
					startActivity(intent);
				}else if(curAcc.getAccountType().equals("Admin")){
					Intent intent = new Intent(thisActivity, AdminPanelActivity.class);
					//EditText editText = (EditText) findViewById(R.id.edit_message);
					//String message = editText.getText().toString();
					//intent.putExtra(EXTRA_MESSAGE, mEmail+":"+mPassword);
					startActivity(intent);
				}
			} else {
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
				for (Account credential : accounts) {
					if (credential.getUsername().equals(mEmail)) {
						credential.addTry();
						if(credential.isLocked){
							Toast t = Toast.makeText(thisActivity, "Your Account Is Locked", Toast.LENGTH_LONG);
							t.show();
						}
					}
				}
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}
}
