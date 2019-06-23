package com.example.enghacks_2019


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.enghacks_2019.databinding.FragmentHomeBinding
import com.example.enghacks_2019.util.getViewModel
import com.example.enghacks_2019.util.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    private lateinit var viewModel: ArduinoViewModel
    private lateinit var binding: FragmentHomeBinding
  //  private lateinit var adapter: MsgAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
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

        setHasOptionsMenu(true)

        binding.lifecycleOwner = this.viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupFab()
        //setupAdapter()
        setupSnackbar()
        setupDialog()
        //setupDisplay()

        //viewModel.getAll()
      //  viewModel.latestMsg()
    }
/*
    private fun setupDisplay() {
        viewModel.latest.observe(viewLifecycleOwner, Observer {
            binding.tvMsgDesc.text = it.msgCode
            binding.tvMsgTime.text = it.timeStamp.toString()
        })
    } */

    private fun setupFab() {
        // TODO
        binding.fab.setOnClickListener {
            Toast.makeText(this.context, "fab time", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupSnackbar() {
        viewModel.let {
            view?.setupSnackbar(this, it.toastMsg, Snackbar.LENGTH_SHORT)
        }
    }

    private fun setupDialog() {
        viewModel.popup.observe(this, Observer {
            val builder = AlertDialog.Builder(context!!, R.style.InfoDialogStyle)
            val positive: String
            val negative: String

            when (viewModel.popup.value) {
                R.string.dialog_alarm -> {
                    positive = "A"
                    negative = "B"
                }
                R.string.dialog_reset -> {
                    positive = "R"
                    negative = "Wack"
                }
                else -> {
                    positive = "A"
                    negative = "B"
                }
            }
            builder.setTitle("Howdy")
            builder.setMessage(getString(it))
            builder.setPositiveButton("Yes") { _, _ ->
                viewModel.sendBack(positive)
            }

            builder.setNegativeButton("No") { _, _ ->
                viewModel.sendBack(negative)
            }

            val dialog = builder.create()
            dialog.show()
        })
    }

    private fun setupAnimation() {
        viewModel.redAlert.observe(this, Observer {
            if(it) {
                red_alert.playAnimation()
            }
            else {
                red_alert.cancelAnimation()
            }
        })

    }
    /*

    private fun setupAdapter() {
        adapter = MsgAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.msgLog.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }

*/
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
            R.id.action_history -> {
                val direction = HomeFragmentDirections.actionHomeFragmentToListFragment()
                findNavController().navigate(direction)
                true
            }
            else -> false
        }

}
