package com.nibmglobal.nibm.Utilities;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.nibmglobal.nibm.ui.student_activities.studymaterial.detail.ChapterDetailActivity;

public class DownloadFile extends AsyncTask<String, Integer, Void> {
		
		private String fileUrl;
		private String fileName;
		private final int MEGABYTE = 1024*1024;
		private Context cntxt;
		private ProgressDialog dialog ;
		private File fileForWrite;
		private int fileFormat;


		public DownloadFile(Context cntxt, int fileFormat) {
			super();
			this.cntxt = cntxt;
			dialog = new ProgressDialog(cntxt);
			this.fileFormat = fileFormat;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			try {
				dialog.setIndeterminate(false);
				dialog.setMax(100);
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);
				dialog.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(String... strings) {
			fileUrl = strings[0]; //
			fileName = strings[1]; //
//			userId = strings[2];

			fileForWrite = new File(Util.getUtils().pathForWriteDoc(), fileName);


			try {
				fileForWrite.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			int bufferLength;
			try {

				URL url = new URL(fileUrl);
				URLConnection urlConnection = url.openConnection();
				/*HttpURLConnection urlConnection = (HttpURLConnection) url
						.openConnection();*/
				urlConnection.connect();
				int totalSize = urlConnection.getContentLength();


				InputStream inputStream = new BufferedInputStream(url.openStream());
//				urlConnection.getInputStream();

				OutputStream fileOutputStream = new FileOutputStream(fileForWrite);
				byte buffer[] = new byte[MEGABYTE];
				int total = 0;

//				FileOutputStream fileOutputStream = new FileOutputStream(fileForWrite);

				while ((bufferLength = inputStream.read(buffer)) != -1) {
					total += bufferLength;
//					Log.d("hai", total * 100 / totalSize +"");
					publishProgress((int) ((total * 100) / totalSize));
					fileOutputStream.write(buffer, 0, bufferLength);

				}

				fileOutputStream.flush();
				fileOutputStream.close();
				inputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// downloadFile(fileUrl, pdfFile, getActivity());

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			try {
				if (dialog.isShowing()) {
					dialog.dismiss();
				}
				switch (fileFormat) {
					case 0:
						Util.getUtils().showDocFile(fileForWrite.getPath(), cntxt);
						break;
					case 1:
						Util.getUtils().showPdfFile(fileForWrite.getPath(), cntxt);
						break;
					case 2:
//						ChapterDetailActivity.loadView("file://"+fileForWrite.getPath());
						break;
					default:
						break;
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(progress);

			dialog.setProgress(progress[0]);
		}
	}