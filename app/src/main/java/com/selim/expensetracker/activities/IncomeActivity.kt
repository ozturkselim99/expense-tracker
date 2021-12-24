package com.selim.expensetracker.activities

import android.Manifest
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.selim.expensetracker.R
import com.selim.expensetracker.data.MockData
import com.selim.expensetracker.databinding.ActivityIncomeBinding
import com.selim.expensetracker.utils.FirebaseUtils.storageRef
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class IncomeActivity : AppCompatActivity() {


    lateinit var filepath: Uri
    private val CAMERA_PERM_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    private val GALLERY_REQUEST_CODE = 105
    private var imageName: String? = null
    private var contentUri: Uri? = null
    var currentPhotoPath: String? = null


    private var selectedCategory: String? = null
    private var description: String? = null
    private var selectedBank: String? = null


    private var binding: ActivityIncomeBinding? = null
    private val selectedImage by lazy { findViewById<ImageView>(R.id.imageView34) }

    private lateinit var dialog: Dialog
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_income)

        bottomSheetDialog = BottomSheetDialog(this, R.style.AttachmentBottomSheetDialogTheme)
        dialog = Dialog(this)

        setupBottomSheet()
        setupSpinners()

        binding!!.addAttachmentIncome.setOnClickListener {
            bottomSheetDialog.show()
        }
        binding!!.addIncomeButton.setOnClickListener {
            addIncomeToFirebase()
        }
        binding!!.incomeBackButton.setOnClickListener {
            onBackPressed()
            finish()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.green_100)
    }


    private fun setupBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_modal, null)
        bottomSheetDialog.setContentView(view)
        view.findViewById<CardView>(R.id.image).setOnClickListener {
            startFileChooser()
        }
        view.findViewById<CardView>(R.id.camera).setOnClickListener {
            askCameraPermissions()
        }
    }

    private fun openCam() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, CAMERA_REQUEST_CODE)

    }

    private fun startFileChooser() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(gallery, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === CAMERA_REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val f = File(currentPhotoPath)
                selectedImage.setImageURI(Uri.fromFile(f))
                Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
                    val f = File(currentPhotoPath)
                    mediaScanIntent.data = Uri.fromFile(f)
                    sendBroadcast(mediaScanIntent)

                    contentUri = Uri.fromFile(f)
                    imageName = f.name

                    //uploadImageToFirebase(f.name, contentUri)
                }
            }
        }
        if (requestCode === GALLERY_REQUEST_CODE) {
            if (resultCode === RESULT_OK) {
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val imageFileName = "JPEG_" + timeStamp + contentUri?.let { getFileExt(it) }
                selectedImage.setImageURI(contentUri)

                contentUri = data!!.data
                imageName = imageFileName

                //uploadImageToFirebase(imageFileName, contentUri!!)
            }
        }
    }

    private fun getFileExt(contentUri: Uri): String? {
        val c = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(c.getType(contentUri))
    }


    private fun askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERM_CODE
            )
        } else {
            //openCam()
            dispatchTakePictureIntent()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //openCam()
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",   /* suffix */
            storageDir      /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.selim.expensetracker.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
                }
            }
        }
    }

    private fun uploadImageToFirebase(name: String, contentUri: Uri) {

        var pd = ProgressDialog(this)
        pd.setTitle("Uploading")
        pd.show()

        var image = storageRef.child("images/" + name)
        image.putFile(contentUri).addOnSuccessListener {
            dialog.setContentView(R.layout.transaction_successfully_dialog)
            dialog.show()
            Handler(Looper.getMainLooper()).postDelayed({
                dialog.dismiss()
                onBackPressed()
                finish()
            }, 1500)
            Toast.makeText(this, "Image Is Uploaded.", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Upload Failed.", Toast.LENGTH_SHORT).show()
        }.addOnProgressListener { po ->
            var progress = (100.00 * po.bytesTransferred)
            pd.setMessage("Uploaded ${progress.toInt()}")
        }
    }

    private fun setupSpinners() {
        val incomeCategoryFilterSpinnerAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getCategories()
        )
        val incomeWalletFilterSpinnerAdapter = ArrayAdapter(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            MockData.getBanks()
        )
        binding!!.incomeCategoryFilterSpinner.setAdapter(incomeCategoryFilterSpinnerAdapter)
        binding!!.incomeWalletFilterSpinner.setAdapter(incomeWalletFilterSpinnerAdapter)

        binding!!.incomeWalletFilterSpinner
            .setOnItemClickListener { _, _, position, _ ->
                Toast.makeText(
                    this,
                    "${incomeWalletFilterSpinnerAdapter.getItem(position).toString()} selected",
                    Toast.LENGTH_SHORT
                ).show()
                selectedBank = incomeWalletFilterSpinnerAdapter.getItem(position).toString()
            }
        binding!!.incomeCategoryFilterSpinner.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                this,
                "${incomeCategoryFilterSpinnerAdapter.getItem(position).toString()} selected",
                Toast.LENGTH_SHORT
            ).show()
            selectedCategory = incomeCategoryFilterSpinnerAdapter.getItem(position).toString()
        }
    }

    private fun addIncomeToFirebase() {
        uploadImageToFirebase(imageName!!, contentUri!!)
    }
}