package com.faranjit.feedbacklist.base

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Bulent Turkmen on 15.03.2021.
 *
 * https://gist.github.com/Zhuinden/ea3189198938bd16c03db628e084a4fa#file-fragmentviewbindingdelegate-kt
 */
fun <T : ViewBinding> Fragment.viewBinding(initializer: (View) -> T?) =
    FragmentViewBindingDelegate(this, initializer)

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val initializer: (View) -> T?
) : ReadWriteProperty<Fragment, T?> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return initializer(thisRef.requireView()).also { this.binding = it }
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) {
        binding = value
    }
}