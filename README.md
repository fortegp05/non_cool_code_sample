# non_cool_code_sample
イケてないコードのサンプル

# 起動方法

cloneしてきたプロジェクト直下に移動してきて以下のコマンドを実行。
initMysql.shはDBが上がり切らないとエラーになるので少し待った方がいいかも。

 * docker-compose up -d
 * sh initMysql.sh
 * gradle war
 * http://localhost:8080/demo/にアクセス

ブラウザにページが表示されればOK。
