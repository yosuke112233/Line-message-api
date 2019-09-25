package jp.co.kbit.shared.line.api.messaging;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.kbit.shared.http.*;
import jp.co.kbit.shared.line.api.messaging.obj.LinePushRequestObj;
import jp.co.kbit.shared.line.api.messaging.obj.MessageObj_Text;

public class LinePush {
	private static final String REQUEST_URL = "https://api.line.me/v2/bot/message/push" ;
	private String strChannelAccessToken;
	
	public LinePush(){
		this.strChannelAccessToken = System.getenv("LINE_CHANNEL_ACCESS_TOKEN");
	}
	
	public boolean doPushText(String strToUserID, String strSendText)throws Exception
	{
		// RequestHeaderを設定
		HashMap <String, String> mapHeaders = new HashMap<String, String>();
		mapHeaders.put("Content-Type", "application/json");
		
		// アクセストークンを設定されていなかったときは例外発生
		if(this.strChannelAccessToken == "") throw new Exception("Not Set Exception");
		
		mapHeaders.put("Authorization", "Bearer " + strChannelAccessToken);
		
		// リクエストボディ作成
		LinePushRequestObj lpro = new LinePushRequestObj();
		lpro.to = strToUserID;
		MessageObj_Text  mot[] = new MessageObj_Text [1];
		mot[0] = new MessageObj_Text();
		mot[0].text = strSendText;
		lpro.messages = mot;
		
		ObjectMapper mapper = new ObjectMapper();
		String strReqBody = mapper.writeValueAsString(lpro);
		
		HttpResponse res = Http.doPost(REQUEST_URL, mapHeaders, strReqBody);
		
		return res.getiStatus() == 200;
	}
	
}
