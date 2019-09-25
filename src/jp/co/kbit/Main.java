package jp.co.kbit;

import jp.co.kbit.shared.line.api.messaging.LinePush;

public class Main {

	public static void main(String[] args) {
		// サンプルの使い勝手のためコマンドライン引数を取得
		if(args.length != 1) {
			System.out.println("Usage: java main [SendMessage]");
			return;
		}
		
		//	Lineプッシュ用オブジェクト作成
		LinePush lp = new LinePush();

		// 環境変数からユーザーIDを取得
		String strMyUserID = System.getenv("LINE_MY_USER_ID");
		
		// プッシュ処理を実施
		try {
			lp.doPushText(strMyUserID, args[0]);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getStackTrace());
		}
		return ;
	}

}
