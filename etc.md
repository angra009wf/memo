# apache再起動
```
sudo service httpd restart
```

# apache bench
```
・POSTの確認
ab -c 100 -n 1000 -p a.json -T "application/json; charset=utf-8" -H "Cookie: CAKEPHP=9509vl7b7pa4vodqngdlrsn2d5; csrfToken=97b050b692c60528c33b5ee1a03c1e5fefe6b4c9c4166e7147ee8180dd54e381dacd5ad36e82090b58d192d2561245591ae0e6966f7d73ab040371249ff8979b" -H "X-CSRF-Token: 97b050b692c60528c33b5ee1a03c1e5fefe6b4c9c4166e7147ee8180dd54e381dacd5ad36e82090b58d192d2561245591ae0e6966f7d73ab040371249ff8979b" http://hogehoge.co.jp/abc

・GETの確認
ab -c 100 -n 1000 -H "Cookie: CAKEPHP=ipedcab00r8qqr1t775qkrsguh; csrfToken=f8d7d6beb20622ee8cca67cffbf11995cf2ea5b04e8188e0820b5cd10a3286257bbead91da3c9aa5266d1aadd45e12d2f4d73e55aa07d22a8ae81b6f2d959168" -H "X-CSRF-Token: f8d7d6beb20622ee8cca67cffbf11995cf2ea5b04e8188e0820b5cd10a3286257bbead91da3c9aa5266d1aadd45e12d2f4d73e55aa07d22a8ae81b6f2d959168" https://hogehoge.co.jp/abc
```

# windows空ファイル作成
```
fsutil file createnew testfile 1073741824
fsutil file createnew testfile 314572800
```
---
---
# 【エンタープライズモード】

# エンタープライズモードとは
[エンタープライズ モードとは (Windows)](https://docs.microsoft.com/ja-jp/internet-explorer/ie11-deploy-guide/what-is-enterprise-mode?redirectedfrom=MSDN)

# エンタープライズモードを有効にする
[エンタープライズ モードを有効にしてサイト一覧を使う (Windows)](https://docs.microsoft.com/ja-jp/internet-explorer/ie11-deploy-guide/turn-on-enterprise-mode-and-use-a-site-list?redirectedfrom=MSDN)

下記、いずれかの方法にてエンタープライズモードを有効にする。  
有効にするサイトリストは別途ツールで設定する。  
・グループ ポリシーを使ってエンタープライズ モードを有効にする  
・レジストリを使ってエンタープライズ モードを有効にする  

# サイトリストを作成する
Enterprise Mode Site List Manager ツールの使用 (Windows)  
① ツールでxmlファイルを作成する。  
② グループポリシーにxmlファイルを指定する。  
③ IEを再起動する。  
④ 再起動後、65秒待つ。  

※65秒の理由はMicrosoftに記載あり。

> Internet Explorer 11 が起動してから約 65 秒後に、適切な形式のサイト一覧が検索されます。アクティブなサイト一覧と異なるバージョン番号を持つ新しい一覧が見つかると、IE11 は新しいバージョンを読み込んで使います。最初の確認の後、ブラウザーが再起動されるまで、IE11 は更新された一覧を検索しません。

xml記載例  
versionはレジストリの値より大きい値を設定する。 versionが変更されていない場合、IEは更新が無いと判断してリスト更新しないため、注意が必要。
```
<rules version="5">
　　<emie>
　　　　<domain exclude="false">127.0.0.1</domain>　- IP指定
　　　　<domain exclude="false">10.245.123.123</domain>　- IP指定
　　　　<domain exclude="false">msdn.microsoft.com</domain>　- ドメイン名指定
　　</emie>
</rules>

※特定パスを指定する場合
<domain exclude="true">news.msn.com
　　<path exclude="false">pop-culture</path>
</domain>
```

# 更新結果を確認する
「HKEY_CURRENT_USER/Software/Microsoft/Internet Explorer/Main/EnterpriseMode/レジストリキーのCurrentVersion」の値を確認 xmlに記載したバージョンで更新されているか確認する。
設定を更新する場合、レジストリキーの値より大きいバージョンを設定する。
なお、バージョン値はIE起動後65秒間待ってから更新される。

---
---
# 【サクラエディタ】
# タグファイル作成
```
--language-force=Java .java .jsp
--language-force=C++ .pc

Ctagのライブラリをインストールする必要あり。
```

# コマンドラインからgrep
複数grepをファイルにはいてくれる
```
ex) 検索文字：import

"C:\Program Files (x86)\sakura\sakura.exe" -GREPMODE -GKEY="import" -GFILE="*.csv" -GFOLDER="C:\Users\yf\work\01_develop" -GCODE=99 -GOPT=SUP >> "C:\Users\yf\Desktop\grep\grep.txt"
```

---
---
