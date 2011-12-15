#pragma strict

import System.Runtime.InteropServices;

var text = "Text";
var textSize = 16;
var textureWidth = 256;
var textureHeight = 256;

@HideInInspector
var texture : Texture2D;

private static var counter : int;

private var id : int = -1;
private var renderedText : String;

#if UNITY_EDITOR

private var targetMaterial : Material;
private var renderTexture : RenderTexture;

function OnDestroy() {
    if (renderTexture) Destroy(renderTexture);
}

function BindMaterial(material : Material) {
    targetMaterial = material;
}

function Update() {
    if (text != renderedText) {
        if (renderTexture) Destroy(renderTexture);

        renderTexture = new RenderTexture(textureWidth, textureHeight, 0, RenderTextureFormat.ARGB32);
        targetMaterial.mainTexture = renderTexture;

        var altRenderer = (new GameObject()).AddComponent.<SystemFontRendererForEditor>();

        altRenderer.target = renderTexture;
        altRenderer.text = text;
        altRenderer.fontSize = textSize;
        
        altRenderer.DoRender();

        renderedText = text;
    }
}

#else

#if UNITY_ANDROID

static private var unityPlayerClass : AndroidJavaClass;
static private var currentActivity : AndroidJavaObject;

#elif UNITY_IPHONE

@DllImportAttribute("__Internal") static private function _SystemFontRendererRenderTextToTexture(text : String, width : int, height : int, textSize : int, textureID : int) {}

#endif

function BindMaterial(material : Material) {
    material.mainTexture = texture;
}

function Awake() {
    texture = new Texture2D(8, 8, TextureFormat.ARGB32, false);

#if UNITY_ANDROID
    if (unityPlayerClass == null) {
        unityPlayerClass = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
    }
#endif
}

function OnDestroy() {
    if (id >= 0) {
#if UNITY_ANDROID
        var activity = unityPlayerClass.GetStatic.<AndroidJavaObject>("currentActivity"); 
        activity.Call("removeTextEntry", id);
#endif
    }
    Destroy(texture);
}

function Update() {
#if UNITY_ANDROID
    if (currentActivity == null) {
        currentActivity = unityPlayerClass.GetStatic.<AndroidJavaObject>("currentActivity"); 
    }
#endif

    if (text != renderedText) {
        if (id >= 0) {
#if UNITY_ANDROID
            currentActivity.Call("removeTextEntry", id);
#endif
        }
        id = counter++;
        renderedText = text;
#if UNITY_IPHONE
        _SystemFontRendererRenderTextToTexture(text, textureWidth, textureHeight, textSize, texture.GetNativeTextureID());
#endif
    }

#if UNITY_ANDROID
    currentActivity.Call("setTextEntry", id, renderedText, textureWidth, textureHeight, textSize, texture.GetNativeTextureID());
#endif
} 

function LateUpdate() {
#if UNITY_ANDROID
    currentActivity = null;
#endif
}

#endif
