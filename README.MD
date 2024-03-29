# ImageComparer(WIP)
用意した画像のデータセットと任意の画像の、RGB、輝度の分布を比較するためのプログラムです。

## 思想
イラスト等を制作しているときに、色の使い方が難しいと思った。  
どういう色を使えばいいのか分からないし、上手な人の作品を手本にしても、違いが言語化できない。何となく淡いな～みたいな感じになって問題が解決できない。  
個人的に、言語化可能な問題は、そうでない問題よりも解決可能性が大幅に高いと思っているので、この問題を言語化することができないだろうかと思ってプログラムを作成してみた。  
手本となる画像を収集して、それをデータセットとして、色のばらつきを視覚可出来れば、違いが言語化できると思ったので、このプログラムはデータセットのRGB, 輝度の平均値、中央値を求めてグラフとして表示する。
その際、自身のイラストの分布との違いを確認したいので、自分で用意した画像のデータも一緒に表示する。   
個人利用を想定して作ったのでUIはまだ凝っていない。   
ターゲットは、自分でイラスト等デザインを作成したい人なので、機械学習関連の機能はここに付けるつもりはあんまりない。付けるならフォークして別のプロジェクトで作る。


## アピールポイント
ある程度オブジェクト指向を頑張った。とくに拡張性について気を付けた。  
初めてgradleを使った。  
グラフの表示がjavafx。他学科が使っていたので一応使ってみた。
拡張性が担保されているので、フォークして機械学習用に特徴量抽出プログラムなんかにも転用できる。  
個人開発  

## 改善点
フォルダ名がよろしくない。後で改善する。プログラム全体で使うフォルダは`public`で`static`な定数として用意してあるので、変更は容易。  
統計量の種類が少ない。RBG + 輝度以外にもいろいろ比較するべき情報がありそう。  
スクレイピングできるサイトが少ない。個人的にはイラスト投稿サイト各種やAmazonなんかもスクレイピングしたい。
私以外が使うことをあんまり想定していないのでユーザーインターフェースが雑。localhostでブラウザ経由するとか、GUIにするとかして改善したい。


## 機能
### スクレイピング
いくつかのサイトから書影をスクレイピングで収集してデータセットを作成することができます。
現在は以下のサイトに対応しています。   
BookWalker

### 画像の比較
ユーザーが用意した任意の画像とデータセットを比較することができます。
R, G, B, 輝度の分布を比較することができます。
データセットのデータは平均値、最頻値から選んで比較します。
統計処理の関係上、用意された画像の解像度が一定でない場合適切な値を出力できない場合があります。

## 使い方(暫定)
---
### 実行方法
もう少し機能を追加したい気持ちがあるので未ビルドです。実行するにはgradleを介する必要があります  
#### Windowsの場合
リポジトリルートディレクトリにある`gradle.bat`をターミナルから実行することで、プログラムを実行することができます
```
gradle.bat run
```

#### Unixの場合
リポジトリルートディレクトリにある`gradlew`を実行することで、プログラムを実行できます
```
./gradlew run
```

### 使い方
プログラムの初回起動時、必要なディレクトリが作成されます。
データセットと比較したい画像を`userPreparedData/img`に配置してください。画像は複数枚でも一応動くと思います。(その場合、単一の画像とデータセットの比較ではなく、データセット同士の比較ということになる)  

実行すると、使用するデータセットを選択できます。現在は、Bookwalkerというサイトからスクレイピングで集めてくるか、もしくはユーザーが自前で用意するか選べます。1 ~ 4の半角数字をターミナルで入力して下さい。  
ユーザーが用意したデータセットを使用したい債は、`userPreparedData/dataset`に配置してください   
数字を入力するとグラフが表示されます。

## 既知の問題
データがフォルダに存在しない場合に、エラーが起きる可能性がある。エラーハンドリングが未実装状態  