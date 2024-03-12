package com.example.kotlinstart3

import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    //1// private var itemList: MutableList<AppModel>? = null
    private var itemList = ArrayList<AppModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        //1// itemList = ArrayList<AppModel>()


        val adapter = AppInstalledAdapter(emptyList())

        recyclerview.adapter = adapter


        lifecycleScope.launch(Dispatchers.Default) {
            val packageManager = packageManager
            val appsData =  // do all loading in background thread
                packageManager.getInstalledPackages(0)
                    .filter { it.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0 }
                    .map {
                        AppModel(
                            it.applicationInfo.loadLabel(packageManager).toString(),
                            it.applicationInfo.loadIcon(packageManager)
                        )
                    }

            withContext(Dispatchers.Main) { adapter.addItems(appsData) }
        }


//        runOnUiThread {
//
//            val packageManager = packageManager
//            val appsData = packageManager.getInstalledPackages(0)
//                    .map {
//                        AppModel(
//                            it.applicationInfo.loadLabel(packageManager).toString(),
//                            it.applicationInfo.loadIcon(packageManager)
//                        )
//                    }
//
//
//
//
//            for (test in appsData)
//            {
//                itemList.add(test)
//            }
//
//
//        }


    }


}


