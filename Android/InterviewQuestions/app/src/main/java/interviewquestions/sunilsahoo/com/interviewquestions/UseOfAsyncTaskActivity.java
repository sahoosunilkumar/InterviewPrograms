package interviewquestions.sunilsahoo.com.interviewquestions;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class UseOfAsyncTaskActivity extends Activity implements HeadlessFragment.TaskStatusCallback,
		OnClickListener {

	private HeadlessFragment mFragment;
	private ProgressBar mProgressBar;
	private TextView mProgressvalue;

	/**
	 * Called when activity is starting. Most initialization part is done here.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.useofasynctaskactivity_main);

		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
		mProgressvalue = (TextView) findViewById(R.id.progressValue);

		if (savedInstanceState != null) {
			int progress = savedInstanceState.getInt("progress_value");
			mProgressvalue.setText(progress + "%");
			mProgressBar.setProgress(progress);
		}

		FragmentManager mMgr = getFragmentManager();
		mFragment = (HeadlessFragment) mMgr
				.findFragmentByTag(HeadlessFragment.TAG_HEADLESS_FRAGMENT);

		if (mFragment == null) {
			mFragment = new HeadlessFragment();
			mMgr.beginTransaction()
					.add(mFragment, HeadlessFragment.TAG_HEADLESS_FRAGMENT)
					.commit();
		}
	}

	/**
	 * This method is called before an activity may be killed Store info in
	 * bundle if required.
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("progress_value", mProgressBar.getProgress());
	}

	// Background task Callbacks

	@Override
	public void onPreExecute() {
		Toast.makeText(getApplicationContext(), "onPreExecute",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onPostExecute() {
		Toast.makeText(getApplicationContext(), "onPostExecute",
				Toast.LENGTH_SHORT).show();
		if (mFragment != null)
			mFragment.updateExecutingStatus(false);
	}

	@Override
	public void onCancelled() {
		Toast.makeText(getApplicationContext(), "onCancelled",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProgressUpdate(int progress) {
		mProgressvalue.setText(progress + "%");
		mProgressBar.setProgress(progress);
	}

	/**
	 * Called when a view has been clicked
	 */
	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.start:
			if (mFragment != null)
				mFragment.startBackgroundTask();

			break;
		case R.id.cancel:
			if (mFragment != null)
				mFragment.cancelBackgroundTask();

			break;
		case R.id.recreate:
			/**
			 * Cause this Activity to be recreated with a new instance. This
			 * results in essentially the same flow as when the Activity is
			 * created due to a configuration change the current instance will
			 * go through its lifecycle to onDestroy and a new instance then
			 * created after it.
			 */
			recreate();
			break;
		}
	}
}
