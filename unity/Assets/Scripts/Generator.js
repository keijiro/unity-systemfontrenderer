#pragma strict

var prefab : GameObject;

function Start() {
    for (var x = -2.5; x <= 2.5; x += 1.0) {
        for (var y = -3.0; y <= 3.0; y += 1.0) {
            Instantiate(prefab, Vector3(x, y, 0), Quaternion.identity);
        }
    }
}
