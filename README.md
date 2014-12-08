lumi
====

lumi (Struts2+Spring3+Mybatis+Thymeleaf) project.

* サンプルアプリケーションの導入方法

lumi、lumi-core、lumi-blankの3つをダウンロードし、自分の環境へ展開します。
lumiは、lumi-coreとlumi-blankの親プロジェクトです。
さらに、Struts2-thymeleaf-pluginもダウンロードし、自分の環境へ展開します。

その後、lumiプロジェクトにてmvn installを実行すると、lumi-core(フレームワークの一式)と、
ブランクプロジェクトを作るlumi-blankアーキタイプがローカルリポジトリに導入されます。

mvn installが正常に終了(BUILD SUCCESS)したら、次にブランクプロジェクトの生成をします。
lumi-blank直下にある、blank-generate.batを実行すると、実行ディレクトリにmavenプロジェクトを生成します。

これをアプリケーションとしてお使いください。
Eclipseであれば、インポート＞mavenプロジェクトのインポートで完了します。

なお、プロジェクト名などはこのbatファイルにて直接記載していますので、自由に書き換えてください。

* 動作させるために必要なこと。

src/main/webapp/META-INF/context.xmlにて、データベース接続JDBC設定があります。
サンプルアプリではSQLは実行しませんが、データベース接続だけは行いますので
お使いのデータベースと対応するJDBCドライバの接続設定をしてください。

またはsrc/main/resources/spring/applicationContext-transaction.xmlのファイル名の先頭に何か記号をつけて、動作しないようにしてください。

* Thymeleafプラグインについて

私のリポジトリに最新版を公開しておりますので、そちらの入手もお願いします。

* BootstrapとjQueryのバージョン

Bootstrapは3.2.0、jQueryは1.11を使っています。これらはStruts2のプラグインは使わずに、独立しています。
HTMLのインポート文にて既に対応してますので、HTML直接実行でもThymeleafからでも参照できるよう記述済みです。
