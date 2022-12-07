using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using UnityEngine.UI;
using TMPro;

public class BestScore : MonoBehaviour
{

    void Start()
    {
        //GetComponent<TextMeshProUGUI>().text = Score.score.ToString();
        if (Score.score > Score.bestScore)
        {
            Score.bestScore = Score.score;
            GetComponent<TextMeshProUGUI>().text = "BestScore : " + Score.score.ToString();
        }
        else
        {
            GetComponent<TextMeshProUGUI>().text = "BestScore : " + Score.bestScore.ToString();
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
