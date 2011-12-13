### 概要

Unity iOS および Android においてシステムフォントをテクスチャにレンダリングするプラグインの実装です。

### 使い方

SystemFontRenderer.js を何らかのゲームオブジェクトに付与します。パラメーターとして Inspector 上に "Text", "Text Size", "Texture Width", "Texture Height" が表示されるので、これにそれぞれ値を設定します。

**注意** Texture Width と Texture Height は 2 のべき乗サイズでなくてはなりません。

これで SystemFontRenderer 内にテクスチャが生成されます。これを表示するには、何らかのマテリアルにバインドする必要があります。例えば、同ゲームオブジェクトにある表示物のマテリアルにバインドするには次のようにします。

    GetComponent.<SystemFontRenderer>().BindMaterial(renderer.material);

なお、パラメーターの Text は動的な変更に対応しています。

    GetComponent.<SystemFontRenderer>().text = "10秒待ちたまえ";
    yield WaitForSeconds(10.0);
    GetComponent.<SystemFontRenderer>().text = "そろそろ時間だ！";

### 組み込み方法

iOS では Plugins ディレクトリの内容を任意のプロジェクトにインポートするだけです。

Android ではアクティビティの置き換えを行っているため、組み込みが若干面倒です。次のような手続きが推奨されます。

  1. android ディレクトリ内にあるライブラリパッケージを自分のプロダクトの名前空間に合わせて改築する。
  1. ant を使ってビルドし、生成された jar ファイルで Plugins/Android/SystemFontRenderer.jar を置き換える。
  1. Plugins/Android/AndroidManifest.xml 内のパッケージの記述を更新する。
