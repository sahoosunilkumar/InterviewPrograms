package interviewquestions.sunilsahoo.com.interviewquestions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class HeadlessFragment extends Fragment {
	
	public static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";

	public static interface TaskStatusCallback {
		void onPreExecute();

		void onProgressUpdate(int progress);

		void onPostExecute();

		void onCancelled();
	}
	
	TaskStatusCallback mStatusCallback;
	BackgroundTask mBackgroundTask;
	boolean isTaskExecuting = false;
	
	/**
	 * Called when a fragment is first attached to its activity. 
	 * onCreate(Bundle) will be called after this.
	 */
	@Override
	public void onAttach(Context activity) {
		super.onAttach(activity);
		mStatusCallback = (TaskStatusCallback)activity;
	}
	
	/**
	 * Called to do initial creation of a fragment. 
	 * This is called after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);
	}
	
	/**
	 * Called when the fragment is no longer attached to its activity. This is called after onDestroy().
	 */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		mStatusCallback = null;
	}

	private class BackgroundTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected void onPreExecute() {
			if(mStatusCallback != null)
				mStatusCallback.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			int progress = 0;
			while(progress < 100 && !isCancelled()){
				progress++;
				publishProgress(progress);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if(mStatusCallback != null)
				mStatusCallback.onPostExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			if(mStatusCallback != null)
				mStatusCallback.onProgressUpdate(values[0]);
		}

		@Override
		protected void onCancelled(Void result) {
			if(mStatusCallback != null)
				mStatusCallback.onCancelled();
		}

	}

	public void startBackgroundTask() {
		if(!isTaskExecuting){
			mBackgroundTask = new BackgroundTask();
			mBackgroundTask.execute();
			isTaskExecuting = true;
		}
	}

	public void cancelBackgroundTask() {
		if(isTaskExecuting){
			mBackgroundTask.cancel(true);
			isTaskExecuting = false;
		}		
	}
	
	public void updateExecutingStatus(boolean isExecuting){
		this.isTaskExecuting = isExecuting;
	}

}
