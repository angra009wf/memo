import java.io.*;
import java.util.*;

class FileReadWrite {

	public static int count = 0;
	public static final int onceItem = 500;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("引数を入力してください。");
			System.out.println("-----------------------------");
			System.out.println("1: process-conf.xml 作成");
			System.out.println("2: ProhibitList.bat 作成");
			return;
		}
		if (args[0].equals("1")) {
			makeConf();
		} else if (args[0].equals("2")) {
			makeBatch();
		}
	}

	// ---------------------
	// process-conf.xml 作成
	// ---------------------
	public static void makeConf() {
		try {
			//Fileクラスに読み込むファイルを指定する
			File file = new File("target.csv");
			
			//ファイルが存在するか確認する
			if(file.exists()) {

				// 読み込み用
				BufferedReader br = new BufferedReader(new FileReader(file));
				String data;

				List<String> list = null;
				Boolean bNew = true;
				int i = 0;
				while((data = br.readLine()) != null) {
					// リストクリア
					if (bNew) {
						list = new ArrayList<String>();
						bNew = false;
					}
					// リスト生成
					if (0 < i) {
						list.add(data.split(",")[0]);
						if (i % onceItem == 0) {
							bNew = true;
							// ファイル書き込み
							write(list);
						}
					}
					i++;
				}
				// 最終書き込み
				if (i % onceItem != 0) {
					write(list);
				}
				
				//ファイルクローズ
				br.close();
				
			} else {
				System.out.print("ファイルは存在しません");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---------------------
	// ファイル書き込み
	// ---------------------
	public static void write(List<String> list) {
		count++;

		try{
			// ベースファイルの読み込み
			File base = new File("..\\config\\process-conf_org.xml");
			BufferedReader br = new BufferedReader(new FileReader(base));

			//ファイルが存在するか確認する
			if(base.exists()) {

				// 書き込み用
				FileWriter file = new FileWriter("..\\config\\list\\process-conf_" + String.format("%06d", count) + ".xml", false);
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));

				// ファイル書き込み
				String data;
				while((data = br.readLine()) != null) {
					// 書き込み
					if(!data.split(",")[0].equals("Phone_LIST")) {
						// そのまま書き込み
						pw.println(data);
					} else {
						// リストを書き込み
						int i[] = { 0 };
						list.forEach(s -> {
							if (i[0]== 0) {
								pw.println("'" + s + "'");
							} else {
								pw.println(",'" + s + "'");
							}
							i[0]++;
						});
					}
				}
				System.out.println("..\\config\\list\\process-conf_" + String.format("%06d", count) + ".xml");
				br.close();
				pw.close();
			} else {
				System.out.println("..\\config\\process-conf_org.xml　がありません");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------
	// ProhibitList.bat 作成
	// ---------------------
	public static void makeBatch() {
		try{
			// ベースファイルの読み込み
			File base = new File("..\\ProhibitList_org.bat");
			BufferedReader br = new BufferedReader(new FileReader(base));

			//ファイルが存在するか確認する
			if(base.exists()) {

				// 書き込み用
				FileWriter file = new FileWriter("..\\ProhibitList.bat", false);
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));

				// ファイル書き込み
				String data;
				while((data = br.readLine()) != null) {
					// 書き込み
					if(!data.split(",")[0].equals("COPY_FILE")) {
						// そのまま書き込み
						pw.println(data);
					} else {
						// ディレクトリのファイル一覧を書き込む
						File dir = new File("..\\config\\list");
						File[] list = dir.listFiles();
						for(int i=0; i<list.length; i++) {
							if(list[i].isFile()) {          //ファイルの場合
								System.out.println(list[i].getName());
								pw.println("COPY /Y \"config\\list\\" + list[i].getName() + "\" \"config\\process-conf.xml\"");
								pw.println("call :sub");
								}
							else if(list[i].isDirectory()) { //ディレクトリの場合
							//何もしない
							}
						}
					}
				}
				br.close();
				pw.close();
			} else {
				System.out.println("..\\config\\ProhibitList_org.bat　がありません");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}