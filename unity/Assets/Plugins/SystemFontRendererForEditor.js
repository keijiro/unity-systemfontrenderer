#pragma strict

var target : RenderTexture;
var text : String;
var fontSize : int;

function DoRender() {
    gameObject.AddComponent.<Camera>();
    gameObject.AddComponent.<GUILayer>();
    gameObject.AddComponent.<GUIText>();

    transform.localPosition = Vector3(0, 1, 0);
    gameObject.layer = 31;

    camera.backgroundColor = Color(0, 0, 0, 0);
    camera.targetTexture = target;
    camera.cullingMask = -0x80000000;

    guiText.text = text;
    guiText.fontSize = fontSize;

    camera.Render();
    Destroy(GetComponent.<GUILayer>());
    Destroy(camera);
    Destroy(guiText);

    Destroy(gameObject);
}
