
package com.example.my_application.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.my_application.R
import com.example.my_application.databinding.FragmentLoginBinding
import com.example.my_application.utils.SessionManager

class LoginFragment : Fragment() {
    // Binding reference (cleared when view is destroyed)
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate binding
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            Log.d("LoginFragment", "Login requested for: $email")
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Email and Password are required",
                    Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
            }
        }
        viewModel.authResult.observe(viewLifecycleOwner) { result ->
            Log.d("LoginFragment", "authResult: $result")
            result.onSuccess { authResponse ->
                Toast.makeText(
                    requireContext(),
                    "Welcome ${authResponse.user.profile.username}",
                    Toast.LENGTH_LONG
                ).show()
                val session = SessionManager(requireContext().applicationContext)
                session.saveAuthToken(authResponse.tokens.accessToken)

                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }.onFailure { error ->
                Toast.makeText(
                    requireContext(),
                    "Login failed: ${error.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Avoid memory leaks
        _binding = null
    }
}