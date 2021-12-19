package com.maxnovikov.filmSearch.presentation.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
  @LayoutRes layoutRes: Int
) : Fragment(layoutRes) {

  private val logTag: String = this.javaClass.simpleName

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d(logTag, "onAttach")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(logTag, "onCreate")
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    Log.d(logTag, "onCreateView")
    return super.onCreateView(inflater, container, savedInstanceState)
  }

  fun ignoreNextAnimation() {
    ignoreAnimationCount = 2
  }

  private var ignoreAnimationCount = 0
  override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
    Log.d(logTag, "onCreateAnimation")
    return if (ignoreAnimationCount > 0) {
      ignoreAnimationCount--
      if (ignoreAnimationCount == 1) {
        AlphaAnimation(1f, 0f).apply { duration = 300 }
      } else {
        AlphaAnimation(0f, 1f).apply { duration = 300 }
      }
    } else {
      super.onCreateAnimation(transit, enter, nextAnim)
    }
  }

  private val key = "Count"
  override fun onSaveInstanceState(outState: Bundle) {
    outState.putInt(key, ignoreAnimationCount--)
    super.onSaveInstanceState(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    savedInstanceState?.getInt(key)?.let {
      ignoreAnimationCount = it
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d(logTag, "onViewCreated")
  }

  override fun onStart() {
    super.onStart()
    Log.d(logTag, "onStart")
  }

  override fun onResume() {
    super.onResume()
    Log.d(logTag, "onResume")
  }

  override fun onPause() {
    super.onPause()
    Log.d(logTag, "onPause")
  }

  override fun onStop() {
    super.onStop()
    Log.d(logTag, "onStop")
  }

  override fun onDestroyView() {
    super.onDestroyView()
    Log.d(logTag, "onDestroyView")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(logTag, "onDestroy")
  }
}