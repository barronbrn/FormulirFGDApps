/*
Nama        : Dani Herawan
NIM         : 10122331
Kelas       : P.ANDRO 4
Tanggal     : 17 Mei 2025
Jam Selesai : 20:40
*/

package com.example.formulirfgdapps

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formulirfgdapps.databinding.ActivityMainBinding
import com.example.formulirfgdapps.model.Participant

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val categories = listOf("Akademisi","Bisnis","Komunitas","Pemerintah","Media","Organisasi","NGO")

    private val skillMap by lazy {
        listOf(
            Pair(binding.chkAlgo,"Algoritma"),
            Pair(binding.chkProblemSolving,"Problem Solving"),
            Pair(binding.chkPrograming,"Programing"),
            Pair(binding.chkDesignthinking,"Design Thinking"),
            Pair(binding.chkCriticalthinking,"Critical Thinking")
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Adapter Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spCategory.adapter = adapter


        binding.btnSubmit.setOnClickListener {
            val name = binding.edtNama.text.toString()
            val phone = binding.edtTelefon.text.toString()
            val email = binding.edtEmail.text.toString()
            val gender = when {
                binding.radLakilaki.isChecked -> "Laki-laki"
                binding.radPerempuan.isChecked -> "Perempuan"
                else -> ""
            }
            val skillset = skillMap.filter { it.first.isChecked }.map { it.second }
            if (skillset.isEmpty()){
                Toast.makeText(this,"Pilih Minimal Satu Skill",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val category = binding.spCategory.selectedItem.toString()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || gender.isEmpty()){
                Toast.makeText(this,"Semua Field Harus Diisi",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val participant = Participant(name,phone,email,gender, skillset, category)
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("Participant",participant)
            startActivity(intent)

        }

    }
}