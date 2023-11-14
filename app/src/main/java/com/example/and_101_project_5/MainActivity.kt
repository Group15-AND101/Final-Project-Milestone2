package com.example.and_101_project_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    // holds data to be sent to Views in "apod_items.xml"
    private lateinit var countryNames : MutableList<String>
    private lateinit var countryFlags : MutableList<String>
    private lateinit var countryCoatOfArms : MutableList<String>
    private lateinit var countryPopulations : MutableList<String>

    // user interaction elements in top half of screen
    private lateinit var searchButton: ImageButton
    private lateinit var searchBar: TextView

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

        // pulls country date from restcountries API
        getCountryData()

        // variables that reference textviews in top half of screen
        var countryName = findViewById<TextView>(R.id.countryName)
        var gdp = findViewById<TextView>(R.id.gdp)
        var gdpGrowth = findViewById<TextView>(R.id.gdpGrowth)
        var unemploymentRate= findViewById<TextView>(R.id.unemploymentRate)
        var leMale = findViewById<TextView>(R.id.leMale)
        var leFemale = findViewById<TextView>(R.id.leFemale)
        var homicideRate = findViewById<TextView>(R.id.homicideRate)


        // references search bar and button
        searchBar = findViewById(R.id.searchBar)
        searchButton = findViewById(R.id.searchButton)

        // updates textviews in top half of screen every time the search button is pressed
        searchButton.setOnClickListener {

            // pulls data from api-ninjas API
            getCountryStats(
                searchBar.text.toString(),
                countryName,
                gdp,
                gdpGrowth,
                unemploymentRate,
                leMale,
                leFemale,
                homicideRate
            )

        }

    }

    private fun getCountryStats(
        searchTerm : String,
        countryName : TextView,
        gdp : TextView,
        gdpGrowth : TextView,
        unemploymentRate : TextView,
        leMale : TextView,
        leFemale : TextView,
        homicideRate : TextView
    ) {
        // stores country name
        var name = searchTerm

        //stores country name with spaces removed
        var modifiedName : String = ""

        // tracks index as we parse "name"
        var counter : Int = 0

        // removes spaces in country name
        while (counter < name.length) {

            if (name[counter] == ' ') {
                modifiedName = modifiedName + "%20"
                counter += 1
            }

            modifiedName = modifiedName + name[counter]
            counter += 1
        }

        // sets "name" to "modifiedName"
        name = modifiedName

        val apiKey = "XfCCE2PbqO1NHMrsjrZg1Q==QHwkRyVMX1PQrmaG"

        // create URL for individual country
        val apiURL = "https://api.api-ninjas.com/v1/country?name=" + name + "&X-Api-Key=" + apiKey

        //calling api-ninjas API
        val client = AsyncHttpClient()

        client[apiURL,
                object : JsonHttpResponseHandler() {

                    override fun onSuccess(statusCode2: Int, headers2: Headers, json2: JSON) {
                        // print to logcat to see if response is successful
                        Log.d("api-ninjas", "api_url: $apiURL")
                        Log.d("api-ninjas", "response successful$json2")
                        Log.d("api-ninjas", "--------------------------------------------------------------------------------------")

                        val jsonArray2 = json2.jsonArray

                        // handles empty JSON array
                        if (jsonArray2.length() == 0) {
                            gdp.text = "data not found"
                            gdpGrowth.text = "data not found"
                            unemploymentRate.text = "data not found"
                            leMale.text = "data not found"
                            leFemale.text = "data not found"
                            homicideRate.text = "data not found"

                        } else {

                            // handles empty JSON object
                            if (jsonArray2.getJSONObject(0).toString() == "{}") {
                                gdp.text = "data not found"
                                gdpGrowth.text = "data not found"
                                unemploymentRate.text = "data not found"
                                leMale.text = "data not found"
                                leFemale.text = "data not found"
                                homicideRate.text = "data not found"

                            } else {
                                // handles missing keys

                                if (jsonArray2.getJSONObject(0).has("name")) {
                                    countryName.text = jsonArray2.getJSONObject(0).getString("name")
                                }

                                if (jsonArray2.getJSONObject(0).has("gdp")) {
                                    gdp.text = "GDP: " + jsonArray2.getJSONObject(0).getInt("gdp").toString()
                                }

                                if (jsonArray2.getJSONObject(0).has("gdp_growth")) {
                                    gdpGrowth.text = "GDP Growth: " + jsonArray2.getJSONObject(0).getDouble("gdp_growth").toString()
                                }

                                if (jsonArray2.getJSONObject(0).has("unemployment")) {
                                    unemploymentRate.text = "Unemployment Rate: " + jsonArray2.getJSONObject(0).getDouble("unemployment").toString()
                                }

                                if (jsonArray2.getJSONObject(0).has("life_expectancy_male")) {
                                    leMale.text = "Life Expectancy (M): " + jsonArray2.getJSONObject(0).getDouble("life_expectancy_male").toString()
                                }

                                if (jsonArray2.getJSONObject(0).has("life_expectancy_female")) {
                                    leFemale.text = "Life Expectancy (F): " + jsonArray2.getJSONObject(0).getDouble("life_expectancy_female").toString()
                                }

                                if (jsonArray2.getJSONObject(0).has("homicide_rate")) {
                                    homicideRate.text = "Homicide Rate: " + jsonArray2.getJSONObject(0).getDouble("homicide_rate").toString()
                                }


                            }

                        }

                    }

                    override fun onFailure(statusCode: Int, headers: Headers?, errorResponse: String, throwable: Throwable?) {
                        Log.d("api-ninjas Error", errorResponse)
                    }

                }

        ]

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

                        //get country names from embeded JSON objects and stores them in "us"
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