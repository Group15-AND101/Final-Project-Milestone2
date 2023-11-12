package com.example.and_101_project_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    // holds data to be sent to Views in "apod_items.xml"
    private lateinit var countryNames : MutableList<String>
    private lateinit var countryFlags : MutableList<String>
    private lateinit var countryCoatOfArms : MutableList<String>
    private lateinit var countryPopulations : MutableList<String>

    // holds RecyclerView
    private lateinit var countryRv : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize lists
        countryNames = mutableListOf()
        countryFlags = mutableListOf()
        countryCoatOfArms = mutableListOf()
        countryPopulations = mutableListOf()

        // links RecyclerView variable to RecyclerView
        countryRv = findViewById(R.id.apodList)

        getCountryData()
        Log.d("APODImageURL", "APOD image URL set")
    }

    private fun getCountryData() {

        // tool for querying APIs
        val client = AsyncHttpClient()

        // query restcountries API
        client["https://restcountries.com/v3.1/all",
                object : JsonHttpResponseHandler() {

                    override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                        // print to Logcat if the api query is successful
                        Log.d("restCountries", "response successful")
                        Log.d("restCountries", "response successful$json")

                        // extract JSON array from JSON response
                        val jsonArray = json.jsonArray

                        //get country names from embeded JSON objects and stores them in "countryNames"
                        for (i in 0 until jsonArray.length()) {
                            countryNames.add(jsonArray.getJSONObject(i).getJSONObject("name").getString("common"))
                            countryFlags.add(jsonArray.getJSONObject(i).getJSONObject("flags").getString("png"))

                            if (jsonArray.getJSONObject(i).getJSONObject("coatOfArms").toString() == "{}") {
                                countryCoatOfArms.add("{}")
                            } else if (jsonArray.getJSONObject(i).getJSONObject("coatOfArms").toString() != "{}") {
                                countryCoatOfArms.add(jsonArray.getJSONObject(i).getJSONObject("coatOfArms").getString("png"))
                            }

                            countryPopulations.add(jsonArray.getJSONObject(i).getInt("population").toString())
                        }

                        val adapter = APODAdapter(
                            countryNames,
                            countryFlags,
                            countryCoatOfArms,
                            countryPopulations
                        )

                        countryRv.adapter = adapter
                        countryRv.layoutManager = LinearLayoutManager(this@MainActivity)

                    }

                    override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                        Log.d("restCountries Error", errorResponse)
                    }

                }

            ]

    }

}