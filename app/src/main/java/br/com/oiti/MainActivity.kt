package br.com.oiti

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.com.oiti.liveness3d.app.ui.Liveness3DActivity
import br.com.oiti.liveness3d.data.model.ENVIRONMENT3D
import br.com.oiti.liveness3d.data.model.Liveness3DUser

class MainActivity : AppCompatActivity() {

    private var startActivityForResult: ActivityResultLauncher<Intent>? = null
    private val appKey = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applyListeners()
        registerActivity()
    }

    private fun applyListeners() {
        findViewById<Button>(R.id.btLiveness3d).setOnClickListener {
            startLiveness3D()
        }
    }
    private fun startLiveness3D() {
        val liveness3DUser = Liveness3DUser(appKey = appKey, ENVIRONMENT3D.HML, null)
        startActivityForResult?.launch(Intent(this, Liveness3DActivity::class.java).apply {
            putExtra(Liveness3DActivity.PARAM_LIVENESS3D_USER, liveness3DUser)
        })
    }

    private fun registerActivity() {
        startActivityForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                when (result.resultCode) {
                    Activity.RESULT_OK -> onLiveness3DResultOk(result)
                    Activity.RESULT_CANCELED -> onLiveness3DResultCancelled(result)
                }
            }
    }

    private fun onLiveness3DResultCancelled(result: ActivityResult) {
        print(result.data)
    }

    private fun onLiveness3DResultOk(result: ActivityResult) {
        print(result.data)
    }
}
