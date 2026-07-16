package com.example.demoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// --- 1. DATA MODELS ---
// These classes represent the JSON data we get from the API
data class User(
    val id: Int? = null,
    val name: String,
    val username: String,
    val email: String,
    val address: Address? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: Company? = null
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(val lat: String, val lng: String)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

// --- 2. RETROFIT SERVICE ---
// This interface defines the API calls (GET, POST, PUT, DELETE)
interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: User): User

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): retrofit2.Response<Unit>
}

// Retrofit instance creation
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

// --- 3. ACTIVITY ---
class ApiTestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ApiScreen()
                }
            }
        }
    }
}

// --- 4. UI SCREEN ---
@Composable
fun ApiScreen() {
    val scope = rememberCoroutineScope()
    var responseText by remember { mutableStateOf("Click a button to start") }
    var usersList by remember { mutableStateOf<List<User>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }

    // Sample user for POST and PUT
    val sampleUser = User(
        id = 1,
        name = "Leanne Graham1",
        username = "Bret",
        email = "Sincere@april.biz",
        address = Address(
            street = "Kulas Light",
            suite = "Apt. 556",
            city = "Gwenborough",
            zipcode = "92998-3874",
            geo = Geo("-37.3159", "81.1496")
        ),
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        company = Company(
            name = "Romaguera-Crona",
            catchPhrase = "Multi-layered client-server neural-net",
            bs = "harness real-time e-markets"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "API Test Dashboard",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Buttons Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        usersList = RetrofitClient.apiService.getUsers()
                        responseText = "GET: Success! Loaded ${usersList.size} users."
                    } catch (e: Exception) {
                        responseText = "GET Error: ${e.message}"
                    }
                    isLoading = false
                }
            }) { Text("GET") }

            Button(onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        val result = RetrofitClient.apiService.createUser(sampleUser)
                        responseText = "POST: Success! Created User ID: ${result.id}"
                        usersList = emptyList() // Clear list to show response
                    } catch (e: Exception) {
                        responseText = "POST Error: ${e.message}"
                    }
                    isLoading = false
                }
            }) { Text("POST") }

            Button(onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        val result = RetrofitClient.apiService.updateUser(1, sampleUser)
                        responseText = "PUT: Success! Updated User: ${result.name}"
                        usersList = emptyList()
                    } catch (e: Exception) {
                        responseText = "PUT Error: ${e.message}"
                    }
                    isLoading = false
                }
            }) { Text("PUT") }

            Button(onClick = {
                scope.launch {
                    isLoading = true
                    try {
                        val response = RetrofitClient.apiService.deleteUser(1)
                        if (response.isSuccessful) {
                            responseText = "DELETE: Success! User 1 deleted."
                        } else {
                            responseText = "DELETE Failed: ${response.code()}"
                        }
                        usersList = emptyList()
                    } catch (e: Exception) {
                        responseText = "DELETE Error: ${e.message}"
                    }
                    isLoading = false
                }
            }) { Text("DEL") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Status / Response Text
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
        ) {
            Text(
                text = responseText,
                modifier = Modifier.padding(16.dp),
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Loading Indicator
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally))
        }

        // List display for GET results
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(usersList) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = user.name, fontWeight = FontWeight.Bold)
                        Text(text = user.email, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
