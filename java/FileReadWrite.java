import java.io.*;
import java.util.*;

class FileReadWrite {

	public static int count = 0;
	public static final int onceItem = 500;

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("��������͂��Ă��������B");
			System.out.println("-----------------------------");
			System.out.println("1: process-conf.xml �쐬");
			System.out.println("2: ProhibitList.bat �쐬");
			return;
		}
		if (args[0].equals("1")) {
			makeConf();
		} else if (args[0].equals("2")) {
			makeBatch();
		}
	}

	// ---------------------
	// process-conf.xml �쐬
	// ---------------------
	public static void makeConf() {
		try {
			//File�N���X�ɓǂݍ��ރt�@�C�����w�肷��
			File file = new File("target.csv");
			
			//�t�@�C�������݂��邩�m�F����
			if(file.exists()) {

				// �ǂݍ��ݗp
				BufferedReader br = new BufferedReader(new FileReader(file));
				String data;

				List<String> list = null;
				Boolean bNew = true;
				int i = 0;
				while((data = br.readLine()) != null) {
					// ���X�g�N���A
					if (bNew) {
						list = new ArrayList<String>();
						bNew = false;
					}
					// ���X�g����
					if (0 < i) {
						list.add(data.split(",")[0]);
						if (i % onceItem == 0) {
							bNew = true;
							// �t�@�C����������
							write(list);
						}
					}
					i++;
				}
				// �ŏI��������
				if (i % onceItem != 0) {
					write(list);
				}
				
				//�t�@�C���N���[�Y
				br.close();
				
			} else {
				System.out.print("�t�@�C���͑��݂��܂���");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ---------------------
	// �t�@�C����������
	// ---------------------
	public static void write(List<String> list) {
		count++;

		try{
			// �x�[�X�t�@�C���̓ǂݍ���
			File base = new File("..\\config\\process-conf_org.xml");
			BufferedReader br = new BufferedReader(new FileReader(base));

			//�t�@�C�������݂��邩�m�F����
			if(base.exists()) {

				// �������ݗp
				FileWriter file = new FileWriter("..\\config\\list\\process-conf_" + String.format("%06d", count) + ".xml", false);
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));

				// �t�@�C����������
				String data;
				while((data = br.readLine()) != null) {
					// ��������
					if(!data.split(",")[0].equals("Phone_LIST")) {
						// ���̂܂܏�������
						pw.println(data);
					} else {
						// ���X�g����������
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
				System.out.println("..\\config\\process-conf_org.xml�@������܂���");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ---------------------
	// ProhibitList.bat �쐬
	// ---------------------
	public static void makeBatch() {
		try{
			// �x�[�X�t�@�C���̓ǂݍ���
			File base = new File("..\\ProhibitList_org.bat");
			BufferedReader br = new BufferedReader(new FileReader(base));

			//�t�@�C�������݂��邩�m�F����
			if(base.exists()) {

				// �������ݗp
				FileWriter file = new FileWriter("..\\ProhibitList.bat", false);
				PrintWriter pw = new PrintWriter(new BufferedWriter(file));

				// �t�@�C����������
				String data;
				while((data = br.readLine()) != null) {
					// ��������
					if(!data.split(",")[0].equals("COPY_FILE")) {
						// ���̂܂܏�������
						pw.println(data);
					} else {
						// �f�B���N�g���̃t�@�C���ꗗ����������
						File dir = new File("..\\config\\list");
						File[] list = dir.listFiles();
						for(int i=0; i<list.length; i++) {
							if(list[i].isFile()) {          //�t�@�C���̏ꍇ
								System.out.println(list[i].getName());
								pw.println("COPY /Y \"config\\list\\" + list[i].getName() + "\" \"config\\process-conf.xml\"");
								pw.println("call :sub");
								}
							else if(list[i].isDirectory()) { //�f�B���N�g���̏ꍇ
							//�������Ȃ�
							}
						}
					}
				}
				br.close();
				pw.close();
			} else {
				System.out.println("..\\config\\ProhibitList_org.bat�@������܂���");
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}