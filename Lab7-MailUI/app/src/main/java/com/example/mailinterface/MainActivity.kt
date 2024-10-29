package com.example.mailinterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        // Create sample email data
        val emailList = listOf(
            Email("Nguyen Cao Ky", "20215072", "Reminder: Submit assignment before 23:59PM 29 Oct, 2024", "23:48 PM"),
            Email("John Smith", "Team Meeting", "The team meeting has been scheduled for Thursday at 10 AM. Please let me know if you have any conflicts.", "09:30 AM"),

                    Email("Karen Lee", "Budget Approval", "The budget proposal has been approved, and funds will be released by end of the month.", "11:15 AM"),

        Email("Michael Brown", "Client Feedback", "The client provided positive feedback on the recent deliverables and requested a few minor adjustments.", "03:45 PM"),

        Email("Sarah Thompson", "Code Review", "The code review session is scheduled for tomorrow at 2 PM. Please ensure your code is up-to-date.", "08:50 AM"),

        Email("David Wilson", "Quarterly Report", "The quarterly report draft is ready for review and will be finalized by the end of the week.", "04:20 PM"))
        val adapter = EmailAdapter(emailList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        // Floating Action Button click action
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "Compose Email", Toast.LENGTH_SHORT).show()
        }
    }
}