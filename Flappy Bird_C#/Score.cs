using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class Score : MonoBehaviour
{
    public static int score = 0;
    public static int bestScore = 0;
    float timer = 0;
    // Start is called before the first frame update
    void Start()
    {
        score = 0;
    }

    // Update is called once per frame
    void Update()
    {
        timer += Time.deltaTime;
        
        if(timer == 1)
        {
            score += (int)timer;
            timer = 0;
        }
        GetComponent<TextMeshProUGUI>().text = score.ToString();   

    }
}
