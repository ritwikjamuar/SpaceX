package ritwik.samples.spacex.ui.main.fragments

import android.content.Context

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer

import androidx.recyclerview.widget.RecyclerView

import ritwik.samples.spacex.R

import ritwik.samples.spacex.common.BaseFragment

import ritwik.samples.spacex.components.adapters.RocketListAdapter

import ritwik.samples.spacex.pojo.rockets.Rocket

/**[androidx.fragment.app.Fragment] to show the list of all the Rockets used by SpaceX.
 * @author Ritwik Jamuar.*/
class RocketListFragment : BaseFragment () {

	// Views.
	private var recyclerView : RecyclerView? = null

	// Adapters.
	private var adapter : RocketListAdapter? = null

	// Listeners.
	private var listener : Listener? = null

	/*------------------------------------- Companion Object -------------------------------------*/

	companion object {

		/**Factory method to create a new instance of [RocketListFragment].
		 * @return A new instance of [RocketListFragment].*/
		@JvmStatic
		fun create () = RocketListFragment ()

	}

	/*----------------------------------------- Observers ----------------------------------------*/

	private val allRocketsObserver = Observer < List < Rocket > > {
		adapter?.replaceLaunchesList ( it )
	}

	/*---------------------------------- BaseFragment Callbacks ----------------------------------*/

	override fun getLayoutRes () : Int = R.layout.fragment_rocket_list

	override fun initializeViews ( view : View ) {
		initializeRecyclerView ( view )
		attachObservers ()
		getRockets ()
	}

	override fun setListener ( context : Context ) {
		if ( context is Listener ) {
			listener = context
		} else {
			throw RuntimeException ( "$context must implement Listener" )
		}
	}

	override fun cleanUp () {
		listener = null
		recyclerView = null
		adapter = null
	}

	override fun tag () : String = RocketListFragment::class.java.simpleName

	override fun isDataBinding () : Boolean = false

	override fun applyBinding (
		inflater : LayoutInflater,
		container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? = null

	/*-------------------------------------- Private Methods -------------------------------------*/

	private fun initializeRecyclerView ( view : View ) {
		recyclerView = view.findViewById ( R.id.fragment_rocket_list_recycler_view )

		listener?.let { listener ->
			adapter = RocketListAdapter ( listener.getVM () )
		}

		recyclerView?.adapter = adapter
	}

	private fun attachObservers () {
		listener?.getVM ()?.allRocketsLiveData?.observe (this, allRocketsObserver )
	}

	private fun getRockets () {
		listener?.getVM ()?.getRockets ()
	}

	/*---------------------------------------- Interfaces ----------------------------------------*/

	/**Interface that acts as a Listener for [VehicleFragment] to whoever implements it.*/
	interface Listener : MainFragmentListener

}