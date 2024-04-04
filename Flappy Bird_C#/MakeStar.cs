using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MakeStar : MonoBehaviour
{
    public GameObject Star;
    public GameObject StarSet;

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

        if (timer >= timeDiff)
        {
            GameObject newStar = Instantiate(Star) as GameObject;
            newStar.transform.position = new Vector3(6, Random.Range(4.0f, -2.5f), 0);
            timer = 0;
            Destroy(newStar, 10.0f);
        }

    }

}
