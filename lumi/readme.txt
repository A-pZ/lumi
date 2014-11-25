■Lumiプロジェクトについて

Lumiプロジェクトは、Struts2をベースとするWebアプリケーション開発に特化したプロジェクトです。

□採用しているフレームワーク群

* Struts (2.3.16.3) -- Actionクラスで画面制御を担当。
** Struts2-Conventionプラグイン -- アノテーションベースのAction設定＆全体的な規約を定義

* Spring (3.x) -- 実装Action内部で呼び出すロジック＋データアクセスを呼び出す
** Struts2-Springプラグインで連携
** Action以外からもロジックを提供可能
*** アプリケーションの初期化処理
*** Validationからロジックを実行
*** AspectJを使ったAction・Serviceの前後処理

* Mybatis (3.x) -- データベースとのI/O
** Mybatis-Springプラグインで連携

* Thymeleaf (2.x) -- HTML5テンプレート
** Struts2-Thymeleafプラグインで連携

* Bootstrap (3.2) -- CSSテンプレート

* Log4j (2.x)
** ロギングフレームワーク。

他にも開発をお助けするため、以下を提供。

* lombok
** アノテーションでボイラープレートなコードを省略。

□それぞれの学習ポイント

lumiでは、それぞれのクラスやライブラリを使う上での学習ポイントがあります。

○Action - 画面の動き（画面遷移）と、呼び出すロジックの決定
○Service - ロジックの実装
○DAO - データベースアクセスの実装
○HTML - 画面の実装

1. Action - アノテーションベースのActionクラスを作ります。
* lumi-blankから生成したサンプルのActionクラスをコピーして使うと、Actionクラスで設定すべき内容がすべて決まる。
* Serviceクラスの連携は、サンプルのActionクラスをコピーし、利用するServiceクラスに変更して使う。
* Serviceクラスの実行結果を受けて画面へ渡すところはActionクラスで実装する。

2. Service - データベースアクセスを使ったロジックを作ります。
* 業務システムで言うビジネスロジックの開始地点。
* lumi-blankから生成したサンプルのServiceクラスは、必須のSpring設定がアノテーションで記載。
* Actionやその他から呼び出され、Mybatisを使ったデータベースアクセスや、その他のServiceクラスを呼び出せる。
* Serviceの実行結果をActionへ返す。
* トランザクション境界はActionクラス～Actionから最初に呼び出すServiceクラスの間。
* Serviceは@Scope("prototype")、Actionクラス1インスタンスから1つのインスタンスが起動。
** Serviceクラスのメンバ変数を使っても良い。@Scope("Singleton")の場合は、Actionクラスからスレッドセーフでなくなるのでｘ。

3. DAO - Mybatisを使ってデータベースアクセスをXML+SQLで記載します。
* 動的なSQLを作るO/Rマッピングツール。
* 引数や実行結果をJavaのクラスで記述。
* SQL＋動的に変わる部分をJSTLライクなXMLで記述。

4. HTML - Thymeleafを使ったHTMLテンプレート
* HTMLをそのまま使ったテンプレートが作成できる。
* JSP不要。フロントエンジニアが作成したHTMLに対して影響が出ない作りができる。
* サーバ側の処理をタグの属性に追記する方式。
* ThymeleafからActionクラスのフィールドを出力する方法は、${action.フィールド名}でよい。

□プロジェクトの作り方
次のプロジェクトを入手（インポート）します。
* lumi - すべてのプロジェクトの親プロジェクト
* lumi-core - lumiの制御部ならびに親クラス・インタフェースの提供
* lumi-blank - プロジェクト生成用のひな型プロジェクト
* Struts2-thymeleaf-plugin - Struts2でThymeleafを使うためのプラグイン

次に、lumiをmvn installして、lumi-coreとlumi-blankをローカルのリポジトリへインストールします。

インストール後、lumi-blankをテンプレートとして新しいプロジェクトを作成します。
任意のディレクトリにて、mvn archetype:generateを実行します。
※mvn archetype:generateは現在のEclipse mavenプラグイン(m2e)では実行できません。別途コマンドラインから実行します。
コマントラインからmavenを実行するには、
* JavaSDKとmavenのインストール
* 環境変数にJavaSDKとmavenのパスを追加してください。
この作業は1度のみです。


□データベース接続について
lumi-blankプロジェクトをインポート後、blank-generate.batでプロジェクトを生成します。
生成したプロジェクトはmavenプロジェクトとなっているので、これをmavenプロジェクトとしてインポートします。

次に、インポートしたプロジェクトのMETA-INF/context.xmlにて、JDBCの設定を修正してください。

