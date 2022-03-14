package com.crystal.timeisgold

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.crystal.timeisgold.databinding.ActivityMainBinding
import com.crystal.timeisgold.fragments.RecordFragment
import com.crystal.timeisgold.fragments.SettingsFragment
import com.crystal.timeisgold.fragments.StopWatchFragment
import com.crystal.timeisgold.fragments.TargetFragment

lateinit var binding: ActivityMainBinding

private const val TAG_STOP_WATCH = "stop_watch_fragment"
private const val TAG_RECORD = "record_fragment"
private const val TAG_TARGET = "target_fragment"
private const val TAG_SETTINGS = "settings_fragment"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setFragment(TAG_STOP_WATCH, StopWatchFragment())

        setupEvents()






    }

    private fun setupEvents() {
        binding.mainNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.stop_watch_fragment -> setFragment(TAG_STOP_WATCH, StopWatchFragment())
                R.id.record_fragment -> setFragment(TAG_RECORD, RecordFragment())
                R.id.target_fragment -> setFragment(TAG_TARGET, TargetFragment())
                R.id.settings_fragment -> setFragment(TAG_SETTINGS, SettingsFragment())

            }
            true
        }

    }

    //    프래그먼트 컨트롤 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

//        트랜잭션에 tag로 전달된 fragment가 없을 경우 add
        if (manager.findFragmentByTag(tag) == null) {
            ft.add(R.id.main_navi_fragment_container, fragment, tag)
        }

        val stopWatch = manager.findFragmentByTag(TAG_STOP_WATCH)
        val record = manager.findFragmentByTag(TAG_RECORD)
        val target = manager.findFragmentByTag(TAG_TARGET)
        val settings = manager.findFragmentByTag(TAG_SETTINGS)

        if (stopWatch != null) {
            ft.hide(stopWatch)
        }
        if (record != null) {
            ft.hide(record)
        }
        if (target != null) {
            ft.hide(target)
        }
        if (settings != null) {
            ft.hide(settings)
        }

        if(tag == TAG_STOP_WATCH) {
            if (stopWatch != null){
                ft.show(stopWatch)
            }
        } else if (tag == TAG_RECORD) {
            if (record != null) {
                ft.show(record)
            }
        } else if (tag == TAG_TARGET) {
            if (target != null) {
                ft.show(target)
            }
        } else if (tag == TAG_SETTINGS) {
            if (settings != null) {
                ft.show(settings)
            }
        }

        ft.commitAllowingStateLoss()


    }
}

