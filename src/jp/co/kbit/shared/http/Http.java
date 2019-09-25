package jp.co.kbit.shared.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Http {
	public static HttpResponse doPost(
			String strURL
			,Map<String, String> mapReqHeaders
			,String strReqBody
			)
	{
		HttpURLConnection urlConn = null;

		StringBuffer result = new StringBuffer();
		HttpResponse res = new HttpResponse();
		
		try {
			// URLオブジェクト作成
			URL url = new URL(strURL);
			
			// URLオブジェクトからHttpURLConnectionオブジェクトを作成
			urlConn = (HttpURLConnection) url.openConnection();
			
			// メソッド
			urlConn.setRequestMethod("POST");
			
			// Header準備
			for(Map.Entry<String, String> pair : mapReqHeaders.entrySet()) {
				urlConn.addRequestProperty(pair.getKey(), pair.getValue());
			}
			
			// 出力を設定
			urlConn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlConn.getOutputStream());
			out.write(strReqBody);
			out.close();
			
			// 接続
			urlConn.connect();
			
			// 送信結果
			res.setiStatus(urlConn.getResponseCode());
			
			// 成功ならば
			if(HttpURLConnection.HTTP_OK == res.getiStatus()) {
				// レスポンスを取得
				InputStream in = urlConn.getInputStream();
				String encoding = urlConn.getContentEncoding();
				if ( null == encoding) {
					encoding = "UTF-8";
				}
				InputStreamReader inReader = new InputStreamReader(in, encoding);
				BufferedReader bufReader = new BufferedReader(inReader);
				
				// レスポンスがなくなるまで取得
				String line = null;
				while((line = bufReader.readLine()) != null) {
					result.append(line);
				}
				
				// ストリームをクローズ
				bufReader.close();
				inReader.close();
				in.close();
				
				// 出力結果を結果変数に格納
				res.setstrResBody(result.toString());
			}
			// 失敗ならば
			else
			{
				// なにもしない
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if( null != urlConn)
			{
				urlConn.disconnect();
			}
		}
		
		// 結果を返却
		return res;
	}
}
