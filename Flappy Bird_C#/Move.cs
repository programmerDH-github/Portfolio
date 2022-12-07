using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Move : MonoBehaviour
{
    public float speed;
    void Start()
    {
        
    }

    void Update()
    {
        transform.position += Vector3.left * Time.deltaTime * speed;
    }
}
