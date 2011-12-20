#pragma strict

private var textList : String[] = [
    "解衣\n推食",
    "蓋棺\n事定",
    "外寛\n内明",
    "戒驕\n戒躁",
    "解甲\n帰田",
    "外交\n辞令",
    "邂逅\n相遇",
    "懐古\n趣味",
    "鎧袖\n一触",
    "外柔\n内剛",
    "下位\n上達",
    "咳唾\n成珠",
    "街談\n巷説",
    "怪誕\n不経",
    "快刀\n乱麻",
    "懐宝\n夜行",
    "槐門\n棘路",
    "傀儡\n政権",
    "怪力\n乱神",
    "偕老\n同穴"
];

function Start() {
    var fontRenderer = GetComponent.<SystemFontRenderer>();

    fontRenderer.BindMaterial(renderer.material);

    while (true) {
        fontRenderer.text = textList[Random.Range(0, textList.Length)];
        yield WaitForSeconds(Random.Range(0.5, 1.3));
    }
}

