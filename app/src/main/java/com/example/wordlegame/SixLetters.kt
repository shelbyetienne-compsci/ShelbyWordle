package com.example.wordlegame

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class SixLetters : Fragment() {
    private val wordSize = 6
    private val tries = 6

    private lateinit var game: Wordlet
    private val buttons = Array(tries) { arrayOfNulls<Button>(wordSize) }
    private lateinit var guess: StringBuilder
    private lateinit var popupWindow: PopupWindow
    private var numGuessed = 0
    private lateinit var rootView: View
    private lateinit var tableLayout: TableLayout
    private lateinit var tableRowParams: TableRow.LayoutParams
    private var buttonSize = 0
    private lateinit var font: Typeface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        rootView = inflater.inflate(R.layout.six_letters, container, false)

        tableLayout = rootView.findViewById(R.id.table_layout_6)
        tableRowParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        game = Wordlet(wordSize, requireContext())
        game.setRandomWord()
        guess = StringBuilder()

        rootView.findViewById<TextView>(R.id.title).text = "Six Letter Wordle"

        Toast.makeText(
            requireActivity().applicationContext,
            "${game.randomWord}",
            Toast.LENGTH_LONG
        ).show()

        val screenWidth = resources.displayMetrics.widthPixels

        val totalPadding = 32 * wordSize
        buttonSize = (screenWidth - totalPadding) / wordSize

        buttonSize = kotlin.math.min(buttonSize, 150) // optional cap for tablets

        font = Typeface.createFromAsset(requireActivity().assets, "font/public_pixel.ttf")

        rootView.findViewById<TextView>(R.id.title).typeface = font

        for (i in 0 until tries) {
            val tableRow = TableRow(requireContext())
            tableRow.layoutParams = tableRowParams

            for (j in 0 until wordSize) {
                buttons[i][j] = Button(requireContext())
                buttons[i][j]!!.tag = intArrayOf(i, j)
                val params = TableRow.LayoutParams(buttonSize, buttonSize)
                params.setMargins(8, 8, 8, 8)
                buttons[i][j]!!.layoutParams = params
                buttons[i][j]!!.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    buttonSize * 0.4f
                )
                buttons[i][j]!!.typeface = font
                buttons[i][j]!!.isAllCaps = false
                buttons[i][j]!!.setTextColor(resources.getColor(R.color.neon_blue))
                buttons[i][j]!!.setBackgroundResource(R.drawable.button_borders_grey)
                buttons[i][j]!!.setOnClickListener(null)
                tableRow.addView(buttons[i][j])
            }
            tableLayout.addView(tableRow)
        }

        blinkAnim()
        initKeys(rootView)

        return rootView
    }

    fun blinkAnim() {
        if (guess.isNotEmpty()) {
            buttons[numGuessed][guess.length - 1]!!.clearAnimation()
        }
        if (guess.length + 1 < wordSize) {
            buttons[numGuessed][guess.length + 1]!!.clearAnimation()
        }

        val animation =
            AnimationUtils.loadAnimation(requireContext().applicationContext, R.anim.blink)

        if (guess.length < wordSize) {
            buttons[numGuessed][guess.length]!!.text = "_"
            buttons[numGuessed][guess.length]!!.startAnimation(animation)
        }
    }

    fun type(view: View) {
        when (view.id) {
            R.id.a -> append("a")
            R.id.b -> append("b")
            R.id.c -> append("c")
            R.id.d -> append("d")
            R.id.e -> append("e")
            R.id.f -> append("f")
            R.id.g -> append("g")
            R.id.h -> append("h")
            R.id.i -> append("i")
            R.id.j -> append("j")
            R.id.k -> append("k")
            R.id.l -> append("l")
            R.id.m -> append("m")
            R.id.n -> append("n")
            R.id.o -> append("o")
            R.id.p -> append("p")
            R.id.q -> append("q")
            R.id.r -> append("r")
            R.id.s -> append("s")
            R.id.t -> append("t")
            R.id.u -> append("u")
            R.id.v -> append("v")
            R.id.w -> append("w")
            R.id.x -> append("x")
            R.id.y -> append("y")
            R.id.z -> append("z")
        }
        blinkAnim()
    }

    fun initKeys(view: View) {
        rootView.findViewById<ImageButton>(R.id.a).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.b).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.c).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.d).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.e).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.f).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.g).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.h).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.i).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.j).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.k).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.l).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.m).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.n).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.o).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.p).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.q).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.r).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.s).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.t).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.u).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.v).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.w).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.x).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.y).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.z).setOnClickListener(this::type)
        rootView.findViewById<ImageButton>(R.id.enter).setOnClickListener(this::enter)
        rootView.findViewById<ImageButton>(R.id.delete).setOnClickListener(this::delete)
    }

    fun append(string: String) {
        if (guess.length < wordSize) {
            guess.append(string)
            val word = guess.toString()
            for (i in word.indices) {
                buttons[numGuessed][i]!!.text = word[i].toString()
            }
        }
    }

    fun delete(view: View) {
        if (guess.isNotEmpty()) {
            guess.deleteCharAt(guess.length - 1)
            val word = guess.toString()
            for (i in 0 until wordSize) {
                buttons[numGuessed][i]!!.text = ""
            }
            for (i in word.indices) {
                buttons[numGuessed][i]!!.text = word[i].toString()
            }
        }
        blinkAnim()
    }

    fun playAgain(view: View) {
        game.setRandomWord()
        numGuessed = 0
        guess.delete(0, wordSize)
        for (i in 0 until tries) {
            for (j in 0 until wordSize) {
                buttons[i][j]!!.text = ""
                buttons[i][j]!!.setTextColor(resources.getColor(R.color.neon_blue))
            }
        }
        popupWindow.dismiss()
        blinkAnim()
    }

    fun enter(view: View) {
        if (guess.length == wordSize && game.validWord(guess.toString(), wordSize)) {
            if (game.isWord(guess.toString())) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Congrats you guessed the word!!",
                    Toast.LENGTH_LONG
                ).show()
                endGame(view)
            }

            if (game.containsLetter(guess.toString(), game.randomWord)) {
                val word = guess.toString()
                val lettersContained = game.getLetters(guess.toString())
                for (i in lettersContained.indices) {
                    for (j in 0 until wordSize) {
                        if (lettersContained[i] == word[j]) {
                            buttons[numGuessed][j]!!.setTextColor(resources.getColor(R.color.yellow))
                        }
                    }
                }

                for (j in 0 until wordSize) {
                    if (game.randomWord[j] == word[j]) {
                        buttons[numGuessed][j]!!.setTextColor(resources.getColor(R.color.green))
                    }
                }
            }

            if (numGuessed == 5) {
                Toast.makeText(
                    requireContext().applicationContext,
                    "You are a loser!! The word was ${game.randomWord}",
                    Toast.LENGTH_LONG
                ).show()
                endGame(view)
            }

            if (numGuessed < 5 && guess.isNotEmpty()) {
                numGuessed++
                guess.delete(0, wordSize)
                blinkAnim()
            }
        } else {
            Toast.makeText(
                requireContext().applicationContext,
                "Not a valid word in list!!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun endGame(view: View) {
        val inflater = requireContext().getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.win_or_loss, null)

        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true

        popupWindow = PopupWindow(popupView, width, height, focusable)

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance() = SixLetters().apply {
            arguments = Bundle().apply {
            }
        }
    }
}