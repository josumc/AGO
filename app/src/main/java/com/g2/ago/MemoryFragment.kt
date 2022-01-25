package com.g2.ago

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_memory.*

private const val TAG = "MemoryFragment"
class MemoryFragment : Fragment() {

    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    private var contador = 0
    private lateinit var bd:Base_de_Datos

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val images = mutableListOf(R.drawable.pareja11, R.drawable.pareja12, R.drawable.pareja21, R.drawable.pareja22, R.drawable.pareja31, R.drawable.pareja32, R.drawable.pareja41, R.drawable.pareja42, R.drawable.pareja51, R.drawable.pareja52)
        // Add each image twice so we can create pairs
        // Randomize the order of images
        images.shuffle()

        buttons = listOf(imageButton1, imageButton2, imageButton3, imageButton4, imageButton5, imageButton6, imageButton7, imageButton8, imageButton9, imageButton10)


        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }


        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "button clicked!!")
                // Update models
                updateModels(index)
                // Update the UI for the game
                updateViews()
            }
        }
        slideView(1)
    }
    private fun updateViews() {
        cards.forEachIndexed { index, card ->
            val button = buttons[index]
            if (card.isMatched) {
                button.alpha = 0.1f
            }
            button.setImageResource(if (card.isFaceUp) card.identifier  else R.drawable.logostz)
        }
    }

    private fun updateModels(position: Int) {
        val card = cards[position]
        // Error checking:
        if (card.isFaceUp) {
            return
        }
        // Three cases
        // 0 cards previously flipped over => restore cards + flip over the selected card
        // 1 card previously flipped over => flip over the selected card + check if the images match
        // 2 cards previously flipped over => restore cards + flip over the selected card
        if (indexOfSingleSelectedCard == null) {
            // 0 or 2 selected cards previously
            restoreCards()
            indexOfSingleSelectedCard = position
        } else {
            // exactly 1 card was selected previously
            checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp

    }

    private fun restoreCards() {
        for (card in cards ) {
            if (!card.isMatched) {
                card.isFaceUp = false
            }
        }
    }

    private fun checkForMatch(position1: Int, position2: Int) {
        if(comprobarpar(cards[position1].identifier) == cards[position2].identifier){
            cards[position1].isMatched = true
            cards[position2].isMatched = true
            contador++
        }
        if (contador == 5){
            MediaPlayer.create(requireContext(), R.raw.ondo).start()
            Sharedapp.puntojuego.Juego = "4"
            if (Sharedapp.tipousu.tipo != "profesor"){
                bd = Base_de_Datos(requireContext(), "bd", null, 1)
                bd.actualizar(Sharedapp.users.user.toString(), "2")
            }
            replaceFragment(R.id.FragmentMapaJuego, LetraFragment())
            replaceFragment(R.id.FragmentExplicacionJuego, ExplicacionFragment())
        }
    }
    private fun comprobarpar(idnt: Int): Int {
        var identificador:Int
        if(cards[0].identifier%2 == 0){
            if (idnt%2 == 0){
                identificador = idnt + 1
            }else{
                identificador = idnt - 1
            }
        }else{
            if (idnt%2 == 0){
                identificador = idnt - 1
            }else{
                identificador = idnt + 1
            }
        }
        return identificador
    }
 fun replaceFragment(Contenedor: Int, fragment: Fragment) {
    val transaction = activity?.supportFragmentManager?.beginTransaction()
    if (transaction != null) {
        transaction.replace(Contenedor, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}
    fun slideView(i:Int) {

        var fragment1 = requireActivity().findViewById<FrameLayout>(R.id.FragmentMapaJuego)
        var activity = requireActivity().findViewById<LinearLayout>(R.id.Linearjuego)
        var size:Int = 0

        if (i==1){
            size = activity.height
        }else{
            size = 0
        }

        var slideAnimator = ValueAnimator
            .ofInt(fragment1.height, size)
            .setDuration(500)

        slideAnimator.addUpdateListener {
            var value = it.animatedValue
            fragment1.layoutParams.height = value as Int
            fragment1.requestLayout()
        }

        var animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(slideAnimator)
        animationSet.start()
    }
    override fun onDestroy() {
        slideView(0)
        super.onDestroy()
    }

    override fun onPause() {
        slideView(0)
        super.onPause()
    }
}


