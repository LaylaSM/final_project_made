package com.laylamac.finalprojectmade.view.settings

import android.os.Bundle
import com.laylamac.finalprojectmade.R
import com.laylamac.finalprojectmade.base.BaseAppCompatActivity

class SettingsActivity : BaseAppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        loadFragment(SettingsFragment())
    }

    private fun loadFragment(settingFragment: androidx.fragment.app.Fragment): Boolean {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container_setting, settingFragment).commit()
        return true

    }
}
