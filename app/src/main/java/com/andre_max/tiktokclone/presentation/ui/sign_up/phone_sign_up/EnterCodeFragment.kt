package com.andre_max.tiktokclone.presentation.ui.sign_up.phone_sign_up

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andre_max.tiktokclone.R
import com.andre_max.tiktokclone.databinding.FragmentEnterCodeBinding
import com.andre_max.tiktokclone.userVerificationId
import com.andre_max.tiktokclone.utils.ResUtils
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    lateinit var binding: FragmentEnterCodeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)
        val codeView = binding.inputCode

        binding.signUpBtn.setOnClickListener {
            if (codeView.code.length != codeView.codeLength) {
                ResUtils.showToast(requireContext(), R.string.code_invalid)
                codeView.clearCode()
            } else getUserCredential(codeView.code)
        }

    }

    private fun getUserCredential(code: String) {
        val credential =
            PhoneAuthProvider.getCredential(userVerificationId.toString(), code) as AuthCredential

        findNavController()
            .navigate(
                EnterCodeFragmentDirections
                    .actionEnterCodeFragmentToCreateUsernameFragment(credential, null, null)
            )
    }

}