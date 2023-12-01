package com.birkancekic.widgetkulanimi

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.birkancekic.widgetkulanimi.databinding.ActivityMainBinding
import java.net.BindException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var kontrol= false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOku.setOnClickListener {
            val alinanVeri= binding.editTextGirdi.text.toString()
            binding.textViewSonuc.text= alinanVeri
        }
        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.resim1)
        }
        binding.buttonResim2.setOnClickListener {
            binding.imageView.setImageResource(
                resources.getIdentifier("resim2","drawable",packageName)
            )
        }
        binding.switch1.setOnCheckedChangeListener { compoundButton, isChecked ->

            if (isChecked){
                Log.e("Widgets","Switch: ON")
            }else{
                Log.e("Widgets","Switch: OF")

            }
        }
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            kontrol=isChecked
            if (isChecked){
            val secilenButton= findViewById<Button>(binding.toggleButton.checkedButtonId)
            val buttonYazi = secilenButton.text.toString()
            Log.e("Widgets",buttonYazi)}
        }

        val ulkeler= ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")


        val arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,ulkeler)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility=View.VISIBLE
        }
        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility=View.INVISIBLE
        }

        binding.textViewSlider.text= binding.slider.progress.toString()

        binding.slider.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.textViewSlider.text=p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
            
        })


        binding.buttonGoster.setOnClickListener {
            Log.e("Widgets","Switch Durum: ${binding.switch1.isChecked}")
            if (kontrol) {
                val secilenButton = findViewById<Button>(binding.toggleButton.checkedButtonId)
                val buttonYazi = secilenButton.text.toString()
                Log.e("Widgets", "Toggle durum: $buttonYazi")
            }
            val ulke=binding.autoCompleteTextView.text.toString()
            Log.e("Widgets","Ülke: $ulke")
            Log.e("Widgets","Ülke: ${binding.slider.progress}")
        }

    }
}