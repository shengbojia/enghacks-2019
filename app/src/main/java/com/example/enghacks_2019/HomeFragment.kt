package com.example.enghacks_2019


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.enghacks_2019.databinding.FragmentHomeBinding
import com.example.enghacks_2019.util.getViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: ArduinoViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        viewModel.setupListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeArduino()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = getViewModel()

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    private fun setupFab() {
        // TODO
        binding.fab.setOnClickListener {
            Toast.makeText(this.context, "fab time", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        // TODO
        when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this.context, "Yeet", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }

}
