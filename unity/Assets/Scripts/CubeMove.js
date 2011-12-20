#pragma strict

private var ry : float = Random.Range(30.0, 180.0);

function Update() {
    transform.localRotation = Quaternion.AngleAxis(ry * Time.deltaTime, Vector3.up) * transform.localRotation;
}
