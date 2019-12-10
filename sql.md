# 【SQL】
# テーブル定義出力
```
desc テーブル名
desc mst_users;
```

---
---
# 【Oracle】
# SYSDB接続方法
```
sqlplus /nolog
connect / as sysdba
```

# バージョン確認
```
SELECT * FROM V$VERSION
```

# SQLファイル実行
```
ID/PASS指定
sqlplus id/pass@instance @SQLファイル名
ID/PASS指定なし（SQLファイル内で指定）
sqlplus @SQLファイル名
```

< SQLファイル >
```
id/pass@instance
hogehoge
```

# DB実行履歴の確認
```
SELECT LAST_ACTIVE_TIME, PARSING_SCHEMA_NAME, SQL_TEXT FROM V$SQLAREA
```

# フォーマット変換
```
TO_DATE('20180203', 'YYYY/MM/DD HH24:MI:SS')
```

# DataPump（バックアップ/リストア）
[Data Pump(expdp/impdp)の使い方～エクスポート／インポート、データ移動、論理バックアップ | Oracle オラクルエンジニア通信 - 技術資料、マニュアル、セミナー Blog](https://blogs.oracle.com/oracle4engineer/data-pumpexpdpimpdp)

> 従来のExp/Impは基本的にユーティリティ・ツールが実行される側で処理されるが、Data Pumpは、データベース・サーバー側でジョブとして管理・処理されます。得られる大きなメリットは、「パフォーマンス向上」と「管理性の向上」です。

## ディレクトリオブジェクト作成
CREATE DIRECTORY HOGE_DIR as '/home/oracle/test_dir';

## 権限付与
grant read, write on directory HOGE_DIR to test_user;

## ディレクトリオブジェクトの確認
SELECT * FROM DBA_DIRECTORIES;

## 権限確認
SELECT * FROM USER_TAB_PRIVS;

## ディレクトリ削除
DROP DIRECTORY HOGE_DIR;

# expdp（エクスポート）
```
expdp HOGE1
TABLES=HOGE1.EMP
DUMPFILE=TEST_DUMP:EMP.dmp
REUSE_DUMPFILES=Y
```

# impdp（エクスポート）
```
impdp HOGE2
DIRECTORY=HOGE_DIR
DUMPFILE=EMP.dmp
TABLES=HOGE1.EMP
REMAP_SCHEMA=HOGE1:HOGE2
REMAP_TABLESPACE=HOGE1_SPACE:HOGE2_SPACE
```
```
ex)
impdp user/pass dumpfile=dump.dmp directory=HOGE_DIR remap_schema=hoge1:hoge2 remap_tablespace=hoge1_space:hoge2_space logfile=test.log
```

# SQL*Loader
[SQL*Loader の使い方 - オラクル・Oracleをマスターするための基本と仕組み](https://www.shift-the-oracle.com/utility/sqlloader/)  
[SQL*Loader制御ファイル・リファレンス](http://otndnld.oracle.co.jp/document/products/oracle10g/102/doc_cd/server.102/B19211-01/ldr_control_file.html)

# コマンドラインで実行
```
sqlldr userid="username/password" control='ldrSample.ctl' log=test.log badfile=test.bad
```

# コントロールファイル<ldrSample.ctl>
```
LOAD DATA
INFILE 'TEST_DATA' "FIX 120"
PRESERVE BLANKS
INTO TABLE TEST.LOAD_TBL TRUNCATE
WHEN COL1 = 'CODE01'
(
　　COL1　　POSITION(1:5)　　CHAR "RTRIM(:COL1)",
　　COL2　　POSITION(6:10)　 CHAR "RTRIM(:COL2)",
　　COL3　　POSITION(11:13)　ZONED(3) NULLIF COL3=BLANKS,
　　COL4　　POSITION(14:18)　DECIMAL(9,1) NULLIF COL4=BLANKS, <-パック10新数
　　COL5　　POSITION(19:19)　CHAR "RTRIM(:COL5)",
　　COL6　　POSITION(20:29)　CHAR "RTRIM(:COL6)",
　　COL7　　 　　CHAR "SUBSTRB(:COL1, 1, 3) || SUBSTRB(:COL6, 3, 5)"
)

※WHEN句は = <> AND のみ使用可。OR LIKE などは使用できないので注意
※バッドファイルはロードエラーのレコードがそのまま出力される
```

---
---
# 【MySQL】
# コマンドプロンプトからのログイン
```
mysql -uユーザ名 -p -Dデータベース名 -hホスト -e" select 文とか" > ファイル名.tsv

-u
ユーザ名指定

-p
パスワード指定

-D
データベース名指定

-h
ホスト名の値を指定

-e
コマンドラインから直接 sql を実行
```

# コマンドプロンプトからのsqlファイル実行
```
C:\xampp\mysql\bin\mysql -u root -p < C:\Users\yf\work\get.sql
```

# テーブル作成
```
CREATE TABLE persons (
    id    INT(11) PRIMARY KEY AUTO_INCREMENT,
    name  VARCHAR(11) NOT NULL,
    age   INT(11),
    mail  VARCHAR(11)
)
DEFAULT CHARSET=utf8mb4
COLLATE utf8mb4_unicode_520_ci
```

# 参照DB変更
```
use
ex)use example;
```

# dbがlatin1になっている場合
```
alter database example default character set utf8;でdbの文字コードを変更する。
alter database ring_db_dev default character set utf8;
set character_set_database = utf8;
set global  character_set_database=utf8;
```

# UNIQUE制約削除
```
alter table trn_histories drop index trn_input_kbn
```

# NOT NULL解除
```
alter table trn_histories MODIFY trn_histories_id int(11) DEFAULT NULL COMMENT '履歴ID'
```

# カラム追加
```
alter table trn_histories add answer_flg tinyint(1) DEFAULT NULL after id COMMENT '回答フラグ'
```

# カラム名変更
```
alter table trn_order change trn_order_name trn_order_no int(11) NOT NULL COMMENT '受注ID'
```

# CSVからインポート
```
LOAD DATA INFILE 'imp_data/trn_histories.csv' INTO TABLE `db_dev`.`trn_histories` FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

LOAD DATA LOCAL INFILE 'C:/Users/yf/work/data_Trn_Histories.csv' INTO TABLE db_dev.Trn_Histories FIELDS TERMINATED BY ',';

※CSVファイルのヘッダ行は削除
※UTF-8、LFで保存
```

# エクスポート
```
テーブル単位
mysqldump -u root -t db_dev trn_histories > dump_trn_histories.sql

データベース単位
mysqldump -u root -pdev_pass db_dev > 20190521_db_dev.sql
```

# インポート
```
mysql -u root -pdev_pass -h XXXX db_dev < dump_trn_histories.sql
```

---
---
# 【PostgreSQL】
# シーケンスを利用したテーブル作成
```
・シーケンス作成
create sequence db_dev.mst_users_id_seq;

・シーケンスを元にテーブル作成
create table db_dev.mst_users (
  id bigint default nextval('db_dev.mst_users_id_seq'::regclass) not null
  , user_id character varying(256) not null
  , password character varying(256) default NULL
  , created timestamp(6) without time zone not null
  , created_user bigint not null
  , modified timestamp(6) without time zone not null
  , modified_user bigint not null
  , primary key (id)
);
```

# NOT NULL 制約削除
```
alter table mst_users alter column first_name DROP NOT NULL;
```

# デフォルト値をNULLに
```
alter table mst_users alter column first_name SET DEFAULT NULL
```

# カラム名変更
```
alter table db_dev.mst_users RENAME COLUMN name TO seimei
```