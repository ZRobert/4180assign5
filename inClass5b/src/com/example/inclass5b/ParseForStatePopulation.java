package com.example.inclass5b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ParseForStatePopulation extends AsyncTask<String, Void, String> {
				String result = null;
			Bundle bundle = new Bundle();
			Message msg = new Message();
			Handler h;
			MainActivity activity;
			String urlString;
			
			public ParseForStatePopulation(Handler handler, MainActivity activity){
				this.h = handler;
				this.activity = activity;
			}
			
			public ParseForStatePopulation(Handler handler, Activity activity) {
				// TODO Auto-generated constructor stub
				this.activity = activity;
				this.h = handler;
			}

			private void sendMessage(String key, String msg){
				Bundle bundle = new Bundle();
				Message message = new Message();
				bundle.putString(key, msg);
				message.setData(bundle);
				this.h.sendMessage(message);
			}
			
			
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.d("in async", "do in background");

			try {
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect();
				int statusCode = con.getResponseCode();
				if (statusCode == HttpURLConnection.HTTP_OK) {
					Log.d("in async", "connection ok");
					BufferedReader in = new BufferedReader(new InputStreamReader(
							con.getInputStream()));
						result = in.readLine();

						Log.d("DoInBackGround",result + "");
						bundle.putString("status", result);
					in.close();
					con.disconnect();
		
				}
			} catch (MalformedURLException e) {
				bundle.putString("ERROR", "Problem with URL");
				Log.d("Error", "Problem with URL");
			} catch (IOException e) {
				bundle.putString("ERROR", "Problem with connection");
				Log.d("Error", "Problem with connection");
			}
			msg.setData(bundle);
			//h.sendMessage(msg);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			this.activity.handleResponse(result);
			
			super.onPostExecute(result);
			Log.d("in on post Execute", "Result:" + result);
		}

	}
