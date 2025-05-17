package com.example.formulirfgdapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formulirfgdapps.databinding.ActivityDetailBinding
import com.example.formulirfgdapps.model.Participant

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val participant = intent.getParcelableExtra<Participant>("Participant")
        participant?.let{
            binding.txtNamaResult.text = it.nama
            binding.txtTelefonResult.text = it.telefon
            binding.txtEmailResult.text = it.email
            binding.txtGenderResult.text = it.gender
            binding.txtSkillsetResult.text = it.skillset.joinToString(", ")
            binding.txtKategoriResult.text = it.category
        }

        binding.btnInfoDevelover.setOnClickListener {
            val bottomSheet = InfoDeveloper()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

    }
}