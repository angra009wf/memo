# 【CakePHP】
# データベースの定義
```
cake\samplecake\config\app.php
上記ファイルのDatasourcesにDB情報を設定する。
```

# URLとControlerの紐づき
```
routes.php
```

# コントローラからビューに値を渡す
```
コントローラ
$this->set('message', 'Hello! this is sample page. ;-)');
ビュー
<?= $message ?>
```

# コントローラでのデータ取得
```
POST
$item = $this->request->data['hoge'];
or
$item = json_decode($this->request->getData('json'), true)['hoge'];

GET
URL/?キー=値&キー=値
http://localhost:8765/helo/index?id=123&name=taro

$id = $this->request->query('id');
$name = $this->request->query('name');
```

# テーブルのModel作成
```
コマンドプロンプト

cd プロジェクト\bin
cake bake model テーブル名
```

# セッション保存
```
$this->Common->setSession('sess_login', null);
```

# debugKitの表示
```
C:\xampp\htdocs\prj\config\bootstrap.php
if (Configure::read('debug')) {
    Plugin::load('DebugKit', ['bootstrap' => true]);
}
```

# バッチ起動
```
prf\bin>cake ExecBatch
```