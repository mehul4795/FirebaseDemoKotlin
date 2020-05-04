package tec.com.firebase.demo.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Database reference pointing to root of database
        val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference

        // Database reference pointing to demo node
        val demoRef: DatabaseReference = rootRef.child("demo")

        btnSubmit.setOnClickListener {
            val value: String = etValue.text.toString()

            // Push creates a unique id in database
            demoRef.setValue(value)
        }

        btnFetch.setOnClickListener {
            demoRef.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    tvValue.text = value
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@MainActivity, "Error fetching data", Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
    }
}
