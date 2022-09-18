package com.example.allpicture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Switch
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.allpicture.dat.ImageData
import com.example.allpicture.dat.Result
import com.example.allpicture.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() ,Listener{
    lateinit var myAdaptor: imageAdaptor
    private  var imageResult= mutableListOf<Result>()
    lateinit var binding: ActivityMainBinding
    var page=1;
    var totalpage=-1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        myAdaptor= imageAdaptor(this@MainActivity, imageResult,this)
        binding.imageList.adapter=myAdaptor
        val layoutmanager=LinearLayoutManager(this@MainActivity)
        binding.imageList.layoutManager=layoutmanager







        getImage("coding")
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                binding.searchView.clearFocus()
                if (p0 !=null){
                    getImage(p0)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun getImage(search:String) {
        val image=imageServices.photoInstans.getImage(page,search)
        image.enqueue(object :Callback<ImageData>{
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                val image=response.body()
                if (image!=null){
                    totalpage=image.total_pages
                    Log.d("shafi",image.toString())
                    imageResult.clear()
                    imageResult.addAll(image.results)
                    myAdaptor.notifyDataSetChanged()

                }

            }

            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                Log.d("shafi","bhai jaan failded hogaya hai ",t)
            }
        })
    }

    override fun onClickListener(position: Int) {
        Toast.makeText(this, "bhai jaan Success", Toast.LENGTH_SHORT).show()

//        Log.d("Safi","first total -${binding.imageList.layoutManager}")
    }


}