package com.example.enghacks_2019


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enghacks_2019.firebase.CloudAdapter
import com.example.enghacks_2019.firebase.CloudMessage
import com.example.enghacks_2019.util.getViewModel
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {
    private lateinit var viewModel: ArduinoViewModel
    private lateinit var adapter: CloudAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val query = viewModel.logRef.orderBy("timeStamp", Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<CloudMessage>()
            .setQuery(query, CloudMessage::class.java)
            .build()

        adapter = CloudAdapter(options)

        recycler_firestore.setHasFixedSize(true)
        recycler_firestore.layoutManager = LinearLayoutManager(this.context)
        recycler_firestore.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }


}
