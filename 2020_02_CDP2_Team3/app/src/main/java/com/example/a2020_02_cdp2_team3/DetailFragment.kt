package com.example.a2020_02_cdp2_team3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onStart() {
        super.onStart()
        DetailStart()
    }

    private fun DetailStart()
    {

        val btn1 = view?.findViewById<Button>(R.id.sex)
        val btn2 = view?.findViewById<Button>(R.id.age)
        val btn3 = view?.findViewById<Button>(R.id.area)
        val manager = getChildFragmentManager()
        val transaction = manager.beginTransaction()


        btn1?.setOnClickListener()
        {
            //sex.kt 프래그먼트로 화면변환이 안됌 ****
            transaction.replace(R.id.child_fragment, sex())

            transaction.commit()




        }
        btn2?.setOnClickListener()
        {
            transaction.replace(R.id.child_fragment, age())

            transaction.commit()
            //age.kt 프래그먼트로 화면변환이 안됌 ****


        }
        btn3?.setOnClickListener()
        {
            transaction.replace(R.id.child_fragment, area())

            transaction.commit()

            //area.kt 프래그먼트로 화면변환이 안됌 ****

        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

