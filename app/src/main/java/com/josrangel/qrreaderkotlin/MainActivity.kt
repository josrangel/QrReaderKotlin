package com.josrangel.qrreaderkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

/**
 * Source:
 * https://cursokotlin.com/zxing-leer-qr-codigos-de-barras-en-kotlin/#:~:text=Zxing%20es%20una%20librer%C3%ADa%20que%20llevo%20a%C3%B1os%20utilizando,de%20barras.%20Listado%20de%20formatos%20compatibles%20con%20Zxing.
 */

class MainActivity : AppCompatActivity() {

    lateinit var btnStartScanner: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartScanner = findViewById(R.id.btn_start_scanner)
        btnStartScanner.setOnClickListener({ view -> initScanner() })
    }

    fun initScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setBarcodeImageEnabled(true)
        integrator.setBeepEnabled(true)
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents != null) {
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}