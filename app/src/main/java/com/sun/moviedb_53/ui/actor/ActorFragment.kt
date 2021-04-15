package com.sun.moviedb_53.ui.actor

import android.view.View
import androidx.core.os.bundleOf
import com.sun.moviedb_53.R
import com.sun.moviedb_53.base.BaseFragment

class ActorFragment : BaseFragment() {

    private var idActor: Int? = null

    override fun getLayoutId() = R.layout.fragment_actor

    override fun onViewCreated(view: View) {
        arguments?.let {
            idActor = it.getInt(ID_ACTOR_DETAIL)
        }
    }

    companion object {
        private const val ID_ACTOR_DETAIL = "ID_ACTOR_DETAIL"

        fun newInstance(id: Int) = ActorFragment().apply {
            arguments = bundleOf(ID_ACTOR_DETAIL to id)
        }
    }
}
