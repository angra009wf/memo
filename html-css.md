# 【HTML】
# favicon
```
メニューバーに表示されるアイコン

<link rel="shortcut icon" href="favicon.ico">
```

# placeholder
```
テキストエリアにガイド用に表示する内容

<input type="text" placeholder="例）参加します">
```

# ボタン
```
inputタグ
<input type="submit" value="送信">

buttonタグ
ボタンに画像を設定するなど、リッチなボタンが作成できる
<button type="submit"><span><img src="img/title.gif" /></span></button>

クライアントで制御するために、submit以外も設定可
<button type="button">処理する</button>
```

# labelタグ
```
チェックボックスやラジオボタンなど、ラベルをクリックしても選択が可能

inputタグの外側で定義
<label><input type="checkbox">test</label>

labelタグのfor属性を使用
<input type="checkbox" id="ch1"><label for="ch1">test</label>  
```

# width（ウィズ）height（ハイト）
%で指定する場合、親の要素に対する割合が設定される  
但し、heightはクセが強いんじゃ。  
```
---HTML---
<html>
  <head>
  </head>
  <body>
    <div> hogehoge </div>
  </body>
</html>

---CSS---
div{
    width: 50%;
    height: 50%;
}

とした場合、heightはうまく適用されない。

↓↓↓　こんな風に親のサイズも指定する必要がある

body, html{ height: 100%; }
```

---
---
# 【CSS】
# CSSの書き方
書き方は３パターン

①htmlタグ内に記述する
```
<p style="color:blue">
    hogehoge
</p>
```
② html内にstyleタグを作成して記述する
```
<style>
    p{ color: blue; }
</style>
<body>
    <p>hogehoge</p
</body>
```
③ CSSファイルに記述し、htmlから参照する
```
＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
　CSS (ex. css/default.css)
＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
p{ color: blue; }

＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
　HTML
＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
<head>
    <link rel="stylesheet" href="css/default.css"/>
</head>
<body>
    <p>hogehoge</p
</body>
```
# セレクタ
どのHTML要素にスタイルを適用するのかを指定するために記述する。

要素
```
body{ プロパティ }
⇒body要素全体にプロパティの設定が適用される

body p{ プロパティ }
⇒body要素内のp要素にプロパティの設定が適用される
　(要素と要素の間を半角スペース)

h1, h2{ プロパティ }
⇒h1要素とh2要素にプロパティの設定が適用される
　(要素と要素の間をカンマ半角スペース)

他にも、＞や＋がある
＞　⇒　div > span　div直下のspan要素のみ適用
＋　⇒　p + p　　　 pの次にくるp要素に適用
```

class
```
.zoo{ プロパティ }
⇒zooのclass要素にプロパティの設定が適用される

p.zoo{ プロパティ }
⇒p要素かつ、zooのclass要素にプロパティの設定が適用される
```

id
```
#tiger{ プロパティ }
⇒tigerのid要素にプロパティの設定が適用される
```

# 属性セレクタ
指定した属性が設定されているものに適用
```
a[title] { プロパティ }
⇒aタグのうち、title属性が設定されているものにプロパティの設定が適用される
```

属性に特定の値が設定されているものに適用
```
a[href="http://google.com] { プロパティ }
⇒aタグのうち、href属性にgoogleが設定されているものにプロパティの設定が適用される
```

要素内に指定したクラス属性が設定されているものに適用
```
a[class˜="search"] { プロパティ }
⇒aタグのうち、class要素にsearchが含まれるものにプロパティの設定が適用される
```

# 疑似クラス
最初の子要素のみ適用
```
<ul>
　<li>東京</li>
　<li>大阪</li>
　<li>福岡</li>
</ul>

ul li:first-child { プロパティ }
⇒東京のみプロパティの設定が適用される
```

aタグの状態に応じて適用
```
a:link { プロパティ }
⇒未訪問の場合、プロパティの設定が適用される

a:visited { プロパティ }
⇒訪問済みの場合、プロパティの設定が適用される

a:hover { プロパティ }
⇒マウスオーバー時、プロパティの設定が適用される

a:active { プロパティ }
⇒マウスクリック時、プロパティの設定が適用される
```

フォーカスの状態に応じて適用
```
input:focus { プロパティ }
⇒テキストボックスにフォーカスがあたった時、プロパティの設定が適用される
```

# 疑似要素
```
p:before {
        content:  "-->";
}
⇒p要素の前に文字列を表示

p:after {
        content:  "<--";
}
⇒p要素の後に文字列を表示

※before, after 共にcontentプロパティは必須
```

# ボックスモデル
```
margin 他の要素との間のスペース
border 要素の枠線
padding 枠線と子要素の間のスペース
height 子要素の縦サイズ
width 子要素の横サイズ
```

# スタイルの優先度
スタイルはセレクタの詳細度によって優先が決まる。  
(a) style = ""  
(b) ID  
(c) 属性/疑似クラス  
(d) 要素/疑似要素  

(a) > (b) > (c) > (d)  
```
---HTML---
<a href="hoge" class="link search" id="site" style="color:green;">
。。。(a)  styleで記述するとCSS定義より優先
※importantを使用するとimportantが優先される。

---CSS---
a { color: skyblue; }　　　　。。。(d)
a.link { color:pink; }　　　　。。。(c)
a.search { color:orange; }　　。。。(c)  同レベルの場合、後に記述した方が優先
a#site { color:blue; }　　　　。。。(b)  クラスよりIDが優先
```

# important
スタイルは通常、スタイルシートのインポート順に適用される。 （上の階層、親子関係で言えば子供が適用される）

importantを指定すると、階層を無視してスタイルを適用させることができる。

```
ex)  
padding: 0 !important;
```

では、importantが重複した場合は？  
この場合、important指定されているものの中で、上の階層のスタイルが適用される。  

あとからあとから修正しているとこんなカオス状態になってしまう。  

# display要素 table
tableタグを使わずにテーブルのように表示できる  
メニューの作成など、これでできてしまう
```
---HTML---
<div class="container">
　　<div class="box">
　　　　Hello Hello Hello Hello
　　</div>
　　<div class="box">
　　　　Hello Hello Hello Hello Hello Hello
　　</div>
　　<div class="box">
　　　　Hello Hello
　　</div>
</div>

---CSS---
.container {
　　display: table;
　　width:300px;
}

.box {
　　display: table-cell;
　　width:100px;
}
```

# 中央揃え
```
margin: 0 auto;
```

# 左右寄せ
```
float: right;
float: left:
文字は回りこむ
```

# 縦横比の保持
```
---HTML---
<div class="iframe-wrapper">
    <iframe YouTubeとかの動画>
</div>    

---CSS---
iframe {
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
}

.iframe-wrapper {
    padding-bottom: 56.25%;       /* 16:9 */
    haight: 0;
    position: relative;
}
```
---
---
# 【その他】
# キャッシュ削除
Chrome
```
ctrl + shift + R
```

IE
```
ctrl + R
```