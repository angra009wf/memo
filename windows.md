# 空ファイル作成
```
byte指定でファイルを作成。
fsutil file createnew testfile 1073741824 => 1Gbyte
fsutil file createnew testfile 314572800 => 300Mbyte
```