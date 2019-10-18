package ritwik.samples.spacex.common

import android.content.Context

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

/**Base [Fragment] for abstracting common methods and automating their calls.
 * @author Ritwik Jamuar.*/
abstract class BaseFragment : Fragment () {

	/*------------------------------------ Fragment Callbacks ------------------------------------*/

	override fun onCreateView (
		inflater : LayoutInflater,
		container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View? {
		return if ( isDataBinding () ) {
			applyBinding ( inflater, container, savedInstanceState )
		} else {
			inflater.inflate (getLayoutRes (), container, false )
		}
	}

	override fun onViewCreated (view : View, savedInstanceState : Bundle? ) {
		super.onViewCreated ( view, savedInstanceState )
		initializeViews ( view )
	}

	override fun onAttach ( context : Context) {
		super.onAttach ( context )
		setListener ( context )
	}

	override fun onDetach () {
		super.onDetach ()
		cleanUp ()
	}

	/*------------------------------------- Abstract Methods -------------------------------------*/

	/**Fetches Resource ID of Layout of child [Fragment].
	 * Extending [Fragment] should provide the Layout Resource ID.
	 * @return [Int] containing Layout Resource ID.*/
	abstract fun getLayoutRes () : Int

	/**Instantiate the Views of extending [Fragment].
	 * @param view Reference of [View] of [Fragment].*/
	abstract fun initializeViews ( view : View)

	/**Instantiate the interface which is implemented by the container [android.app.Activity].
	 * @param context [Context] of [android.app.Activity].*/
	abstract fun setListener ( context : Context)

	/**Cleans up all the variable reference used in the extending Fragment.*/
	abstract fun cleanUp ()

	/**Fetches Tag from extending [Fragment].
	 * For extending [Fragment]s, return Simple Name of the [Fragment] class.
	 * @return [String] denoting the Tag of the [Fragment].*/
	abstract fun tag () : String

	/**Tells that the implementing [Fragment] is using Data Binding Library.
	 * To implementing Fragments, Return true only when the implementing [Fragment] is using Data
	 * Binding Library or not, else, always return false.
	 * @return [Boolean] that denotes whether Data Binding is used for the implementing
	 * [Fragment] or not.*/
	abstract fun isDataBinding () : Boolean

	/**Tells the implementing [Fragment] to implement their Data Bindings and then return the [View]
	 * from the Data Binding.
	 * If Data Binding is not used, then simply return null in conjunction with returning false
	 * to [isDataBinding].
	 * @return [View] from Data Binding, if the implementing Fragment used Data Binding, else null.*/
	abstract fun applyBinding (
		inflater : LayoutInflater,
		container : ViewGroup?,
		savedInstanceState : Bundle?
	) : View?

}