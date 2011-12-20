#pragma strict

var prefab : GameObject;
var materials : Material[];

function Start() {
    for (var col = 0; col < 5; col++) {
        for (var row = 0; row < 6; row++) {
            var cube = Instantiate(prefab, Vector3(col * 1.2, row * 1.2, 0), Quaternion.identity) as GameObject;
            cube.renderer.material = materials[row % materials.Length];
        }
    }
}
