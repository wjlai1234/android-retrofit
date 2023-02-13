# Android Evaluation Test
## Using retrofit (like fetch and axios fun to handle api call in android)
![image](https://user-images.githubusercontent.com/72342994/159893815-0c9a5f78-cc71-4815-b9a5-ff71da3726a6.png)

# Application Flow<
![image](https://user-images.githubusercontent.com/72342994/159894064-7efad5a0-ccb8-4305-b464-c715559bf2a3.png)

## Step 1
```bash
pip install foobar
```
## Step 1
<p>Go to here: https://developer.nytimes.com</p>
* 1.login

* 2.click the Apps below ur acc
  ![image](https://user-images.githubusercontent.com/72342994/159895822-70ed068d-52db-4fab-b8c1-f9420ef50f9e.png)
  
* 3.add new App
  ![image](https://user-images.githubusercontent.com/72342994/159895947-ea9ec71f-c184-42f3-8704-cdce560effb3.png)
  
* 4.copy the api key
  ![image](https://user-images.githubusercontent.com/72342994/159896088-adee3555-b814-441a-a7ea-9853b3eae465.png)
  
* 5.Go API -> choose top stories API
  ![image](https://user-images.githubusercontent.com/72342994/159896267-95bce944-def4-46f2-b5a4-23e6eefe5a85.png)

## Step2
open file archi struture

```bash
com.example.mytest
->adapter
  ->ArticleAdapter
->api
  ->apiInterface
->viewModel
  ->ArticleViewModel
->DAO
  ->Articles
  ->Result
```
- rmb import plugin
![image](https://user-images.githubusercontent.com/72342994/159897512-e80fb659-9095-4f4b-b0f9-bc1114f79d2a.png)

- open the "json to Data"
![image](https://user-images.githubusercontent.com/72342994/159897585-10a40f23-1db3-4d69-ba38-c9581e6aba9a.png)

- put json inside will direct generate the dao class file
![image](https://user-images.githubusercontent.com/72342994/159897614-22ef8117-52e7-416f-bbcb-927112c20e26.png)


## Step3
bulid.gradle (modules)
```bash
 buildFeatures {
        dataBinding true
        viewBinding true
    }

 //dependencies retrofit/volley
 
dependencies {
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
 }
```

## Step4
create Interface to call get method
->Call: to request and get the callback to return response
```bash
    @GET("v2/arts.json?api-key=HCF6a8QBQsCGcFLcr3rnuX1ikbwV9H3S")
    fun getData(): Call <Articles>
```

## Step5
```bash
//build retrofilt
   val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()
//enqueue to execute it 
        retrofitData.enqueue(object : Callback<Articles?> {
            override fun onResponse(
                call: Call<Articles?>,
                response: Response<Articles?>
            ) {
                val responseBody = response.body()!!
                val myStringBuilder = StringBuilder()
                myStringBuilder.append(responseBody)
                //binding.textView.text = myStringBuilder
                Log.i("MainActivtyS", ":${responseBody.results.size}")
                for (myData in responseBody.results) {
                        newArrayList.add(myData)
                    }
                temArrayList.addAll(newArrayList)
                
  # put into adapter
                binding.apply {
                    rvArt.apply {
                        layoutManager =
                            LinearLayoutManager(
                                activity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        adapter = ArticlesAdapter(responseBody.results)
                    }
                    textView.text ="Top Article for you {${temArrayList.size}}"
                }
            }
```


# Production
- all result
  ![image](https://user-images.githubusercontent.com/72342994/159898016-2f07abd9-3708-4982-98cb-c60eca6e0299.png)
  
- search result based on the query
 ![image](https://user-images.githubusercontent.com/72342994/159898124-bf48e4f2-5cda-429e-bfae-94e22ffd4a14.png)


