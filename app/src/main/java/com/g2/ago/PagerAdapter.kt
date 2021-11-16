package com.g2.ago

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return RankingFragment.newInstance("ejemplo1", getAquaticAnimals())
            1->return RankingFragment.newInstance("ejemplo1", getAquaticAnimals())
        }
        return RankingFragment.newInstance("ejemplo1", getAquaticAnimals())
    }
    fun getAquaticAnimals():ArrayList<Partidas>{
        val aquaticAnimals = ArrayList<Partidas>()
        aquaticAnimals.add(Partidas("1", "1"))
        aquaticAnimals.add(Partidas("2", "2"))
        aquaticAnimals.add(Partidas("3", "3"))
        return aquaticAnimals
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Ejemplo ${position+1}"
    }
}