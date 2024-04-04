using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MakePipe : MonoBehaviour
{
    public GameObject Pipe;

    public float timeDiff;

    float timer = 0;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        timer += Time.deltaTime;

        if(timer >= timeDiff)
        {
            GameObject newPipe = Instantiate(Pipe);
            newPipe.transform.position = new Vector3(9, Random.Range(4.0f, -2.5f), 0);
            timer = 0;
            Destroy(newPipe, 10.0f);
        }
        
    }
}
