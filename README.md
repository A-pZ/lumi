# Lumiプロジェクトについて #

Lumiプロジェクトは、Struts2をベースとするWebアプリケーション開発に特化したプロジェクトです。

## 採用しているフレームワーク群 ##

* Struts (2.5-BETA3) -- Actionクラスで画面制御を担当。
  * Struts2-Conventionプラグイン -- アノテーションベースのAction設定＆全体的な規約を定義
* Spring (4.1.x) -- 実装Action内部で呼び出すロジック＋データアクセスを呼び出す
  * Struts2-Springプラグインで連携
  * Action以外からもロジックを提供可能
    * アプリケーションの初期化処理
    * Validationからロジックを実行
    * AspectJを使ったAction・Serviceの前後処理
* Mybatis (3.2.x) -- データベースとのI/O
  * Mybatis-Springプラグインで連携
* Thymeleaf (2.1.8) -- HTML5テンプレート
  * Struts2-Thymeleafプラグインで連携
  * Struts2-Thymeleafプラグインの入手先：https://github.com/A-pZ/struts-thymeleaf-plugin
* Bootstrap (3.3) -- CSSテンプレート
* Log4j (2.3)
  * ロギングフレームワーク。

他にも開発をお助けするため、以下を提供。

* lombok
  * アノテーションでボイラープレートなコードを省略

## 導入方法 ##

以下の2つのプロジェクトをお使いの開発環境に展開します。

* lumi : lumiプロジェクトのルートプロジェクトです。
  * lumi-blank : lumiプロジェクトを使ったアプリケーションを新規作成するためのテンプレート。
  * lumi-core : lumiプロジェクトの共通実装部分です。
* struts-thymeleaf-plugin : lumiプロジェクトで利用するThymeleafのStruts2用プラグインです。

これらのプロジェクトは全てmaven管理していますので、事前にmavenの入手(https://maven.apache.org/download.cgi) とインストールをお願いします。

## インストール手順 ##

lumiプロジェクトを導入するには、まず先にstruts-thymeleaf-plugin(https://github.com/A-pZ/struts-thymeleaf-plugin)のローカル環境導入が必要です。

* mavenのインストールを実施します
* struts-themeleaf-plugin(https://github.com/A-pZ/struts-thymeleaf-plugin)のプロジェクトをクローンします。
  * struts-thymeleaf-pluginプロジェクト内に含まれているinstall.batを実行します
* lumiプロジェクトをクローンします。
  * lumiプロジェクト内に含まれているinstall.batを実行します

以上でインストール作業は完了です。

## 新規アプリケーションの作成

lumi-blankプロジェクトをインポート後、blank-generate.batを編集し、batファイルを実行してプロジェクトを生成します。
生成したプロジェクトはmavenプロジェクトとなっているので、これをmavenプロジェクトとしてインポートします。

その後、lumi-blankプロジェクト内にある、bank-generate.batを実行すると、lumiプロジェクトを使ったサンプルコードを含んだプロジェクトが生成されます。

## それぞれの学習ポイント ##

lumiでは、それぞれのクラスやライブラリを使う上での学習ポイントがあります。

* Action - 画面の動き（画面遷移）と、呼び出すロジックの決定
* Service - ロジックの実装
* DAO - データベースアクセスの実装
* HTML - 画面の実装

### 1. Action - アノテーションベースのActionクラスを作ります。

* lumi-blankから生成したサンプルのActionクラスをコピーして使うと、Actionクラスで設定すべき内容がすべて決まる。
* Serviceクラスの連携は、サンプルのActionクラスをコピーし、利用するServiceクラスに変更して使う。
* Serviceクラスの実行結果を受けて画面へ渡すところはActionクラスで実装する。

### 2. Service - データベースアクセスを使ったロジックを作ります。

* 業務システムで言うビジネスロジックの開始地点。
* lumi-blankから生成したサンプルのServiceクラスは、必須のSpring設定がアノテーションで記載。
* Actionやその他から呼び出され、Mybatisを使ったデータベースアクセスや、その他のServiceクラスを呼び出せる。
* Serviceの実行結果をActionへ返す。
* トランザクション境界はActionクラス～Actionから最初に呼び出すServiceクラスの間。
* Serviceは@Scope("prototype")、Actionクラス1インスタンスから1つのインスタンスが起動。
  * Serviceクラスのメンバ変数を使っても良い。@Scope("Singleton")の場合は、Actionクラスからスレッドセーフでなくなるのでｘ。

### 3. DAO - Mybatisを使ってデータベースアクセスをXML+SQLで記載します。

* 動的なSQLを作るO/Rマッピングツール。
* 引数や実行結果をJavaのクラスで記述。
* SQL＋動的に変わる部分をJSTLライクなXMLで記述。

### 4. HTML - Thymeleafを使ったHTMLテンプレート

* HTMLをそのまま使ったテンプレートが作成できます。
* JSP不要。フロントエンジニアが作成したHTMLに対して影響が出ない作りができます。
* サーバ側の処理をタグの属性に追記する方式です。
* ThymeleafからActionクラスのフィールドを出力する方法は、${action.フィールド名}で可能です。

## データベース接続について ##

インポートしたプロジェクトのsrc/main/webapp/META-INF/context.xmlにて、データベース接続JDBC設定があります。
生成したプロジェクトでは、ServiceクラスでSQLは実行せず、データベース接続もしません。
もしご利用の場合は、お使いのデータベースと対応するJDBCドライバの接続設定をしてください。

そののち、SpringFrameworkの設定ファイルで、データベース接続に関する設定ファイルである、NONACTIVE-applicationContext-transaction.xmlのファイル名から、
"NONACTIVE-"を削除してください。これでデータベース接続が有効になり、DAOのインスタンスがServiceクラスで利用できます。

