package com.uc3m.foodstuff.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uc3m.foodstuff.R
import com.uc3m.foodstuff.login.LoggedUserRepo

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private val loggedUserRepo by lazy { LoggedUserRepo(requireContext()) }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_username)
        textView.text = "Hello, ${loggedUserRepo.getLoggedUser()}."
        /*profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
}