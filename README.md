lumi
====

lumi (Struts2+Spring4+Mybatis3+Thymeleaf2.1) project.

* サンプルアプリケーションの導入方法

lumi、lumi-core、lumi-blankの3つと、Struts2-thymeleaf-pluginをダウンロードし、自分の環境へ展開します。
lumiは、lumi-coreとlumi-blankの親プロジェクトです。

その後、lumiプロジェクトにてmvn installを実行すると、lumi-core(フレームワークの一式)と、
ブランクプロジェクトを作るlumi-blankアーキタイプがローカルリポジトリに導入されます。

mvn installが正常に終了(BUILD SUCCESS)したら、次にブランクプロジェクトの生成をします。
lumi-blank直下にある、blank-generate.batを実行すると、実行ディレクトリにmavenプロジェクトを生成します。

これをアプリケーションとしてお使いください。
Eclipseであれば、インポート＞mavenプロジェクトのインポートで完了します。

なお、プロジェクト名などはこのbatファイルにて直接記載していますので、自由に書き換えてください。

* 動作させるために必要なこと。

src/main/webapp/META-INF/context.xmlにて、データベース接続JDBC設定があります。
サンプルアプリではSQLは実行せず、データベース接続もしません。
もしご利用の場合は、お使いのデータベースと対応するJDBCドライバの接続設定をしてください。

src/main/resources/spring/applicationContext-transaction.xmlのファイル名の先頭にNOACTIVEをつけて、動作しないようにしています。

* Thymeleafプラグインについて

私のリポジトリに最新版を公開しておりますので、そちらの入手もお願いします。

* BootstrapとjQueryのバージョン

Bootstrapは3.3.2、jQueryは1.11を使っています。これらはStruts2のプラグインは使わずに、独立しています。
HTMLのインポート文にて既に対応してますので、HTML直接実行でもThymeleafからでも参照できるよう記述済みです。
